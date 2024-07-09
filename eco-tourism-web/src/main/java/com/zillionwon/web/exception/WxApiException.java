package com.zillionwon.web.exception;

/**
 * 微信 api 错误类
 * @author InwardFlow
 */
public class WxApiException extends BaseException {
    public WxApiException(String code, String message) {
        super("web.wxapi.error", code, message);
    }
}
