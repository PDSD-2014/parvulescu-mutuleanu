package com.me.ws;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import com.sun.jersey.spi.resource.Singleton;

@Produces("application/xml")
@Path("games")
@Singleton
public class GamesResources {

	private ResultSet resultDB;
	
	public GamesResources() {
	    
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            Connection c = DriverManager.getConnection(
                    "jdbc:sqlserver://ROBERT-PC:1433;database=GameFinder",
                    "Robert123",
                    "1234567");
            Statement s = c.createStatement(
                    ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);
            
            resultDB = s.executeQuery("SELECT * FROM Game");
        } catch (Exception e) {
            e.printStackTrace();
        }
	  }
	
	@GET
	public List<Game> getGames() {
		List<Game> games = new ArrayList<Game>();
		
		try {
			
			resultDB.beforeFirst();
	        while(resultDB.next()){
	        	games.add(new Game(resultDB.getString("Name"), 
	        			resultDB.getString("Description"),
	        			resultDB.getString("Publisher"),
	        			resultDB.getString("Picture"),
	        			resultDB.getInt("Genre"),
	        			resultDB.getInt("Platform")));
	        }
        }catch(Exception e){
            e.printStackTrace();
        }
		
	    return games;
	}
	
	@GET
	@Path("{platform}/{genre}")
	public List<Game> getGames(
			@PathParam("platform") int platformNumber,
			@PathParam("genre") int genreNumber) {

		List<Game> games = new ArrayList<Game>();
		
		try {
			
			resultDB.beforeFirst();
	        while (resultDB.next()){
	        	
	        	if (platformNumber != 0 && resultDB.getInt("Platform") != platformNumber)
	        		continue;
	        	
	        	if (genreNumber != 0 && resultDB.getInt("Genre") != genreNumber)
	        		continue;
	        	
	        	games.add(new Game(resultDB.getString("Name"), 
	        			resultDB.getString("Description"),
	        			resultDB.getString("Publisher"),
	        			resultDB.getString("Picture"),
	        			resultDB.getInt("Genre"),
	        			resultDB.getInt("Platform")));
	        }
        }catch(Exception e){
            e.printStackTrace();
        }
		
	    return games;
	}

	@GET
	@Path("{platform}/{genre}/{qname}")
	public List<Game> getGames(
			@PathParam("platform") int platformNumber,
			@PathParam("genre") int genreNumber,
			@PathParam("qname") String qName) {

		List<Game> games = new ArrayList<Game>();
		
		try {
			
			resultDB.beforeFirst();
	        while (resultDB.next()){
	        	
	        	if (platformNumber != 0 && resultDB.getInt("Platform") != platformNumber)
	        		continue;
	        	
	        	if (genreNumber != 0 && resultDB.getInt("Genre") != genreNumber)
	        		continue;
	        	
	        	if (qName != null && !qName.isEmpty() && !qName.trim().isEmpty() && !resultDB.getString("Name").trim().toLowerCase().contains(qName.trim().toLowerCase()))
	        		continue;
	        	
	        	games.add(new Game(resultDB.getString("Name"), 
	        			resultDB.getString("Description"),
	        			resultDB.getString("Publisher"),
	        			resultDB.getString("Picture"),
	        			resultDB.getInt("Genre"),
	        			resultDB.getInt("Platform")));
	        }
        }catch(Exception e){
            e.printStackTrace();
        }
		
	    return games;
	}

	@GET
	@Produces("image/png")
	@Path("getpicture/{picture}")
	public Response getPicture(@PathParam("picture") String pictureName) {
		
		
		String filePath = "";
		
		try {
			
			resultDB.beforeFirst();
	        while(resultDB.next())
	        {
	        			
        		if (pictureName.equals(resultDB.getString("Picture")))
        		{
        			filePath = resultDB.getString("PictureURL") + ".png";
        			break;
        		}
        			
	        }
        }catch(Exception e){
            e.printStackTrace();
        }
		
		if (filePath.isEmpty())
			return Response.noContent().build();
		
		java.nio.file.Path path = Paths.get(filePath);
		
		byte[] data = null;
		
		try {
			data = Files.readAllBytes(path);
		} catch (IOException e) {
			e.printStackTrace();
		}

		return Response.ok(data).build();
	}
}