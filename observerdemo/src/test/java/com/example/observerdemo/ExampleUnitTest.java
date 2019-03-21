package com.example.observerdemo;

import com.example.observerdemo.mode.builder_mode.Builder;
import com.example.observerdemo.mode.builder_mode.ConcreatBuilder;
import com.example.observerdemo.mode.builder_mode.Dirctor;
import com.example.observerdemo.mode.builder_mode.Product;
import com.example.observerdemo.mode.factory_mode.AbstractFactory;
import com.example.observerdemo.mode.factory_mode.AudiFactory;
import com.example.observerdemo.mode.factory_mode.BenzFactory;
import com.example.observerdemo.mode.factory_mode.CarEngineer;
import com.example.observerdemo.mode.flyweight_mode.FlyWeightConpositeFactory;
import com.example.observerdemo.mode.flyweight_mode.FlyWeightFactory;
import com.example.observerdemo.mode.flyweight_mode.Flyweight;
import com.example.observerdemo.mode.proxy_mode.AbstractObject;
import com.example.observerdemo.mode.proxy_mode.ProxyObject;
import com.example.observerdemo.mode.proxy_mode.Star;
import com.example.observerdemo.mode.proxy_mode.SuperStar;
import com.example.observerdemo.mode.proxy_mode.SuperStarProxy;

import org.junit.Test;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void test_1() {
        CarEngineer carEngineer = new CarEngineer();
        AbstractFactory audiFactory = new AudiFactory();
        carEngineer.makeCar(audiFactory);
        AbstractFactory benzFactory = new BenzFactory();
        carEngineer.makeCar(benzFactory);
    }

    @Test
    public void test_2() {
        Builder concreatBuilder = new ConcreatBuilder();
        Dirctor dirctor = new Dirctor(concreatBuilder);
        dirctor.construt();
        Product product = concreatBuilder.retriviceResult();
        System.out.print("product:" + product);
        System.out.print("part1:" + product.getPart1());
        System.out.print("part2:" + product.getPart2());
    }

    @Test
    public void test_3() {
        FlyWeightFactory flyWeightFactory = new FlyWeightFactory();
        Flyweight a = flyWeightFactory.factory(new Character('a'));
        a.opreation("first");
        FlyWeightFactory flyWeightFactory1 = new FlyWeightFactory();
        Flyweight b = flyWeightFactory1.factory('b');
        b.opreation("second");
        FlyWeightFactory flyWeightFactory2 = new FlyWeightFactory();
        Flyweight a1 = flyWeightFactory2.factory('a');
        a1.opreation("third");
    }

    @Test
    public void test_4() {
        List<Character> compositeState = new ArrayList<Character>();
        compositeState.add('a');
        compositeState.add('b');
        compositeState.add('c');
        compositeState.add('a');
        compositeState.add('b');

        FlyWeightConpositeFactory flyFactory = new FlyWeightConpositeFactory();
        Flyweight compositeFly1 = flyFactory.factory(compositeState);
        Flyweight compositeFly2 = flyFactory.factory(compositeState);
        compositeFly1.opreation("Composite Call");

        System.out.println("---------------------------------");
        System.out.println("复合享元模式是否可以共享对象：" + (compositeFly1 == compositeFly2));

        Character state = 'a';
        Flyweight fly1 = flyFactory.factory(state);
        Flyweight fly2 = flyFactory.factory(state);
        System.out.println("单纯享元模式是否可以共享对象：" + (fly1 == fly2));
    }

    @Test
    public void test_5() {
        AbstractObject abstractObject = new ProxyObject();
        abstractObject.opreation();
    }

    @Test
    public void test_6() {
        final Star superStar = new SuperStar();
        Star singer = (Star) Proxy.newProxyInstance(superStar.getClass().getClassLoader(), superStar.getClass().getInterfaces(), new InvocationHandler() {
            @Override
            public Object invoke(Object o, Method method, Object[] objects) throws Throwable {
                Object invoke = method.invoke(superStar, objects);
                return invoke;
            }
        });
        singer.singsong();
    }

    @Test
    public void test_7() {
        Star star = new SuperStarProxy(new SuperStar());
        star.singsong();
    }
}