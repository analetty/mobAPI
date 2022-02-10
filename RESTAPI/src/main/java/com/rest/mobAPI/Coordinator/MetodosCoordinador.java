package com.rest.mobAPI.Coordinator;

import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.*;
import java.net.Socket;

public class MetodosCoordinador {

    private static DataInputStream in;
    private static DataOutputStream out;
    private static DataInputStream in2;
    private static DataOutputStream out2;
    static Socket sockTCPSR1;
    static Socket sockTCPSR2;
    final static int SocketSR1Port=8888;
    final static int SocketSR2Port=9999;
    static String  SocketSR1Host="172.26.236.15";
    static String  SocketSR2Host="172.26.236.15";
    static String documento;
    static String response;
    static String response2;
    static String respuesta;

    public  static boolean RestaurarObjetos() throws IOException, ParserConfigurationException, SAXException, TransformerException {

        sockTCPSR1 = new Socket(SocketSR1Host,SocketSR1Port);
        in = new DataInputStream(sockTCPSR1.getInputStream());
        out = new DataOutputStream(sockTCPSR1.getOutputStream());
        out.writeUTF("RESTAURAR");
        documento = in.readUTF();

        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
        Document document = documentBuilder.parse(new InputSource(new StringReader(documento)));
        DOMSource source = new DOMSource(document);
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        StreamResult result = new StreamResult("persistencia.xml");
        transformer.transform(source, result);
        return true;
    }

    public  static String ReplicarObjeto(String action) throws IOException, ParserConfigurationException {

        sockTCPSR1 = new Socket(SocketSR1Host,SocketSR1Port);
        in = new DataInputStream(sockTCPSR1.getInputStream());
        out = new DataOutputStream(sockTCPSR1.getOutputStream());

        sockTCPSR2 = new Socket(SocketSR2Host,SocketSR2Port);
        in2 = new DataInputStream(sockTCPSR2.getInputStream());
        out2 = new DataOutputStream(sockTCPSR2.getOutputStream());

        out.writeUTF("REPLICAR");
        out2.writeUTF("REPLICAR");

        if (action.equals("COMMIT")){
            out.writeUTF("COMMIT");
            out2.writeUTF("COMMIT");

            response = in.readUTF();
            response2 = in2.readUTF();

                if (response.equals("VOTE_COMMIT") && response2.equals("VOTE_COMMIT")){
                    out.writeUTF("GLOBAL_COMMIT");
                    out2.writeUTF("GLOBAL_COMMIT");

                    respuesta = MetodosCoordinador.ObtenerObjetos();

                    out.writeUTF(respuesta);
                    out2.writeUTF(respuesta);
                }

        }else if (action.equals("ABORT")){
            out.writeUTF("ABORT");
            out2.writeUTF("ABORT");

            response = in.readUTF();
            response2 = in2.readUTF();

            if (response.equals("VOTE_ABORT") || response2.equals("VOTE_ABORT")){
                out.writeUTF("GLOBAL_ABORT");
                out2.writeUTF("GLOBAL_ABORT");

                System.out.println("Se Abortó la Replicación");
            }
        }else if (action.equals("AZAR")){
            out.writeUTF("AZAR");
            out2.writeUTF("AZAR");

            response = in.readUTF();
            response2 = in2.readUTF();

            if (response.equals("VOTE_COMMIT") && response2.equals("VOTE_COMMIT")){
                out.writeUTF("GLOBAL_COMMIT");
                out2.writeUTF("GLOBAL_COMMIT");

                System.out.println("Inicia la restauración");
                respuesta = MetodosCoordinador.ObtenerObjetos();

                out.writeUTF(respuesta);
                out2.writeUTF(respuesta);
            }else {
                out.writeUTF("GLOBAL_ABORT");
                System.out.println("Se Abortó la Replicación");
            }

        }


        return null;
    }

    public static String ObtenerObjetos() throws ParserConfigurationException {

        try {

            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(new File("persistencia.xml"));
            return toString(document);

        } catch (Exception e) {
            e.printStackTrace();
            return "ERROR";
        }

    }

    public static String toString(Document doc) {
        try {

            StringWriter sw = new StringWriter();
            TransformerFactory tf = TransformerFactory.newInstance();
            Transformer transformer = tf.newTransformer();
            transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "no");
            transformer.setOutputProperty(OutputKeys.METHOD, "xml");
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
            transformer.transform(new DOMSource(doc), new StreamResult(sw));
            return sw.toString();

        } catch (Exception ex) {

            throw new RuntimeException("Error converting to String", ex);

        }
    }

}


