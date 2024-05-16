package application;

import java.util.List;

import model.dao.DaoFactory;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

public class Program {

	public static void main(String[] args) {
		
		
		
		SellerDao sellerDao = DaoFactory.createSellerDao();
		System.out.println("===TEST 1: seller findyById===");
		Seller seller = sellerDao.findById(3);
		System.out.println(seller);
		
		System.out.println("-------------------------------------");
		
		System.out.println("===TEST 2: seller findyDepartment===");
		Department department = new Department(2,null);
		List<Seller> listaSeller = sellerDao.findDepartment(department);
		
		for(Seller obj : listaSeller) {
			System.out.println(obj);
		}
	

	}

}
