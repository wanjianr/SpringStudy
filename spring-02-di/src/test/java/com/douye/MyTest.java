package com.douye;

import com.douye.pojo.Address;
import com.douye.pojo.Student;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MyTest {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
        Student student = (Student) context.getBean("student");
        System.out.println(student.toString());
        /*
        Student{name='douye',
                address=Address{address='China'},
                books=[深入java虚拟机, 高性能mysql, 算法4],
                hobbys=[健身, 编程, 音乐], card={动物=猫, 星球=火星},
                games=[QQ飞车, CS],
                girlfriend='null',
                info={phone=888-888, user=zhang san, age=22}}
        * */
    }

    @Test
    public void Pnamespace() {
        ApplicationContext context = new ClassPathXmlApplicationContext("namespaceBeans.xml");
        Address addr1 = context.getBean("addr1", Address.class);
        System.out.println(addr1.toString());

        Address addr2 = context.getBean("addr2", Address.class);
        System.out.println(addr2.toString());

        System.out.println(addr1 == addr2);
    }
}
