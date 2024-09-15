package com.zillionwon.common.core.domain;

/**
 * 附加属性接口
 *
 * @author InwardFlow
 */
public interface WithProperties<T> {
    void setProperties(T properties);
    T getProperties();
}
