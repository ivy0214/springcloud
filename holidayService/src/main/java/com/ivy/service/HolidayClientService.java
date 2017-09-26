/**
 * Project Name:holidayService
 * File Name:HolidayClient.java
 * Package Name:com.ivy.service
 * Date:2017年9月26日下午1:51:56
 * Copyright (c) 2017, ivy_tangjintao@163.com All Rights Reserved.
 *
 */
package com.ivy.service;

import java.io.IOException;
import java.io.StringReader;
import java.util.Calendar;
import java.util.Date;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.namespace.QName;
import javax.xml.transform.TransformerException;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ws.WebServiceMessage;
import org.springframework.ws.client.core.WebServiceMessageCallback;
import org.springframework.ws.client.core.WebServiceTemplate;
import org.springframework.ws.soap.SoapHeader;
import org.springframework.ws.soap.SoapHeaderElement;
import org.springframework.ws.soap.SoapMessage;

import com.ivy.bean.EmployeeType;
import com.ivy.bean.HolidayRequest;
import com.ivy.bean.HolidayResponse;
import com.ivy.bean.HolidayType;
import com.ivy.bean.IsAliveRequest;

/**
 * ClassName: HolidayClient <br/>
 * Function: Spring WebService客户端. <br/>
 * date: 2017年9月26日 下午1:51:56 <br/>
 *
 * @author ivy_tang
 * @version 
 * @since JDK 1.8
 */
@Service
public class HolidayClientService
{
	private static final String URI = "http://com.ivy.project/hr";
	private static final String IS_ALIVE_MESSAGE = "<hr:IsAliveRequest xmlns:hr=\"http://com.ivy.project/hr\">111</hr:IsAliveRequest>";
	@Autowired
	private WebServiceTemplate webServiceTemplate;
	/**
	 * isAliveWithPojo:自定义soapheader信息. <br/>
	 *
	 * @author ivy_tang
	 * @since JDK 1.8
	 */
	public void isAliveWithPojo()
	{
		this.webServiceTemplate.marshalSendAndReceive(new IsAliveRequest(), new WebServiceMessageCallback()
		{
			
			@Override
			public void doWithMessage(WebServiceMessage message) throws IOException, TransformerException
			{
				SoapHeader soapHeader = ((SoapMessage)message).getSoapHeader();
				// 必须使用别名，否则无法发送到服务端
				SoapHeaderElement name = soapHeader.addHeaderElement(new QName(URI, "username", "ns1"));
				name.setText("username");
			}
		});
	}
	public void isAliveWithStream()
	{
		StreamSource request = new StreamSource(new StringReader(IS_ALIVE_MESSAGE));
		StreamResult response = new StreamResult(System.out);
		this.webServiceTemplate.sendSourceAndReceiveToResult(request, response);
	}
	
	public HolidayResponse holidayMethod()
	{
		Calendar cal = Calendar.getInstance();  
        cal.setTime(new Date());  
        DatatypeFactory dtf = null;  
         try {  
            dtf = DatatypeFactory.newInstance();  
        } catch (DatatypeConfigurationException e) {  
        }  
        XMLGregorianCalendar dateType = dtf.newXMLGregorianCalendar();  
        dateType.setYear(cal.get(Calendar.YEAR));  
        //由于Calendar.MONTH取值范围为0~11,需要加1  
        dateType.setMonth(cal.get(Calendar.MONTH)+1);  
        dateType.setDay(cal.get(Calendar.DAY_OF_MONTH));  
        dateType.setHour(cal.get(Calendar.HOUR_OF_DAY));  
        dateType.setMinute(cal.get(Calendar.MINUTE));  
        dateType.setSecond(cal.get(Calendar.SECOND));
		EmployeeType employee = new EmployeeType();
		employee.setFirstName("ivy");
		employee.setLastName("tang");
		employee.setNumber(1);
		
		HolidayType holidayType = new HolidayType();
		holidayType.setEndDate(dateType);
		holidayType.setStartDate(dateType);
		
		HolidayRequest request = new HolidayRequest();
		request.setEmployee(employee);
		request.setHoliday(holidayType);
		HolidayResponse response = (HolidayResponse)this.webServiceTemplate.marshalSendAndReceive(request);
		return response;
	}
}
