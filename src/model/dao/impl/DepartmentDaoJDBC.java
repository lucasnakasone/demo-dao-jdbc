package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import db.DB;
import db.DbException;
import model.dao.DepartmentDao;
import model.entities.Department;

public class DepartmentDaoJDBC implements DepartmentDao {

	private Connection conn;
	
	public DepartmentDaoJDBC(Connection conn) {
		this.conn = conn;
	}
	
	private Department instantiateDepartment(ResultSet rs) throws SQLException {
		Department d = new Department();
		d.setId(rs.getInt("ID"));
		d.setName(rs.getString("NAME"));
		return d;
	}
	
	@Override
	public void insert(Department obj) {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement(
					"INSERT INTO DEPARTMENT "
					+ "(NAME) "
					+ "VALUES (?)",
					Statement.RETURN_GENERATED_KEYS);
			st.setString(1, obj.getName());
			
			int rowsAffected = st.executeUpdate();
			
			if (rowsAffected > 0) {
				rs = st.getGeneratedKeys();
				if (rs.next()) {
					int id = rs.getInt(1);
					obj.setId(id);
				}
			} else {
				throw new DbException("Unexpected error, no rows affected.");
			}
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatement(st);
			DB.closeResultSet(rs);
		}
	}

	@Override
	public void update(Department obj) {
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement(
					"UPDATE DEPARTMENT "
					+ "SET NAME = ? "
					+ "WHERE ID = ?");
			st.setString(1, obj.getName());
			st.setInt(2, obj.getId());
			st.executeUpdate();
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatement(st);
		}
	}

	@Override
	public void deleteById(Integer id) {
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement("DELETE FROM DEPARTMENT WHERE ID = ?");
			st.setInt(1, id);
			st.executeUpdate();
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatement(st);
		}
	}

	@Override
	public Department findById(Integer id) {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {	
			st = conn.prepareStatement("SELECT * FROM DEPARTMENT WHERE ID = ?");
			st.setInt(1, id);
			rs = st.executeQuery();
			if(rs.next()) {
				Department d = instantiateDepartment(rs);
				return d;
			}
			return null;
		} catch (SQLException e){
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatement(st);
			DB.closeResultSet(rs);
		}
	}

	@Override
	public List<Department> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

}
