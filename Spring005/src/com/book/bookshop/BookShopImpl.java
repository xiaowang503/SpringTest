package com.book.bookshop;

import javax.security.auth.login.AccountException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository("bookShopDao")
public class BookShopImpl implements BookShopDao {
	@Autowired
	private JdbcTemplate JdbcTemplate;
	@Override
	public int findBookPriceByIsbn(String isbn) {
		String sql = "SELECT price from book WHERE isbn=?";
	
		return JdbcTemplate.queryForObject(sql, Integer.class, isbn);
	
	}
	
	//¼ì²éÊéµÄ¿â´æÊÇ·ñ×ã¹»£¬Èô²»×ã¹»  ÔòÅ×³öÒì³£

	@Override
	public void updateBookStork(String isbn) {
		String sql2 = "SELECT bookstrok FROM book_strok WHERE isbn = ?";
		
		
			int stock = JdbcTemplate.queryForObject(sql2, Integer.class, isbn);
			if(stock == 0){
				throw new BookStorkException("¿â´æ²»×ã!");
			}
	
		
		String sql = "update book_strok set bookstrok = bookstrok - 1 where isbn=?";
		
		JdbcTemplate.update(sql, isbn);

	}

	@Override
	public void updateUserAccount(String username, int price) {
		String sql = "select balance from account  where username=?";
		int balance = JdbcTemplate.queryForObject(sql, Integer.class, username);
		//¼ì²éÓà¶îÊÇ·ñ×ã¹»
		if(balance<price){
			throw new UserAccountException("Óà¶î²»×ã");
		}
		
		String sql2 = "update account set balance = balance - ? where username=?";
		
		JdbcTemplate.update(sql2,price,username);

	}

}
