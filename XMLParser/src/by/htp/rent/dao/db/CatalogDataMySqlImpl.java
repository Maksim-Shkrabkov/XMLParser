package by.htp.rent.dao.db;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import by.htp.rent.dao.CatalogData;
import by.htp.rent.domain.Catalog;
import by.htp.rent.domain.Equipment;

public class CatalogDataMySqlImpl implements CatalogData {

	@Override
	public Catalog readCatalog() {
		
		Catalog catalog = new Catalog();
		
		List<Equipment> equipments = selectEquipmentList();
		catalog.setEquipments(equipments);
		
		return catalog;
	}
	
	private List<Equipment> selectEquipmentList() {
		
		ResourceBundle rd = ResourceBundle.getBundle("db_config");
		String url = rd.getString("db.url");
		String user = rd.getString("db.login");
		String pass = rd.getString("db.pass");
		// driver - это обычный java class 
		String driver = rd.getString("db.driver.name");
		
		List<Equipment> equipments = new ArrayList<>();
		
		try(Connection conn = DriverManager.getConnection(url, user, pass);) {
			//принудительная загрузка класса 
			//Class.forName(driver);
			
			//statemenrt вернул объект, который готов выполнить наш запрос
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery("select * from equipment;");
			
			while(rs.next()) {
				
				int id = rs.getInt(1);
				String title = rs.getString(2);
				Double phone = rs.getDouble(3);
				Date date = rs.getDate(4);
				
				System.out.println(id + " " + title + " " + phone + " " + date);
				
			}
			
			PreparedStatement ps;
			CallableStatement cs;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		return equipments;
	}

}
