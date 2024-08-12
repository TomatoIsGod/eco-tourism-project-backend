package com.zillionwon.web.controller;

import io.swagger.annotations.Api;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    @Autowired
    @Resource(name = "jdbcTemplate")
    public JdbcTemplate jdbcTemplate;

    @GetMapping("/getTags")
    public List<String> getTags(){
        List<String> lstTag = new ArrayList<>();

        String sql = "select tag_name from tag";
        List query = jdbcTemplate.queryForList(sql);
        if (query == null || query.isEmpty()) {
            return lstTag;
        }

        for (Object value : query) {
            Map q = (Map)value;
            if (q != null) {
                String tag = q.get("tag_name") != null ? (String) q.get("tag_name") : "";
                lstTag.add(tag);
            }
        }

        return lstTag;
    }
}
