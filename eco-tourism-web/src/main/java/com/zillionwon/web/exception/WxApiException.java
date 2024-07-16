package com.zillionwon.web.exception;

/**
 * 微信小程序 api 错误类
 *
 * @author InwardFlow
 */
public class WxApiException extends BaseException {
    public WxApiException(String code, String message) {
        super("web.wxapi.error", code, message);
    }
}
