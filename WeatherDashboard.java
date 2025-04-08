import java.io.BufferedReader;

import java.io.InputStreamReader;

import java.net.HttpURLConnection;

import java.net.URL;

import org.json.JSONObject;


public class WeatherDashboard
 {
   
 private static final String API_KEY = "YOUR_API_KEY"; 
// Replace with your OpenWeatherMap API key
    private static final String BASE_URL = "https://api.openweathermap.org/data/2.5/weather?q=";

 
   public static void main(String[] args)
 {
    
    try
 {
            String city = "London";
 // You can allow user input too
            String urlString = BASE_URL + city + "&appid=" + API_KEY + "&units=metric";

 
    // Create connection
            URL url = new URL(urlString);
        
    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
          
  conn.setRequestMethod("GET");

            // Read response
        
    BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
           
 String inputLine;
          
  StringBuilder response = new StringBuilder();
          
  while((inputLine = in.readLine()) != null)
 {
               
 response.append(inputLine);
       
     }
      
      in.close();

          
  // Parse JSON
        
    JSONObject json = new JSONObject(response.toString());

      
      String cityName = json.getString("name");
         
   JSONObject main = json.getJSONObject("main");
         
   double temp = main.getDouble("temp");
         
   int humidity = main.getInt("humidity");

       
     JSONObject wind = json.getJSONObject("wind");
   
         double windSpeed = wind.getDouble("speed");

      
   JSONObject weather = json.getJSONArray("weather").getJSONObject(0);
           
 String description = weather.getString("description");

        
    // Display data
     
       System.out.println("Weather Dashboard for " + cityName);
      
      System.out.println("Temperature: " + temp + "°C");
     
       System.out.println("Humidity: " + humidity + "%");
            
System.out.println("Wind Speed: " + windSpeed + " m/s");
   
         System.out.println("Condition: " + description);

     
   } 
catch (Exception e)
 {
          
  System.out.println("Error fetching weather data: " + e.getMessage());
     
   }
  
  }

}


