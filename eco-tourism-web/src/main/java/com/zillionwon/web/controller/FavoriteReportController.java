package com.zillionwon.web.controller;
import com.zillionwon.web.service.FavoriteReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class FavoriteReportController {

    @Autowired
    private FavoriteReportService favoriteReportService;

    @PostMapping("/favoriteReport")
    public void favoriteReport(@RequestParam Long userId, 
                               @RequestParam String reportName, 
                               @RequestParam String reportContent) {
        favoriteReportService.saveFavoriteReport(userId, reportName, reportContent);
    }
}

