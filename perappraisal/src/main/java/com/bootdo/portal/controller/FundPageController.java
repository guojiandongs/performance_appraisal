package com.bootdo.portal.controller;

import com.bootdo.blog.domain.ContentDO;
import com.bootdo.blog.service.ContentService;
import com.bootdo.common.utils.PageUtils;
import com.bootdo.common.utils.Query;
import com.bootdo.system.domain.FundDO;
import com.bootdo.system.service.FundService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by ZhangLei on 2019/12/3 0003
 */
@Controller
@RequestMapping("/fund")
public class FundPageController {
    private static final int LIMIT = 5;
    @Autowired
    private FundService fundService;
    @Autowired
    private ContentService contentService;
    @GetMapping
    public String fundlist(HttpServletRequest httpRequest,Model model){
        Map<String,Object> onemap = new HashMap<>();
        onemap.put("limit",LIMIT);
        onemap.put("offset",0);
        List<FundDO> fundlist = fundService.list(onemap);
        model.addAttribute("fundlist", fundlist);
        //科技
        getTechnology(model);
        //登录获取username
        HttpSession session = httpRequest.getSession(true);
        String username = (String)session.getAttribute("username");
        model.addAttribute("username",username);

        return "portal/HomePageWechat/fundList";
    }

    @ResponseBody
    @GetMapping("/pagelist")
    public PageUtils pageList(@RequestParam Map<String, Object> params){
        //查询列表数据
        params.put("limit",LIMIT);
        Query query = new Query(params);
        //查询页码起始数
        int page = query.getPage();
        int offset = page*query.getLimit();
        query.setOffset(offset);
        List<FundDO> fundlist = fundService.list(query);

        int total = fundService.count(query);
        PageUtils pageUtils = new PageUtils(fundlist, total,offset);
        return pageUtils;
    }
    /**
     * 获取普惠科技图片
     * */
    void getTechnology(Model model) {
        Map<String, Object> map = new HashMap<>();
        map.put("imagetype", "wx");
        map.put("levelType", "2");
        List<ContentDO> technology = contentService.list(map);
        model.addAttribute("technology", technology.get(0));
    }
}
