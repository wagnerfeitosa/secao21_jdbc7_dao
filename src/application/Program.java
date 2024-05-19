package application;

import java.util.Date;
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
		System.out.println("--------------------------------------");
		
		System.out.println("====TEST 3: seller findAll");
		listaSeller = sellerDao.findAll();
		
		for(Seller obj: listaSeller) {
			System.out.println(obj);
		}
		
		System.out.println("--------------------------------------");
		
		System.out.println("====TEST 4: seller insert");
		//CRIANDO OBJETO SELLER
		Seller newSeller = new Seller(null,"Greg","greg@Gmail.com",new Date(),4000.0,department);
		//INSERINDO OBJETO SELLER CRIADO NA BASE DE DADOS
		sellerDao.insert(newSeller);
		//IMPRIMINDO O ID INSERIDO NA BASE DE DADOS
		System.out.println("Insert new id: "+newSeller.getId());
		
	

	}

}
