package application;

import java.util.List;

import model.dao.DaoFactory;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

public class Program {
	public static void main(String[] args) {
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
	}
}
