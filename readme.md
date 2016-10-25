**Stefano Tavonatti**

#Introduction to Service Design and Engineering Course

#First Assignment

###1. Implementation

#####1.1 XPATH (Based on Lab3)

In this chapter will be discussed the implementation of the code for the task based on Lab3.

In order to execute this task the class **PeopleStoreXML** has been implemented. This class, in its constructor load and parse the **people.xml** file.

```java
 public PeopleStoreXML() throws ParserConfigurationException, SAXException, IOException{
		DocumentBuilderFactory factory=DocumentBuilderFactory.newInstance();
		factory.setNamespaceAware(true);
		
		DocumentBuilder documentBuilder=factory.newDocumentBuilder();
		
		document=documentBuilder.parse(DOCUMENT_TO_PARSE);
		xPath=XPathFactory.newInstance().newXPath();	
	}
```

This class exposes the method that allows the programmer to doing the following operations:
- list all persons int the XML file
- get the weight of a particular person
- get the height of a particular person
- get the *healt profile* of a particular person
- get the list of people that satisfied a particular constraint on weight (i.e. all people in wich the weight is less or equal at 75 Kg)
- get the list of people that satisfied a particular constraint on height.

-----

In order to list all person in the **people.xml** file the following function has been defined:

```java
public String list() throws XPathExpressionException{
		return list(getNodeByExpression("/people/person"));
	}
```

The *list()* function uses another two function to execute his task: *list(NodeList)* and *getNodeByExpression(String exp)*.


*getNodeByExpression(String exp)* takes as a parameter an XPATH expression and returns the node list which satisfies it.

```java
public NodeList getNodeByExpression(String exp) throws XPathExpressionException{
		//compile the XPATH expression
		XPathExpression e=xPath.compile(exp);
		
		//Evaluate the expression and return a node set
		Object list=e.evaluate(document,XPathConstants.NODESET);
		
		//check if the list object is realy a nodeset
		if(list instanceof NodeList)
			return (NodeList) list;
		else return null;
	}
```

The *list(NodeSet)* method takes as a parameter a NodeList and return a string which contains all content of the list, printed in a formatted way.
For each node in the list, this method prints: the name of the node, if the node is a *element node*, or the value of the node, if the node is a text node. Also, if the node have children it calls itself recursively on the children. Finnally it returns the formmated string which is ready to be printed.

```java
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
			
			//if the node has children, print also the children
			if(node.hasChildNodes()){
				result+=list(node.getChildNodes());
			}
			
			
		}
		
		return result;
	}
```

- In order to obtain the weight of a particular person the following method has been implemented:

```java
public double getWeight(long personID) throws XPathExpressionException{
		XPathExpression exp=xPath.compile("/people/person[@id="+personID+"]/healthprofile/weight");
		Double weight= (Double) exp.evaluate(document, XPathConstants.NUMBER);
		return weight.doubleValue();
	}
```

This method takes as a paramether the id of a person and returns the corresponding weight. In order to doing that it uses a XPATH expression so shaped: "**/people/person[@id="+personID+"]/healthprofile/weight**". This expression selects the person with the given ID an return its weight.

- To obtain the height of a person the following method as been implemented:

```java
public double getHeight(long personID) throws XPathExpressionException{
		XPathExpression exp=xPath.compile("/people/person[@id="+personID+"]/healthprofile/height");
		Double height= (Double) exp.evaluate(document, XPathConstants.NUMBER);
		return height.doubleValue();
	}
```

This method work in the same way as the *getHeight(Long ID)* method.

- In order to get the **health profile** of a particular person the following method as been implememted:

```java
public String getHealtProfile(long id) throws XPathExpressionException{
		return list(getNodeByExpression("//person[@id="+id+"]/healthprofile"));
	}
```

This method uses the *getNodeByExpression* mehtods and passes to it the XPATH expression shaped in this way: "**//person[@id="+id+"]/healthprofile**", where **id** is the person id passed in the parameters. This expression selects a person with a specific id and returns the corristonding healt profile. The result of the *getNodeByExpression* calls will be passes to the *list(NodeList)* function that read the node list and retun a formatted string. Finally the *getHealthProfile* function returns this formatted string.


- In order to obtain the list of people that satisfied a particular constraint on weight, the following methods has been implemented:

```java
public String getWeight(double weight, String operator) throws XPathExpressionException{
		
		/* check if the operator given in the parameter is a valid operator*/
		if(operator.equals(">") || operator.equals(">=") || operator.equals("=") || operator.equals("<") || operator.equals("<="))
			return list(getNodeByExpression("//person[healthprofile/weight"+operator+"'"+weight+"']"));
		else
			return null;
	}
```

This method takes as input a weight and an operator and returns the list of people that satisfies the costraint. First, this function sanitises the operator and finally it uses a XPATH which select the set of people that satisies the constraints.

- In order to get the list of people that satisfied a particular constraint on height, the following methods has been implemented:

```java
public String getHeight(double height, String operator) throws XPathExpressionException{
		
		/* check if the operator given in the parameter is a valid operator*/
		if(operator.equals(">") || operator.equals(">=") || operator.equals("=") || operator.equals("<") || operator.equals("<="))
			return list(getNodeByExpression("//person[healthprofile/height"+operator+"'"+height+"']"));
		else
			return null;
	}
```

This method works in the same way as *getWeight(double weight, String operator)*.


#####1.2 XML marshalling and un-marshalling (Based on Lab 4)


#####1.3 JSON (Based on Lab4)

#####2


#####3 Run the assignments


In order to compile the assigment use the following command:

```bash
ant compile
```

This task creates the build folder, donwloads the requested libbraries, generates the necessary class for the XML marshalling and un-marshalling and compile the code.

To run the assignment use the following command:

```bash
 ant execute.evaluation  
```
This task first execute the *compile* task and finally execute the code
