package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import db.DB;
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
					"SELECT SELLER.*, DEPARTMENT.NAME AS DEPARTMENT " + 
					"FROM SELLER INNER JOIN DEPARTMENT " + 
					"ON SELLER.DEPARTMENTID = DEPARTMENT.ID " + 
					"WHERE SELLER.ID = ?");
			st.setInt(1, id);
			rs = st.executeQuery();
			if (rs.next()) {
				Department d = instantiateDepartment(rs);
				Seller s = instantiateSeller(rs, d);
				return s;
			}
			return null;
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatement(st);
			DB.closeResultSet(rs);
		}
	}

	@Override
	public List<Seller> findAll() {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement(
					"SELECT SELLER.*, DEPARTMENT.NAME AS DEPARTMENT " +
					"FROM SELLER INNER JOIN DEPARTMENT " +
					"ON SELLER.DEPARTMENTID = DEPARTMENT.ID " +
					"ORDER BY NAME"
					);
			rs = st.executeQuery();
			List<Seller> list = new ArrayList<Seller>();
			Map<Integer, Department> map = new HashMap<>();
			while (rs.next()) {
				Department d = map.get(rs.getInt("DEPARTMENTID"));
				if(d == null) {
					d = instantiateDepartment(rs);
					map.put(rs.getInt("DEPARTMENTID"), d);
				}	
				Seller s = instantiateSeller(rs, d);
				list.add(s);
			}
			return list;
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatement(st);
			DB.closeResultSet(rs);
		}
	}
	
	private Seller instantiateSeller(ResultSet rs, Department d) throws SQLException {
		Seller s = new Seller();
		s.setId(rs.getInt("ID"));
		s.setName(rs.getString("NAME"));
		s.setEmail(rs.getString("EMAIL"));
		s.setBaseSalary(rs.getDouble("BASESALARY"));
		s.setBirthDate(rs.getDate("BIRTHDATE"));
		s.setDepartment(d);
		return s;
	}
	
	private Department instantiateDepartment(ResultSet rs) throws SQLException {
		Department d = new Department();
		d.setId(rs.getInt("DEPARTMENTID"));
		d.setName(rs.getString("DEPARTMENT"));
		return d;
	}

	@Override
	public List<Seller> findByDepartment(Department department) {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement(
					"SELECT SELLER.*, DEPARTMENT.NAME AS DEPARTMENT " +
					"FROM SELLER INNER JOIN DEPARTMENT " +
					"ON SELLER.DEPARTMENTID = DEPARTMENT.ID " +
					"WHERE DEPARTMENTID = ? " +
					"ORDER BY NAME"
					);
			st.setInt(1, department.getId());
			rs = st.executeQuery();
			List<Seller> list = new ArrayList<Seller>();
			Map<Integer, Department> map = new HashMap<>();
			while (rs.next()) {
				Department d = map.get(rs.getInt("DEPARTMENTID"));
				if(d == null) {
					d = instantiateDepartment(rs);
					map.put(rs.getInt("DEPARTMENTID"), d);
				}	
				Seller s = instantiateSeller(rs, d);
				list.add(s);
			}
			return list;
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatement(st);
			DB.closeResultSet(rs);
		}
	}
	
}
