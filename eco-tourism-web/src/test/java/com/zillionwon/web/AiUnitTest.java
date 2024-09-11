package com.zillionwon.web;

import com.zillionwon.common.langchain.factory.AiModelFactory;
import dev.langchain4j.model.chat.ChatLanguageModel;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@DisplayName("AI 单元测试")
public class AiUnitTest {

    @DisplayName("AI 配置项测试")
    @Test
    void configTest() {
        ChatLanguageModel model = AiModelFactory.getInstance("zhipu");
        System.out.println(model.generate("你好"));
    }
}
