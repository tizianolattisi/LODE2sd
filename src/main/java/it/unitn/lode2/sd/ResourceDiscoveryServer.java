package it.unitn.lode2.sd;

import it.unitn.lode2.sd.services.Camera;

import java.io.IOException;
import java.net.*;
import java.util.*;

/**
 * User: tiziano
 * Date: 15/06/15
 * Time: 14:48
 */
public class ResourceDiscoveryServer {

    private static final int SERVER_PORT = 9999;
    private static final int BUFFER_SIZE = 1024;

    private DatagramSocket socket;
    private byte buffer[] = new byte[BUFFER_SIZE];



    private static final Map<String, Class<? extends Service>> servicesMap;
    static
    {
        servicesMap = new HashMap<>();
        servicesMap.put("CAMERA", Camera.class);
    }

    public static ResourceDiscoveryServer build() throws SocketException {
        ResourceDiscoveryServer server = new ResourceDiscoveryServer();
        server.socket = new DatagramSocket(SERVER_PORT);
        return server;
    }

    public static ResourceDiscoveryServer build(int port) throws SocketException {
        ResourceDiscoveryServer server = new ResourceDiscoveryServer();
        server.socket = new DatagramSocket(port);
        return server;
    }

    public static ResourceDiscoveryServer build(int port, String host) throws UnknownHostException, SocketException {
        InetAddress inetAddress = InetAddress.getByName(host);
        ResourceDiscoveryServer server = new ResourceDiscoveryServer();
        server.socket = new DatagramSocket(port, inetAddress);
        return server;
    }

    public void start() throws IOException {

        while(true){

            // receive request
            DatagramPacket reqPacket = new DatagramPacket(buffer, buffer.length);
            socket.receive(reqPacket);
            String req = new String(reqPacket.getData(), 0, reqPacket.getLength());

            // eval request
            String res = parse(req);

            // send response
            InetAddress address = reqPacket.getAddress();
            int port = reqPacket.getPort();
            DatagramPacket resPacket = new DatagramPacket(res.getBytes(), res.getBytes().length, address, port);
            socket.send(resPacket);

        }
    }

    // REGISTER CAMERA 192.168.1.3 9998 WITH PAN, TILT, ZOOM
    // REGISTER CAMERA 192.168.1.3 9998
    // QUERY CAMERA
    // QUERY CAMERA WITH PAN, TILT

    private String parse(String s) throws IOException {
        s = s.replace("\n", "");
        s = s.replace("\r", "");
        List<String> tkns = Arrays.asList(s.split(" "));
        String command = tkns.get(0);
        switch( command ){
            case "REGISTER":
                String service = tkns.get(1);
                String host = tkns.get(2);
                Integer port = Integer.parseInt(tkns.get(3));
                register(service, host, port);
                return "OK\n";
            case "QUERY":
                service = tkns.get(1);
                List<String> services = query(service);
                StringBuffer sb = new StringBuffer();
                for( String serviceReg: services ){
                    sb.append(serviceReg);
                    sb.append("\n");
                }
                return sb.toString();
            default:
                 return null;
        }

    }

    private void register(String serviceName, String host, Integer port){
        ServiceRegister.register(servicesMap.get(serviceName), host, port);
    }

    private List<String> query(String serviceName){
        List<ServiceRegistration> registrations = ServiceRegister.query(servicesMap.get(serviceName));
        List<String> services = new ArrayList<>();
        for( ServiceRegistration registration: registrations ){
            services.add(registration.getHost() + " " + registration.getPort());
        }
        return services;
    }

}
