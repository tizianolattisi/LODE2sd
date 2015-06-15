package it.unitn.lode2.sd;

import java.util.Arrays;
import java.util.List;

/**
 * User: tiziano
 * Date: 15/06/15
 * Time: 13:05
 */
public class ServiceRegistration {

    private final Class<? extends Service> service;
    private final String host;
    private final Integer port;
    private final List<Capability> capabilities;

    public ServiceRegistration(Class<? extends Service> service, String host, Integer port, Capability... capabilities) {
        this.service = service;
        this.host = host;
        this.port = port;
        this.capabilities = Arrays.asList(capabilities);
    }

    public Class<? extends Service> getService() {
        return service;
    }

    public String getHost() {
        return host;
    }

    public Integer getPort() {
        return port;
    }

    public List<Capability> getCapabilities() {
        return capabilities;
    }
}
