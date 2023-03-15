package com.example.cakemachine.net.client;
/*
 * Name: Weijie Zhang
 * Matrikelnummer: s0582504
 */

import com.example.cakemachine.cli.console.ConsoleImpl;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

/**
 * @author Weijie Zhang, weijiezhangpku@gmail.com
 * @version 1.0
 */
public class TCPClient {
    public static void run() {
        try (Socket socket = new Socket("localhost", 9000);
             DataInputStream in = new DataInputStream(socket.getInputStream());
             DataOutputStream out = new DataOutputStream(socket.getOutputStream())) {
            ConsoleImpl console = new ConsoleImpl();
            while (true){
                String cmd = Frontend.getCMD(console);
                out.writeUTF(cmd);
                String responseContent = in.readUTF();
                System.out.print(responseContent);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
