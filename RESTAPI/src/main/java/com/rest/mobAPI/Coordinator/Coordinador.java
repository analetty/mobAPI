package com.rest.mobAPI.Coordinator;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;


public class Coordinador {

    private static DataInputStream in;
    private static DataOutputStream out;
    static Socket sockTCPSA;
    static Socket sockTCPSR1;
    static Socket sockTCPSR2;
    static Socket sockTCPCR;
    final static int SocketSAPort = 8080;
    final static int SocketSR1Port = 19876;
    final static int SocketSR2Port = 19877;
    final static int SocketCRPort = 8888;
    static String SocketSAHost = "127.0.0.1";
    static String SocketSR1Host = "127.0.0.1";
    static String SocketSR2ost = "127.0.0.1";
    static String SocketCRHost = "127.0.0.1";


    public static void main(String[] args) throws IOException {

        ServerSocket servidor;
        String msg;
        String action;

        try {
            System.out.println("¡Ha iniciaco el coordinador de Replicas!");
            servidor = new ServerSocket(SocketCRPort);

            while (true) {

                sockTCPCR = servidor.accept();
                System.out.println(" ");
                System.out.println("Coordinador de Replicas ha establecido una conexión");

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

                } else {
                    System.out.println("ACCIÓN INVALIDA");
                }
                sockTCPCR.close();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

