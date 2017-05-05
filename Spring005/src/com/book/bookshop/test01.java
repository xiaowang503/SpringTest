package com.book.bookshop;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.book.bookshop.bookshopservice.BookShopService;
import com.book.bookshop.bookshopservice.Cashier;

public class test01 {
	
	private ApplicationContext ctx= null;
	private BookShopDao bookshop = null;
	private BookShopService bss = null;
	private Cashier cashier = null;
	
	{
		
		ctx = new ClassPathXmlApplicationContext("jdbcTest.xml");
		bookshop = ctx.getBean(BookShopDao.class);
		bss = ctx.getBean(BookShopService.class);
		cashier = ctx.getBean(Cashier.class);
	}
	@Test
	public void testfindBookPriceByIsbn() {
		System.out.println(bookshop.findBookPriceByIsbn("002"));
	}
	@Test
	public void testupdateBookStrok() {
		bookshop.updateBookStork("002");
	
	}
	
	@Test
	public void testupdateUserAccount() {
		bookshop.updateUserAccount("wangxiao", 50);
	
	}
	
	@Test
	public void testService(){
		bss.purchase("wangxiao", "2");
		
	}
	/*
	 * 测试事务的传播行为
	 * */
	
	@Test
	public void testTransactionPropagation(){
		
		cashier.checkOut("wangxiao", Arrays.asList("1","2"));
	}
}
