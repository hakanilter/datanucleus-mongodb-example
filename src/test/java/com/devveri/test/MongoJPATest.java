package com.devveri.test;

import junit.framework.Assert;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.devveri.dao.MemberDAO;
import com.devveri.model.Member;

public class MongoJPATest 
{
	private MemberDAO dao;
	
	@Before
	public void setUp() {
		dao = new MemberDAO();
	}
	
	@After
	public void tearDown() 
	{
		if (dao != null) {
			dao.close();
		}
	}
	
	@Test
	public void test()
	{
		// save
		Member member = new Member();
		member.setFullName("John Doe");
		member.setEmail("johndoe@gmail.com");
		member.setAge(35);
		member.setBalance(10.5);
		dao.save(member);
		Assert.assertNotNull(member.getId());
		
		// find by id
		Member loaded = dao.findById(member.getId());
		System.out.println(loaded);
		Assert.assertNotNull(loaded);
		Assert.assertEquals(member.getId(), loaded.getId());
		Assert.assertEquals(member.getFullName(), loaded.getFullName());
		Assert.assertEquals(member.getEmail(), loaded.getEmail());
		Assert.assertEquals(member.getAge(), loaded.getAge());
		Assert.assertEquals(member.getBalance(), loaded.getBalance(), 0.01);
		
		// delete
		dao.delete(loaded.getId());
		Member deleted = dao.findById(loaded.getId());
		Assert.assertNull(deleted);
	}
}
