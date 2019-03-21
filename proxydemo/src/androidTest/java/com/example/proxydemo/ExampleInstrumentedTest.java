package com.example.proxydemo;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.lang.reflect.Proxy;

import static org.junit.Assert.*;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {
    @Test
    public void useAppContext() {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();

        assertEquals("com.example.proxydemo", appContext.getPackageName());
    }

    @Test
    public void test() {
        Subject sub = new RealSUbject();
        ProxySubject proxySubject = new ProxySubject(sub);
        Subject subject = (Subject) Proxy.newProxyInstance(sub.getClass().getClassLoader(), sub.getClass().getInterfaces(), proxySubject);
        String opraetion = subject.opraetion();
        System.out.print(opraetion);
    }

}
