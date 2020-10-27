import com.douye.mapper.PaymentMapper;
import com.douye.pojo.Payment;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

public class MyTest {
    @Test
    public void test() {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        PaymentMapper paymentMapper = context.getBean("paymentMapper", PaymentMapper.class);
        List<Payment> payments = paymentMapper.selectPayment();
        for (Payment payment : payments) {
            System.out.println(payment.toString());
        }
    }
}
