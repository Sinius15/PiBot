package com.sinius15.gyro;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

public class SendSocket{

    private Socket sock;

    private PrintWriter out;

    public SendSocket(Socket sok) throws IOException {
        this.sock = sok;
        out = new PrintWriter(sock.getOutputStream(), true);
    }

    public void sendData(float x, float y, float z){
        out.println(x + ";" + y + ";" + z);
    }

    public void close() throws IOException {
        out.close();
        sock.close();
    }
}
