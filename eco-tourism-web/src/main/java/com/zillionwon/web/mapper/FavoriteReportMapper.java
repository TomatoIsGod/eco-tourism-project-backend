package com.zillionwon.web.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zillionwon.web.domain.FavoriteReport;
import org.apache.ibatis.annotations.Mapper;

/**
 * 旅行路书界面收藏夹功能 Mapper接口类
 */
@Mapper
public interface FavoriteReportMapper extends BaseMapper<FavoriteReport> {
}
