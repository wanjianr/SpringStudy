import com.douye.config.MyConfigure;
import com.douye.pojo.Tiger;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MyTest {
    @Test
    public void test1() {
        ApplicationContext context = new AnnotationConfigApplicationContext(MyConfigure.class);
        Tiger tiger = context.getBean("tiger", Tiger.class);
        System.out.println(tiger.getName());
    }
}
