package com.haowu.demo;

import org.springframework.beans.factory.support.MethodReplacer;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.lang.reflect.Method;

/**
 * @ClassName TestMethodReplacer
 * @Description 测试spring中xml文件的<replaced-method name = "" replacer="/>用法
 * @Author 20190023
 * @Date 2020/3/9 11:47
 * @Version 1.0
 **/
public class TestMethodReplacer implements MethodReplacer {
	@Override
	public Object reimplement(Object obj, Method method, Object[] args) throws Throwable {
		System.out.println("我替换了原有的方法");
		return null;
	}

	public static void main(String[] args) {
		ApplicationContext bf = new ClassPathXmlApplicationContext("classpath:/com.haowu.demo/replaceMethodTest.xml");
		TestChangeMethod bean = (TestChangeMethod) bf.getBean("testChangeMethod");
		bean.changeMe();
	}

}
/**
 * @ClassName TestChangeMethod
 * @Description TODO
 * @Author 20190023
 * @Date 2020/3/9 13:46
 * @Version 1.0
 **/
class TestChangeMethod {
	public void changeMe(){
		System.out.println("changeMe");
	}
}


