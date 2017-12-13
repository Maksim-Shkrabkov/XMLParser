package by.htp.rent.dao.parser.sax;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import by.htp.rent.dao.parser.CatalogTagName;
import by.htp.rent.dao.parser.DataTypeTransformUtil;
import by.htp.rent.domain.Equipment;

public final class CatalogDataHandler extends DefaultHandler{
	
	private StringBuilder text;
	private Equipment equipment;
	private List<Equipment> equipments = new ArrayList<>();
	
	private static final char UNDERSCORE = '_';
	private static final char DASH = '-';
	
	private static final String ID = "id";
	
	
	@Override
	public void characters(char[] ch, int start, int lenghts) throws SAXException {
//		System.out.println("characters: " + Arrays.toString(ch) + "start= " + start +  "lenghts= " + lenghts);
//		System.out.println("start= " + start +  "lenghts= " + lenghts);
		text.append(ch, start, lenghts);
		
	}


	@Override
	public void endElement(String uri, String localname, String qName) throws SAXException {
		
		CatalogTagName tag = getTag(localname);
		switch(tag) {
		case EQUIPMENT:
			equipments.add(equipment);
			break;
		case TITLE:
			equipment.setTitle(text.toString().trim());
			break;
		case PRICE:
			String price = text.toString().trim();
			equipment.setPrice(DataTypeTransformUtil.convertPrice(price));
			break;
		case DATE:
			String date = text.toString().trim();
			equipment.setDate(DataTypeTransformUtil.convertData(date));
			break;
		}
		
	}


	@Override
	public void startElement(String uri, String localname, String qName, Attributes attributes) throws SAXException {
		
		CatalogTagName tag = getTag(localname);
		switch(tag) {
		case EQUIPMENT:
			equipment = new Equipment();
			String id = attributes.getValue("id");
			equipment.setId(DataTypeTransformUtil.convertID(id));
			break;
		}
		
		text = new StringBuilder();
		
	}

	public List<Equipment> getEquipments() {
		return equipments;
	}

	public void setEquipments(List<Equipment> equipments) {
		this.equipments = equipments;
	}
	
	private CatalogTagName getTag(String localName) {
		String tag = localName.toUpperCase().replace(DASH, UNDERSCORE);
		CatalogTagName tagElement = CatalogTagName.valueOf(tag);
		return tagElement;
	}
	
	
	
	
	

}
