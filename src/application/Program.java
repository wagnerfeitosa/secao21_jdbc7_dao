package application;

import java.util.Date;
import java.util.List;
import java.util.Scanner;

import model.dao.DaoFactory;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

public class Program {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		
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
		
		System.out.println("-------------------------------------");
		System.out.println("====TEST 5: seller Update");
		//CAPTURANDO SELLER DE ID =1
		seller = sellerDao.findById(1);
		//SETANDO UM NOVO NOMEDO SELLER CAPTURADO
		seller.setName("Martha Waine");
		//ALTERANDO NA BASE DE DADOS
		sellerDao.update(seller);
		
		System.out.println("Update completed");
		
		System.out.println("-------------------------------------");
		System.out.println("====TEST 6: seller Delete");
		
		System.out.print("Enter id for delete test: ");
		int id = sc.nextInt();
		sellerDao.deleteById(id);
		System.out.println("Delete completed");
		
		sc.close();

	}

}
