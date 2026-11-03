import java.net.*;

public class NetworkInspector{
    public static void main(String[] domain) {
            if (domain.length != 1) {
                System.out.println("Usage: java NetworkInspector <hostname>");// prints line one
                return;// guard
            }
            String host = domain[0];

            try {
                InetAddress[] resolvedList = InetAddress.getAllByName(host);
                //resolver requst to translate host name

                System.out.println("Hostname:\n" + host + "\n");
                System.out.println("Resolved Addresses:");

                for (InetAddress oneAddress : resolvedList ) {
                    System.out.println(oneAddress.getHostAddress());
                }

            } catch (UnknownHostException e) {
                System.out.println("DNS lookup failed for: " + host);
                return;
            }
    }
}