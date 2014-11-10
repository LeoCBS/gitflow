package br.com.neoway.gitflow;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.dom.DOMResult;
import javax.xml.transform.sax.SAXTransformerFactory;
import javax.xml.transform.sax.TransformerHandler;

import org.apache.tika.Tika;
import org.apache.tika.exception.TikaException;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.ParseContext;
import org.apache.tika.parser.pdf.PDFParser;
import org.apache.tika.sax.BodyContentHandler;
import org.jsoup.Jsoup;
import org.jsoup.select.Elements;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws IOException
    {
    	Bot.firstMethod();
//    	testeParse();
    	testeHtmlParse();
    	 InputStream is = null;
    	    try {
    	      is = new FileInputStream("/home/neoway/Downloads/teste.pdf");
    	      BodyContentHandler contenthandler = new BodyContentHandler();
    	      Metadata metadata = new Metadata();
    	      PDFParser pdfparser = new PDFParser();
    	      pdfparser.parse(is, contenthandler, metadata, new ParseContext());
    	      String retorno = contenthandler.toString();
    	      Pattern pattern = Pattern.compile("[a-z]");
    	      Matcher matcher = pattern.matcher(retorno);
    	      matcher.find();
    	      System.out.println(matcher.group());
    	      System.out.println(contenthandler.toString());
    	    }
    	    catch (Exception e) {
    	      e.printStackTrace();
    	    }
    	    finally {
    	        if (is != null) is.close();
    	    }
    }
    
    private static void testeHtmlParse(){
    	org.jsoup.nodes.Document doc;
		try {
			doc = Jsoup.connect("http://en.wikipedia.org/").get();
			Elements newsHeadlines = doc.select("#mp-itn b a");
			System.out.println("teste");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    private static void testeParse(){
    	try {
			InputStream input = new FileInputStream("/home/neoway/Downloads/documentacao.pdf");
			String mimeType = new Tika().detect(input);
			Metadata metadata = new Metadata();
			metadata.set(Metadata.CONTENT_TYPE, mimeType);
			DOMResult result = new DOMResult();
			TransformerHandler transformerHandler = ((SAXTransformerFactory) SAXTransformerFactory.newInstance()).newTransformerHandler();
			transformerHandler.setResult(result);
			new PDFParser().parse(input, transformerHandler, metadata, new ParseContext());
			result.toString();
			System.out.println(transformerHandler.toString());
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TransformerConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TransformerFactoryConfigurationError e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TikaException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
}
