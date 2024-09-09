package com.zillionwon.web.constant;

/**
 * 用户收藏类型
 *
 * @author CH
 */
public enum FavoriteType {

    /**
     * 城市
     */
    CITY(1, "收藏城市"),

    /**
     * 景点
     */
    SIGHT(2, "收藏景点"),

    /**
     * 旅行计划
     */
    REPORT(3, "收藏旅游规划报告");

    /**
     * 枚举编码
     */
    private final Integer code;

    /**
     * 枚举描述
     */
    private final String desc;

    public static FavoriteType getByCode(Integer code) {
        for (FavoriteType type : FavoriteType.values()) {
            if (type.getCode().equals(code)) {
                return type;
            }
        }
        throw new RuntimeException("The enum with code [" + code + "] is not found in " + FavoriteType.class.getName());
    }

    public static FavoriteType getByName(String name) {
        for (FavoriteType type : FavoriteType.values()) {
            if (type.name().equals(name)) {
                return type;
            }
        }
        throw new EnumConstantNotPresentException(FavoriteType.class, name);
    }

    public Integer getCode() {
        return code;
    }

    FavoriteType(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    @Override
    public String toString() {
        return "FavoriteType{" +
                "code=" + code +
                ", desc='" + desc + '\'' +
                '}';
    }
}
