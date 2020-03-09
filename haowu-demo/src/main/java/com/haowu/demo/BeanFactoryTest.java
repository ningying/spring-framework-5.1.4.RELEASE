package com.haowu.demo;

import com.haowu.model.MyTestBean;
import org.junit.Test;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.ClassPathResource;

import static org.junit.Assert.assertEquals;

/**
 * Copyright (C), 2015-2020, 上海好屋网信息技术有限公司
 * FileName: BeanFactoryTest
 * Author:   ningying
 * Date:     2020/3/4
 * Description: TODO
 * Version 1.0
 */
public class BeanFactoryTest {

	@Test
	public void testSimpleLoad(){
		BeanFactory bf = new XmlBeanFactory(new ClassPathResource("beanFactoryTest.xml"));
		MyTestBean bean = (MyTestBean) bf.getBean("myTestBean");
		assertEquals("testStr", bean.getTestStr());
	}
}
