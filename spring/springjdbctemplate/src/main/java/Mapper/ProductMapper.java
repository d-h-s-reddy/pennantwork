package Mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import Models.Products;

public class ProductMapper implements RowMapper<Products> {

	@Override
	public Products mapRow(ResultSet rs, int rowNum) throws SQLException {
		Products p = new Products();
		p.setProduct_id(rs.getInt("prod_id"));
		p.setProduct_title(rs.getString("prod_title"));
		p.setProduct_brand(rs.getString("prod_brand"));
		p.setProduct_img(rs.getString("prod_image"));
		p.setProduct_price(rs.getDouble("prod_price"));
		return p;
	}

}
