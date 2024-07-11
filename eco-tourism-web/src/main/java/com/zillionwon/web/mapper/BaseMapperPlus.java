package com.zillionwon.web.mapper;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.reflect.GenericTypeUtils;
import com.zillionwon.web.util.MapstructUtils;

import java.io.Serializable;
import java.util.List;

/**
 * 自定义 Mapper 接口, 实现自定义扩展
 *
 * @param <T> table 泛型
 * @param <V> vo 泛型
 * @author InwardFlow
 */
public interface BaseMapperPlus<T, V> extends BaseMapper<T> {
    default Class<V> currentVoClass() {
        GenericTypeUtils.resolveTypeArguments(this.getClass(), BaseMapperPlus.class);
        return (Class<V>) GenericTypeUtils.resolveTypeArguments(this.getClass(), BaseMapperPlus.class)[1];
    }
    default V selectVoById(Serializable id) {
        return selectVoById(id, this.currentVoClass());
    }
    /**
     * 根据 ID 查询
     */
    default <C> C selectVoById(Serializable id, Class<C> voClass) {
        T obj = this.selectById(id);
        if (ObjectUtil.isNull(obj)) {
            return null;
        }
        return MapstructUtils.convert(obj, voClass);
    }

    /**
     * 根据 entity 条件，查询全部记录
     */
    default <C> List<C> selectVoList(Wrapper<T> wrapper, Class<C> voClass) {
        List<T> list = this.selectList(wrapper);
        if (CollUtil.isEmpty(list)) {
            return CollUtil.newArrayList();
        }
        return MapstructUtils.convert(list, voClass);
    }
}
