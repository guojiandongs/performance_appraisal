package com.bootdo.kpi.service;

import com.bootdo.kpi.domain.DetailTotal;
import com.bootdo.kpi.domain.PerformanceAppraisalDetailsDO;
import com.bootdo.kpi.domain.PerformanceAppraisalUserDO;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author gjd
 * @email guojiandong@gaoxinzb.com
 * @date 2020-05-03 19:38:15
 */
public interface PerformanceAppraisalDetailsService {
	
	PerformanceAppraisalDetailsDO get(Integer id);
	
	List<PerformanceAppraisalDetailsDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(PerformanceAppraisalDetailsDO performanceAppraisalDetails);
	
	int update(PerformanceAppraisalDetailsDO performanceAppraisalDetails);
	
	int remove(Integer id);
	
	int batchRemove(Integer[] ids);

    List<DetailTotal>  total(Map<String, Object> map);

    void exportExcel(HttpServletResponse response) throws IOException;
}
