/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ads.blWS.logica;


import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
/**
 *
 * @author Julião
 */
public class BuscaLugares {
    public static String s_url = "https://maps.googleapis.com/maps/api/place/textsearch/xml";
    public static String d_url = "https://maps.googleapis.com/maps/api/place/details/xml";
    public static final String apiKey = "AIzaSyC_qCOTjhUSHj1XRUXhVpoUGS7GJUpPGgE";

    public static boolean conectar() {

        return true;
    }

    // PlaceSearch
    public static List<Lugar> buscarLugares(String busqueda, String ciudad, String tipo) {
        HttpClient client = new DefaultHttpClient();
        String responseBody = null;

        busqueda = toGetReady(busqueda);
        ciudad = toGetReady(ciudad);

        String uri = s_url + "?" + "key=" + apiKey + "&sensor=true" + "&query=" + busqueda + "+in+" + ciudad;
        if (tipo != null) {
            uri = uri + ("&types=" + tipo);
        }

        HttpGet request = new HttpGet(uri);

        try {
            HttpResponse response = client.execute(request);
            responseBody = EntityUtils.toString(response.getEntity());
        } catch (IOException ex) {
            Logger.getLogger(BuscaLugares.class.getName()).log(Level.SEVERE, null, ex);
        }
        return procesarbuscarLugaresXML(responseBody);
    }

    // PlaceDetails
    public static Lugar verDetalles(String referencia) {
        HttpClient client = new DefaultHttpClient();
        String responseBody = null;

        String uri = d_url + "?" + "key=" + apiKey + "&sensor=true" + "&reference=" + referencia;

        HttpGet request = new HttpGet(uri);

        try {
            HttpResponse response = client.execute(request);
            responseBody = EntityUtils.toString(response.getEntity());
        } catch (IOException ex) {
            Logger.getLogger(BuscaLugares.class.getName()).log(Level.SEVERE, null, ex);
        }
        return procesarverDetallesXML(responseBody);
    }

    public static List<Lugar> procesarbuscarLugaresXML(String xml) {
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db;
        List<Lugar> resultados = new ArrayList<>();
        //String resultados = "";

        try {
            db = dbf.newDocumentBuilder();
            Document doc = db.parse(new InputSource(new ByteArrayInputStream(xml.getBytes("utf-8"))));
            doc.getDocumentElement().normalize();

            Element rootElement = doc.getDocumentElement();
            NodeList results = rootElement.getElementsByTagName("result");

            for (int i = 0; i < results.getLength(); i++) {
                Element e = (Element) results.item(i);

                String nombre = "~";
                NodeList name = e.getElementsByTagName("name");
                if (name.getLength() != 0) {
                    Element e1 = (Element) name.item(0);
                    nombre = e1.getTextContent();
                }

                String direccion = "~";
                NodeList address = e.getElementsByTagName("formatted_address");
                if (address.getLength() != 0) {
                    Element e2 = (Element) address.item(0);
                    direccion = e2.getTextContent();
                }

                String clas = "~";
                NodeList rating = e.getElementsByTagName("rating");
                if (rating.getLength() != 0) {
                    Element e3 = (Element) rating.item(0);
                    clas = e3.getTextContent();
                }

                String icono = "~";
                NodeList icon = e.getElementsByTagName("icon");
                if (icon.getLength() != 0) {
                    Element e4 = (Element) icon.item(0);
                    icono = e4.getTextContent();
                }

                String referencia = "~";
                NodeList reference = e.getElementsByTagName("reference");
                if (reference.getLength() != 0) {
                    Element e5 = (Element) reference.item(0);
                    referencia = e5.getTextContent();
                }

                String ident = "~";
                NodeList id = e.getElementsByTagName("id");
                if (id.getLength() != 0) {
                    Element e6 = (Element) id.item(0);
                    ident = e6.getTextContent();
                }

                Lugar resultado = new Lugar(nombre, direccion, clas, icono, referencia, ident);
                resultados.add(resultado);
                
                /*
                String resultado = "Name: " + nombre + "\n"
                        + "address: " + direccion + "\n"
                        + "rating: " + clas + "\n"
                        + "icon: " + icono + "\n"
                        + "reference: " + referencia + "\n"
                        + "id: " + ident + "\n";
                resultados = resultados + resultado + "\n";
                */
            }

        } catch (SAXException ex) {
            Logger.getLogger(BuscaLugares.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(BuscaLugares.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParserConfigurationException ex) {
            Logger.getLogger(BuscaLugares.class.getName()).log(Level.SEVERE, null, ex);
        }

        return resultados;
    }

    public static Lugar procesarverDetallesXML(String xml) {
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db;
        Lugar resultado = new Lugar();
        //String resultado = "";

        try {
            db = dbf.newDocumentBuilder();
            Document doc = db.parse(new InputSource(new ByteArrayInputStream(xml.getBytes("utf-8"))));
            doc.getDocumentElement().normalize();

            Element rootElement = doc.getDocumentElement();
            NodeList results = rootElement.getElementsByTagName("result");

            Element e = (Element) results.item(0);

            String nombre = "~";
            NodeList name = e.getElementsByTagName("name");
            if (name.getLength() != 0) {
                Element e1 = (Element) name.item(0);
                nombre = e1.getTextContent();
            }

            String direccion = "~";
            NodeList address = e.getElementsByTagName("formatted_address");
            if (address.getLength() != 0) {
                Element e2 = (Element) address.item(0);
                direccion = e2.getTextContent();
            }

            String clas = "~";
            NodeList rating = e.getElementsByTagName("rating");
            if (rating.getLength() != 0) {
                Element e3 = (Element) rating.item(0);
                clas = e3.getTextContent();
            }

            String icono = "~";
            NodeList icon = e.getElementsByTagName("icon");
            if (icon.getLength() != 0) {
                Element e4 = (Element) icon.item(0);
                icono = e4.getTextContent();
            }

            String referencia = "~";
            NodeList reference = e.getElementsByTagName("reference");
            if (reference.getLength() != 0) {
                Element e5 = (Element) reference.item(0);
                referencia = e5.getTextContent();
            }

            String ident = "~";
            NodeList id = e.getElementsByTagName("id");
            if (id.getLength() != 0) {
                Element e6 = (Element) id.item(0);
                ident = e6.getTextContent();
            }

            String veci = "~";
            NodeList vicinity = e.getElementsByTagName("vicinity");
            if (vicinity.getLength() != 0) {
                Element e7 = (Element) vicinity.item(0);
                veci = e7.getTextContent();
            }

            String phone = "~";
            NodeList phone_number = e.getElementsByTagName("formatted_phone_number");
            if (phone_number.getLength() != 0) {
                Element e8 = (Element) phone_number.item(0);
                phone = e8.getTextContent();
            }

            String purl = "~";
            NodeList url_p = e.getElementsByTagName("url");
            if (url_p.getLength() != 0) {
                Element e9 = (Element) url_p.item(0);
                purl = e9.getTextContent();
            }

            String international_phone = "~";
            NodeList international_phone_number = e.getElementsByTagName("international_phone_number");
            if (international_phone_number.getLength() != 0) {
                Element e10 = (Element) international_phone_number.item(0);
                international_phone = e10.getTextContent();
            }

            String web = "~";
            NodeList website = e.getElementsByTagName("website");
            if (website.getLength() != 0) {
                Element e11 = (Element) website.item(0);
                web = e11.getTextContent();
            }
            
            Lugar resu = new Lugar(nombre, direccion, clas, icono, referencia, ident, veci, phone, international_phone, purl, web);
            resultado = resu;

            /*
            resultado = "Name: " + nombre + "\n"
                    + "address: " + direccion + "\n"
                    + "rating: " + clas + "\n"
                    + "icon: " + icono + "\n"
                    + "reference: " + referencia + "\n"
                    + "id: " + ident + "\n"
                    + "vicinity: " + veci + "\n"
                    + "formatted phone number: " + phone + "\n"
                    + "url: " + purl + "\n"
                    + "international_phone_number: " + international_phone + "\n"
                    + "website: " + web + "\n";
            */

        } catch (SAXException ex) {
            Logger.getLogger(BuscaLugares.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(BuscaLugares.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParserConfigurationException ex) {
            Logger.getLogger(BuscaLugares.class.getName()).log(Level.SEVERE, null, ex);
        }

        return resultado;
    }

    public static String toGetReady(String text) {
        String readytoGet = "";

        for (int i = 0; i < text.length(); i++) {
            if (text.charAt(i) == ' ') {
                readytoGet = readytoGet + "%20";
            } else {
                readytoGet = readytoGet + text.charAt(i);
            }
        }

        return readytoGet;
    }
}
