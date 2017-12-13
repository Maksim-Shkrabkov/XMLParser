package by.htp.rent.dao.parser.dom;

//import static by.htp.rent.dao.parser.*;

import by.htp.rent.dao.CatalogData;
import by.htp.rent.dao.parser.DataTypeTransformUtil;
import by.htp.rent.domain.Catalog;
//import com.sun.org.apache.xerces.internal.parsers.*
import by.htp.rent.domain.Equipment;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.*;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class CatalogDataDomImpl implements CatalogData {

	@Override
	public Catalog readCatalog() {
		// TODO Auto-generated method stub
		final String XML_FILE_PATH = "resources/rent_station.xml";

		
		Catalog catalog = new Catalog();
		
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		
		try {
			DocumentBuilder db = dbf.newDocumentBuilder();
			try {
				Document document = db.parse(XML_FILE_PATH);
				parseDocument(document);
				
				List<Equipment> eq = parseDocument(document);
				catalog.setEquipments(eq);
				
			} catch (SAXException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
//		DOMParser parser = new DOMParser();
		
		return catalog;
	}
	
	private List<Equipment> parseDocument(Document document) {
		List<Equipment> list = new ArrayList<>();
		Element root = document.getDocumentElement();
		NodeList nodes = root.getElementsByTagName("equipment");
		Equipment equipment = null;
		
		for(int i = 0; i < nodes.getLength(); i++) {
			
			equipment = new Equipment();
			Element currentNode = (Element) nodes.item(i);
			
			//Извлекли id
			String id = currentNode.getAttribute("id");
			equipment.setId(DataTypeTransformUtil.convertID(id));
			
			Element element = getSingleChild(currentNode, "title");
			String title = element.getTextContent().trim();
			equipment.setTitle(title);
			
			element = getSingleChild(currentNode, "price");
			String price = element.getTextContent().trim();
			equipment.setPrice(DataTypeTransformUtil.convertPrice(price));
			
			element = getSingleChild(currentNode, "date");
			String date = element.getTextContent().trim();
			equipment.setDate(DataTypeTransformUtil.convertData(date));
			
			
			list.add(equipment);
		}
		
		return list;
	}
	
	
	private Element getSingleChild(Element node, String name){
		NodeList nodeList = node.getElementsByTagName(name);
		Element childElement = (Element) nodeList.item(0);
		return childElement;
	}

}
