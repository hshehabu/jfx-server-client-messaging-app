package application;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
	
	private ServerSocket serverSocket;
	private Socket socket;
	private BufferedReader bufferedReader;
	private BufferedWriter bufferedWriter;
	
	public Server (ServerSocket serverSocket) throws IOException {
		
		this.serverSocket = serverSocket;
		this.socket = serverSocket.accept();
		
		this.bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		
		
		
	}
	
}
