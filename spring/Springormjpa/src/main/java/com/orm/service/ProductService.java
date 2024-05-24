package com.orm.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.orm.model.Products;
import com.orm.repository.ProductRepository;

@Service
public class ProductService implements ProductDao {

	@Autowired
	ProductRepository pr;

	public void addproducts() {

		Products p = new Products();
		p.setProd_name("iphone 13");
		p.setProd_price(100000.00);

		pr.save(p);
		System.out.println("data is successfully added ");
	}
}
