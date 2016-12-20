package OnlineMealOrder;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.stream.JsonReader;

public class MealClient {
	
	public List<Meal> search(int rstId) 
	{
		List<Meal> Meals = null;
		try
		{
			String urlStr = String.format("https://r.ordr.in/rd/%s",rstId);
			String api_key = "YvBNngarwaNHwN3ALLDCpVVRB_6nPnVx_TgzBtNp3Yg";
			URL url = new URL(urlStr);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestProperty("X-NAAMA-CLIENT-AUTHENTICATION","id=\"YvBNngarwaNHwN3ALLDCpVVRB_6nPnVx_TgzBtNp3Yg\", version=\"1\"");
			conn.setRequestMethod("GET");
            JsonReader reader = new JsonReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
            Meals = readMealObject(reader);
			conn.disconnect();
		}
		catch (MalformedURLException e)
		{
			e.printStackTrace();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		return Meals;
	}
	
	 public List<Meal> readMealObject(JsonReader reader) throws IOException {
	     List<Meal> Meals = new ArrayList<Meal>();

	     reader.beginObject();
		 while (reader.hasNext()) {
		    String name = reader.nextName();
		    if (name.equals("menu")) {
		    	List<Meal> newMeals = readMealChildren(reader);
		    	if (!newMeals.isEmpty())
		    	Meals.addAll(newMeals);
		    }
		    else {
		    	reader.skipValue();
		   	}
		}
		reader.endObject();

	    return Meals;
	}
	 
	 
	 public List<Meal> readMealChildren(JsonReader reader) throws IOException {
	     List<Meal> Meals = new ArrayList<Meal>();

	     reader.beginArray();
		 while (reader.hasNext()) {
			  reader.beginObject();
			  while (reader.hasNext()) {
				  String name = reader.nextName();
				    if (name.equals("children")) {
				    	List<Meal> newMeals = readMeals(reader);
				    	if (!newMeals.isEmpty())
				    	Meals.addAll(newMeals);
				    }
				    else {
				    	reader.skipValue();
				   	}
			   }
			  reader.endObject();
		 }
		    
		reader.endArray();

	    return Meals;
	}
	 
	 public List<Meal> readMeals(JsonReader reader) throws IOException {
		 
	     List<Meal> Meals = new ArrayList<Meal>();

	     reader.beginArray();
		 while (reader.hasNext()) {
			  reader.beginObject();
			  int id = -1;
	    	  String descrip = null;
	    	  double price = 0.00;
	  		  String na = null;
			  while (reader.hasNext()) {
	    		   String name = reader.nextName();
	  		       if (name.equals("id")) {
	  	   				id = reader.nextInt();
	  	   			} else if (name.equals("descrip")) {
	  	   				descrip = reader.nextString();
	  	   			} else if (name.equals("price")) {
	  	   				price = reader.nextDouble();
	  	   			} else if (name.equals("name")) {
	  	   				na = reader.nextString();
	  	   			} else {
	  	   				reader.skipValue();
	  	   			}
			  }
			  if(na != null)
		      {
				  Meals.add(new Meal(id, na, descrip, price));
		      }
			  reader.endObject();
		}	    
		reader.endArray();
	    return Meals;
	 }
}
