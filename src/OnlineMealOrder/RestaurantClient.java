package OnlineMealOrder;


import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.stream.JsonReader;
import com.sun.jndi.toolkit.url.UrlUtil;

public class RestaurantClient {
	
	public List<Restaurant> search(String zip, String city, String address) 
	{
		List<Restaurant> Restaurants = null;
		try
		{
			 String urlStr = String.format("https://r.ordr.in/dl/%s/%s/%s/%s",
					 UrlUtil.encode("ASAP","UTF-8"),
					 UrlUtil.encode(zip,"UTF-8"),
					 UrlUtil.encode(city,"UTF-8"),
					 UrlUtil.encode(address,"UTF-8"));
			URL url = new URL(urlStr);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestProperty("X-NAAMA-CLIENT-AUTHENTICATION","id=\"YvBNngarwaNHwN3ALLDCpVVRB_6nPnVx_TgzBtNp3Yg\", version=\"1\"");
			conn.setRequestMethod("GET");
            JsonReader reader = new JsonReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
            Restaurants = readRestaurantArray(reader);
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
		return Restaurants;
	}
	
	public List<String> readCuisine(JsonReader reader) throws IOException {
		List<String> cuisines = new ArrayList<String>();
		 reader.beginArray();
	     while (reader.hasNext()) {
	    	 cuisines.add(reader.nextString());
	     }
	     reader.endArray();
	     return cuisines;
	}
	
	 public Restaurant readRestaurant(JsonReader reader) throws IOException {
	     int id = -1;
	     String addr = null;
	     String city = null;
	     String na = null;
	     List<String> cuisine = null;
	     reader.beginObject();
	     while (reader.hasNext()) {
	       String name = reader.nextName();
	       if (name.equals("id")) {
   				id = reader.nextInt();
   			} else if (name.equals("addr")) {
   				addr = reader.nextString();
   			} else if (name.equals("city")) {
   				city = reader.nextString();
   			} else if (name.equals("cu")) {
   				cuisine = readCuisine(reader);
   			} else if (name.equals("na")) {
   				na = reader.nextString();
   			} else {
   				reader.skipValue();
   			}
	     }
	     reader.endObject();
	     return new Restaurant(id, na, addr, city, cuisine);
	   }
	 
	 public List<Restaurant> readRestaurantArray(JsonReader reader) throws IOException {
	     List<Restaurant> Restaurants = new ArrayList<Restaurant>();

	     reader.beginArray();
	     while (reader.hasNext()) {
	    	 Restaurants.add(readRestaurant(reader));
	     }
	     reader.endArray();
	     return Restaurants;
	   }
}