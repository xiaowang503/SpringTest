package com.book.bookshop.bookshopservice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("cashier")
public class CashierImpl implements Cashier {
	
	@Autowired
	
	private BookShopService bookShopService;
	@Override
	public void checkOut(String username, List<String> isbns) {
		
		for(String isbn:isbns){
			bookShopService.purchase(username, isbn);
		}

	}

}
