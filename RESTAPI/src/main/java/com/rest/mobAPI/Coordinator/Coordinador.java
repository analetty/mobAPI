package com.rest.mobAPI.Coordinator;

import javax.xml.parsers.ParserConfigurationException;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;


public class Coordinador {

    private static DataInputStream in;
    private static DataOutputStream out;

    static Socket sockTCPCR;
    final static int SocketCRPort = 8888;

    public static void main(String[] args) throws IOException {

        ServerSocket servidor;
        String msg;
        String action;

        try {
            System.out.println("¡Ha Iniciado el Coordinador de Replicas!");
            servidor = new ServerSocket(SocketCRPort);

            while (true) {

                sockTCPCR = servidor.accept();
                System.out.println(" ");
                System.out.println("El Coordinador de Replicas ha Establecido una Conexión");

                in = new DataInputStream(sockTCPCR.getInputStream());
                out = new DataOutputStream(sockTCPCR.getOutputStream());
                msg = in.readUTF();
                if (msg.equals("RESTAURAR")) {

                    try {
                        System.out.println("Inicio de Restauración de los Objetos");
                        MetodosCoordinador.RestaurarObjetos();
                        System.out.println("Restauración realizada con Éxito");
                    } catch (Exception e) {
                        out.writeUTF("Ocurrio un ERROR");
                        System.out.println("¡ERROR! Restauración fallida.");

                    }

                } else if (msg.equals("REPLICAR")) {
                    System.out.println("Esperando Acción de Replicación...");
                    action = in.readUTF();

                    if (action.equals("COMMIT")){
                        System.out.println("Se ha recibido la Acción: " + action);
                        System.out.println("Enviando la petición a los Servidores de Replicas");
                        MetodosCoordinador.ReplicarObjeto(action);
                        System.out.println("Replicacion Realizada con Éxito");

                    }else if (action.equals("ABORT")){
                        System.out.println("Se ha recibido la Acción: "+ action);
                        MetodosCoordinador.ReplicarObjeto(action);
                        System.out.println("Replicacion Abortada con Éxito");
                    }else if (action.equals("AZAR")){
                        System.out.println("Se ha recibido la Acción: "+ action);
                        MetodosCoordinador.ReplicarObjeto(action);
                    }
                    System.out.println("Fin del proceso de Replicación");
                } else {
                    System.out.println("ACCIÓN INVALIDA");
                }
                sockTCPCR.close();
            }

        } catch (IOException | ParserConfigurationException e) {
            e.printStackTrace();
        }
    }
}

