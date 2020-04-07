package com.haowu.test.customtag;

import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.xml.AbstractSingleBeanDefinitionParser;
import org.springframework.util.StringUtils;
import org.w3c.dom.Element;

/**
 * @ClassName UserBeanDefinitionParser
 * @Description 自定义标签解析器
 * @Author 20190023
 * @Date 2020/3/24 19:47
 * @Version 1.0
 **/
public class UserBeanDefinitionParser extends AbstractSingleBeanDefinitionParser {
	// Element对应的类
	@Override
	protected Class getBeanClass(Element element){
		return User.class;
	}

	// 从element中解析并提取对应的元素
	@Override
	protected void doParse(Element element, BeanDefinitionBuilder builder){
		String userName = element.getAttribute("userName");
		String email = element.getAttribute("email");
		// 将提取的数据放入到BeanDefinitionBuilder中, 待完成所有的bean解析后统一注册到beanFactory中
		if(StringUtils.hasText(userName)){
			builder.addPropertyValue("userName", userName);
		}
		if(StringUtils.hasText(email)){
			builder.addPropertyValue("email", email);
		}
	}
}
