/**
 * Project Name:holidayService
 * File Name:HolidayClient.java
 * Package Name:com.ivy.client
 * Date:2017年9月26日下午1:48:10
 * Copyright (c) 2017, ivy_tangjintao@163.com All Rights Reserved.
 *
 */
package com.ivy.client;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ivy.bean.HolidayResponse;
import com.ivy.service.HolidayClientService;

/**
 * ClassName: HolidayClient <br/>
 * Function: spring WebService 客户端测试. <br/>
 * date: 2017年9月26日 下午1:48:10 <br/>
 *
 * @author ivy_tang
 * @version 
 * @since JDK 1.8
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring-client.xml")
public class HolidayClient
{
	@Autowired
	private HolidayClientService holidayClientService;
	@Test
	public void testIsAliveWithStream()
	{
		holidayClientService.isAliveWithStream();
	}
	@Test
	public void testIsAliveWithPojo()
	{
		this.holidayClientService.isAliveWithPojo();
	}
	
	@Test
	public void testholidayMethod()
	{
		HolidayResponse response = this.holidayClientService.holidayMethod();
		System.out.println(response);
	}
	
}
