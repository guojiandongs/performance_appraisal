package com.bootdo.kpi.service.impl;

import com.bootdo.kpi.dao.PerformanceAppraisalOptionDao;
import com.bootdo.kpi.domain.DetailTotal;
import com.bootdo.kpi.domain.PerformanceAppraisalUserDO;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import com.bootdo.kpi.dao.PerformanceAppraisalDetailsDao;
import com.bootdo.kpi.domain.PerformanceAppraisalDetailsDO;
import com.bootdo.kpi.service.PerformanceAppraisalDetailsService;

import javax.servlet.http.HttpServletResponse;


@Service
public class PerformanceAppraisalDetailsServiceImpl implements PerformanceAppraisalDetailsService {
	@Autowired
	private PerformanceAppraisalDetailsDao performanceAppraisalDetailsDao;

	@Override
	public PerformanceAppraisalDetailsDO get(Integer id){
		return performanceAppraisalDetailsDao.get(id);
	}
	
	@Override
	public List<PerformanceAppraisalDetailsDO> list(Map<String, Object> map){
		return performanceAppraisalDetailsDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return performanceAppraisalDetailsDao.count(map);
	}
	
	@Override
	public int save(PerformanceAppraisalDetailsDO performanceAppraisalDetails){
		return performanceAppraisalDetailsDao.save(performanceAppraisalDetails);
	}
	
	@Override
	public int update(PerformanceAppraisalDetailsDO performanceAppraisalDetails){
		return performanceAppraisalDetailsDao.update(performanceAppraisalDetails);
	}
	
	@Override
	public int remove(Integer id){
		return performanceAppraisalDetailsDao.remove(id);
	}
	
	@Override
	public int batchRemove(Integer[] ids){
		return performanceAppraisalDetailsDao.batchRemove(ids);
	}

    @Override
    public List<DetailTotal> total(Map<String, Object> map) {
        return performanceAppraisalDetailsDao.total(map);
    }

	@Override
	public void exportExcel(HttpServletResponse response) throws IOException {
		//创建工作簿
		XSSFWorkbook xssfWorkbook = new XSSFWorkbook();
		//创建工作表
		XSSFSheet sheet = xssfWorkbook.createSheet();
		xssfWorkbook.setSheetName(0,"考核记录");
		//创建表头
		XSSFRow head = sheet.createRow(0);
		String[] heads = {"序号","被考核人","被考核人部门","考核分数"};
		for(int i = 0;i < heads.length;i++){
			XSSFCell cell = head.createCell(i);
			cell.setCellValue(heads[i]);
		}

		List<PerformanceAppraisalDetailsDO> list = performanceAppraisalDetailsDao.excelExport();
		for (int i = 0;i < list.size();i++) {
			XSSFRow row = sheet.createRow(i+1);
			row.createCell(0).setCellValue(list.get(i).getId());
			row.createCell(1).setCellValue(list.get(i).getUserName());
			row.createCell(2).setCellValue(list.get(i).getExamineeUserDept());
			row.createCell(3).setCellValue(list.get(i).getAppraisalScore());
		}

		//准备将Excel的输出流通过response输出到页面下载
		//八进制输出流
		response.setContentType("application/octet-stream");

		//这后面可以设置导出Excel的名称，此例中名为student.xls
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		String timeStr = sdf.format(new Date());
		response.setHeader("Content-disposition","attachment;filename="
				+ new String("考核记录".getBytes("UTF-8"), "ISO-8859-1")+timeStr+".xlsx");

		//刷新缓冲
		response.flushBuffer();

		//workbook将Excel写入到response的输出流中，供页面下载
		xssfWorkbook.write(response.getOutputStream());
	}

}
