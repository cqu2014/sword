package oliver.shein.sword.test;

import oliver.shein.sword.test.dto.A;
import oliver.shein.sword.test.dto.B;
import oliver.shein.sword.test.dto.C;
import oliver.shein.sword.vo.Foo;
import oliver.shein.sword.vo.Person;
import org.junit.Test;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @author Oliver Wang
 * @description
 * @created by IntelliJ IDEA 2018.3.3
 * @date Create at 2019/6/24
 * @since
 */
public class InstanceofTest {

    @Test
    public void instanceObject(){
        /**
         * null用instanceof跟任何类型比较时都是false
         */
        Foo foo = null;
        Person person = null;
        System.out.println("foo instanceof Foo:"+(foo instanceof Foo));
        System.out.println("person instanceof Person:"+(person instanceof Person));

        A a = null;
        B b = null;

        a = new B();
        b = new B();
        //a是接口A的实例对象引用指向子类类B，类B实现了接口A，所以属于同一个继承树分支
        System.out.println("a instanceof A: "+(a instanceof A));
        // a是接口A的实例对象引用指向子类类B，类B实现了接口A，所以属于同一个继承树分支
        System.out.println("a instanceof B: " + (a instanceof B));
        System.out.println("b instanceof A: " + (b instanceof A));
        System.out.println("b instanceof B: " + (b instanceof B));

        B b2 = new C();
        System.out.println(b2 instanceof A);
        System.out.println(b2 instanceof B);
        System.out.println(b2 instanceof C);

        System.out.println(b instanceof C);
    }

    @Test
    public void instanceOfDeepObject(){
        String[] strings = null;
        System.out.println(strings instanceof String[]);
        strings = new String[1];
        System.out.println(strings instanceof String[]);
        System.out.println(strings.getClass().isArray());

        List list = new ArrayList();
        System.out.println(list instanceof List);
        System.out.println(list instanceof ArrayList);
        System.out.println(list instanceof LinkedList);
    }
}
