package it.unitn.lode2.sd;

import java.util.*;

/**
 * User: tiziano
 * Date: 15/06/15
 * Time: 13:03
 */
public class ServiceRegister {

    private static Map<Class<? extends Service>, List<ServiceRegistration>> register = new HashMap<>();


    public static void register(Class<? extends Service> service, String host, Integer port, Capability...capabilities ){
        if( !register.keySet().contains(service) ){
            register.put(service, new ArrayList<>());
        }
        ServiceRegistration registration = new ServiceRegistration(service, host, port, capabilities);
        register.get(service).add(registration);
    }

    public static List<ServiceRegistration> query(Class<? extends Service> service){
        if( register.keySet().contains(service) ) {
            return register.get(service);
        }
        return new ArrayList<>();
    }
}
