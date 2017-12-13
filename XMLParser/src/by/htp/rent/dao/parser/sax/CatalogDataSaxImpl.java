package by.htp.rent.dao.parser.sax;

import java.io.IOException;
import java.util.List;

import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

import by.htp.rent.dao.CatalogData;
import by.htp.rent.domain.Catalog;
import by.htp.rent.domain.Equipment;

public class CatalogDataSaxImpl implements CatalogData{
	
	private static final String XML_FILE_PATH = "resources/rent_station.xml";
	
	@Override
	public Catalog readCatalog() {
		
		Catalog catalog = new Catalog();
		
		try {
			
			CatalogDataHandler handler = new CatalogDataHandler();
			
			XMLReader reader = XMLReaderFactory.createXMLReader();
			reader.setContentHandler(handler);
			reader.parse(XML_FILE_PATH);
			
			List<Equipment> equipments = handler.getEquipments();
			catalog.setEquipments(equipments);
			
		} catch (SAXException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return catalog;
	}

}
