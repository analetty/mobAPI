package main;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketImpl;
import java.time.LocalDateTime;
import java.util.Random;

import javax.xml.parsers.ParserConfigurationException;


public class ServerMain {
	
	private static DataInputStream in;
	private static DataOutputStream out;
	static Socket sockTCP; 
    public static String direccOrg;
    
    public static void main(String[] args){
    	ServerSocket servidor;
		String msg;
		String log;
		int port = 19876;
		
		try {
			
			System.out.println("");
            System.out.println ("�Inicio del servidor TCP!");
			servidor = new ServerSocket(port);
			while(true) {	
				
				sockTCP = servidor.accept();
				
				System.out.println("");
	            System.out.println ("Servidor: Conexi�n establecida.");
				in = new DataInputStream(sockTCP.getInputStream());
				out = new DataOutputStream(sockTCP.getOutputStream());		
				msg = in.readUTF();
				if(msg.equals("RESTAURAR")) {
					
					try {
						String response = ServerMethod.RestaurarObjetos();
						out.writeUTF(response);
						

					} catch (Exception e) {
						System.out.println(e);
						out.writeUTF("ERROR");
					}
					
				}else if(msg.equals("REPLICAR")){
					String action = in.readUTF();
					out.writeUTF(ReturnVote(action));
					System.out.println(action);
					msg = in.readUTF();
					
					if(msg.equals("GLOBAL_COMMIT")) {
						
						msg = in.readUTF();
						System.out.println(msg);
						ServerMethod.ReplicarObjetos(msg);
						
					}	
				
				}else {
					out.writeUTF("ACCION INVALIDA");
				}
				sockTCP.close();
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }


public  static String ReturnVote(String action) {
	
		String[] response = {"VOTE_COMMIT","VOTE_ABORT"};
		Random aleatorio = new Random(System.currentTimeMillis());
		int intAletorio = aleatorio.nextInt(2);
		if(action.equals("COMMIT")) {
			return response[0];
		}else if(action.equals("ABORT")) {
			return response[1];
		}
		return response[intAletorio];
}

}
