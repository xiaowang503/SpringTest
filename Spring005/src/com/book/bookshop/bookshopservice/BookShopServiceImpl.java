package com.book.bookshop.bookshopservice;

import static org.junit.Assert.assertFalse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.book.bookshop.BookShopDao;

//@Service("bookShopService")
public class BookShopServiceImpl implements BookShopService {
	
//	@Autowired
	private BookShopDao bookshop = null;
	
	
	public BookShopDao getBookshop() {
		return bookshop;
	}


	public void setBookshop(BookShopDao bookshop) {
		this.bookshop = bookshop;
	}


	/*
	 * 注解方式的声明式事务步骤：
	 * 1.配置事务管理器
	 * 2.启动事务注解
	 * 3.在目标方法上添加@Transactional注解
	 * 
	 * 事务能保持数据的一致性   一旦任何一个异常发生所有的数据都不会改变
	 * 添加事务注解
	 * */
	/*
	 * 1.使用propagation指定事务的传播行为     默认是REQUIRED的方式
	 * 即使用调用方法的事务
	 * REQUIRES_NEW表示每次调用事务方法都会开启一个新的事务   每个事务独立运行  如果有事务运行  先将事务挂起       
	 * 2.使用isolation指定事务的隔离级别  中最常用的是READ_COMMITTED
	 * 
	 * 3.默认情况下 spring的声明式事务对所有的运行时异常进行回滚，也可以通过对应的属性进行回滚   通常情况下都是使用默认设置
	 * 4.readOnly=false设置是否是  只读事务  这样可以帮助数据库引擎优化事务
	 * 5.使用timeout指定  强制回滚  之前事务可以占用的时间。单位：秒
	 * */
//	@Transactional(propagation=Propagation.REQUIRED, 
//			isolation=Isolation.READ_COMMITTED,readOnly=false,timeout=3)
//	@Transactional(propagation=Propagation.REQUIRED, 
//			isolation=Isolation.READ_COMMITTED,readOnly=false)
	@Override
	public void purchase(String username, String isbn) {
		
		/*
		 * 线程睡眠5秒钟   但是事务timeout的属性是  3 秒 强制回滚  所以  会出错   事务会回滚  对数据就没有产生效果
			报错timeout
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		*/
		//获取书的单价
		int price = bookshop.findBookPriceByIsbn(isbn);
		//更新书的余量
		bookshop.updateBookStork(isbn);
		//更新用户余额
		bookshop.updateUserAccount(username, price);

	}

}
