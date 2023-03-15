package com.example.cakemachine.net.client;
/*
 * Name: Weijie Zhang
 * Matrikelnummer: s0582504
 */

import com.example.cakemachine.cli.console.ConsoleImpl;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.net.*;
import java.util.Random;

/**
 * @author Weijie Zhang, weijiezhangpku@gmail.com
 * @version 1.0
 */
public class UDPClient {
    private static int port;
    private static DatagramSocket datagramSocket;

    static {
        Random random = new Random();
        while (datagramSocket == null) {
            try {
                port = random.nextInt(999) + 5001;
                datagramSocket = new DatagramSocket(port);
            } catch (SocketException ignored) {
            }
        }
    }

    public static void run() {
        try {
            InetAddress address = InetAddress.getByName("localhost");
            ConsoleImpl console = new ConsoleImpl();
            while (true) {
                String cmd = Frontend.getCMD(console);
                byte[] bytes = cmd.getBytes();

                DatagramPacket packetOut = new DatagramPacket(bytes, bytes.length, address, 5000);
                datagramSocket.send(packetOut);

                byte[] buffer = new byte[1024];
                DatagramPacket packetIn = new DatagramPacket(buffer, buffer.length);
                datagramSocket.receive(packetIn);
                DataInputStream dis = new DataInputStream(new ByteArrayInputStream(packetIn.getData()));

                String responseContent = dis.readUTF();
                System.out.print(responseContent);
            }
        } catch (SocketException e) {
            e.printStackTrace();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
