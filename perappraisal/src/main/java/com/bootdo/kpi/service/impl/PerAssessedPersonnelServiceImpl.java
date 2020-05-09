package com.bootdo.kpi.service.impl;

import com.bootdo.common.domain.Tree;
import com.bootdo.common.utils.BuildTree;
import com.bootdo.kpi.dao.PerAssessedUserDao;
import com.bootdo.kpi.domain.PerAssessedUserDO;
import com.bootdo.kpi.service.PerAssessedUserService;
import com.bootdo.system.dao.DeptDao;
import com.bootdo.system.dao.UserDao;
import com.bootdo.system.domain.DeptDO;
import com.bootdo.system.domain.MenuDO;
import com.bootdo.system.domain.UserDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.bootdo.kpi.dao.PerAssessedPersonnelDao;
import com.bootdo.kpi.domain.PerAssessedPersonnelDO;
import com.bootdo.kpi.service.PerAssessedPersonnelService;



@Service
public class PerAssessedPersonnelServiceImpl implements PerAssessedPersonnelService {
	@Autowired
	private PerAssessedPersonnelDao perAssessedPersonnelDao;
    @Autowired
    private DeptDao deptDao;
    @Autowired
    private UserDao userDao;
    @Autowired
    private PerAssessedUserDao perAssessedUserDao;

    @Override
	public PerAssessedPersonnelDO get(Integer id){
		return perAssessedPersonnelDao.get(id);
	}
	
	@Override
	public List<PerAssessedPersonnelDO> list(Map<String, Object> map){
		return perAssessedPersonnelDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return perAssessedPersonnelDao.count(map);
	}
	
	@Override
	public int save(PerAssessedPersonnelDO perAssessedPersonnel){
		return perAssessedPersonnelDao.save(perAssessedPersonnel);
	}
	
	@Override
	public int update(PerAssessedPersonnelDO perAssessedPersonnel){
		return perAssessedPersonnelDao.update(perAssessedPersonnel);
	}
	
	@Override
	public int remove(Integer id){
		return perAssessedPersonnelDao.remove(id);
	}
	
	@Override
	public int batchRemove(Integer[] ids){
		return perAssessedPersonnelDao.batchRemove(ids);
	}

    @Override
    public Tree<DeptDO> getTree(String examineeUserId) {
            // 根据roleId查询权限
        Map<String, Object> examineeUserMap = new HashMap<>();
        examineeUserMap.put("assessedPersonnelId",examineeUserId);
        List<PerAssessedUserDO> perAssessedUserDOList = perAssessedUserDao.list(examineeUserMap);
        // 根据roleId查询权限
        /*List<MenuDO> menus = menuMapper.list(new HashMap<String, Object>(16));
        List<Long> menuIds = roleMenuMapper.listMenuIdByRoleId(id);
        List<Long> temp = menuIds;
        for (MenuDO menu : menus) {
            if (temp.contains(menu.getParentId())) {
                menuIds.remove(menu.getParentId());
            }
        }*/
        List<Tree<DeptDO>> trees = new ArrayList<Tree<DeptDO>>();
        List<DeptDO> menuDOs = deptDao.list(new HashMap<String, Object>(16));
        for (DeptDO deptDO : menuDOs) {
            Tree<DeptDO> tree = new Tree<DeptDO>();
            tree.setId(deptDO.getDeptId().toString());
            tree.setParentId(deptDO.getParentId().toString());
            tree.setText(deptDO.getName());
            Map<String, Object> state = new HashMap<>(16);
            Long deptId = deptDO.getDeptId();
            Map<String, Object> usermap = new HashMap<>();
            usermap.put("deptId",deptId);
            List<UserDO> userlist = userDao.list(usermap);
            if(userlist.size()>0){
                List<Tree<DeptDO>> childrenList = new ArrayList();
                for (int i = 0; i < userlist.size(); i++) {
                    if(examineeUserId.equals(userlist.get(i).getUserId().toString())){
                        continue;
                    }
                    Tree<DeptDO> merchanttree = new Tree<DeptDO>();
                    merchanttree.setId(userlist.get(i).getUserId().toString());
                    merchanttree.setParentId(userlist.get(i).getDeptId().toString());
                    merchanttree.setText(userlist.get(i).getName());
                    for (int j = 0; j < perAssessedUserDOList.size(); j++) {
                        PerAssessedUserDO perAssessedUserDO = perAssessedUserDOList.get(j);
                        String appraisalUserId = String.valueOf(perAssessedUserDO.getAppraisalUserId());
                        if (appraisalUserId.equals(userlist.get(i).getUserId().toString())) {
                            state.put("selected", true);
                            merchanttree.setChecked(true);
                        }
                        merchanttree.setState(state);
                    }
                    Map<String, Object> otherMap = new HashMap<>(16);
                    otherMap.put("flag",userlist.get(i).getUserId());
                    merchanttree.setAttributes(otherMap);
                    childrenList.add(merchanttree);
                    tree.setChildren(childrenList);
                }
            }
            Map<String, Object> otherMap = new HashMap<>(16);
            otherMap.put("flag","0");
            tree.setAttributes(otherMap);
            trees.add(tree);
        }
        // 默认顶级菜单为０，根据数据库实际情况调整
        Tree<DeptDO> t = BuildTree.build(trees);
        return t;
    }

}
