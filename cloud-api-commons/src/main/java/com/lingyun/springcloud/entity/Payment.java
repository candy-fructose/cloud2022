package com.lingyun.springcloud.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * (Payment)实体类
 *
 * @author 
 * @since 2022-02-27 09:46:37
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Payment  {

    @TableId(type=IdType.AUTO)
    //主键自增
    private Integer id;
    
    private String serial;

}

