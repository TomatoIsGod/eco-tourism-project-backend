package com.zillionwon.web.service;

import com.zillionwon.web.domain.vo.TagVO;
import com.zillionwon.web.mapper.TagMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Tag 业务层处理
 *
 * @author Ciel3232
 */
@Service
public class TagService {

    @Autowired
    private TagMapper tagMapper;

    public List<TagVO> getTagsByCity(Long cityId) {
        return tagMapper.findTagsByCity(cityId);
    }

}
