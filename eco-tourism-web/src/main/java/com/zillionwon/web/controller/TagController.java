package com.zillionwon.web.controller;

import com.zillionwon.common.core.domain.R;
import com.zillionwon.web.domain.vo.CityVO;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * 分类接口
 *
 * @author InwardFlow
 */

@Slf4j
@RestController
@RequestMapping("/webapi/mp/category")
@CrossOrigin
@Api(tags = "城市分类接口服务")
public class TagController {
    @GetMapping("/all")
    public R<Map<String, List<CityVO>>> getAll() {
        return null;
    }

}
