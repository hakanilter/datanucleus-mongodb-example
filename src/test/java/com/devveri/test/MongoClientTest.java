package com.devveri.test;

import java.net.UnknownHostException;

import junit.framework.Assert;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.Mongo;
import com.mongodb.MongoException;

public class MongoClientTest 
{
	private Mongo mongo;
	
	@Before
	public void setUp() throws UnknownHostException, MongoException {
		mongo = new Mongo("localhost:27001");
	}
	
	@After
	public void tearDown()
	{
		if (mongo != null) {
			mongo.close();
		}
	}
	
	@Test
	public void test()
	{
		DB db = mongo.getDB("example");
		DBCollection members = db.getCollection("members");
		DBCursor cursor = members.find();
		Assert.assertNotNull(cursor);
		
		while (cursor.hasNext()) {
			DBObject member = cursor.next();
			System.out.println("id      : " + member.get("id"));
			System.out.println("fullName: " + member.get("fullName"));
			System.out.println("email   : " + member.get("email"));
			System.out.println("age     : " + member.get("age"));
			System.out.println("balance : " + member.get("balance"));
		}
	}
}
