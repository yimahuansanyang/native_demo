package com.example.observerdemo.file;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.observerdemo.R;
import com.example.observerdemo.mode.builder_mode.Builder;
import com.example.observerdemo.mode.builder_mode.ConcreatBuilder;
import com.example.observerdemo.mode.builder_mode.Dirctor;
import com.example.observerdemo.mode.builder_mode.Product;
import com.example.observerdemo.mode.factory_mode.AbstractFactory;
import com.example.observerdemo.mode.factory_mode.AudiFactory;
import com.example.observerdemo.mode.factory_mode.BenzFactory;
import com.example.observerdemo.mode.factory_mode.CarEngineer;
import com.example.observerdemo.mode.flyweight_mode.FlyWeightFactory;
import com.example.observerdemo.mode.flyweight_mode.Flyweight;
import com.example.observerdemo.mode.proxy_mode.Star;
import com.example.observerdemo.mode.proxy_mode.SuperStar;
import com.example.observerdemo.mode.proxy_mode.SuperStarProxy;
import com.example.observerdemo.observer.EventObserver;
import com.example.observerdemo.observer.EventSubject;
import com.example.observerdemo.observer.Notify;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class MainActivity extends AppCompatActivity {

    private EventObserver eventObserver;
    private TextView tv;
    private Button btn;
    private Button btn_show;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv = (TextView) findViewById(R.id.tv);
        btn = (Button) findViewById(R.id.btn);
        btn_show = (Button) findViewById(R.id.btn_show);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //刷新文字
                eventObserver = new EventObserver() {
                    @Override
                    public void onChange(String eventType) {
                        Log.d("TAG", "eventType:" + eventType);
                        if (eventType.equals("com.updateMain")) {
                            //刷新主界面
                            btn.setText("点击");

                        } else {
                            //刷新文字
                            tv.setText("我是警察");

                        }

                    }
                };
                EventSubject.getInstance().registerObserver(eventObserver);
                Notify.getInstance().notifyInfo("com.updateText");
                Notify.getInstance().notifyInfo("com.updateMain");
            }
        });
        //抽象工厂模式

        CarEngineer carEngineer = new CarEngineer();
        AbstractFactory audiFactory = new AudiFactory();
        carEngineer.makeCar(audiFactory);
        AbstractFactory benzFactory = new BenzFactory();
        carEngineer.makeCar(benzFactory);

        //建造者模式的测试
        Builder concreatBuilder = new ConcreatBuilder();
        Dirctor dirctor = new Dirctor(concreatBuilder);
        dirctor.construt();
        Product product = concreatBuilder.retriviceResult();
        System.out.println("product:" + product);
        System.out.println("part1:" + product.getPart1());
        System.out.println("part2:" + product.getPart2());
        //享元模式
        FlyWeightFactory flyWeightFactory = new FlyWeightFactory();
        Flyweight a = flyWeightFactory.factory(new Character('a'));
        a.opreation("first");
        FlyWeightFactory flyWeightFactory1 = new FlyWeightFactory();
        Flyweight b = flyWeightFactory1.factory('b');
        b.opreation("second");
        FlyWeightFactory flyWeightFactory2 = new FlyWeightFactory();
        Flyweight a1 = flyWeightFactory2.factory('a');
        a1.opreation("third");
        Star star = new SuperStarProxy(new SuperStar());
        star.singsong();
        final Star superStar = new SuperStar();
        Proxy.newProxyInstance(superStar.getClass().getClassLoader(), superStar.getClass().getInterfaces(), new InvocationHandler() {
            @Override
            public Object invoke(Object o, Method method, Object[] objects) throws Throwable {

                Object object = method.invoke(superStar, objects);

                return object;
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (eventObserver != null) {
            EventSubject.getInstance().removeObserver(eventObserver);
        }
    }

}
