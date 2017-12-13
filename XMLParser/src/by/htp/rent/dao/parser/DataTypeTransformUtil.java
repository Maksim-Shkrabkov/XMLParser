package by.htp.rent.dao.parser;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DataTypeTransformUtil {
	
	public static int convertID(String id) {
		int idInt = 0;
		
			try {
				idInt =Integer.parseInt(id);
			} catch(NumberFormatException e) {
				System.out.println("wrong value for id");
				e.printStackTrace();
			}
		
		return idInt;
	}
	
	public static double convertPrice(String price){
		double priceDouble = 0;
		
		try {
			priceDouble = Double.parseDouble(price);
		} catch(NumberFormatException e) {
			System.out.println("wrong value for price");
			e.printStackTrace();
		}
		
		return priceDouble;
	}
	
	public static Date convertData(String dateString){
		
		Date date = null;
		
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-YYYY");
			date = sdf.parse(dateString);
		} catch(ParseException e) {
			System.out.println("wrong value for date");
			e.printStackTrace();
		}
		
		return date;
	}

}
