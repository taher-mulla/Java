//url -> universal resource locator
//uri -> universal resource identifier
// 1.scheme
// 2.scheme-specific-path
// 3.authority
// 4.user-info
// 5.host
// 6.port
// 7.path
// 8.query
// 9.fragment
// Absolute URI -> specify scheme
// Relative URI -> no scheme

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

public class Main {

    public static void main(String[] args) {

        try {
            //the URI wil li=ook like this
            URI uri = new URI("http://username:password@myserver.com:5000/catalogue/phones?os=android#samsung");

            //parts of the URI
            System.out.println("scheme : "+uri.getScheme());
            System.out.println("scheme-specific-path : "+uri.getSchemeSpecificPart());
            System.out.println("authority : "+uri.getAuthority());
            System.out.println("user-info : "+uri.getUserInfo());
            System.out.println("host : "+uri.getHost());
            System.out.println("port : "+uri.getPort());
            System.out.println("path : "+uri.getPath());
            System.out.println("query : "+uri.getQuery());
            System.out.println("fragment : "+uri.getFragment());


            //converting URI to URL
            URL url = uri.toURL();
            System.out.println("\n URL = "+url);


            //its always better to use a base-URI and a relative-URI in a program
            URI baseURI = new URI("http://username:password@myserver.com:5000");
            URI relativeURI = new URI("/catalogue/phones?os=android#samsung");
            URI resolvedURI = baseURI.resolve(relativeURI);
            System.out.println();
            System.out.println("Base URI : "+baseURI);
            System.out.println("Relative URi : "+relativeURI);
            System.out.println("Resolved URI : "+resolvedURI);

            //Using multiple relative URIs
            URI relativeURI1 = new URI("/catalogue/tvs?manufacturer=samsung");
            URI relativeURI2 = new URI("/stores/location?zip=12345");

            //hence the server can be changed easily without much of trouble, as you will have to only change the base URI
            URI resolvedURI1 = baseURI.resolve(relativeURI1);
            URI resolvedURI2 = baseURI.resolve(relativeURI2);

            //resolving a URI into the base and a relative
            URI resolvedURi = baseURI.relativize(resolvedURI1);



        } catch (URISyntaxException e) {
            e.printStackTrace();
        }catch (MalformedURLException e){
            e.printStackTrace();
        }


    }
}
