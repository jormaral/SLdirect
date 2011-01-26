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

import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 *
 * @since  26/10/2010
 */
public class XmlUtil {
	public static Element addElement(Document document, Element parentElement, String elementName){
		Element childElement = document.createElement(elementName);
		parentElement.appendChild(childElement);
		return childElement;
	}
	
	public static Element addElement(Document document, Element parentElement, String elementName, String elementValue){
		Element childElement = addElement(document, parentElement, elementName);
		childElement.setTextContent(elementValue);
		return childElement;
	}
}
