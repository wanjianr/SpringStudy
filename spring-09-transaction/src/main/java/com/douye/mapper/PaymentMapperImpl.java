package com.douye.mapper;

import com.douye.pojo.Payment;
import org.mybatis.spring.SqlSessionTemplate;

import java.util.List;

public class PaymentMapperImpl implements PaymentMapper {

    SqlSessionTemplate sqlSessionTemplate;

    public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
        this.sqlSessionTemplate = sqlSessionTemplate;
    }

    public List<Payment> selectPayment() {
        return sqlSessionTemplate.getMapper(PaymentMapper.class).selectPayment();
    }

    public void delete(int id) {
        sqlSessionTemplate.getMapper(PaymentMapper.class).delete(id);
    }

    public void insert(Payment payment) {
        sqlSessionTemplate.getMapper(PaymentMapper.class).insert(payment);
    }
}
