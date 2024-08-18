package com.zillionwon.web;

import com.zillionwon.common.langchain.factory.AiModelFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@DisplayName("AI 单元测试")
public class AiUnitTest {

    @DisplayName("AI 配置项测试")
    @Test
    void configTest() {
        System.out.println(AiModelFactory.getInstance("zhipu"));
    }
}
