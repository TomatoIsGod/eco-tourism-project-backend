package com.zillionwon.common.core.domain.model;

import jakarta.validation.constraints.NotBlank;

/**
 * 微信小程序登录对象
 *
 * @author InwardFlow
 */

public class WxLoginBody extends LoginBody {

    /**
     * 小程序 code
     */
    @NotBlank(message = "{wx.code.not.blank}")
    private String wxCode;

    public String getWxCode() {
        return wxCode;
    }

    public void setWxCode(String wxCode) {
        this.wxCode = wxCode;
    }
}
