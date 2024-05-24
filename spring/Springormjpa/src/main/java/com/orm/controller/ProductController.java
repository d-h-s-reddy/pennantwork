package com.orm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.orm.service.ProductDao;

@Controller
public class ProductController {

	@Autowired
	ProductDao pd;

	@RequestMapping(value = "/addproducts", method = RequestMethod.GET)
	@ResponseBody
	public String addproducts() {
		System.out.println("you can add the products");
		pd.addproducts();
		return "<h1>data is added successfully </h1>";
	}

}
