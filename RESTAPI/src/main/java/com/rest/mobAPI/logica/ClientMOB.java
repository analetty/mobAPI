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

	private static DataInputStream in;
	private static DataOutputStream out;
	static Socket sockTCP; 
	public String direccOrg;
	final static int port = 4040;
	static String host = "127.0.0.1";

	public void restaurar() {
		try {

			sockTCP = new Socket(host,port);
			in = new DataInputStream(sockTCP.getInputStream());
			out = new DataOutputStream(sockTCP.getOutputStream());
			out.writeUTF("RESTAURAR");
			System.out.println(in.readUTF());

		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}


	}

	public void replicar(String modo) {
		try {

			sockTCP = new Socket(host,port);
			in = new DataInputStream(sockTCP.getInputStream());
			out = new DataOutputStream(sockTCP.getOutputStream());
			out.writeUTF("REPLICAR");
			out.writeUTF(modo);
			System.out.println(in.readUTF());

		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
