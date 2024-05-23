package Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import DAO.DAO_Login;
import Models.Products;

@Controller
public class ProductController {

	@Autowired
	DAO_Login dl;

	@RequestMapping(value = "/allproducts", method = RequestMethod.GET)
	public String getproducts(Model model) {
		List<Products> products = dl.getallproducts();
		model.addAttribute("product", products);
		return "AllProducts";
	}

}
