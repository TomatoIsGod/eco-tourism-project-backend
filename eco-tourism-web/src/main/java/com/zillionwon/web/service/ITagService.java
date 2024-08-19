package com.zillionwon.web.service;

import com.zillionwon.web.domain.PageQuery;
import com.zillionwon.web.domain.vo.CityVO;
import com.zillionwon.web.domain.TableDataInfo;
import com.zillionwon.web.domain.vo.TagVO;

import java.util.List;

/**
 * 城市 Service 接口
 *
 * @author InwardFlow
 */
public interface ITagService {

    /**
     * 查询城市
     */
    TagVO queryById(Long tagId);

    /**
     * 查询城市列表
     */
    TableDataInfo<TagVO> queryPageList(TagVO bo, PageQuery pageQuery);

    /**
     * 查询城市列表
     */
    List<CityVO> queryList(TagVO bo);
}
