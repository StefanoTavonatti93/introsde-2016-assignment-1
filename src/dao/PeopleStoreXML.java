package dao;

import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class PeopleStoreXML {

	private Document document;
	private XPath xPath;
	private final static String DOCUMENT_TO_PARSE="people.xml";
	
	/**
	 * 
	 * @throws ParserConfigurationException
	 * @throws SAXException
	 * @throws IOException
	 */
	public PeopleStoreXML() throws ParserConfigurationException, SAXException, IOException{
		DocumentBuilderFactory factory=DocumentBuilderFactory.newInstance();
		factory.setNamespaceAware(true);
		
		DocumentBuilder documentBuilder=factory.newDocumentBuilder();
		
		document=documentBuilder.parse(DOCUMENT_TO_PARSE);
		xPath=XPathFactory.newInstance().newXPath();	
	}
	
	/**
	 * Return the weight of the person identified by the parameter "personID"
	 * 
	 * @param personID the id of the person
	 * @return the weight of the person identified by the parameter "personID"
	 * @throws XPathExpressionException 
	 */
	public double getWeight(long personID) throws XPathExpressionException{
		XPathExpression exp=xPath.compile("/people/person[@id="+personID+"]/healthprofile/weight");
		Double weight= (Double) exp.evaluate(document, XPathConstants.NUMBER);
		return weight.doubleValue();
	}
	
	/**
	 * Return the height of the person identified by the parameter "personID"
	 * 
	 * @param personID the id of the person
	 * @return the height of the person identified by the parameter "personID"
	 * @throws XPathExpressionException 
	 */
	public double getHeight(long personID) throws XPathExpressionException{
		XPathExpression exp=xPath.compile("/people/person[@id="+personID+"]/healthprofile/height");
		Double height= (Double) exp.evaluate(document, XPathConstants.NUMBER);
		return height.doubleValue();
	}
	
	public NodeList getNodeByExpression(String exp) throws XPathExpressionException{
		XPathExpression e=xPath.compile(exp);
		Object list=e.evaluate(document,XPathConstants.NODESET);
		
		if(list instanceof NodeList)
			return (NodeList) list;
		else return null;
	}
	
	public String list(NodeList nList){
		
		String result="";
		
		if(nList==null)
			return result;
		
		for(int i=0;i<nList.getLength();i++){
			Node node=nList.item(i);
			
			if(node.getNodeName().equals("person"))
				result+="person [ID="+node.getAttributes().item(0).getNodeValue()+"]:";
			else if(node.getNodeType()==Node.ELEMENT_NODE)
				result+=""+node.getNodeName()+": ";
			else if(node.getNodeType()==Node.TEXT_NODE)
				result+=node.getNodeValue();
			
			//result+=node.getNodeName()+": "+node.getNodeValue();
			if(node.hasChildNodes()){
				result+=list(node.getChildNodes());
			}
			
			
		}
		
		return result;
	}
	
	public String list() throws XPathExpressionException{
		return list(getNodeByExpression("/people/person"));
	}

	
}
