package it.unitn.lode2.sd;

import java.io.IOException;

/**
 * User: tiziano
 * Date: 15/06/15
 * Time: 12:17
 */
public class Start {

    // nc -u localhost 9999

    public static void main( String[] argv ) throws IOException {

        ResourceDiscoveryServer.build(9999).start();

    }

}
