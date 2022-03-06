package com.lingyun.springcloud.dao;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lingyun.springcloud.entity.Payment;
import org.apache.ibatis.annotations.Mapper;

/**
 * (Payment)表数据库访问层
 *
 * @author 
 * @since 2022-02-27 09:46:37
 */
@Mapper
public interface PaymentDao extends BaseMapper<Payment> {

}

