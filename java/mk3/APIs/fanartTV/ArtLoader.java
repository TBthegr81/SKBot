package botMk2.APIs.fanartTV;

import java.net.MalformedURLException;
import java.net.URL;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import botMk2.*;

public class ArtLoader {


	public static void main(String[] args) {
		String movie = "";
		
		while(true)
		{
			movie = CLib.input("Movie");
			
			try {
				/*context = JAXBContext.newInstance(Root.class);
				File file = new File("./omdbapi_text.xml");
		        System.out.println("Unmarshaling...");
		        Root r = (Root) context.createUnmarshaller().unmarshal(file);*/
				JAXBContext jc = JAXBContext.newInstance(Fanart.class);
			       Unmarshaller u = jc.createUnmarshaller();
			       URL url = new URL("http://api.fanart.tv/webservice/movie/7b32e82b6c805a88d81a59eb6aca7d4c/603-the-matrix/XML/");
			       Fanart f = (Fanart) u.unmarshal(url);
			       Fanart.Movie m = f.getMovie();
		        if(m != null)
		        {
		        	System.out.println("Movie: " + movie);
		        	System.out.println("<hdmoviecleararts>");
		        	for(int i = 0; i < m.getHdmoviecleararts().getHdmovieclearart().size(); i++)
		        	{
		        			System.out.println(m.getHdmoviecleararts().getHdmovieclearart().get(i).getUrl());
		        	}
		        	System.out.println("</hdmoviecleararts>");
		        	System.out.println("<hdmovielogos>");
		        	for(int i = 0; i < m.getHdmovielogos().getHdmovielogo().size(); i++)
		        	{
		        			System.out.println(m.getHdmovielogos().getHdmovielogo().get(i).getUrl());
		        	}
		        	System.out.println("</hdmovielogos>");
		        	System.out.println("<movieposters>");
		        	for(int i = 0; i < m.getMovieposters().getMovieposter().size(); i++)
		        	{
		        			System.out.println(m.getMovieposters().getMovieposter().get(i).getUrl());
		        	}
		        	System.out.println("</movieposters>");
		        	System.out.println("<movielogos>");
		        	for(int i = 0; i < m.getMovielogos().getMovielogo().size(); i++)
		        	{
		        			System.out.println(m.getMovielogos().getMovielogo().get(i).getUrl());
		        	}
		        	System.out.println("</movielogos>");
		        	System.out.println("<moviearts>");
		        	for(int i = 0; i < m.getMoviearts().getMovieart().size(); i++)
		        	{
		        			System.out.println(m.getMoviearts().getMovieart().get(i).getUrl());
		        	}
		        	System.out.println("</moviearts>");
		        	System.out.println("<moviediscs>");
		        	for(int i = 0; i < m.getMoviediscs().getMoviedisc().size(); i++)
		        	{
		        			System.out.println(m.getMoviediscs().getMoviedisc().get(i).getUrl());
		        	}
		        	System.out.println("</moviediscs>");
		        	System.out.println("<moviethumbs>");
		        	for(int i = 0; i < m.getMoviethumbs().getMoviethumb().size(); i++)
		        	{
		        			System.out.println(m.getMoviethumbs().getMoviethumb().get(i).getUrl());
		        	}
		        	System.out.println("</moviethumbs>");
		        	System.out.println("<moviebackgrounds>");
		        	for(int i = 0; i < m.getMoviebackgrounds().getMoviebackground().size(); i++)
		        	{
		        			System.out.println(m.getMoviebackgrounds().getMoviebackground().get(i).getUrl());
		        	}
		        	System.out.println("</moviebackgrounds>");
		        	System.out.println("<moviebanners>");
		        	for(int i = 0; i < m.getMoviebanners().getMoviebanner().size(); i++)
		        	{
		        			System.out.println(m.getMoviebanners().getMoviebanner().get(i).getUrl());
		        	}
		        	System.out.println("</moviebanners>");
		        }
		        System.out.println("Data from the fanart.tv API - http://fanart.tv");
			} catch (JAXBException e) {
				e.printStackTrace();
			} catch (MalformedURLException e) {
				e.printStackTrace();
			}
		}
	}

}
