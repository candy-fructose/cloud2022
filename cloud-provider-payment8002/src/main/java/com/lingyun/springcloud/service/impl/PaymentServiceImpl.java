package com.lingyun.springcloud.service.impl;

import com.lingyun.springcloud.dao.PaymentDao;
import com.lingyun.springcloud.entity.Payment;
import com.lingyun.springcloud.service.PaymentService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * (Payment)表服务实现类
 *
 * @author 
 * @since 2022-02-27 09:46:38
 */
@Service
public class PaymentServiceImpl implements PaymentService {
    @Resource
    private PaymentDao paymentDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public Payment queryById(Integer id) {
        return paymentDao.selectById(id);
    }


    /**
     * 新增数据
     *
     * @param payment 实例对象
     * @return 实例对象
     */
    @Override
    public int insert(Payment payment) {
        return paymentDao.insert(payment);
    }

    /**
     * 修改数据
     *
     * @param payment 实例对象
     * @return 实例对象
     */
    @Override
    public Payment update(Payment payment) {
        paymentDao.updateById(payment);
        return queryById(payment.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer id) {
        return paymentDao.deleteById(id) > 0;
    }
}
