import com.douye.pojo.User;
import com.douye.pojo.UserOne;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 构造函数参数注入的3种方法
 */
public class Test02 {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");

        // 1. 通过参数下标注入构造参数
        UserOne userOne1 = (UserOne) context.getBean("userOne1");
        userOne1.show();
        UserOne userOne2 = (UserOne) context.getBean("userOne2");
        userOne2.show();
        UserOne userOne3 = (UserOne) context.getBean("userOne3");
        userOne3.show();
        System.out.println("------------");

        // 2. 通过参数名注入构造参数
        UserOne userOne4 = (UserOne) context.getBean("userOne4");
        userOne4.show();

        // 3. 通过参数类型注入构造参数
        UserOne userOne5 = (UserOne) context.getBean("userOne5");
        userOne5.show();
    }
}
