package it.unitn.lode2.sd.services;


import it.unitn.lode2.sd.AbstractServiceImpl;
import it.unitn.lode2.sd.Capability;
import it.unitn.lode2.sd.Service;


/**
 * User: tiziano
 * Date: 15/06/15
 * Time: 12:27
 */

public final class Camera extends AbstractServiceImpl implements Service {

    private static final String name = "CAMERA";
    public static final Class<? extends Capability> capabilities = CameraCapability.class;

}
