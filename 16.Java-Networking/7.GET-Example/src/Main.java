import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Map;

public class Main {

    public static void main(String[] args) {

        try {

            URL url = new URL("http://example.org");

            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            //setting the connection properties
            urlConnection.setRequestMethod("GET");
            urlConnection.setRequestProperty("User-Agent", "Chrome");
            urlConnection.setReadTimeout(30000);


            //response codes are sent by a website to show the status of the request
            //eg: code 404 = server could not find the webpage
            //eg: code 200 = successful in finding and has sent the required page back
            int responseCode = urlConnection.getResponseCode();//getResponseCode() performs the .connect() internally
            System.out.println("Response Code : "+responseCode);

            if(responseCode!=200){
                System.out.println("Error reading page ");
                //this will give us more details on whe response
                System.out.println("Response message : "+urlConnection.getResponseMessage());
                return;
            }

            BufferedReader inputStream = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));

            String line;
            while((line= inputStream.readLine()) != null){
                System.out.println(line);
            }

            inputStream.close();

        } catch (IOException e) {
            System.out.println("Error in IO : " + e.getMessage());
        }

    }
}
