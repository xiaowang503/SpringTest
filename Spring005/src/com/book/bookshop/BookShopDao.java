package com.book.bookshop;

public interface BookShopDao {
	
	//����������  ��ȡ����
	public int findBookPriceByIsbn(String isbn);
	
	//������Ŀ�棬ʹ��Ŷ�Ӧ�Ŀ��-1
	public void updateBookStork(String isbn);
	
	//�����û����˻���ʹ�û���balance-price
	public void updateUserAccount(String username,int price);
}
