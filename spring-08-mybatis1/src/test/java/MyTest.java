import com.douye.mapper.UserMapper;
import com.douye.pojo.Payment;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class MyTest {
    @Test
    public void test() throws IOException {
        String resourse = "mybatisConfig.xml";
        InputStream in = Resources.getResourceAsStream(resourse);
        SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(in);
        SqlSession sqlSession = sessionFactory.openSession(true);

        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        List<Payment> payments = mapper.findData();
        for (Payment payment : payments) {
            System.out.println(payment.toString());
        }
    }
}
