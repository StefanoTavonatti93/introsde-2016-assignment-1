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
	 * return a NodeList which contains the nodes that satisfied the condition specified
	 * @param weight the value of weight that will be used in the condition
	 * @param operator the operator of the condition, one of: "<", "<=", ">", ">=", "="
	 * @return NodeList which contains the nodes that satisfied the condition specified
	 * @throws XPathExpressionException
	 */
	public NodeList getWeight(double weight, String operator) throws XPathExpressionException{
		
		/* check if the operator given in the parameter is a valid operator*/
		if(operator.equals(">") || operator.equals(">=") || operator.equals("=") || operator.equals("<") || operator.equals("<="))
			return getNodeByExpression("//person[healthprofile/weight"+operator+"'"+weight+"']");
		else
			return null;
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
	
	/**
	 *  return a NodeList which contains the nodes that satisfied the condition specified
	 * @param height the value of height that will be used in the condition
	 * @param operator the operator of the condition, one of: "<", "<=", ">", ">=", "="
	 * @return NodeList which contains the nodes that satisfied the condition specified
	 * @throws XPathExpressionException
	 */
	public NodeList getHeight(double height, String operator) throws XPathExpressionException{
		
		/* check if the operator given in the parameter is a valid operator*/
		if(operator.equals(">") || operator.equals(">=") || operator.equals("=") || operator.equals("<") || operator.equals("<="))
			return getNodeByExpression("//person[healthprofile/height"+operator+"'"+height+"']");
		else
			return null;
	}
	
	/**
	 * Get a node list by an XPATH expression
	 * @param exp the xepression that will be parsed
	 * @return a NodeList containing all nodes that satisied the given expression
	 * @throws XPathExpressionException
	 */
	public NodeList getNodeByExpression(String exp) throws XPathExpressionException{
		XPathExpression e=xPath.compile(exp);
		Object list=e.evaluate(document,XPathConstants.NODESET);
		
		if(list instanceof NodeList)
			return (NodeList) list;
		else return null;
	}
	
	/**
	 * Read the NodeList passed in paramiters an write it as a string
	 * @param nList
	 * @return
	 */
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
			
			
			if(node.hasChildNodes()){
				result+=list(node.getChildNodes());
			}
			
			
		}
		
		return result;
	}
	
	/**
	 * return a string that contains all node in the XML database
	 * @return
	 * @throws XPathExpressionException
	 */
	public String list() throws XPathExpressionException{
		return list(getNodeByExpression("/people/person"));
	}
	
	/**
	 * Print the HealtProfile of the person identified by the id in the parameters
	 * @param id Person ID
	 * @return a String containing the healtprofile of the person, this string is ready to print
	 * @throws XPathExpressionException
	 */
	public String getHealtProfile(long id) throws XPathExpressionException{
		return list(getNodeByExpression("//person[@id="+id+"]/healthprofile"));
	}

}
