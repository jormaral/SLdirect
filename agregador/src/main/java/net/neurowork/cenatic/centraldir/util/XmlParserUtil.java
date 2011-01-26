/*
 * Copyright 2010 CENATIC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package net.neurowork.cenatic.centraldir.util;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 *
 * @since  26/10/2010
 */
public class XmlParserUtil {
	public static String getStringNodeValue(Node node, String nodeName) {
        Element fstElmnt = (Element) node;	
		NodeList fstNmElmntLst = fstElmnt.getElementsByTagName(nodeName);
		
		if(fstNmElmntLst.getLength() == 0)
			return "";
		
		Element fstNmElmnt = (Element) fstNmElmntLst.item(0);
		NodeList fstNm = fstNmElmnt.getChildNodes();
		
		if(fstNm.getLength() > 0){
			String retValue = ((Node) fstNm.item(0)).getNodeValue();
			try {
				return new String(retValue.getBytes("ISO-8859-1"));
			} catch (UnsupportedEncodingException e) {
			}
		}
		return "";
	}	
	
	public static int getIntegerNodeValue(Node node, String nodeName) {
		return Integer.parseInt(getStringNodeValue(node, nodeName));
	}

	public static boolean hasChildren(Element element, String childName) {
		NodeList ndLs = element.getElementsByTagName(childName);
		if(ndLs == null)
			return false;
		if(ndLs.getLength() > 0)
			return true;
		return false;
	}	
	
	public static Element getChildren(Element element, String childName) {
		NodeList ndLs = element.getElementsByTagName(childName);
		return (Element)ndLs.item(0);
	}

	public static long getLongNodeValue(Node node, String nodeName) {
		return Long.parseLong(getStringNodeValue(node, nodeName));
	}
	
	public static String[] getNodesValue(Element node, String nodeName) {
		List<String> ret = new ArrayList<String>();  
		
		NodeList ndLs = node.getElementsByTagName(nodeName);
		for (int s = 0; s < ndLs.getLength(); s++) {
		    Node fstNode = ndLs.item(s);
	        if (fstNode.getNodeType() == Node.ELEMENT_NODE) {
	        	ret.add(fstNode.getTextContent());
	        }
		}
		
		return ret.toArray(new String[0]);
	}
	
	public static String getAttribute(Element element, String attrName) {
		return element.getAttribute(attrName); 
	}	
	
	public static Document createDocumentFromString(String xmlString) throws ParserConfigurationException, SAXException, IOException{
		DocumentBuilder parser = DocumentBuilderFactory.newInstance().newDocumentBuilder();
		// Create the Input Stream
		ByteArrayInputStream byteIs = new ByteArrayInputStream(xmlString.getBytes());
		// parse the XML purely as XML and get a DOM tree representation.			
		Document doc = parser.parse(byteIs);
		return doc;
	}

	public static float getFloatNodeValue(Node node, String nodeName) {
		return Float.parseFloat(getStringNodeValue(node, nodeName));
	}	
}
