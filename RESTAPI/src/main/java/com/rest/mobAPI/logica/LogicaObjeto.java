package com.rest.mobAPI.logica;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Random;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.springframework.http.HttpStatus;
import org.springframework.web.client.HttpClientErrorException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.rest.mobAPI.controlador.PeticionRestMOB;
import com.rest.mobAPI.dominio.ModeloObjeto;


public class LogicaObjeto {
	
	public String consultarObjeto(ModeloObjeto objeto) throws Exception {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document document = builder.parse(new File("persistencia.xml"));
        List<ModeloObjeto> objetos = new ArrayList<>();
        String respuesta;
        NodeList nodeList = document.getDocumentElement().getChildNodes();
        for (int i = 0; i < nodeList.getLength(); i++) {
            Node node = nodeList.item(i);
            if (node.getNodeType() == Node.ELEMENT_NODE) {
            	Element elem = (Element) node;
            	if(elem.getElementsByTagName("id")
                        .item(0).getChildNodes().item(0).getNodeValue().equals(objeto.getId())) {
            		
            		String id = elem.getElementsByTagName("id")
                            .item(0).getChildNodes().item(0).getNodeValue();
                    String fechaCreacion = elem.getElementsByTagName("fechaCreacion")
                            .item(0).getChildNodes().item(0).getNodeValue();
                    String nombre = elem.getElementsByTagName("nombre").item(0)
                            .getChildNodes().item(0).getNodeValue();
                    String accion =elem.getElementsByTagName("accion").item(0)
                            .getChildNodes().item(0).getNodeValue();
                    ModeloObjeto obj = new ModeloObjeto(id, nombre, accion, fechaCreacion);
                    objetos.add(obj);	
            	} 
            }
        }
        if(objetos.size() > 0) {
        	final ObjectMapper mapper = new ObjectMapper();
            respuesta = mapper.writeValueAsString(objetos);
            return respuesta;
        } else {
        	throw new HttpClientErrorException(HttpStatus.NOT_FOUND);
        }
        
	}
	
	public String consultarObjetos() throws Exception {
		
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document document = builder.parse(new File("persistencia.xml"));
        List<ModeloObjeto> objetos = new ArrayList<>();
        String respuesta;
        NodeList nodeList = document.getDocumentElement().getChildNodes();
        for (int i = 0; i < nodeList.getLength(); i++) {
            Node node = nodeList.item(i);
            if (node.getNodeType() == Node.ELEMENT_NODE) {
                Element elem = (Element) node;
                String id = elem.getElementsByTagName("id")
                        .item(0).getChildNodes().item(0).getNodeValue();
                String fechaCreacion = elem.getElementsByTagName("fechaCreacion")
                        .item(0).getChildNodes().item(0).getNodeValue();
                String nombre = elem.getElementsByTagName("nombre").item(0)
                        .getChildNodes().item(0).getNodeValue();
                String accion =elem.getElementsByTagName("accion").item(0)
                        .getChildNodes().item(0).getNodeValue();
                ModeloObjeto objeto = new ModeloObjeto(id, nombre, accion, fechaCreacion);
                objetos.add(objeto);
 
            }
        }
        
        final ObjectMapper mapper = new ObjectMapper();
        System.out.println(mapper.writeValueAsString(objetos));
        respuesta = mapper.writeValueAsString(objetos);
        return respuesta;
        		
	}
	
	public String crearObjeto(PeticionRestMOB peticion) throws TransformerException, ParserConfigurationException, SAXException, IOException {
		DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
        Document document = documentBuilder.parse("persistencia.xml");
        Element root = document.getDocumentElement();

        Collection<ModeloObjeto> objetos = new ArrayList<ModeloObjeto>();
        objetos.add(peticion.obtenerObjeto());

        for (ModeloObjeto objeto : objetos) {
            // server elements
            Element nuevoObjeto = document.createElement("objeto");
            
            Element id = document.createElement("id");
            id.appendChild(document.createTextNode(objeto.getId()));
            nuevoObjeto.appendChild(id);

            Element fecha = document.createElement("fechaCreacion");
            fecha.appendChild(document.createTextNode(objeto.getFechaCreacion().toString()));
            nuevoObjeto.appendChild(fecha);
            
            Element nombre = document.createElement("nombre");
            nombre.appendChild(document.createTextNode(objeto.getNombre()));
            nuevoObjeto.appendChild(nombre);

            Element accion = document.createElement("accion");
            accion.appendChild(document.createTextNode(objeto.getAccion()));
            nuevoObjeto.appendChild(accion);

            root.appendChild(nuevoObjeto);
        }

        DOMSource source = new DOMSource(document);

        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        StreamResult result = new StreamResult("persistencia.xml");
        transformer.transform(source, result);
        
        final ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(peticion.obtenerObjeto());
	}
	
	public void eliminarObjeto(ModeloObjeto objeto) throws Exception {
		//Logica de eliminar objeto
		Boolean eliminar = false;
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document document = builder.parse(new File("persistencia.xml"));
        Element root = document.getDocumentElement();
        NodeList nodeList = root.getChildNodes();
        for (int i = 0; i < nodeList.getLength(); i++) {
        
            Node node = nodeList.item(i);
            if (node.getNodeType() == Node.ELEMENT_NODE) {
            	Element elem = (Element) node;
            	if(elem.getElementsByTagName("id")
                        .item(0).getChildNodes().item(0).getNodeValue().equals(objeto.getId())) {
            		root.removeChild(elem);
            		eliminar = true;
            		break;	
            	}
            }
        }
        
        if (eliminar) {
        	DOMSource source = new DOMSource(document);

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            StreamResult result = new StreamResult("persistencia.xml");
            transformer.transform(source, result);
        } else {
        	throw new HttpClientErrorException(HttpStatus.NOT_FOUND);
        }
	}
	
	public void replicar() {
		
	}
	
	public void restaurar() {
		
	}
	
	
}
