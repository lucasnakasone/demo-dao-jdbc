package application;

import java.util.Date;

import model.entities.Department;
import model.entities.Seller;

public class Program {
	public static void main(String[] args) {
		Department d = new Department(1, "d");
		System.out.println(d);
		Seller s = new Seller(1, "L", "L@a", new Date(), 3., d);
		System.out.println(s);
	}
}
