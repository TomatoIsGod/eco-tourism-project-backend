package com.zillionwon.web;

import com.zillionwon.web.domain.SightOverview;
import com.zillionwon.web.mapper.SightOverviewMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class MySQLUnitTest {
    @Autowired
    private SightOverviewMapper sightOverviewMapper;

    @Test
    public void setSightOverviewMapperTest() {
        System.out.println(("----- selectAll method test ------"));
        List<SightOverview> list = sightOverviewMapper.selectList(null);
        System.out.println(list);
        list.forEach(System.out::println);
    }
}
