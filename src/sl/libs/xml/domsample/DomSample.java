/*********************************
 * REF=> http://www.fireproject.jp/feature/xml/programing/java-dom.html
 *********************************/
package sl.libs.xml.domsample;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import android.util.Log;

public class DomSample {

    protected Document doc;
    protected int tab;

    public DomSample(String filename){
	try{
	    DocumentBuilderFactory dbf=DocumentBuilderFactory.newInstance();
	    DocumentBuilder db=dbf.newDocumentBuilder();
	    /* XML������ǂݍ���.*/
	    doc=db.parse(new FileInputStream(filename));
	}catch(Exception e){
	    e.printStackTrace();
	}
	tab=0;
    }

    public DomSample(String uri, String keyWord) {
    	
	    DocumentBuilderFactory dbf=DocumentBuilderFactory.newInstance();
	    DocumentBuilder db = null;
	    
		try {
			
			db = dbf.newDocumentBuilder();
			
		} catch (ParserConfigurationException e) {
			
			// Log
			Log.d("DomSample.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ ":"
					+ Thread.currentThread().getStackTrace()[2].getMethodName()
					+ "]", e.toString());

			doc = null;
			
		}
	    /* XML������ǂݍ���.*/
	    
	    String url = uri + "/" + keyWord;
	    
	    URL u = null;
	    
		try {
			
			u = new URL(url);
			
		} catch (MalformedURLException e) {
			
			// Log
			Log.d("DomSample.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ ":"
					+ Thread.currentThread().getStackTrace()[2].getMethodName()
					+ "]", e.toString());

			doc = null;
			
		}
	    
		if (u != null && db != null) {
			
			try {
				
				doc=db.parse(u.openConnection().getInputStream());
				
			} catch (SAXException e) {
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		} else {//if (u != null)
			
			// Log
			Log.d("DomSample.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ ":"
					+ Thread.currentThread().getStackTrace()[2].getMethodName()
					+ "]", "u == null");
			
		}//if (u != null)
		
    }//public DomSample(String uri, String keyWord)
    
    public static DomSample getDomSampleFromUri(String uri, String keyWord) {
    	
    	return new DomSample(uri, keyWord);
    }
    
    public void getNodeInfo(Node node){
		
    	tabbing();
		
		/* �m�[�h�̎�ނ��o�� */
		// Log
		Log.d("DomSample.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ ":"
				+ Thread.currentThread().getStackTrace()[2].getMethodName()
				+ "]", "Node type= "+node.getNodeType());
		
		/* �m�[�h�����o�� */
		tabbing();
		// Log
		Log.d("DomSample.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ ":"
				+ Thread.currentThread().getStackTrace()[2].getMethodName()
				+ "]", "Node name= "+node.getNodeName());
				
		/* �m�[�h�̒l���o�� */

		// Log
		Log.d("DomSample.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ ":"
				+ Thread.currentThread().getStackTrace()[2].getMethodName()
				+ "]", "Node value= "+node.getNodeValue());
		
    }//public void getNodeInfo(Node node)

    /* �S�m�[�h��T�� */
    public void walkThrough(){
    	
    	if (doc == null) {
			
    		// Log
			Log.d("DomSample.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ ":"
					+ Thread.currentThread().getStackTrace()[2].getMethodName()
					+ "]", "doc => null");
			
			return;
    		
		}//if (doc == null)
    	
		Node root=doc.getDocumentElement();
		recursiveWalk(root);
    }

    private void recursiveWalk(Node node){
		/*
		�����,XML�����̃C���f���g�Ȃǂ̋󔒂̃m�[�h��ǂݔ�΂����߂̏���.
		Node.TEXT_NODE �m�[�h���e�L�X�g��,�m�[�h�̒l�̋󔒂�������������̒�����0�̏ꍇ�͓ǂݔ�΂�.
		*/
		   
		if(node.getNodeType()==Node.TEXT_NODE && node.getNodeValue().trim().length()==0){
		    return;
		}
		getNodeInfo(node);
		tab++;
		/* node.getFirstChild : node�̍ŏ��̎q�𓾂� */
		/* child.getNextSibling : child�̌Z��m�[�h�𓾂� */
		for(Node child=node.getFirstChild();child!=null;child=child.getNextSibling()){
		    recursiveWalk(child);
		}
		
		tab--;
		
    }//private void recursiveWalk(Node node)

    protected void tabbing(){
	for(int i=0;i<tab;i++){
	    System.out.print("\t");
	}
    }

//    public static void main(String args[]){
////		DomSample ds=new DomSample("sample.xml");
////    	DomSample ds=new DomSample("dat/sample.xml");
//    	DomSample ds=new DomSample("dat/furigana.xml");
//		ds.walkThrough();
//    }
}

//Node type= 1
//Node name= ResultSet
//Node value= null
//	Node type= 1
//	Node name= Result
//	Node value= null
//		Node type= 1
//		Node name= WordList
//		Node value= null
//			Node type= 1
//			Node name= Word
//			Node value= null
//				Node type= 1
//				Node name= Surface
//				Node value= null
//					Node type= 3
//					Node name= #text
//					Node value= ����
//				Node type= 1
//				Node name= Furigana
//				Node value= null
//					Node type= 3
//					Node name= #text
//					Node value= ���񂽂�
//				Node type= 1
//				Node name= Roman
//				Node value= null
//					Node type= 3
//					Node name= #text
//					Node value= sentaku
//			Node type= 1
//			Node name= Word
//			Node value= null
//				Node type= 1
//				Node name= Surface
//				Node value= null
//					Node type= 3
//					Node name= #text
//					Node value= ��
//				Node type= 1
//				Node name= Furigana
//				Node value= null
//					Node type= 3
//					Node name= #text
//					Node value= ����
//				Node type= 1
//				Node name= Roman
//				Node value= null
//					Node type= 3
//					Node name= #text
//					Node value= mou
//			Node type= 1
//			Node name= Word
//			Node value= null
//				Node type= 1
//				Node name= Surface
//				Node value= null
//					Node type= 3
//					Node name= #text
//					Node value= �i
//			Node type= 1
//			Node name= Word
//			Node value= null
//				Node type= 1
//				Node name= Surface
//				Node value= null
//					Node type= 3
//					Node name= #text
//					Node value= ��
//				Node type= 1
//				Node name= Furigana
//				Node value= null
//					Node type= 3
//					Node name= #text
//					Node value= �Ȃ�
//				Node type= 1
//				Node name= Roman
//				Node value= null
//					Node type= 3
//					Node name= #text
//					Node value= naka
//			Node type= 1
//			Node name= Word
//			Node value= null
//				Node type= 1
//				Node name= Surface
//				Node value= null
//					Node type= 3
//					Node name= #text
//					Node value= �j
