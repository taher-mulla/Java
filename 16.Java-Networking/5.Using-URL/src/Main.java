import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class Main {

    public static void main(String[] args) {

        //we are using a high level API and hence don't need to use any socket and stuff

        try {

            URL url = new URL("http://example.org");

            //instead of doing this directly we can do the below also
            BufferedReader inputStream = new BufferedReader(new InputStreamReader(url.openStream()));


//            //this will return an instance of the class
//            URLConnection urlConnection = url.openConnection();
//            //you can now set any values you want of the connection b4 opening the connection
//            urlConnection.setDoOutput(true);
//            //opening connection
//            urlConnection.connect();
//            BufferedReader inputStream = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));


            String line="";
            while(line!=null){
                line= inputStream.readLine();
                System.out.println(line);
            }
            inputStream.close();

        }catch (IOException e){
            System.out.println("Error in IO : "+e.getMessage());
        }



    }
}
