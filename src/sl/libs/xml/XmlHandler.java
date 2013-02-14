/*********************************
 * 20130214_140339
 * REF=> http://axbeak.blog60.fc2.com/blog-entry-287.html
 *********************************/
package sl.libs.xml;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import android.util.Log;

public class XmlHandler {
	 
//	private final String filename;
	private String filename;

	/**
	* �R���X�g���N�^
	* @param filename �Ǎ��t�@�C���p�X
	*/
	public XmlHandler(String filename) {
		this.filename = filename;
	}

	public XmlHandler() {
//		this.filename = filename;
	}

	/**
	* XML�t�@�C���̓��e��\������
	* @throws SAXException
	* @throws IOException
	* @throws ParserConfigurationException
	*/
	public void showXml() throws SAXException, IOException,
				ParserConfigurationException {
		Document doc = DocumentBuilderFactory.newInstance()
						.newDocumentBuilder().parse(new File(filename));
		showChildNodes(doc.getChildNodes(), 0);
	}

	public void
	showXml(InputStream is)
	throws SAXException, IOException, ParserConfigurationException {
		
		Document doc = DocumentBuilderFactory.newInstance()
					.newDocumentBuilder().parse(is);
		showChildNodes(doc.getChildNodes(), 0);
		
	}//showXml(InputStream is)

	public void
//	showXml(String xmlString)
	showXml(String uri)
	throws SAXException, IOException, ParserConfigurationException {
		
		// Log
		Log.d("XmlHandler.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ ":"
				+ Thread.currentThread().getStackTrace()[2].getMethodName()
				+ "]", "Entering...");
		
		Document doc = DocumentBuilderFactory.newInstance()
//					.newDocumentBuilder().parse(xmlString);
					.newDocumentBuilder().parse(uri);
		showChildNodes(doc.getChildNodes(), 0);
		
		// Log
		Log.d("XmlHandler.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ ":"
				+ Thread.currentThread().getStackTrace()[2].getMethodName()
				+ "]", "Exiting...");
		
	}//showXml(InputStream is)

	/**
	* XML�̗v�f�����o���\������
	* @param nodeList
	* @param level
	*/
	private void showChildNodes(NodeList nodeList, int level) {
		if (nodeList == null) {
				return;
		}

		for (int i = 0; i < nodeList.getLength(); i++) {
				Node node = nodeList.item(i);
				if (node.getNodeType() == Node.TEXT_NODE) {
						String text = node.getNodeValue().trim();
						if (!text.isEmpty()) {
//								System.out.println(String.format("%sText:%s",
//												indent(level), text));
								
								String message = String.format("%sText:%s",
										indent(level), text);
								// Log
								Log.d("XmlHandler.java"
										+ "["
										+ Thread.currentThread()
												.getStackTrace()[2]
												.getLineNumber()
										+ ":"
										+ Thread.currentThread()
												.getStackTrace()[2]
												.getMethodName() + "]",
										message);
						}
						continue;
				}
				if (!(node instanceof Element)) {
						continue;
				}

						Element element = (Element) node;
						String tagName = element.getTagName();
						String attributes = getAttributeStrings(element.getAttributes());
//						System.out.println(String.format(
//										"%sTagName:%s, Attributes:[%s]", indent(level), tagName,
//										attributes));
						String message = String.format(
								"%sTagName:%s, Attributes:[%s]", indent(level), tagName,
								attributes);
						// Log
						Log.d("XmlHandler.java"
								+ "["
								+ Thread.currentThread()
										.getStackTrace()[2]
										.getLineNumber()
								+ ":"
								+ Thread.currentThread()
										.getStackTrace()[2]
										.getMethodName() + "]",
								message);


						showChildNodes(element.getChildNodes(), level + 1);
		}//for (int i = 0; i < nodeList.getLength(); i++)
		 
	}//private void showChildNodes(NodeList nodeList, int level)

			/**
	* Attributes�̓��e�𕶎���Ŏ擾
	* @param attributes
	* @return Attributes�̕�����
	*/
	private String getAttributeStrings(NamedNodeMap attributes) {
		if (attributes == null) {
				return "";
				}

				StringBuffer sb = new StringBuffer();
				for (int i = 0; i < attributes.getLength(); i++) {
						Node node = attributes.item(i);
						String nodeName = node.getNodeName();
						String nodeValue = node.getNodeValue();
						sb.append(nodeName).append("=").append(nodeValue);

						if (i < attributes.getLength() - 1) {
								sb.append(", ");
						}
				}

				return sb.toString();
				 
	}//private String getAttributeStrings(NamedNodeMap attributes)

	/**
	* �C���f���g�쐬
	* @param level
	* @return �C���f���g������
	*/
	public static String indent(int level) {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < level; i++) {
			sb.append('\t');
		}
		return sb.toString();

	}//public static String indent(int level)
	
}//public class XmlHandler
