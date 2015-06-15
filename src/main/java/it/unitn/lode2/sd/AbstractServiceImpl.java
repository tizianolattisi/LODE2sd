package it.unitn.lode2.sd;

import java.util.List;

/**
 * User: tiziano
 * Date: 15/06/15
 * Time: 12:29
 */
public abstract class AbstractServiceImpl implements Service {

    private final static String name = "";
    private final static List<Capability> capabilities = null;

    public static String name() {
        return name;
    }

    public static List<Capability> capabilities() {
        return capabilities;
    }

}
