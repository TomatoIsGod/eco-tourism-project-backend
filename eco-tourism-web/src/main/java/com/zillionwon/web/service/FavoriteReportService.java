package com.zillionwon.web.service;
import com.zillionwon.web.domain.FavoriteReport;
import com.zillionwon.web.mapper.FavoriteReportMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 旅行路书界面收藏夹功能业务层处理
 */
@Service
public class FavoriteReportService {

    @Autowired
    private FavoriteReportMapper favoriteReportMapper;

    public void saveFavoriteReport(Long userId, String reportName, String reportContent) {
        FavoriteReport favoriteReport = new FavoriteReport();
        favoriteReport.setUserId(userId);
        favoriteReport.setReportName(reportName);
        favoriteReport.setReportContent(reportContent);
        favoriteReportMapper.insert(favoriteReport);
    }
}
