package com.zillionwon.web.controller;

import com.zillionwon.web.domain.Tag;
import io.swagger.annotations.Api;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 分类接口
 *
 * @author InwardFlow
 */

@Slf4j
@RestController
@RequestMapping("/tag")
@CrossOrigin
@Api(tags = "城市分类接口服务")
public class TagController {

    @Resource(name = "jdbcTemplate")
    public JdbcTemplate jdbcTemplate;

    /**
     * 获取标签列表
     *
     * @param tagId     标签ID（可选）
     * @param tagName   标签名称（可选）
     * @return          符合条件的标签列表
     */
    @GetMapping
    public List<Tag> getCityTags(@RequestParam(required = false) Long tagId,
                                 @RequestParam(required = false) String tagName) {
        List<Tag> cityTags = new ArrayList<>();

        // 构建SQL查询语句
        String sql = "SELECT tag_id, tag_name FROM tag WHERE isopen = 1";
        List<Object> parameters = new ArrayList<>();
        if (tagId != null) {
            sql += " AND tag_id = ?";
            parameters.add(tagId);
        }
        if (tagName != null) {
            sql += " AND tag_name LIKE ?";
            parameters.add("%" + tagName + "%");
        }

        // 执行参数化查询
        List<Map<String, Object>> queryResults = jdbcTemplate.queryForList(sql, parameters.toArray());

        // 处理查询结果
        for (Map<String, Object> row : queryResults) {
            Tag tag = new Tag();
            tag.setTagId((Long) row.get("tag_id"));
            tag.setTagName((String) row.get("tag_name"));
            cityTags.add(tag);
        }

        return cityTags;
    }

}
