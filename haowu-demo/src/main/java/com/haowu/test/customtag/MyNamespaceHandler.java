package com.haowu.test.customtag;

import org.springframework.beans.factory.xml.NamespaceHandlerSupport;

/**
 * @ClassName MyNamespaceHandler
 * @Description 自定义命名空间处理器
 * @Author 20190023
 * @Date 2020/3/24 20:05
 * @Version 1.0
 **/
public class MyNamespaceHandler extends NamespaceHandlerSupport {
	@Override
	public void init() {
		// 只要碰到有user的标签, 就用UserBeanDefinitionParser来解析
		registerBeanDefinitionParser("user", new UserBeanDefinitionParser());
	}
}
