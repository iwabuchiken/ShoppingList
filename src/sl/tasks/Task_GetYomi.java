package sl.tasks;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;

import org.apache.http.HttpEntity;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import sl.libs.xml.XmlHandler;
import sl.libs.xml.domsample.DomSample;
import sl.utils.CONS;
import sl.utils.DBUtils;
import sl.utils.Methods;
import sl.utils.Methods_sl;
import android.R;
import android.app.Activity;
import android.app.Dialog;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

public class Task_GetYomi extends AsyncTask<String, Integer, Integer> {

	static Activity actv;
	
	Dialog dlg;
	
	public Task_GetYomi(Activity actv) {
		// TODO Auto-generated constructor stub
		this.actv = actv;
	}

	public Task_GetYomi(Activity actv, Dialog dlg) {
		// TODO Auto-generated constructor stub
		this.actv = actv;
		
		this.dlg = dlg;
		
	}

	@Override
	protected Integer doInBackground(String... arg0) {
		// TODO Auto-generated method stub
		
//		int res = Task_GetYomi.doInBackground__1();
//		Task_GetYomi.doInBackground__1();
//		Task_GetYomi.doInBackground__2();
		
//		int res = Methods_sl.getYomi(actv, dlg);
		
		/*********************************
		 * XmlHandler
		 *********************************/
//		Task_GetYomi.doInBackground__2__1__XmlHandler();
		
//		Task_GetYomi.doInBackground_B18_v_2_0_d();
		
//		Task_GetYomi.doInBackground_B18_v_2_0_d_t_2();
//		Task_GetYomi.doInBackground_B18_v_2_0_d_t_3();
//		Task_GetYomi.doInBackground_B18_v_2_0_d_t_4();
//		Task_GetYomi.doInBackground_B18_v_2_0_d_t_5();
//		Task_GetYomi.doInBackground_B18_v_2_0_d_t_6();
		Task_GetYomi.doInBackground_B18_v_2_0_d_t_7();
		
		
		return CONS.GETYOMI_FAILED;
//		return res;
		
//		return null;
	}

	private static void doInBackground_B18_v_2_0_d_t_2() {
		// TODO Auto-generated method stub
		String kw = "����ԁi���j";
		
		String url = "http://jlp.yahooapis.jp/FuriganaService/V1/furigana" +
				"?appid=dj0zaiZpPTZjQWNRNExhd0thayZkPVlXazlhR2gwTTJGUE56SW1jR285TUEtLSZzPWNvbnN1bWVyc2VjcmV0Jng9Mjc-" +
				"&grade=1" +
				"&sentence=" + kw;

		XmlHandler xh = new XmlHandler();
		
		Document doc = xh.getDoc(url);
		
		String tagName = "Word";
		
		NodeList nlWord = doc.getElementsByTagName(tagName);

		/*********************************
		 * Word node
		 *********************************/
		Node nWord1 = nlWord.item(0);
		
		if (nWord1.getNodeType() == Node.ELEMENT_NODE) {
			
			// Log
			Log.d("Task_GetYomi.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ ":"
					+ Thread.currentThread().getStackTrace()[2].getMethodName()
					+ "]", "nWord1 => Element node");
		} else {
			
			// Log
			Log.d("Task_GetYomi.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ ":"
					+ Thread.currentThread().getStackTrace()[2].getMethodName()
					+ "]", "nNode => Not an element node");
			
			return;
		}

			//=> [98:doInBackground_B18_v_2_0_d_t_2](23709): nWord1 => Element node
		
		/*********************************
		 * Node list of nWord1
		 *********************************/
		NodeList nlWord1Surface = ((Element) nWord1).getElementsByTagName("Surface");
		
		// Log
		Log.d("Task_GetYomi.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ ":"
				+ Thread.currentThread().getStackTrace()[2].getMethodName()
				+ "]",
				"nlWord1Surface.getLength()=" + nlWord1Surface.getLength());

			//=> [123:doInBackground_B18_v_2_0_d_t_2](23876): nlWord1Surface.getLength()=1

		/*********************************
		 * Surface
		 *********************************/
		Node nWord1Surface = nlWord1Surface.item(0);
		
		/*********************************
		 * Children of Surface
		 *********************************/
		NodeList nlChildrenOfSurface = nWord1Surface.getChildNodes();
		
		for (int i = 0; i < nlChildrenOfSurface.getLength(); i++) {
			
			Node n = nlChildrenOfSurface.item(i);
			
			if (n.getNodeType() == Node.TEXT_NODE) {
				
				// Log
				Log.d("Task_GetYomi.java"
						+ "["
						+ Thread.currentThread().getStackTrace()[2]
								.getLineNumber()
						+ ":"
						+ Thread.currentThread().getStackTrace()[2]
								.getMethodName() + "]",
						"name=" + n.getNodeName()
						+ "/"
						+ "value=" + n.getNodeValue());

			} else {//if (n.getNodeType() == Node.TEXT_NODE)
				
				// Log
				Log.d("Task_GetYomi.java"
						+ "["
						+ Thread.currentThread().getStackTrace()[2]
								.getLineNumber()
						+ ":"
						+ Thread.currentThread().getStackTrace()[2]
								.getMethodName() + "]",
						"name=" + n.getNodeName());
				
			}//if (n.getNodeType() == Node.TEXT_NODE)
			
			
		}//for (int i = 0; i < nlChildrenOfSurface.getLength(); i++)
		
			//=> [150:doInBackground_B18_v_2_0_d_t_2](24081): name=#text/value=?


		/*********************************
		 * Try again
		 *********************************/
		Node nChildrenOfSurface = nlChildrenOfSurface.item(0);
		
		for (int i = 0; i < nlChildrenOfSurface.getLength(); i++) {
			
			Node n = nlChildrenOfSurface.item(i);
			
			// Log
			Log.d("Task_GetYomi.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ ":"
					+ Thread.currentThread().getStackTrace()[2].getMethodName()
					+ "]", "nlChildrenOfSurface.item(" + i + ")");
			
			// Log
			Log.d("Task_GetYomi.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ ":"
					+ Thread.currentThread().getStackTrace()[2].getMethodName()
					+ "]",
					(i+1) + ": Name=" + n.getNodeName()
					+ "/"
					+ "Type=" + n.getNodeType()
					+ "/"
					+ "Value=" + n.getNodeValue()
					);
			
				//=> [197:doInBackground_B18_v_2_0_d_t_2](24410): 1: Name=#text/Type=3/Value=?

			
			NodeList nl = n.getChildNodes();
			
			// Log
			Log.d("Task_GetYomi.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ ":"
					+ Thread.currentThread().getStackTrace()[2].getMethodName()
					+ "]", "n.getChildNodes().getLength()" + nl.getLength());
			
			Element elem = (Element) n;
			
			// Log
			Log.d("Task_GetYomi.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ ":"
					+ Thread.currentThread().getStackTrace()[2].getMethodName()
					+ "]", "elem...");
			// Log
			Log.d("Task_GetYomi.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ ":"
					+ Thread.currentThread().getStackTrace()[2].getMethodName()
					+ "]",
					(i+1) + ": Name=" + elem.getNodeName()
					+ "/"
					+ "Type=" + elem.getNodeType()
					+ "/"
					+ "Value=" + elem.getNodeValue()
					);
			
				//=> [197:doInBackground_B18_v_2_0_d_t_2](24410): 1: Name=#text/Type=3/Value=?
			
		}//for (int i = 0; i < nlChildrenOfSurface.getLength(); i++)
		
		
//		NodeList nlChildrenOfSurface2 = nChildrenOfSurface.getChildNodes();
		
		
		
	}//private static void doInBackground_B18_v_2_0_d_t_2()

	private static void doInBackground_B18_v_2_0_d_t_3() {
		// TODO Auto-generated method stub
		String kw = "����ԁi���j";
		
		String url = "http://jlp.yahooapis.jp/FuriganaService/V1/furigana" +
				"?appid=dj0zaiZpPTZjQWNRNExhd0thayZkPVlXazlhR2gwTTJGUE56SW1jR285TUEtLSZzPWNvbnN1bWVyc2VjcmV0Jng9Mjc-" +
				"&grade=1" +
				"&sentence=" + kw;

		XmlHandler xh = new XmlHandler();
		
		Document doc = xh.getDoc(url);
		
		String tagName = "Word";
		
		NodeList nlWord = doc.getElementsByTagName(tagName);

		/*********************************
		 * Word node
		 *********************************/
		Node nWord1 = nlWord.item(0);
		
//		if (nWord1.getNodeType() == Node.ELEMENT_NODE) {
//			
//			// Log
//			Log.d("Task_GetYomi.java" + "["
//					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
//					+ ":"
//					+ Thread.currentThread().getStackTrace()[2].getMethodName()
//					+ "]", "nWord1 => Element node");
//		} else {
//			
//			// Log
//			Log.d("Task_GetYomi.java" + "["
//					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
//					+ ":"
//					+ Thread.currentThread().getStackTrace()[2].getMethodName()
//					+ "]", "nNode => Not an element node");
//			
//			return;
//		}
//
//			//=> [98:doInBackground_B18_v_2_0_d_t_2](23709): nWord1 => Element node
		
		/*********************************
		 * Node list of nWord1
		 *********************************/
//		NodeList nlWord1Surface = ((Element) nWord1).getElementsByTagName("Surface");
//		
//		// Log
//		Log.d("Task_GetYomi.java" + "["
//				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
//				+ ":"
//				+ Thread.currentThread().getStackTrace()[2].getMethodName()
//				+ "]",
//				"nlWord1Surface.getLength()=" + nlWord1Surface.getLength());
//
//			//=> [123:doInBackground_B18_v_2_0_d_t_2](23876): nlWord1Surface.getLength()=1

		/*********************************
		 * Surface
		 *********************************/
//		Node nWord1Surface = nlWord1Surface.item(0);
		
//		/*********************************
//		 * Children of Surface
//		 *********************************/
//		NodeList nlChildrenOfSurface = nWord1Surface.getChildNodes();
//		
//		for (int i = 0; i < nlChildrenOfSurface.getLength(); i++) {
//			
//			Node n = nlChildrenOfSurface.item(i);
//			
//			if (n.getNodeType() == Node.TEXT_NODE) {
//				
//				// Log
//				Log.d("Task_GetYomi.java"
//						+ "["
//						+ Thread.currentThread().getStackTrace()[2]
//								.getLineNumber()
//						+ ":"
//						+ Thread.currentThread().getStackTrace()[2]
//								.getMethodName() + "]",
//						"name=" + n.getNodeName()
//						+ "/"
//						+ "value=" + n.getNodeValue());
//
//			} else {//if (n.getNodeType() == Node.TEXT_NODE)
//				
//				// Log
//				Log.d("Task_GetYomi.java"
//						+ "["
//						+ Thread.currentThread().getStackTrace()[2]
//								.getLineNumber()
//						+ ":"
//						+ Thread.currentThread().getStackTrace()[2]
//								.getMethodName() + "]",
//						"name=" + n.getNodeName());
//				
//			}//if (n.getNodeType() == Node.TEXT_NODE)
//			
//			
//		}//for (int i = 0; i < nlChildrenOfSurface.getLength(); i++)
//		
//			//=> [150:doInBackground_B18_v_2_0_d_t_2](24081): name=#text/value=?


//		/*********************************
//		 * Try again
//		 *********************************/
//		Node nChildrenOfSurface = nlChildrenOfSurface.item(0);
//		
//		for (int i = 0; i < nlChildrenOfSurface.getLength(); i++) {
//			
//			Node n = nlChildrenOfSurface.item(i);
//			// Log
//			Log.d("Task_GetYomi.java" + "["
//					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
//					+ ":"
//					+ Thread.currentThread().getStackTrace()[2].getMethodName()
//					+ "]", "n=" + n.getClass().getName());
//			
//			// Log
//			Log.d("Task_GetYomi.java" + "["
//					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
//					+ ":"
//					+ Thread.currentThread().getStackTrace()[2].getMethodName()
//					+ "]", "nlChildrenOfSurface.item(" + i + ")");
//			
//			// Log
//			Log.d("Task_GetYomi.java" + "["
//					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
//					+ ":"
//					+ Thread.currentThread().getStackTrace()[2].getMethodName()
//					+ "]",
//					(i+1) + ": Name=" + n.getNodeName()
//					+ "/"
//					+ "Type=" + n.getNodeType()
//					+ "/"
//					+ "Value=" + n.getNodeValue()
//					);
//			
//				//=> [197:doInBackground_B18_v_2_0_d_t_2](24410): 1: Name=#text/Type=3/Value=?
//
//			
//			NodeList nl = n.getChildNodes();
//			
//			// Log
//			Log.d("Task_GetYomi.java" + "["
//					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
//					+ ":"
//					+ Thread.currentThread().getStackTrace()[2].getMethodName()
//					+ "]", "n.getChildNodes().getLength()" + nl.getLength());
//			
//			if (n.getNodeType() == Node.ELEMENT_NODE) {
//				
//				Element elem = (Element) n;
//				
//				// Log
//				Log.d("Task_GetYomi.java" + "["
//						+ Thread.currentThread().getStackTrace()[2].getLineNumber()
//						+ ":"
//						+ Thread.currentThread().getStackTrace()[2].getMethodName()
//						+ "]",
//						(i+1) + ": Name=" + n.getNodeName()
//						+ "/"
//						+ "Type=" + n.getNodeType()
//						+ "/"
//						+ "Value=" + n.getNodeValue()
//						);
//				
//				
//			} else {//if (n.getNodeType() == Node.ELEMENT_NODE)
//
//				// Log
//				Log.d("Task_GetYomi.java"
//						+ "["
//						+ Thread.currentThread().getStackTrace()[2]
//								.getLineNumber()
//						+ ":"
//						+ Thread.currentThread().getStackTrace()[2]
//								.getMethodName() + "]",
//						"Not an element node: " + n.getNodeType());
//				
//			}//if (n.getNodeType() == Node.ELEMENT_NODE)
//			
//				//=> [430:doInBackground_B18_v_2_0_d_t_3](1117): Not an element node: 3
//			
//
//			
////			Element elem = (Element) n;
////			
////				//=> java.lang.ClassCastException: org.apache.harmony.xml.dom.TextImpl
////
////			
////			// Log
////			Log.d("Task_GetYomi.java" + "["
////					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
////					+ ":"
////					+ Thread.currentThread().getStackTrace()[2].getMethodName()
////					+ "]", "elem...");
////			// Log
////			Log.d("Task_GetYomi.java" + "["
////					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
////					+ ":"
////					+ Thread.currentThread().getStackTrace()[2].getMethodName()
////					+ "]",
////					(i+1) + ": Name=" + elem.getNodeName()
////					+ "/"
////					+ "Type=" + elem.getNodeType()
////					+ "/"
////					+ "Value=" + elem.getNodeValue()
////					);
//			
//				//=> [197:doInBackground_B18_v_2_0_d_t_2](24410): 1: Name=#text/Type=3/Value=?
//			
//		}//for (int i = 0; i < nlChildrenOfSurface.getLength(); i++)
		
		
//		NodeList nlChildrenOfSurface2 = nChildrenOfSurface.getChildNodes();

//		/*********************************
//		 * Surface node
//		 *********************************/
//		if (nWord1Surface.getNodeType() == Node.ELEMENT_NODE) {
//			
//			// Log
//			Log.d("Task_GetYomi.java"
//					+ "["
//					+ Thread.currentThread().getStackTrace()[2]
//							.getLineNumber()
//					+ ":"
//					+ Thread.currentThread().getStackTrace()[2]
//							.getMethodName() + "]",
//					"nWord1Surface => Element node");
//			
//		} else {//if (nWord1Surface.getNodeType() == Node.ELEMENT_NODE)
//			
//			// Log
//			Log.d("Task_GetYomi.java"
//					+ "["
//					+ Thread.currentThread().getStackTrace()[2]
//							.getLineNumber()
//					+ ":"
//					+ Thread.currentThread().getStackTrace()[2]
//							.getMethodName() + "]",
//					"nWord1Surface => Not an element node");
//			
//		}//if (nWord1Surface.getNodeType() == Node.ELEMENT_NODE)
//		
//			//=> [449:doInBackground_B18_v_2_0_d_t_3](1634): nWord1Surface => Element node
//		
//		Element eSurface = (Element) nWord1Surface;
//		
//		NodeList nlChildrenOfSurface = eSurface.getChildNodes();
//
//		for (int i = 0; i < nlChildrenOfSurface.getLength(); i++) {
//			
//			Node n = nlChildrenOfSurface.item(0);
//			
//			// Log
//			Log.d("Task_GetYomi.java" + "["
//					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
//					+ ":"
//					+ Thread.currentThread().getStackTrace()[2].getMethodName()
//					+ "]",
//					(i+1) + ": Name=" + n.getNodeName()
//					+ "/"
//					+ "Type=" + n.getNodeType()
//					+ "/"
//					+ "Value=" + n.getNodeValue()
//					);
//			
//		}//for (int i = 0; i < nlChildrenOfSurface.getLength(); i++)
//
//			//=> [515:doInBackground_B18_v_2_0_d_t_3](2417): 1: Name=#text/Type=3/Value=?

		/*********************************
		 * Node.getFirstChild()�@���g�����@
		 *********************************/
		Node n1 = nWord1.getFirstChild();
		
		// Log
		Log.d("Task_GetYomi.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ ":"
				+ Thread.currentThread().getStackTrace()[2].getMethodName()
				+ "]", "Node n1 = nWord1.getFirstChild()");

		// Log
		Log.d("Task_GetYomi.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ ":"
				+ Thread.currentThread().getStackTrace()[2].getMethodName()
				+ "]",
				": Name=" + nWord1.getNodeName()
				+ "/"
				+ "Type=" + nWord1.getNodeType()
				+ "/"
				+ "Value=" + nWord1.getNodeValue());
		
		// Log
		Log.d("Task_GetYomi.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ ":"
				+ Thread.currentThread().getStackTrace()[2].getMethodName()
				+ "]",
				": Name=" + n1.getNodeName()
				+ "/"
				+ "Type=" + n1.getNodeType()
				+ "/"
				+ "Value=" + n1.getNodeValue());
		
//			[537:doInBackground_B18_v_2_0_d_t_3](3414): Node n1 = nWord1.getFirstChild()
//			[544:doInBackground_B18_v_2_0_d_t_3](3414): : Name=Word/Type=1/Value=null
//			[556:doInBackground_B18_v_2_0_d_t_3](3414): : Name=#text/Type=3/Value=
//			[556:doInBackground_B18_v_2_0_d_t_3](3414):         

		
		
	}//private static void doInBackground_B18_v_2_0_d_t_3()

	private static void doInBackground_B18_v_2_0_d_t_4() {
		// TODO Auto-generated method stub
		String kw = "����ԁi���j";
		
		String url = "http://jlp.yahooapis.jp/FuriganaService/V1/furigana" +
				"?appid=dj0zaiZpPTZjQWNRNExhd0thayZkPVlXazlhR2gwTTJGUE56SW1jR285TUEtLSZzPWNvbnN1bWVyc2VjcmV0Jng9Mjc-" +
				"&grade=1" +
				"&sentence=";

//		DomSample ds = new DomSample(url, kw);
		DomSample ds = DomSample.getDomSampleFromUri(url, kw);
		
		// Log
		Log.d("Task_GetYomi.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ ":"
				+ Thread.currentThread().getStackTrace()[2].getMethodName()
				+ "]", "Starting... => walk");
		
		ds.walkThrough();
		
	}//private static void doInBackground_B18_v_2_0_d_t_3()

	private static void doInBackground_B18_v_2_0_d_t_5() {
		// TODO Auto-generated method stub
		String kw = "����ԁi���j";
		
		String url = "http://jlp.yahooapis.jp/FuriganaService/V1/furigana" +
				"?appid=dj0zaiZpPTZjQWNRNExhd0thayZkPVlXazlhR2gwTTJGUE56SW1jR285TUEtLSZzPWNvbnN1bWVyc2VjcmV0Jng9Mjc-" +
				"&grade=1" +
				"&sentence=";

//		DomSample ds = new DomSample(url, kw);
		DomSample ds = DomSample.getDomSampleFromUri(url, kw);
		
		// Log
		Log.d("Task_GetYomi.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ ":"
				+ Thread.currentThread().getStackTrace()[2].getMethodName()
				+ "]", "Starting... => walk");
		
		ds.walkThrough();
		
	}//private static void doInBackground_B18_v_2_0_d_t_3()

	private static void doInBackground_B18_v_2_0_d_t_6() {
		// TODO Auto-generated method stub
		String kw = "����ԁi���j";
		
		String url = "http://jlp.yahooapis.jp/FuriganaService/V1/furigana" +
				"?appid=dj0zaiZpPTZjQWNRNExhd0thayZkPVlXazlhR2gwTTJGUE56SW1jR285TUEtLSZzPWNvbnN1bWVyc2VjcmV0Jng9Mjc-" +
				"&grade=1" +
				"&sentence=" + kw;

		XmlHandler xh = new XmlHandler();
		
		Document doc = xh.getDoc(url);
		
		Element root = doc.getDocumentElement();
		
//		// Log
//		Log.d("Task_GetYomi.java" + "["
//				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
//				+ ":"
//				+ Thread.currentThread().getStackTrace()[2].getMethodName()
//				+ "]", "root.getNodeType()=" + root.getNodeType());
		
		/*********************************
		 * Surfaces
		 *********************************/
		NodeList surfaces = root.getElementsByTagName("Surface");
		
//		// Log
//		Log.d("Task_GetYomi.java" + "["
//				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
//				+ ":"
//				+ Thread.currentThread().getStackTrace()[2].getMethodName()
//				+ "]", "surfaces.getLength()=" + surfaces.getLength());
		
//			[643:doInBackground_B18_v_2_0_d_t_6](17459): root.getNodeType()=1
//			[652:doInBackground_B18_v_2_0_d_t_6](17459): surfaces.getLength()=6

		Element el_Surface = (Element) surfaces.item(0);
		
		String s = el_Surface.getChildNodes().item(0).getNodeValue();
		
//		// Log
//		Log.d("Task_GetYomi.java" + "["
//				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
//				+ ":"
//				+ Thread.currentThread().getStackTrace()[2].getMethodName()
//				+ "]", "s=" + s);

		String s2 = el_Surface.getFirstChild().getNodeValue();
		
//		// Log
//		Log.d("Task_GetYomi.java" + "["
//				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
//				+ ":"
//				+ Thread.currentThread().getStackTrace()[2].getMethodName()
//				+ "]", "s2=" + s2);
		
//			[669:doInBackground_B18_v_2_0_d_t_6](17706): s=?
//			[678:doInBackground_B18_v_2_0_d_t_6](17706): s2=?

		/*********************************
		 * TRY: http://www.anddev.org/parse_xml_with_dom_-_getnodevalue_always_null-t3082.html
		 * 	=> by ExxKA �� Sun Nov 08, 2009 5:05 pm
		 *********************************/
		Node childOfSurface = el_Surface.getFirstChild();
		
//		// Log
//		Log.d("Task_GetYomi.java" + "["
//				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
//				+ ":"
//				+ Thread.currentThread().getStackTrace()[2].getMethodName()
//				+ "]", "childOfSurface.getNodeType()=" + childOfSurface.getNodeType());
		
//			=> [694:doInBackground_B18_v_2_0_d_t_6](17995): childOfSurface.getNodeType()=3
		
		String val = childOfSurface.getNodeValue();
		
//		// Log
//		Log.d("Task_GetYomi.java" + "["
//				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
//				+ ":"
//				+ Thread.currentThread().getStackTrace()[2].getMethodName()
//				+ "]", "childOfSurface.getNodeValue()=" + val);
		
//			=> [705:doInBackground_B18_v_2_0_d_t_6](18118): childOfSurface.getNodeValue()=?

//		// Log
//		Log.d("Task_GetYomi.java" + "["
//				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
//				+ ":"
//				+ Thread.currentThread().getStackTrace()[2].getMethodName()
//				+ "]", "val.equals(\"\")=" + (val.equals(""))
//				+ "/"
//				+ "(val == null)=" + (val == null));
		
//			=> [714:doInBackground_B18_v_2_0_d_t_6](18244): val.equals("")=false/(val == null)=false

		// Log
		Log.d("Task_GetYomi.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ ":"
				+ Thread.currentThread().getStackTrace()[2].getMethodName()
				+ "]", "val.getClass().getName()=" + val.getClass().getName());
		
		// Log
		Log.d("Task_GetYomi.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ ":"
				+ Thread.currentThread().getStackTrace()[2].getMethodName()
				+ "]", "val.length()=" + val.length());

	}//private static void doInBackground_B18_v_2_0_d_t_3()

	private static void doInBackground_B18_v_2_0_d_t_7() {
		// TODO Auto-generated method stub
		String kw = "����ԁi���j";
		
		String url = "http://jlp.yahooapis.jp/FuriganaService/V1/furigana" +
				"?appid=dj0zaiZpPTZjQWNRNExhd0thayZkPVlXazlhR2gwTTJGUE56SW1jR285TUEtLSZzPWNvbnN1bWVyc2VjcmV0Jng9Mjc-" +
				"&grade=1" +
				"&sentence=" + kw;

		XmlHandler xh = new XmlHandler();
		
		Document doc = xh.getDoc(url);
		
		Element root = doc.getDocumentElement();
		
//		// Log
//		Log.d("Task_GetYomi.java" + "["
//				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
//				+ ":"
//				+ Thread.currentThread().getStackTrace()[2].getMethodName()
//				+ "]", "root.getNodeType()=" + root.getNodeType());
		
		/*********************************
		 * Surfaces
		 *********************************/
		NodeList surfaces = root.getElementsByTagName("Surface");
		
//		// Log
//		Log.d("Task_GetYomi.java" + "["
//				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
//				+ ":"
//				+ Thread.currentThread().getStackTrace()[2].getMethodName()
//				+ "]", "surfaces.getLength()=" + surfaces.getLength());
		
//			[643:doInBackground_B18_v_2_0_d_t_6](17459): root.getNodeType()=1
//			[652:doInBackground_B18_v_2_0_d_t_6](17459): surfaces.getLength()=6

		Element el_Surface = (Element) surfaces.item(0);
		
		String s = el_Surface.getChildNodes().item(0).getNodeValue();
		
//		// Log
//		Log.d("Task_GetYomi.java" + "["
//				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
//				+ ":"
//				+ Thread.currentThread().getStackTrace()[2].getMethodName()
//				+ "]", "s=" + s);

		String s2 = el_Surface.getFirstChild().getNodeValue();
		
//		// Log
//		Log.d("Task_GetYomi.java" + "["
//				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
//				+ ":"
//				+ Thread.currentThread().getStackTrace()[2].getMethodName()
//				+ "]", "s2=" + s2);
		
//			[669:doInBackground_B18_v_2_0_d_t_6](17706): s=?
//			[678:doInBackground_B18_v_2_0_d_t_6](17706): s2=?

		/*********************************
		 * TRY: http://www.anddev.org/parse_xml_with_dom_-_getnodevalue_always_null-t3082.html
		 * 	=> by ExxKA �� Sun Nov 08, 2009 5:05 pm
		 *********************************/
		Node childOfSurface = el_Surface.getFirstChild();
		
//		// Log
//		Log.d("Task_GetYomi.java" + "["
//				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
//				+ ":"
//				+ Thread.currentThread().getStackTrace()[2].getMethodName()
//				+ "]", "childOfSurface.getNodeType()=" + childOfSurface.getNodeType());
		
//			=> [694:doInBackground_B18_v_2_0_d_t_6](17995): childOfSurface.getNodeType()=3
		
		String val = childOfSurface.getNodeValue();
		
//		// Log
//		Log.d("Task_GetYomi.java" + "["
//				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
//				+ ":"
//				+ Thread.currentThread().getStackTrace()[2].getMethodName()
//				+ "]", "childOfSurface.getNodeValue()=" + val);
		
//			=> [705:doInBackground_B18_v_2_0_d_t_6](18118): childOfSurface.getNodeValue()=?

//		// Log
//		Log.d("Task_GetYomi.java" + "["
//				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
//				+ ":"
//				+ Thread.currentThread().getStackTrace()[2].getMethodName()
//				+ "]", "val.equals(\"\")=" + (val.equals(""))
//				+ "/"
//				+ "(val == null)=" + (val == null));
		
//			=> [714:doInBackground_B18_v_2_0_d_t_6](18244): val.equals("")=false/(val == null)=false

		// Log
//		Log.d("Task_GetYomi.java" + "["
//				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
//				+ ":"
//				+ Thread.currentThread().getStackTrace()[2].getMethodName()
//				+ "]", "val.getClass().getName()=" + val.getClass().getName());
//		
//		// Log
//		Log.d("Task_GetYomi.java" + "["
//				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
//				+ ":"
//				+ Thread.currentThread().getStackTrace()[2].getMethodName()
//				+ "]", "val.length()=" + val.length());
//
//		// Log
//		Log.d("Task_GetYomi.java" + "["
//				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
//				+ ":"
//				+ Thread.currentThread().getStackTrace()[2].getMethodName()
//				+ "]",
//				"childOfSurface.getClass().getName()=" + childOfSurface.getClass().getName());
		
//			[846:doInBackground_B18_v_2_0_d_t_7](18504): val.length()=1
//			[853:doInBackground_B18_v_2_0_d_t_7](18504):
//					childOfSurface.getClass().getName()=org.apache.harmony.xml.dom.TextImpl

		/*********************************
		 * CharacterDataImpl
		 *********************************/
//		// Log
//		Log.d("Task_GetYomi.java" + "["
//				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
//				+ ":"
//				+ Thread.currentThread().getStackTrace()[2].getMethodName()
//				+ "]",
//				"childOfSurface.getClass().getSuperclass().getName()="
//						+ childOfSurface.getClass().getSuperclass().getName());

//				[868:doInBackground_B18_v_2_0_d_t_7](18770):
//					childOfSurface.getClass().getSuperclass().getName()=
//					org.apache.harmony.xml.dom.CharacterDataImpl

		/*********************************
		 * el_Surface
		 *********************************/
//		// Log
//		Log.d("Task_GetYomi.java" + "["
//				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
//				+ ":"
//				+ Thread.currentThread().getStackTrace()[2].getMethodName()
//				+ "]",
//				"el_Surface.getClass().getName()=" + el_Surface.getClass().getName());
		
//			=> [884:doInBackground_B18_v_2_0_d_t_7](19039):
//					el_Surface.getClass().getName()=org.apache.harmony.xml.dom.ElementImpl

//		// Log
//		Log.d("Task_GetYomi.java" + "["
//				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
//				+ ":"
//				+ Thread.currentThread().getStackTrace()[2].getMethodName()
//				+ "]",
//				"el_Surface.getClass().getSuperclass().getName()="
//						+ el_Surface.getClass().getSuperclass().getName());
		
//			=> [895:doInBackground_B18_v_2_0_d_t_7](19166):
//					el_Surface.getClass().getSuperclass().getName()=
//						org.apache.harmony.xml.dom.InnerNodeImpl

		
		/*********************************
		 * Root
		 *********************************/
		// Log
		Log.d("Task_GetYomi.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ ":"
				+ Thread.currentThread().getStackTrace()[2].getMethodName()
				+ "]", "root.getClass().getName()=" + root.getClass().getName());
		
//			=> [912:doInBackground_B18_v_2_0_d_t_7](19500):
//						root.getClass().getName()=
//								org.apache.harmony.xml.dom.ElementImpl

		/*********************************
		 * InnerNodeImpl
		 *********************************/
//		Class c1 = root.getClass().getSuperclass();
//		
//		Class c2 = c1.getClass().getSuperclass();
//		
//		// Log
//		Log.d("Task_GetYomi.java" + "["
//				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
//				+ ":"
//				+ Thread.currentThread().getStackTrace()[2].getMethodName()
//				+ "]", "c2.getClass().getName()=" + c2.getClass().getName());
		
		s = root.getClass().getSuperclass().getSuperclass().getName();
		
//		// Log
//		Log.d("Task_GetYomi.java" + "["
//				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
//				+ ":"
//				+ Thread.currentThread().getStackTrace()[2].getMethodName()
//				+ "]", "root.getClass().getSuperclass().getSuperclass().getName()=" + s);
		
//			=> [939:doInBackground_B18_v_2_0_d_t_7](19782):
//					root.getClass().getSuperclass().getSuperclass().getName()=
//						org.apache.harmony.xml.dom.LeafNodeImpl
		
		s = root.getClass()
				.getSuperclass()
				.getSuperclass()
				.getSuperclass()
				.getName();

		// Log
		Log.d("Task_GetYomi.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ ":"
				+ Thread.currentThread().getStackTrace()[2].getMethodName()
				+ "]",
				"root.getClass().getSuperclass().getSuperclass().getSuperclass().getName()" + s);

//			=> [952:doInBackground_B18_v_2_0_d_t_7](19953):
//					root.getClass().getSuperclass().getSuperclass().getSuperclass().getName()
//						org.apache.harmony.xml.dom.NodeImpl

//		s = root.getClass()
//				.getSuperclass()
//				.getSuperclass()
//				.getSuperclass()
//				.getSuperclass()
//				.getSuperclass()
//				.getName();
//
//		// Log
//		Log.d("Task_GetYomi.java" + "["
//				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
//				+ ":"
//				+ Thread.currentThread().getStackTrace()[2].getMethodName()
//				+ "]",
//				"root.getClass()" +
//						".getSuperclass()" +
//						".getSuperclass()" +
//						".getSuperclass()" +
//						".getSuperclass()" +
//						".getSuperclass()" +
//						".getName()=" + s);

//			Caused by: java.lang.NullPointerException
//			at sl.tasks.Task_GetYomi.doInBackground_B18_v_2_0_d_t_7(Task_GetYomi.java:972)

		
	}//private static void doInBackground_B18_v_2_0_d_t_3()

	private static void doInBackground_B18_v_2_0_d() {
		// TODO Auto-generated method stub
		String kw = "����ԁi���j";
		
		String url = "http://jlp.yahooapis.jp/FuriganaService/V1/furigana" +
				"?appid=dj0zaiZpPTZjQWNRNExhd0thayZkPVlXazlhR2gwTTJGUE56SW1jR285TUEtLSZzPWNvbnN1bWVyc2VjcmV0Jng9Mjc-" +
				"&grade=1" +
				"&sentence=" + kw;

		XmlHandler xh = new XmlHandler();
		
		Document doc = xh.getDoc(url);
		
		String tagName = "Word";
		
		NodeList listWord = doc.getElementsByTagName(tagName);
		
		// Log
		Log.d("Task_GetYomi.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ ":"
				+ Thread.currentThread().getStackTrace()[2].getMethodName()
				+ "]", "listWord.getLength()=" + listWord.getLength());
		
		Node nNode = listWord.item(0);
		
		if (nNode.getNodeType() == Node.ELEMENT_NODE) {
			
			// Log
			Log.d("Task_GetYomi.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ ":"
					+ Thread.currentThread().getStackTrace()[2].getMethodName()
					+ "]", "nNode => Element node");
		} else {
			
			// Log
			Log.d("Task_GetYomi.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ ":"
					+ Thread.currentThread().getStackTrace()[2].getMethodName()
					+ "]", "nNode => Not an element node");
		}
		
		// Log
		Log.d("Task_GetYomi.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ ":"
				+ Thread.currentThread().getStackTrace()[2].getMethodName()
				+ "]", "nNode.getNodeName()=" + nNode.getNodeName());
		
		NodeList nListWordChilds = nNode.getChildNodes();
		
		// Log
		Log.d("Task_GetYomi.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ ":"
				+ Thread.currentThread().getStackTrace()[2].getMethodName()
				+ "]", "nListWordChilds=" + nListWordChilds.getLength());
		
		for (int i = 0; i < nListWordChilds.getLength(); i++) {
			
			// Log
			Log.d("Task_GetYomi.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ ":"
					+ Thread.currentThread().getStackTrace()[2].getMethodName()
					+ "]",
					"Child " + (i + 1) + "=" + nListWordChilds.item(i).getNodeName());
			
		}//for (int i = 0; i < nListWordChilds.getLength(); i++)
		
		// Log
		Log.d("Task_GetYomi.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ ":"
				+ Thread.currentThread().getStackTrace()[2].getMethodName()
				+ "]",
				"item(1)=" + nListWordChilds.item(1).getTextContent());
		
		
//		for (int i = 0; i < listWord.getLength(); i++) {
//
//			Node nNode = listWord.item(i);
//			
//			if (nNode.getNodeType() == Node.ELEMENT_NODE) {
//				
//				Element elm = (Element) nNode;
//				
//				String strSurface = elm.getElementsByTagName("Surface").item(0).getTextContent();
//				
//				// Log
//				Log.d("Task_GetYomi.java"
//						+ "["
//						+ Thread.currentThread().getStackTrace()[2]
//								.getLineNumber()
//						+ ":"
//						+ Thread.currentThread().getStackTrace()[2]
//								.getMethodName() + "]",
//						"Surface no." + 1 + "=" + strSurface);
//				
////				Node nChild = elm.getElementsByTagName("Surface").item(0);
//				
////				if (nChild.getNodeType() == Node.ELEMENT_NODE) {
////					
//////					Element elmSurface = (Element) nChild;
//////					
//////					String strSurface = elmSurface.getTextContent();
////					
////					String strSurface = nChild.getTextContent();
////					
////					// Log
////					Log.d("Task_GetYomi.java"
////							+ "["
////							+ Thread.currentThread().getStackTrace()[2]
////									.getLineNumber()
////							+ ":"
////							+ Thread.currentThread().getStackTrace()[2]
////									.getMethodName() + "]",
////							"Surface no." + 1 + "=" + strSurface);
////					
////				}//if (elmChild.getNodeType() == Node.ELEMENT_NODE)
//				
//			}//if (variable == condition)
//			
//		}//for (int i = 0; i < listWord.getLength(); i++)
		
	}//private static void doInBackground_B18_v_2_0_d()

	private static int doInBackground__1() {
		
		DBUtils dbu = new DBUtils(actv, CONS.dbName);
		
		SQLiteDatabase wdb = dbu.getWritableDatabase();
		
		String tableName = "shopping_item";
		
		String sql = "SELECT * FROM " + tableName;

		Cursor c = null;

		try {
			
			c = wdb.rawQuery(sql, null);
			
			/*********************************
			 * Cursor => null?
			 *********************************/
			if (null == c) {
				
				// Log
				Log.d("DialogOnItemClickListener.java"
						+ "["
						+ Thread.currentThread().getStackTrace()[2]
								.getLineNumber()
						+ ":"
						+ Thread.currentThread().getStackTrace()[2]
								.getMethodName() + "]", "Cursor => null");
				
				wdb.close();
				
				return CONS.GETYOMI_FAILED;
				
			}//if (null == c)
			
			/*********************************
			 * Num of entries in the cursor => Less than 1?
			 *********************************/
			if (c.getCount() < 1) {
				
				// Log
				Log.d("DialogOnItemClickListener.java"
						+ "["
						+ Thread.currentThread().getStackTrace()[2]
								.getLineNumber()
						+ ":"
						+ Thread.currentThread().getStackTrace()[2]
								.getMethodName() + "]", "Cursor => No entry");
				
				wdb.close();
				
				return CONS.GETYOMI_FAILED;
				
			}//if (null == c)
			
			/*********************************
			 * Start
			 *********************************/
			c.moveToFirst();
			
			for (int i = 0; i < c.getCount(); i++) {

				String yomi =
						c.getString(1 + Methods.getArrayIndex(
											CONS.columns,
											"yomi"));
	
				String name =
						c.getString(1 + Methods.getArrayIndex(
											CONS.columns,
											"name"));
				
				// Log
				Log.d("Task_GetYomi.java" + "["
						+ Thread.currentThread().getStackTrace()[2].getLineNumber()
						+ ":"
						+ Thread.currentThread().getStackTrace()[2].getMethodName()
						+ "]",
						"name=" + name
						+ "/"
						+ "yomi=" + yomi);
				
				/*********************************
				 * If "yomi" value is null,
				 * 	=> Get yomi using the method: Methods.getYomi_full(String. String)
				 *********************************/
				if (yomi == null || yomi.equals("")) {
					
					yomi = Methods.getYomi_full(name, "utf-8")[2];
					
					// Log
					Log.d("Task_GetYomi.java"
							+ "["
							+ Thread.currentThread().getStackTrace()[2]
									.getLineNumber()
							+ ":"
							+ Thread.currentThread().getStackTrace()[2]
									.getMethodName() + "]", "yomi=" + yomi);
					
				}//if (yomi == null || yomi.equals(""))
				
				/*********************************
				 * Next
				 *********************************/
				c.moveToNext();

			}//for (int i = 0; i < c.getCount(); i++)
			
		} catch (Exception e) {
			
			// Log
			Log.d("DialogOnItemClickListener.java"
					+ "["
					+ Thread.currentThread().getStackTrace()[2]
							.getLineNumber()
					+ ":"
					+ Thread.currentThread().getStackTrace()[2]
							.getMethodName() + "]", e.toString());
			
			wdb.close();
			
			return CONS.GETYOMI_FAILED;
			
		}//try
		
		wdb.close();
		
		return CONS.GETYOMI_FAILED;
		
	}//private static int doInBackground__1()

	private static int doInBackground__2() {
		/*********************************
		 * API-related processes
		 *********************************/
		String name = "����ԁi���j";

		String yomi = Methods_sl.getYomi_full(name, "utf-8")[2];
		
		
		
		if (yomi != null) {
			
			// Log
			Log.d("Task_GetYomi.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ ":"
					+ Thread.currentThread().getStackTrace()[2].getMethodName()
					+ "]", "yomi=" + yomi);
			
			return CONS.GETYOMI_SUCCESSFUL;
			
		} else {//if (yomi != null)
		
			// Log
			Log.d("Task_GetYomi.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ ":"
					+ Thread.currentThread().getStackTrace()[2].getMethodName()
					+ "]", "yomi == null (" + name + ")");
			
			return CONS.GETYOMI_FAILED;
			
		}//if (yomi != null)
		
	}//private static int doInBackground__1()


	private static void doInBackground__2__1__XmlHandler() {
		// TODO Auto-generated method stub
		String kw = "����ԁi���j";
		
		String url = "http://jlp.yahooapis.jp/FuriganaService/V1/furigana" +
				"?appid=dj0zaiZpPTZjQWNRNExhd0thayZkPVlXazlhR2gwTTJGUE56SW1jR285TUEtLSZzPWNvbnN1bWVyc2VjcmV0Jng9Mjc-" +
				"&grade=1" +
				"&sentence=" + kw;

		HttpEntity entity = Methods.getYomi_getHttpEntity(url);
		
		if (entity == null) {
			
			// Log
			Log.d("Methods_sl.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ ":"
					+ Thread.currentThread().getStackTrace()[2].getMethodName()
					+ "]", "entity == null");
			
			return;
			
		}//if (entity == null)

		/*********************************
		 * Get: XMLPullParser
		 *********************************/
		String xmlString =
					Methods.convert_HttpEntity2XmlString(entity);
		
		if (xmlString == null) {
			
			// Log
			Log.d("Methods_sl.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ ":"
					+ Thread.currentThread().getStackTrace()[2].getMethodName()
					+ "]", "xmlString == null");
			
			return;
			
		}//if (entity == null)

		/*********************************
		 * XmlHandler
		 *********************************/
		XmlHandler xh = new XmlHandler();
		
		try {
			
//			xh.showXml(xmlString);
			xh.showXml(url);
			
		} catch (SAXException e) {

			// Log
			Log.e("Task_GetYomi.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ ":"
					+ Thread.currentThread().getStackTrace()[2].getMethodName()
					+ "]", e.toString());
			
		} catch (IOException e) {

			// Log
			Log.e("Task_GetYomi.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ ":"
					+ Thread.currentThread().getStackTrace()[2].getMethodName()
					+ "]", e.toString());
			
		} catch (ParserConfigurationException e) {

			// Log
			Log.e("Task_GetYomi.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ ":"
					+ Thread.currentThread().getStackTrace()[2].getMethodName()
					+ "]", e.toString());
			
		}//try

	}//private static void doInBackground__2__1__XmlHandler()
	

	@Override
	protected void onCancelled() {
		// TODO Auto-generated method stub
		super.onCancelled();
	}

	@Override
	protected void onPostExecute(Integer res) {
		// TODO Auto-generated method stub
		super.onPostExecute(res);
		
		switch (res) {
		case CONS.GETYOMI_SUCCESSFUL:
			
			// debug
			Toast.makeText(actv,
					"Get yomi => Done", Toast.LENGTH_LONG).show();
			
			break;
			
		case CONS.GETYOMI_FAILED:

			// debug
			Toast.makeText(actv,
					"Get yomi => Failed", Toast.LENGTH_LONG).show();

			break;
			
		default:
			break;
		}//switch (res)
		
	}//protected void onPostExecute(Integer res)

	@Override
	protected void onPreExecute() {
		// TODO Auto-generated method stub
		super.onPreExecute();
	}

}//public class Task_GetYomi extends AsyncTask<String, Integer, Integer>
