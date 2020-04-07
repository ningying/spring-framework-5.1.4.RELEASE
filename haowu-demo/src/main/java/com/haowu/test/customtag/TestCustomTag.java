package com.haowu.test.customtag;

import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @ClassName TestCustomTag
 * @Description 测试解析自定义标签
 * @Author 20190023
 * @Date 2020/3/24 20:26
 * @Version 1.0
 **/
public class TestCustomTag {

	@Test
	public void test(){
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("com.haowu.demo/test.xml");
		User user = (User)context.getBean("testBean");
		System.out.println(user.getUserName() + ", " + user.getEmail());
	}
}
