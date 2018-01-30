package com.apps.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import com.apps.BO.FromBO;
import com.apps.BO.Product;
import com.apps.BO.ToBO;
import com.apps.BO.UserBO;

@Repository
public class UserDao {
	private final static String USER_FETCH = "select * from USER_TABLE where USER_NAME=?";

	@Autowired
	private JdbcTemplate jdbcTemplate;

	public UserBO valiadateUser(String userName) {
		return jdbcTemplate.queryForObject(USER_FETCH, new Object[] { userName }, new RowMapper<UserBO>() {

			@Override
			public UserBO mapRow(ResultSet rs, int index) throws SQLException {
				UserBO user = null;
				user = new UserBO();
				user.setUserName(rs.getString("USER_NAME"));
				user.setPassword(rs.getString("PASSWORD"));
				user.setRole(rs.getString("USER_ROLE"));
				String lock = rs.getString("LOCKER");
				user.setOcked(lock);
				return user;
			}

		});
	}

	public String saveUser(Product productBo) {
		int fromKey = 0;
		int Key = 0;
		KeyHolder fromKeyHolder = new GeneratedKeyHolder();
		KeyHolder KeyHolder = new GeneratedKeyHolder();
		Key = jdbcTemplate.update(new AddressBoInsertion(productBo), fromKeyHolder);
		Key = jdbcTemplate.update(new AddressBoInsertion(productBo), KeyHolder);
		return null;
	}

	private class AddressBoInsertion implements PreparedStatementCreator {
		Product product;
		public AddressBoInsertion(Product product) {
			this.product = product;
		}

		@Override
		public PreparedStatement createPreparedStatement(Connection conn) throws SQLException {
			PreparedStatement pstmt = conn.prepareStatement(USER_FETCH);

			return pstmt;
		}

	}
}
