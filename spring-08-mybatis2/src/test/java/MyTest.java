import com.douye.mapper.UserMapper;
import com.douye.mapper.UserMapperImpl;
import com.douye.pojo.Payment;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

public class MyTest {
    @Test
    public void test() {
        ApplicationContext context = new ClassPathXmlApplicationContext("springDao.xml");
        UserMapper userMapper = context.getBean("userMapper",UserMapper.class);
        List<Payment> data = userMapper.findData();
        for (Payment datum : data) {
            System.out.println(datum.toString());
        }
    }
}
