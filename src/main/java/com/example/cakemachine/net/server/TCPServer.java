package com.example.cakemachine.net.server;
/*
 * Name: Weijie Zhang
 * Matrikelnummer: s0582504
 */


import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

/**
 * @author Weijie Zhang, weijiezhangpku@gmail.com
 * @version 1.0
 */
public class TCPServer implements Runnable {
    private Socket socket;

    public TCPServer(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try (DataInputStream in = new DataInputStream(socket.getInputStream());
             DataOutputStream out = new DataOutputStream(socket.getOutputStream())) {
            while (true) {
                String cmd = in.readUTF();
                String response = Backend.processRequest(cmd);
                out.writeUTF(response);
            }
        } catch (IOException ignored) {
        }
    }
}
