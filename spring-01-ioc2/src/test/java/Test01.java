import com.douye.pojo.User;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Test01 {
    public static void main(String[] args) {
        // 1. spring在获取上下文信息时，已经为我们实例化完成bean对象，默认是使用无参构造方法创建对象
        ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
        System.out.println("获取spring上下文...");
        User user = (User) context.getBean("user");
        user.show();
    }
}
