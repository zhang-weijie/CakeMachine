package com.example.cakemachine.net.server;
/*
 * Name: Weijie Zhang
 * Matrikelnummer: s0582504
 */

import com.example.cakemachine.gl.Automat;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.net.UnknownHostException;

/**
 * @author Weijie Zhang, weijiezhangpku@gmail.com
 * @version 1.0
 */
public class UDPServer {
    private DatagramSocket datagramSocket;
    private Automat automat;

    public UDPServer(DatagramSocket datagramSocket, Automat automat) {
        this.datagramSocket = datagramSocket;
        this.automat = automat;
    }

    public void process() {
        try (ByteArrayOutputStream bos = new ByteArrayOutputStream(1024);
             DataOutputStream dos = new DataOutputStream(bos)) {
            byte[] buffer = new byte[1024];
            DatagramPacket packetIn = new DatagramPacket(buffer, buffer.length);
            datagramSocket.receive(packetIn);
            String cmd = new String(packetIn.getData(), 0, packetIn.getLength());
            String responseStr = Backend.processRequest(cmd);
            dos.writeUTF(responseStr);
            byte[] response = bos.toByteArray();
            DatagramPacket packetOut = new DatagramPacket(response, response.length, packetIn.getAddress(), packetIn.getPort());
            datagramSocket.send(packetOut);
            bos.reset();
        } catch (SocketException e) {
            e.printStackTrace();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
