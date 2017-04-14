package com.pay.util;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;

public class ParseXmlUtil {
	public static Document parse(String res) {
		Document doc = null;
		try {
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();  
			DocumentBuilder db = dbf.newDocumentBuilder();
			InputStream in_withcode = new ByteArrayInputStream(res.getBytes("UTF-8"));
			doc = db.parse(in_withcode);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return  doc;
	}

}
