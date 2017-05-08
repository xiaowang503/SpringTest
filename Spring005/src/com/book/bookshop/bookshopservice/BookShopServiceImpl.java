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
	 * ע�ⷽʽ������ʽ�����裺
	 * 1.�������������
	 * 2.��������ע��
	 * 3.��Ŀ�귽�������@Transactionalע��
	 * 
	 * �����ܱ������ݵ�һ����   һ���κ�һ���쳣�������е����ݶ�����ı�
	 * �������ע��
	 * */
	/*
	 * 1.ʹ��propagationָ������Ĵ�����Ϊ     Ĭ����REQUIRED�ķ�ʽ
	 * ��ʹ�õ��÷���������
	 * REQUIRES_NEW��ʾÿ�ε������񷽷����Ὺ��һ���µ�����   ÿ�������������  �������������  �Ƚ��������       
	 * 2.ʹ��isolationָ������ĸ��뼶��  ����õ���READ_COMMITTED
	 * 
	 * 3.Ĭ������� spring������ʽ��������е�����ʱ�쳣���лع���Ҳ����ͨ����Ӧ�����Խ��лع�   ͨ������¶���ʹ��Ĭ������
	 * 4.readOnly=false�����Ƿ���  ֻ������  �������԰������ݿ������Ż�����
	 * 5.ʹ��timeoutָ��  ǿ�ƻع�  ֮ǰ�������ռ�õ�ʱ�䡣��λ����
	 * */
//	@Transactional(propagation=Propagation.REQUIRED, 
//			isolation=Isolation.READ_COMMITTED,readOnly=false,timeout=3)
//	@Transactional(propagation=Propagation.REQUIRED, 
//			isolation=Isolation.READ_COMMITTED,readOnly=false)
	@Override
	public void purchase(String username, String isbn) {
		
		/*
		 * �߳�˯��5����   ��������timeout��������  3 �� ǿ�ƻع�  ����  �����   �����ع�  �����ݾ�û�в���Ч��
			����timeout
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		*/
		//��ȡ��ĵ���
		int price = bookshop.findBookPriceByIsbn(isbn);
		//�����������
		bookshop.updateBookStork(isbn);
		//�����û����
		bookshop.updateUserAccount(username, price);

	}

}
