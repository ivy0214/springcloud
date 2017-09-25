/**
 * Project Name:holidayService
 * File Name:HolidayService.java
 * Package Name:com.ivy.service
 * Date:2017年9月22日下午4:18:47
 * Copyright (c) 2017, ivy_tangjintao@163.com All Rights Reserved.
 *
 */
package com.ivy.service;

import org.jdom2.Element;
import org.jdom2.Namespace;
import org.jdom2.filter.Filters;
import org.jdom2.xpath.XPathExpression;
import org.jdom2.xpath.XPathFactory;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;

/**
 * ClassName: HolidayService <br/>
 * Function: webservice. <br/>
 * date: 2017年9月22日 下午4:18:47 <br/>
 *
 * @author ivy_tang
 * @version 
 * @since JDK 1.8
 */
@Endpoint
public class HolidayService
{
	private static final String NAMESPACE_URI = "http://com.ivy.project/hr";
	/**
	 * holidayMethod:接收http://com.ivy.project/hr命名空间下的数据请求. <br/>
	 *
	 * @author ivy_tang
	 * @param holidayRequest
	 * @since JDK 1.8
	 */
	@PayloadRoot(namespace=NAMESPACE_URI, localPart="HolidayRequest")
	public void holidayMethod(@RequestPayload Element holidayRequest)
	{
		Namespace namespace = Namespace.getNamespace("hr", NAMESPACE_URI);
		
		XPathFactory factory = XPathFactory.instance();
		
		XPathExpression<Element> startDate = factory.compile("//hr:StartDate", Filters.element(), null, namespace);
		XPathExpression<Element> endDate = factory.compile("//hr:EndDate", Filters.element(), null, namespace);
		XPathExpression<Element> firstNameExpression = factory.compile("//hr:FirstName", Filters.element(), null, namespace);
		XPathExpression<Element> lastNameExpression = factory.compile("//hr:LastName", Filters.element(), null, namespace);
		
		Element startDateElement = startDate.evaluateFirst(holidayRequest);
		Element endDateElement = endDate.evaluateFirst(holidayRequest);
		Element firstNameElement = firstNameExpression.evaluateFirst(holidayRequest);
		Element lastNameElement = lastNameExpression.evaluateFirst(holidayRequest);
		System.out.println(startDateElement.getText() + "," + endDateElement.getText() + "," + firstNameElement.getText() + ","+ lastNameElement.getText() + ",");
	}
}
