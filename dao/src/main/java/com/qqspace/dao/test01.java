package com.qqspace.dao;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.qqSpace.domain.UserVo;
import com.qqspace.dao.base.impl.BaseDaoimpl;

public class test01 extends BaseDaoimpl<UserVo>{
	
	public UserVo getUser() {
		List<?> list = this.getHibernateTemplate().find("from UserVo");
		return (UserVo) list.get(0);
	}
	@Test
	public void Test() {
		ApplicationContext a=new ClassPathXmlApplicationContext("applicationContext-datasource.xml");
		test01 t=(test01) a.getBean("test01");
		UserVo user = t.findById(2);
		System.out.println(user);
	}
}
