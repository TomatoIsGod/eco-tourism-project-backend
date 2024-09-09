package com.zillionwon.web;

import com.zillionwon.web.constant.FavoriteType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * 常量单元测试
 *
 * @author InwardFlow
 */
@DisplayName("常量单元测试")
public class EnumUnitTest {
    @Test
    public void enumTest() {
        // FavoriteType{code=1, desc='收藏城市'}
        System.out.println(FavoriteType.CITY);

        // FavoriteType{code=1, desc='收藏城市'}
        System.out.println(FavoriteType.getByCode(1));

        // 抛出 RuntimeException 提示不存在
        assertThrows(RuntimeException.class, () -> FavoriteType.getByCode(233));
    }
}
