package botMk2.APIs.OMDB;

import java.net.MalformedURLException;
import java.net.URL;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import botMk2.*;

public class MovieLoader {

	public static void main(String[] args) {
		//ObjectFactory objFact = new ObjectFactory();
		String movie = "";
		
		while(true)
		{

			movie = CLib.input("Movie");
			//JAXBContext context = null;
			try {
				/*context = JAXBContext.newInstance(Root.class);
				File file = new File("./omdbapi_text.xml");
		        System.out.println("Unmarshaling...");
		        Root r = (Root) context.createUnmarshaller().unmarshal(file);*/
				JAXBContext jc = JAXBContext.newInstance(Root.class);
			       Unmarshaller u = jc.createUnmarshaller();
			       URL url = new URL( "http://www.omdbapi.com/?i=&t="+ movie +"&r=XML" );
			       Root r = (Root) u.unmarshal(url);
		        Root.Movie m = r.getMovie();
		        if(m != null)
		        {
		        	System.out.println(m.getTitle() + "\nDir: " + m.getDirector()  + "\nWrt: " + m.getWriter()  + "\nAct: " + m.getActors()  + "\nPlt: " + m.getPlot() + "\nGnr: " + m.genre + "\nRls: " + m.getReleased());
		        }
		        System.out.println("Data from The Open Movie Database API - www.omdbapi.com");
			} catch (JAXBException e) {
				e.printStackTrace();
			} catch (MalformedURLException e) {
				e.printStackTrace();
			}
		}
	}
}
