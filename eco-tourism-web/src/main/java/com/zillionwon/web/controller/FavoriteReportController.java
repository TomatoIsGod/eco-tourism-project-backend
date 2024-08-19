package com.zillionwon.web.controller;

import com.zillionwon.common.satoken.util.LoginHelper;
import com.zillionwon.web.service.FavoriteReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 路线报告用户收藏接口 (开发中)
 *
 * @author InwardFlow
 */

@RestController
@RequestMapping("/user/favorite/report")
public class FavoriteReportController {

    @Autowired
    private FavoriteReportService favoriteReportService;

    /**
     * 添加记录
     *
     * @param reportName    名称
     * @param reportContent 内容
     */

    @PostMapping
    public void add(@RequestParam String reportName, @RequestParam String reportContent) {
        favoriteReportService.saveFavoriteReport(LoginHelper.getUserId(), reportName, reportContent);
    }
}

