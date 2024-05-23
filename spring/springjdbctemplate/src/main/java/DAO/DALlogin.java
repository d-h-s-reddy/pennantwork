package DAO;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import Mapper.ProductMapper;
import Models.Products;

@Repository
public class DALlogin implements DAO_Login {

	JdbcTemplate jt;

	@Autowired
	public DALlogin(DataSource dataSource) {
		jt = new JdbcTemplate(dataSource);
	}

	private final String SQL_GET_ALL = "select count(*) from i195_user_credentials where user_name=? and password=?";

	@Override
	public boolean LoginInfo(String username, String password) {
		System.out.println("dl");
		int count = jt.queryForObject(SQL_GET_ALL, Integer.class, username, password);
		if (count == 1) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public List<Products> getallproducts() {
		String sql = "select * from i197_products";
		return jt.query(sql, new ProductMapper());
	}

}
