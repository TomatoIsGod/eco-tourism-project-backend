package com.zillionwon.web.controller;

import cn.hutool.core.util.StrUtil;
import com.zillionwon.common.core.domain.R;
import com.zillionwon.web.domain.*;
import com.zillionwon.web.domain.vo.CityVO;
import com.zillionwon.web.service.CityService;
import com.zillionwon.web.service.CityTagService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 城市接口
 *
 * @author InwardFlow
 */

@Slf4j
@RestController
@RequestMapping("/city")
@CrossOrigin
public class CityController {

    @Resource(name = "jdbcTemplate")
    public JdbcTemplate jdbcTemplate;

    @Autowired
    private CityService cityService;

    @Autowired
    private CityTagService cityTagService;

    /**
     * 获取城市列表信息
     *
     * @param city 城市
     * @return 城市列表
     */
    @GetMapping
    public R<List<CityVO>> queryList(City city) {
        List<CityVO> cities = cityService.queryList(city);
        if (cities.isEmpty()) {
            log.info("城市 {} 不存在", city);
            return R.fail("城市不存在");
        } else {
            // TODO: 性能略低, 直接用 SQL 可能效率更高
            return R.ok(cities.stream()
                    .peek(cityVO -> cityVO.setTags(cityTagService.getTagsByCityId(cityVO.getCityId())))
                    .collect(Collectors.toList()));
        }
    }

    /**
     * 查询城市信息 (分页)
     *
     * @param cityBo    城市业务层对象
     * @param pageQuery 分页
     * @return 城市列表 (分页对象)
     */
    @GetMapping("/list")
    public TableDataInfo<CityVO> list(City cityBo, PageQuery pageQuery) {
        return cityService.queryPageList(cityBo, pageQuery);
    }

    /**
     * 获取一个随机城市
     *
     * @return 城市信息
     */
    @GetMapping("/random")
    public R<CityVO> getRandomCity() {
        CityVO city = cityService.getRandomCity();
        if (city == null) {
            return R.fail("获取随机城市失败");
        } else {
            city.setTags(cityTagService.getTagsByCityId(city.getCityId()));
            return R.ok(city);
        }
    }

    /**
     * 获取 City 与 Tag 之间的映射关系
     *
     * @param tagId 标签ID
     * @return 城市列表
     */
    @GetMapping("/queryCityTag")
    public R<List<CityTag>> getCitiesByTag(@RequestParam(required = false) Long tagId,
                                           @RequestParam(required = false) Long cityId) {
        return R.ok(cityTagService.getCityTags(new CityTag(tagId, cityId)));
    }

    /**
     * 获取城市分类
     *
     * @return 标签列表
     */
    @GetMapping("/getCityCategories")
    public List<Tag> getCityCategories() {
        List<Tag> lstCityStyle = new ArrayList<>();

        String sql = "select tag_id,tag_name from tag where isopen=1";
        List query = jdbcTemplate.queryForList(sql);
        if (query == null || query.size() == 0) {
            return lstCityStyle;
        }

        for (Object value : query) {
            Map q = (Map) value;
            if (q != null) {

                String tag_name = q.get("tag_name") != null ? (String) q.get("tag_name") : "";

                Tag tag = new Tag();
                tag.setTagId((long) q.get("tag_id"));
                tag.setTagName(tag_name);
                lstCityStyle.add(tag);
            }
        }

        return lstCityStyle;
    }

    /**
     * 获取城市列表
     *
     * @param pageSize    每页显示记录数
     * @param pageIndex   当前页码
     * @param categoryIds 城市分类Ids（例如：1,3,4）
     * @param cityName    城市名称（模糊查询）
     * @return
     */
    @Deprecated
    @GetMapping("/getCities")
    public List<CityVO> getCities(@RequestParam(required = false, defaultValue = "10") int pageSize, @RequestParam(required = false, defaultValue = "1") int pageIndex, @RequestParam(required = false) String categoryIds, @RequestParam(required = false) String cityName) {
        List<CityVO> lstCities = new ArrayList<>();
        String sql =
                "select c.city_id, c.city_name " +
                ",(select cover_img_url from sight_overview sv where sv.city=c.city_name and sv.isCityFlag=1) as cover_img_url  " +
                ",(select GROUP_CONCAT(t.tag_name) from city_tag ct left join tag t on t.tag_id=ct.tag_id where t.isOpen=1 and ct.city_id=c.city_id) as tags " +
                "from city c " +
                "where c.isOpen=1 ";
        if (!StrUtil.isEmpty(categoryIds)) {
            sql += StrUtil.format(" and exists (select 1 from city_tag ct where ct.city_id=c.city_id and tag_id in ({})) ", categoryIds);
        }
        if (!StrUtil.isEmpty(cityName)) {
            sql += StrUtil.format(" and c.city_name like '%{}%'", cityName);
        }

        if (pageSize < 1) {
            pageSize = 10;
        }
        if (pageIndex < 1) {
            pageIndex = 1;
        }
        int offset = (pageIndex - 1) * pageSize;
        sql += StrUtil.format(" LIMIT {} OFFSET {}", pageSize, offset);

        List query = jdbcTemplate.queryForList(sql);
        if (query == null || query.size() == 0) {
            return lstCities;
        }
        for (Object value : query) {
            Map q = (Map) value;
            if (q != null) {

                long city_id = (long) q.get("city_id");
                ;
                String city_name = q.get("city_name") != null ? (String) q.get("city_name") : "";
                String tags = q.get("tags") != null ? (String) q.get("tags") : "";
                String cover_img_url = q.get("cover_img_url") != null ? (String) q.get("cover_img_url") : "";

                CityVO obj = new CityVO();
                obj.setCityId(city_id);
                obj.setCityName(city_name);
//                obj.cover_img_url = cover_img_url;
                if (!StrUtil.isEmpty(tags)) {
//                    obj.setTags(tags.split(","));
                }

                lstCities.add(obj);
            }
        }

        return lstCities;
    }
}
