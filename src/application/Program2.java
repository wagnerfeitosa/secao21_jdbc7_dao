package application;

import java.util.List;
import java.util.Scanner;

import model.dao.DaoFactory;
import model.dao.DepartmentDao;
import model.entities.Department;

public class Program2 {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		//INSTANCIANDO INTERFACE DepartmentDao ATRIBUINDO FABRICA DE DAO DaoFactoy.createDepartmentDao
		DepartmentDao departmentDao = DaoFactory.createDepartmentDao();
		
		System.out.println("===Test:1 Department FindById===");
		//Criando um department e atribuindo deaprtmentDao.findById passando id como argumento da consulta
		Department department = departmentDao.findById(2);
		System.out.println(department);
		
		System.out.println("-----------------------------------");
		
		System.out.println("===Test:2 Department findAll===");
		//Criando uma lista Department atribuindo findAll
		List<Department> listaDep = departmentDao.findAll();
		//percorrendo listaDep Imprimindo cada dep
		for(Department dep : listaDep) {
			System.out.println(dep);
		}
		
		System.out.println("-------------------------------------");
		
		System.out.println("===Test:3 Department insert==");
		Department newDepartment = new Department(null,"Software");
		departmentDao.insert(newDepartment);
		System.out.println("New Department completed");
		
		System.out.println("--------------------------------------");
		
		System.out.println("===Test:4 Department Update");
		//atribuindo a variavel new Department o findById recebendo como argumento id=6
		newDepartment = departmentDao.findById(6);
		//setando novo Department a varivel newDepartment
		newDepartment.setName("D10");
		//alterando na base de dados
		departmentDao.update(newDepartment);
		System.out.println("Department Update completed");
		
		System.out.println("----------------------------------------");
		
		System.out.println("===Test:5 Department DeleteById===");
		System.out.print("Enter id for delete test: ");
		int id = sc.nextInt();
		departmentDao.deleteById(id);
		System.out.println("Delete completed");
		
		
		

	}

}
