package com.book.bookshop;

public interface BookShopDao {
	
	//根据书的书号  获取单价
	public int findBookPriceByIsbn(String isbn);
	
	//更新书的库存，使书号对应的库存-1
	public void updateBookStork(String isbn);
	
	//更新用户的账户余额，使用户的balance-price
	public void updateUserAccount(String username,int price);
}
