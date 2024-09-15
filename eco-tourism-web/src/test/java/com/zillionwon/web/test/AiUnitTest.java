package com.zillionwon.web.test;

import com.zillionwon.common.langchain.factory.AiFactory;
import com.zillionwon.web.EcoTourismWebApplication;
import dev.langchain4j.model.chat.ChatLanguageModel;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@Slf4j
@SpringBootTest(classes = EcoTourismWebApplication.class)
@DisplayName("AI 单元测试")
public class AiUnitTest {

    @DisplayName("AI 配置项测试")
    @Test
    void propertiesTest() {
        ChatLanguageModel model = AiFactory.getInstance("zhipu");
        log.debug(model.generate("你好"));
    }
}
