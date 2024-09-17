package com.zillionwon.common.langchain.exception;

import com.zillionwon.common.core.exception.BaseException;

/**
 * LangChain4j 异常
 *
 * @author InwardFlow
 */

public class AiException extends BaseException {
    public AiException(String code, String message) {
        super("ai.config.error", code, message);
    }

}
