package com.bootdo.kpi.service.impl;

import com.bootdo.common.domain.Tree;
import com.bootdo.common.utils.BuildTree;
import com.bootdo.system.dao.DeptDao;
import com.bootdo.system.domain.DeptDO;
import com.bootdo.system.domain.UserDO;
import org.apache.commons.lang.ArrayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.bootdo.kpi.dao.PerformanceAppraisalUserDao;
import com.bootdo.kpi.domain.PerformanceAppraisalUserDO;
import com.bootdo.kpi.service.PerformanceAppraisalUserService;



@Service
public class PerformanceAppraisalUserServiceImpl implements PerformanceAppraisalUserService {
	@Autowired
	private PerformanceAppraisalUserDao performanceAppraisalUserDao;
    @Autowired
    DeptDao deptMapper;
	
	@Override
	public PerformanceAppraisalUserDO get(Integer id){
		return performanceAppraisalUserDao.get(id);
	}
	
	@Override
	public List<PerformanceAppraisalUserDO> list(Map<String, Object> map){
		return performanceAppraisalUserDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return performanceAppraisalUserDao.count(map);
	}
	
	@Override
	public int save(PerformanceAppraisalUserDO performanceAppraisalUser){
		return performanceAppraisalUserDao.save(performanceAppraisalUser);
	}
	
	@Override
	public int update(PerformanceAppraisalUserDO performanceAppraisalUser){
		return performanceAppraisalUserDao.update(performanceAppraisalUser);
	}
	
	@Override
	public int remove(Integer id){
		return performanceAppraisalUserDao.remove(id);
	}
	
	@Override
	public int batchRemove(Integer[] ids){
		return performanceAppraisalUserDao.batchRemove(ids);
	}

    @Override
    public List<PerformanceAppraisalUserDO> departmentlist(Map<String, Object> map) {
        return performanceAppraisalUserDao.departmentlist(map);
    }

    @Override
    public Tree<PerformanceAppraisalUserDO> getTree() {
        List<Tree<PerformanceAppraisalUserDO>> trees = new ArrayList<Tree<PerformanceAppraisalUserDO>>();
        List<PerformanceAppraisalUserDO> departmentList = performanceAppraisalUserDao.departmentlist(new HashMap<>());
        for (PerformanceAppraisalUserDO dept : departmentList) {
            Tree<PerformanceAppraisalUserDO> tree = new Tree<PerformanceAppraisalUserDO>();
            tree.setId(dept.getUserDeparment());
            tree.setParentId("");
            tree.setText(dept.getUserDeparment());
            Map<String, Object> state = new HashMap<>(16);
            state.put("opened", true);
            state.put("mType", "dept");
            tree.setState(state);
            trees.add(tree);
        }
        // 默认顶级菜单为０，根据数据库实际情况调整
        Tree<PerformanceAppraisalUserDO> t = BuildTree.build(trees);
        return t;
    }

}
