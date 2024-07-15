package com.zillionwon.web.service.impl;

import com.zillionwon.web.domain.PageQuery;
import com.zillionwon.web.domain.TableDataInfo;
import com.zillionwon.web.domain.vo.CityVO;
import com.zillionwon.web.domain.vo.TagVO;
import com.zillionwon.web.mapper.TagMapper;
import com.zillionwon.web.service.ITagService;
import com.zillionwon.common.core.util.MapstructUtils;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 标签业务层处理
 *
 * @author InwardFlow
 */
@Service
public class TagServiceImpl implements ITagService {
    private final TagMapper mapper;

    public TagServiceImpl(TagMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public TagVO queryById(Long tagId) {
        return MapstructUtils.convert(mapper.selectById(tagId), TagVO.class);
    }

    @Override
    public TableDataInfo<TagVO> queryPageList(TagVO bo, PageQuery pageQuery) {
        // TODO: 未完成翻页查找
        return null;
    }

    @Override
    public List<CityVO> queryList(TagVO bo) {
        return null;
    }
}
