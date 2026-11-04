import java.net.*;
import java.net.http.*;

public class NetworkInspector{
    public static void main(String[] domain) {
            if (domain.length != 1) {
                System.out.println("Usage: java NetworkInspector <hostname>");// prints line one
                return;// guard
            }
            String host = domain[0];


            //HTTP Connection

            try {
                InetAddress[] resolvedList = InetAddress.getAllByName(host);
                //resolver requst to translate host name

                System.out.println("Hostname:\n" + host + "\n");
                System.out.println("Resolved Addresses:");

                for (InetAddress oneAddress : resolvedList ) {
                    System.out.println(oneAddress.getHostAddress());
                }

            } catch (UnknownHostException e) {
                System.out.println("DNS lookup failed for: " + host);// Catch gaurd
                return;
            }

            //HTTPS request/reponce
            try {
                HttpClient client = HttpClient.newBuilder()
                        .followRedirects(HttpClient.Redirect.NORMAL)
                        .build();

                HttpRequest request = HttpRequest.newBuilder(URI.create("https://" + host)).build();
                HttpResponse<byte[]> response = client.send(request, HttpResponse.BodyHandlers.ofByteArray());

                String tlsVersion = response.sslSession().get().getProtocol();//Calls from import lib
                int size = response.body().length;

                //Text Builder based off of the homework example.
                System.out.println("\nHTTPS Conncetion Sucessful\n");
                System.out.println("Status Code:\n" + response.statusCode() + "\n");
                System.out.println("TLS Version:\n" + tlsVersion + "\n");
                System.out.println("Response Size:\n" + size + " bytes");
            }

            catch (Exception e) {
                System.out.println("HTTPS failed: " + e.getMessage());
            }


    }
}