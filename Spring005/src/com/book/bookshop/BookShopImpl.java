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
	
	//�����Ŀ���Ƿ��㹻�������㹻  ���׳��쳣

	@Override
	public void updateBookStork(String isbn) {
		String sql2 = "SELECT bookstrok FROM book_strok WHERE isbn = ?";
		
		
			int stock = JdbcTemplate.queryForObject(sql2, Integer.class, isbn);
			if(stock == 0){
				throw new BookStorkException("��治��!");
			}
	
		
		String sql = "update book_strok set bookstrok = bookstrok - 1 where isbn=?";
		
		JdbcTemplate.update(sql, isbn);

	}

	@Override
	public void updateUserAccount(String username, int price) {
		String sql = "select balance from account  where username=?";
		int balance = JdbcTemplate.queryForObject(sql, Integer.class, username);
		//�������Ƿ��㹻
		if(balance<price){
			throw new UserAccountException("����");
		}
		
		String sql2 = "update account set balance = balance - ? where username=?";
		
		JdbcTemplate.update(sql2,price,username);

	}

}
