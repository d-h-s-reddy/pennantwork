package Mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import Models.Users;

public class UserMapper implements RowMapper<Users> {

	@Override
	public Users mapRow(ResultSet rs, int rowNum) throws SQLException {
		Users u = new Users();
		u.setUserName(rs.getString("username"));
		u.setPassWord(rs.getString("password"));

		return u;
	}

}
