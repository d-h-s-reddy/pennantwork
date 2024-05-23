package DAO;

import java.util.List;

import org.springframework.stereotype.Component;

import Models.Products;

@Component
public interface DAO_Login {

	boolean LoginInfo(String username, String password);

	List<Products> getallproducts();
}
