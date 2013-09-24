/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ads.blWS.logica;

import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;

/**
 *
 * @author Julião
 */
@WebService(serviceName = "BuscaLugaresWS")
public class BuscaLugaresWS {

    /**
     * This is a sample web service operation
     */
    @WebMethod(operationName = "PlaceSearch")
    public String PlaceSearch(@WebParam(name = "query") String busqueda, @WebParam(name = "place") String ciudad, @WebParam(name = "type") String tipo) {
        return BuscaLugares.buscarLugares(busqueda, ciudad, tipo);
    }
    
    @WebMethod(operationName = "PlaceDetails")
    public String PlaceDetails(@WebParam(name = "reference") String referencia) {
        return BuscaLugares.verDetalles(referencia);
    }
}
