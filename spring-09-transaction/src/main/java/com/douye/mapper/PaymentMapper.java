package com.douye.mapper;

import com.douye.pojo.Payment;

import java.util.List;

public interface PaymentMapper {
    public List<Payment> selectPayment();
    public void delete(int id);
    public void insert(Payment payment);
}
