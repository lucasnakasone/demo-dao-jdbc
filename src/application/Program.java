package application;

import java.util.List;

import model.dao.DaoFactory;
import model.dao.DepartmentDao;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

public class Program {
	public static void main(String[] args) {
		/* SellerDao Tests
		SellerDao sellerDao = DaoFactory.createSellerDao();
		
		System.out.println("\tfindById");
		Seller s = sellerDao.findById(3);
		System.out.println(s);
		
		System.out.println("\tfindByDepartment");
		Department d = new Department(2, null);
		List<Seller> list = sellerDao.findByDepartment(d);
		list.forEach(System.out::println);
		
		System.out.println("\tfindAll");
		list = sellerDao.findAll();
		list.forEach(System.out::println);
		
		System.out.println("\tinsert");
		Seller newSeller = new Seller(null, "Greg", "greg@gmail.com", new Date(), 4000.0, d);
		sellerDao.insert(newSeller);
		System.out.println("Seller inserted. ID: " + newSeller.getId());
		
		System.out.println("\tupdate");
		System.out.println();
		s = sellerDao.findById(1);
		s.setName("Martha Waine");
		sellerDao.update(s);
		System.out.print("Update completed.");
		
		System.out.println("\tdelete");
		sellerDao.deleteById(14);
		*/
		
		DepartmentDao departmentDao = DaoFactory.createDepartmentDao();
		
		/*
		System.out.println("\tinsert department");
		Department d = new Department(null, "Furnitures");
		departmentDao.insert(d);
		System.out.println("Department inserted. ID: " + d.getId());
		*/
		
		System.out.println("\tupdate department");
		Department findDep = departmentDao.findById(5);
		findDep.setName("Souvenirs");
		departmentDao.update(findDep);
		System.out.println("Update completed.");
		
		System.out.println("\tfind department by id (5)");
		System.out.println(departmentDao.findById(5));
		
		System.out.println("\tfindAll departments");
		List<Department> depList = departmentDao.findAll();
		depList.forEach(System.out::println);
	}
}
