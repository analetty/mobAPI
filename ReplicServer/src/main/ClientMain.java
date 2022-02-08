package main;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;

public class ClientMain {
	
	private static DataInputStream in;
	private static DataOutputStream out;
	static Socket sockTCP; 
    public String direccOrg;
	final static int port = 19876;
	static String host = "127.0.0.1";

	public static void main(String[] args) {
		try {
			
			sockTCP = new Socket(host,port);
			in = new DataInputStream(sockTCP.getInputStream());
			out = new DataOutputStream(sockTCP.getOutputStream());
//			out.writeUTF("RESTAURAR");
//			System.out.println(in.readUTF());
			out.writeUTF("REPLICAR");
			out.writeUTF("AZAR");
			System.out.println(in.readUTF());
			out.writeUTF("GLOBAL_ABORT");
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
    		DocumentBuilder builder = factory.newDocumentBuilder();
			Document document = builder.parse(new File("persistencia2.xml"));
			out.writeUTF(ServerMethod.toString(document));


		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}


	}

}
