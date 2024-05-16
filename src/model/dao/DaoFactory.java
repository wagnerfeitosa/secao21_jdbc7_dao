package model.dao;

import db.DB;
import model.dao.Impl.SellerDaoJDBC;

public class DaoFactory {
	
	//CLASSE RESPONSAVEL PARA INSTACIAR SELLERDAO() RECEBENDO DB.CONNECTION
	public static SellerDao createSellerDao() {
		return new SellerDaoJDBC(DB.getConnection());
	}

}
