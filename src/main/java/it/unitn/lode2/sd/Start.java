package it.unitn.lode2.sd;

import java.io.IOException;

/**
 * User: tiziano
 * Date: 15/06/15
 * Time: 12:17
 */
public class Start {

    // nc -u localhost 9999

    /*
    $ nc -u 192.168.1.2 9999
    REGISTER LIGHT 192.168.1.3 9998 ON OFF DIMMER
    OK

    $ nc -u 192.168.1.2 9999
    QUERY LIGHT ON OFF
    192.168.1.3 9998
     */

    public static void main( String[] argv ) throws IOException {

        ResourceDiscoveryServer.build(9999).start();

    }

}
