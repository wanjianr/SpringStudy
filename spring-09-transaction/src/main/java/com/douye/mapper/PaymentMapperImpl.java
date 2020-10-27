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
        Payment payment = new Payment(6,"2020-10-27 10:40:21");
        PaymentMapper mapper = sqlSessionTemplate.getMapper(PaymentMapper.class);
        mapper.insert(payment);
        mapper.delete(10);
        return mapper.selectPayment();
    }

    public void delete(int id) {
        sqlSessionTemplate.getMapper(PaymentMapper.class).delete(id);
    }

    public void insert(Payment payment) {
        sqlSessionTemplate.getMapper(PaymentMapper.class).insert(payment);
    }
}
