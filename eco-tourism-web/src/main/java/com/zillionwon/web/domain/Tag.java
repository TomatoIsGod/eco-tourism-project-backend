package com.zillionwon.web.domain;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * 标签实体类
 *
 * @author InwardFlow
 */

@Data
@TableName("tag")
public class Tag {
    @TableId
    private Long tagId;
    private String tagName;
}
