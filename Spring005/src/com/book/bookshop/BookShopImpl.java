package com.book.bookshop;

import javax.security.auth.login.AccountException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

//@Repository("bookShopDao")
public class BookShopImpl implements BookShopDao {
//	@Autowired
	//使用xml文件进行配置
	private JdbcTemplate JdbcTemplate;
	//需要getter和setter
	public JdbcTemplate getJdbcTemplate() {
		return JdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		JdbcTemplate = jdbcTemplate;
	}

	@Override
	public int findBookPriceByIsbn(String isbn) {
		String sql = "SELECT price from book WHERE isbn=?";
	
		return JdbcTemplate.queryForObject(sql, Integer.class, isbn);
	
	}
	
	//检查书的库存是否足够，若不足够  则抛出异常

	@Override
	public void updateBookStork(String isbn) {
		String sql2 = "SELECT bookstrok FROM book_strok WHERE isbn = ?";
		
		
			int stock = JdbcTemplate.queryForObject(sql2, Integer.class, isbn);
			if(stock == 0){
				throw new BookStorkException("库存不足!");
			}
	
		
		String sql = "update book_strok set bookstrok = bookstrok - 1 where isbn=?";
		
		JdbcTemplate.update(sql, isbn);

	}

	@Override
	public void updateUserAccount(String username, int price) {
		String sql = "select balance from account  where username=?";
		int balance = JdbcTemplate.queryForObject(sql, Integer.class, username);
		//检查余额是否足够
		if(balance<price){
			throw new UserAccountException("余额不足");
		}
		
		String sql2 = "update account set balance = balance - ? where username=?";
		
		JdbcTemplate.update(sql2,price,username);

	}

}
