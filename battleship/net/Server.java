package net;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ArrayBlockingQueue;

public class Server {
	private ServerSocket acceptor;
	public ArrayBlockingQueue<String> messages;

	public Server(int port, ArrayBlockingQueue<String> messages) throws IOException {
		acceptor = new ServerSocket(port);
		this.messages = messages;
		System.out.println("Server: IP address: " + acceptor.getInetAddress() + " (" + port + ")");

	}

	public void listen() throws IOException {
		for (;;) {
			Socket s = acceptor.accept();
			SocketEchoThread echoer = new SocketEchoThread(s);
			System.out.println("Server: Connection accepted from " + s.getInetAddress());
			echoer.start();

		}
	}

	private class SocketEchoThread extends Thread {
	    private Socket socket;
	    
	    public SocketEchoThread(Socket socket) {
	        this.socket = socket;
	    }

	    public void run() {
	        try {
	            String msg = getMessage();
	            System.out.println("Server: Received [" + msg + "]");
				messages.add(msg);
				socket.close();
	        } catch (IOException ioe) {
	            ioe.printStackTrace();
	        }
		}



	    private String getMessage() throws IOException {
            BufferedReader responses = 
            		new BufferedReader(new InputStreamReader(socket.getInputStream()));
            StringBuilder sb = new StringBuilder();
            while (!responses.ready()){}
            while (responses.ready()) {
                sb.append(responses.readLine() + '\n');
            }
	    	return sb.toString();
	    }
	}
}
