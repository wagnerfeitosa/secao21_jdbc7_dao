package model.dao.Impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import db.DB;
import db.DbException;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

public class SellerDaoJDBC implements SellerDao {
	private Connection conn = null;
	
	//FECHANDO UMA INJEÇÃO DE INDEPENDENCIA
	public SellerDaoJDBC(Connection conn) {
		this.conn = conn;
	}

	@Override
	public void insert(Seller obj) {
		
		
	}

	@Override
	public void update(Seller obj) {
		
		
	}

	@Override
	public void deleteById(Integer id) {
		
		
	}
    //metodo que retorna um seller por Id
	@Override
	public Seller findById(Integer id) {
		PreparedStatement st = null;
		ResultSet rs = null;
		
		try {
			st = conn.prepareStatement("select seller.*,department.Name as DepName "
					+ "from seller inner join department "
					+ "on seller.DepartmentId = department.Id "
					+ "where seller.Id = ?");
			st.setInt(1, id);
			rs = st.executeQuery();
			if(rs.next()) {
				Department dep = instantiateDepartament(rs);
				Seller obj = instantiateSelle(rs,dep);
				return obj;
			}
			return null;
		
			
		}catch(SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally {
			DB.closeResultSet(rs);
			DB.closeStatement(st);
		}
		
	}
    //METODO PARA INSTANCIAR OBJETO SELLER
	private Seller instantiateSelle(ResultSet rs, Department dep) throws SQLException {
		Seller obj = new Seller();
		obj.setId(rs.getInt("Id"));
		obj.setName(rs.getString("Name"));
		obj.setEmail(rs.getString("Email"));
		obj.setBaseSalary(rs.getDouble("BaseSalary"));
		obj.setBirthDate(rs.getDate("BirthDate"));
		obj.setDepartment(dep);
		return obj;
	}
    //METODO PARA INSTACIAR OBJETO DEPARTMENT
	private Department instantiateDepartament(ResultSet rs) throws SQLException {
		Department dep = new Department();
		dep.setId(rs.getInt("DepartmentId"));
		dep.setName(rs.getString("DepName"));
		return dep;
	}

	@Override
	public List<Seller> findAll() {
		
		Statement st = null;
		ResultSet rs = null;
		List<Seller> lista = new ArrayList<>();
		Map<Integer,Department> map = new HashMap<>();
		
		try {
			String sql = "SELECT seller.*,department.Name as DepName "
					+ "FROM seller INNER JOIN department "
					+ "ON seller.DepartmentId = department.Id "
					+ "ORDER BY Name";
			
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			while(rs.next()) {
				
				//forma para não repetir de department
				Department dep = map.get(rs.getInt("DepartmentId"));
				
				if(dep == null) {
					dep = instantiateDepartament(rs);
					map.put(rs.getInt("DepartmentId"), dep);
				}
				
				Seller c = instantiateSelle(rs, dep);
				lista.add(c);
			}
			
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		return lista;
	}
    //metodo que retorna uma lista de seller por Id department
	@Override
	public List<Seller> findDepartment(Department department) {
		
		PreparedStatement st = null;
		ResultSet rs = null;
		
		try {
			st = conn.prepareStatement("SELECT seller.*,department.Name as DepName "
					+ "FROM seller INNER JOIN department "
					+ "ON seller.DepartmentId = department.Id "
					+ "WHERE DepartmentId = ? "
					+ "ORDER BY Name");
			
			st.setInt(1, department.getId());
			
			rs = st.executeQuery();
			
			List<Seller> lista = new ArrayList<>();
			//UTILIZAMOS MAP PARA NÃO REPETIRMOS A INSTANCIAÇÃO DE DEPARTMENT
			Map<Integer, Department> map = new HashMap<>(); 
			
			while(rs.next()) {
				
				Department dep = map.get(rs.getInt("DepartmentId"));
				
				if(dep == null) {
					dep = instantiateDepartament(rs);
					map.put(rs.getInt("DepartmentId"), dep);
				}
				
				Seller obj = instantiateSelle(rs,dep);
				lista.add(obj);
			}
			return lista;
		
			
		}catch(SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally {
			DB.closeResultSet(rs);
			DB.closeStatement(st);
		}
		
		
	
	}
	

}
