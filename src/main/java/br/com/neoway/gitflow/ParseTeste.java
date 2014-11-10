package br.com.neoway.gitflow;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.tika.exception.TikaException;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.mime.MediaType;
import org.apache.tika.parser.ParseContext;
import org.apache.tika.parser.Parser;
import org.apache.tika.parser.pdf.PDFParser;
import org.apache.tika.sax.BodyContentHandler;
import org.apache.tika.sax.XHTMLContentHandler;
import org.xml.sax.ContentHandler;
import org.xml.sax.SAXException;

public class ParseTeste implements Parser {

	public Set<MediaType> getSupportedTypes(ParseContext arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	public void parse(InputStream arg0, ContentHandler handler, Metadata metadata,
			ParseContext arg3) throws IOException, SAXException, TikaException {
		   XHTMLContentHandler xhtml = new XHTMLContentHandler(handler, metadata);
		   xhtml.startDocument();
		   xhtml.endDocument();
	}
	
	public boolean testeParttern(String param) throws IOException{
		 InputStream is = null;
		 System.out.println(param);
 	    try {
 	      is = new FileInputStream("/home/neoway/Downloads/teste.pdf");
 	      BodyContentHandler contenthandler = new BodyContentHandler();
 	      Metadata metadata = new Metadata();
 	      PDFParser pdfparser = new PDFParser();
 	      pdfparser.parse(is, contenthandler, metadata, new ParseContext());
 	      String retorno = contenthandler.toString();
 	      Pattern pattern = Pattern.compile(param);
 	      Matcher matcher = pattern.matcher(retorno);
 	      matcher.find();
 	      System.out.println(matcher.group());
 	      System.out.println(contenthandler.toString());
 	      return matcher.find();
 	    }
 	    catch (Exception e) {
 	      e.printStackTrace();
 	    }
 	    finally {
 	        if (is != null) is.close();
 	    }
 	    return false;
	}
	

}
