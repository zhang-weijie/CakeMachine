package com.example.cakemachine.net;/*
 * Name: Weijie Zhang
 * Matrikelnummer: s0582504
 */

import com.example.cakemachine.gl.Automat;
import com.example.cakemachine.net.server.Backend;
import com.example.cakemachine.net.server.TCPServer;
import com.example.cakemachine.net.server.UDPServer;

import java.io.IOException;
import java.net.DatagramSocket;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author Weijie Zhang, weijiezhangpku@gmail.com
 * @version 1.0
 */
public class Server {

    public static void main(String[] args) {

        try {
            if (args.length == 2) {
                int maxCapacity = Integer.parseInt(args[1]);
                Automat automat = new Automat(maxCapacity);
                Backend.init(automat);
                switch (args[0]) {
                    case "TCP":
                        try (ServerSocket serverSocket = new ServerSocket(9000)) {
                            while (true) {
                                Socket socket = serverSocket.accept();
                                TCPServer s = new TCPServer(socket);
                                new Thread(s).start();
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        break;
                    case "UDP":
                        try (DatagramSocket datagramSocket = new DatagramSocket(5000)) {
                            UDPServer s = new UDPServer(datagramSocket, automat);
                            while (true) {
                                s.process();
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        break;
                    default:
                        System.out.println("Unbekannter Protokoll!");
                }
            } else {
                System.out.println("Falsche Anzahl von Argumenten!");
            }
        } catch (Exception exception) {
            System.out.println("Falsche Argumente!");
        }
    }
}