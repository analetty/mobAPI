package com.rest.mobAPI.logica;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;

public class ClientMOB {

	private static DataOutputStream out;
	static Socket sockTCP; 
	public String direccOrg;
	final static int port = 8888;
	static String host = "172.26.232.93";
	
	public void comunicarCoordReplicas(String accion, String modo) {
		try {

			sockTCP = new Socket(host,port);
			out = new DataOutputStream(sockTCP.getOutputStream());
			out.writeUTF(accion);
			if (modo != null) {
				out.writeUTF(modo);
			}

		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}


}
