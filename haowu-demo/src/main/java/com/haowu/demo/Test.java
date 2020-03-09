package com.haowu.demo;

import com.haowu.dao.IndexDao;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Copyright (C), 2015-2020, 上海好屋网信息技术有限公司
 * FileName: Test
 * Author:   ningying
 * Date:     2019/7/27
 * Description: TODO
 * Version 1.0
 */
public class Test {

	public static void main(String[] args) {

		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
		context.refresh();
		IndexDao indexDao = (IndexDao) context.getBean("indexDao");
		indexDao.query();
	}
}
