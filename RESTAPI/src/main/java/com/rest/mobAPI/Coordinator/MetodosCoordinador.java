package com.rest.mobAPI.Coordinator;

import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.StringReader;
import java.net.Socket;

public class MetodosCoordinador {

    private static DataInputStream in;
    private static DataOutputStream out;
    static Socket sockTCPSR1;
    static Socket sockTCPSR2;
    final static int SocketSR1Port=19876;
    final static int SocketSR2Port=19877;
    static String  SocketSR1Host="127.0.0.1";
    static String  SocketSR2ost="127.0.0.1";
    static String documento;

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


    public  static String ReplicarObjeto(String action) {

        return null;
    }

}


