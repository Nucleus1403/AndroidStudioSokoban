package com.example.shocobangame;

import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientThread extends Thread {
    private String ipAddress;
    private int port;
    private String sendMsg;

    // Constructor
    public ClientThread(String sendMsg) {
        this.ipAddress = "192.168.100.29";
        this.port = 9999;
        this.sendMsg = sendMsg;
    }

    // Methods
    @Override
    public void run() {
        try {
            String ipAddress_ = "192.168.100.29";
            int port_ = 9999;
            Socket clientSocket = new Socket(ipAddress_, port_);

            // Sending message
            PrintWriter printWriter = Helper.getWriter(clientSocket);
            printWriter.println(sendMsg+"\r\n");

            System.out.println("Message send to server! \n");

            Thread.sleep(1000);

            // Reciving message
            BufferedReader bufferedReader = Helper.getReader(clientSocket);
            //String result = bufferedReader.readLine();
            char[] rawPack = new char[1024];
            bufferedReader.read(rawPack);

            String pack = String.valueOf(rawPack);
            Log.d("RESPONSE", pack);
            String[] result = pack.split("#");
            System.out.println("Recived message is: " + result[0]);   // Mesajul contine null-uri, consider doar primul

        }
        catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void RecivedMessage(){

    }

    private void DecodingData(String message){
        System.out.println("Recived message is" + message);
    }
}
