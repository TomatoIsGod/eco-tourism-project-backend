package com.zillionwon.web;

import com.zillionwon.common.core.util.ValidatorUtils;
import com.zillionwon.common.wxapi.model.GetPhoneNumber;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@DisplayName("微信小程序单元测试")
public class WxUnitTest {

    @Test
    void validTest() {
        GetPhoneNumber getPhoneNumber = new GetPhoneNumber();
        GetPhoneNumber.PhoneInfo phoneInfo = null;
        getPhoneNumber.setPhoneInfo(phoneInfo); // 注意这里没有设置phoneNumber

        String validationErrors = String.valueOf(ValidatorUtils.validate(getPhoneNumber));
        System.out.println(validationErrors);
    }
}
