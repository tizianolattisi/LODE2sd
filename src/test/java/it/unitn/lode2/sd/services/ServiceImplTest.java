package it.unitn.lode2.sd.services;

import it.unitn.lode2.sd.ServiceRegistration;
import it.unitn.lode2.sd.ServiceRegister;
import org.junit.Test;

import java.util.List;

/**
 * User: tiziano
 * Date: 15/06/15
 * Time: 12:33
 */
public class ServiceImplTest {

    @Test
    public void testService() throws Exception {

        // create an register a new Camera service
        List<ServiceRegistration> query = ServiceRegister.query(Camera.class);
        System.out.println(query);

        ServiceRegister.register(Camera.class, "192.168.1.3", 1025, CameraCapability.PAN, CameraCapability.TILT);

        query = ServiceRegister.query(Camera.class);
        System.out.println(query);

        ServiceRegistration registration = query.get(0);

        System.out.println(registration.getHost());

        System.out.println(registration.getCapabilities());

    }

}