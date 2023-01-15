package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import db.DbException;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

public class SellerDaoJDBC implements SellerDao{

	private Connection conn;
	
	public SellerDaoJDBC(Connection conn) {
		this.conn = conn;
	}
	
	@Override
	public void insert(Seller obj) {
		// TODO Auto-generated method stub
	}

	@Override
	public void update(Seller obj) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteById(Integer id) {
		
		
	}

	@Override
	public Seller findById(Integer id) {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement(
					"SELECT SELLER.*, DEPARTMENT.NAME " + 
					"FROM SELLER INNER JOIN DEPARTMENT " + 
					"ON SELLER.DEPARTMENTID = DEPARTMENT.ID " + 
					"WHERE SELLER.ID = ?");
			st.setInt(1, id);
			rs = st.executeQuery();
			if (rs.next()) {
				Department d = new Department();
				d.setId(rs.getInt("DEPARTMENTID"));
				d.setName(rs.getString("DEPARTMENT.NAME"));
				Seller s = new Seller();
				s.setId(rs.getInt("ID"));
				s.setName(rs.getString("NAME"));
				s.setEmail(rs.getString("EMAIL"));
				s.setBaseSalary(rs.getDouble("BASESALARY"));
				s.setBirthDate(rs.getDate("BIRTHDATE"));
				s.setDepartment(d);
				return s;
			}
			return null;
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		}
	}

	@Override
	public List<Seller> findAll() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
