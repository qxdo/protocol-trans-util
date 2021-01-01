package com.main.run;

import com.domain.ProtocolElement;
import com.protocol.ProtocolRequest;
import com.protocol.TestProtocolRequest;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.List;

public class TestMain {

    public String sendMessage(String message) throws Exception {
        String data = "Hello, this is protocol-util.";
        ProtocolRequest protocolRequest = new TestProtocolRequest();

        protocolRequest.setParams(data.getBytes().length, data.getBytes());


        byte[] dataPacked = protocolRequest.packAll();

        String ip = "127.0.0.1";
        // use your own port.
        int port = 1234;
        byte[] dataReturned = sendUdp(ip, port, dataPacked);
        List<ProtocolElement> elements = protocolRequest.unpackAll(dataReturned);
        String returnedStr = new String((byte[])elements.get(1).getValue());
        System.out.println("return ret: " + returnedStr);
        return returnedStr;
    }

    public byte[] sendUdp(String  ip, int port, byte [] buf) throws IOException {
        InetAddress address=InetAddress.getByName(ip);
        DatagramPacket packet=new DatagramPacket(buf, buf.length, address, port);
        DatagramSocket socket=new DatagramSocket();
        socket.send(packet);
        byte[] data2=new byte[1024];
        DatagramPacket packet2 = new DatagramPacket(data2, data2.length);
        socket.receive(packet2);
        socket.close();
        return data2;
    }
}
