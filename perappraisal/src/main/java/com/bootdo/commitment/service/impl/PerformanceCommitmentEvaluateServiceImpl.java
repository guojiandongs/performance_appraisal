package com.bootdo.commitment.service.impl;

import com.bootdo.kpi.domain.PerformanceAppraisalDetailsDO;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.bootdo.commitment.dao.PerformanceCommitmentEvaluateDao;
import com.bootdo.commitment.domain.PerformanceCommitmentEvaluateDO;
import com.bootdo.commitment.service.PerformanceCommitmentEvaluateService;

import javax.servlet.http.HttpServletResponse;


@Service
public class PerformanceCommitmentEvaluateServiceImpl implements PerformanceCommitmentEvaluateService {
	@Autowired
	private PerformanceCommitmentEvaluateDao performanceCommitmentEvaluateDao;
	
	@Override
	public PerformanceCommitmentEvaluateDO get(Integer id){
		return performanceCommitmentEvaluateDao.get(id);
	}
	
	@Override
	public List<PerformanceCommitmentEvaluateDO> list(Map<String, Object> map){
		return performanceCommitmentEvaluateDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return performanceCommitmentEvaluateDao.count(map);
	}
	
	@Override
	public int save(PerformanceCommitmentEvaluateDO performanceCommitmentEvaluate){
		return performanceCommitmentEvaluateDao.save(performanceCommitmentEvaluate);
	}
	
	@Override
	public int update(PerformanceCommitmentEvaluateDO performanceCommitmentEvaluate){
		return performanceCommitmentEvaluateDao.update(performanceCommitmentEvaluate);
	}
	
	@Override
	public int remove(Integer id){
		return performanceCommitmentEvaluateDao.remove(id);
	}
	
	@Override
	public int batchRemove(Integer[] ids){
		return performanceCommitmentEvaluateDao.batchRemove(ids);
	}

	@Override
	public void exportExcel(HttpServletResponse response) throws IOException {
		//创建工作簿
		XSSFWorkbook xssfWorkbook = new XSSFWorkbook();
		//创建工作表
		XSSFSheet sheet = xssfWorkbook.createSheet();
		xssfWorkbook.setSheetName(0,"承诺书考核记录");
		//创建表头
		XSSFRow head = sheet.createRow(0);
		String[] heads = {"评价内容","序号","人员名称","所在部门","总票数"};
		for(int i = 0;i < heads.length;i++){
			XSSFCell cell = head.createCell(i);
			cell.setCellValue(heads[i]);
		}

		List<PerformanceCommitmentEvaluateDO> list = performanceCommitmentEvaluateDao.excelExport();
		List<Integer> mergeLines = new ArrayList<Integer>();
		String lastContent = null;
		Integer lines = 0;
		for (int i = 0;i < list.size();i++) {
			XSSFRow row = sheet.createRow(i+1);
			row.createCell(0).setCellValue(list.get(i).getCommitmentContent());
			row.createCell(1).setCellValue(i+1);
			row.createCell(2).setCellValue(list.get(i).getCommitmentUserName());
			row.createCell(3).setCellValue(list.get(i).getCommitmentDepartmentName());
			row.createCell(4).setCellValue(list.get(i).getCommitmentCount());

			if(lastContent == null)lastContent = list.get(i).getCommitmentContent();
			if(lastContent.equals(list.get(i).getCommitmentContent())){
				lines++;
			}else{
				mergeLines.add(lines);
				lines = 1;
				lastContent = list.get(i).getCommitmentContent();
			}
		}
		mergeLines.add(lines);

		// 合并单元格
		// 起始行, 终止行, 起始列, 终止列
		Integer lastLine = 0;
		for(int i=0;i < mergeLines.size();i++){
			if(mergeLines.get(i) != 1){
				CellRangeAddress cra = new CellRangeAddress(
						lastLine+1, lastLine+mergeLines.get(i), 0, 0);
				sheet.addMergedRegion(cra);
			}
			lastLine += mergeLines.get(i);
		}

		//准备将Excel的输出流通过response输出到页面下载
		//八进制输出流
		response.setContentType("application/octet-stream");

		//这后面可以设置导出Excel的名称，此例中名为student.xls
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		String timeStr = sdf.format(new Date());
		response.setHeader("Content-disposition","attachment;filename="
				+ new String("承诺书考核记录".getBytes("UTF-8"), "ISO-8859-1")+timeStr+".xlsx");

		//刷新缓冲
		response.flushBuffer();

		//workbook将Excel写入到response的输出流中，供页面下载
		xssfWorkbook.write(response.getOutputStream());
	}

}
