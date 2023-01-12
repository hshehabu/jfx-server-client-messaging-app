package application;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

import javafx.scene.layout.VBox;

public class Server {
	
	private ServerSocket serverSocket;
	private Socket socket;
	private BufferedReader bufferedReader;
	private BufferedWriter bufferedWriter;
	
	public Server (ServerSocket serverSocket){
		
		
		try {
			this.serverSocket = serverSocket;
			this.socket = serverSocket.accept();
			this.bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		} catch (IOException e) {
			System.out.println("Error while creating server");
			e.printStackTrace();
		}
		
		
		
		
	}
	public void sendMessageToClient(String message) {
		try {
			bufferedWriter.write(message);
			bufferedWriter.newLine();
			bufferedWriter.flush();
		} catch (IOException e) {
			System.out.println("Error while sending message");
			e.printStackTrace();
			resourceSaver(socket , bufferedReader , bufferedWriter);
		}
	}
	
	public void recieveMessageFromClient(VBox vbox) {
		new Thread(new Runnable() {

			@Override
			public void run() {
				while(socket.isConnected()) {
					try {
						String message = bufferedReader.readLine();
						Controller.addLabel(message, vbox);
					} catch (IOException e) {
						
						e.printStackTrace();
						System.out.println("error while recieving message from client");
						resourceSaver(socket , bufferedReader , bufferedWriter);
						break; //to avoid 
					}
					
				}
				
			}
			
		}).start();
	}
	public void resourceSaver(Socket socket , BufferedReader bufferedReader , BufferedWriter bufferedWriter) {
		try {
		if(socket != null) {
				socket.close();
			}
		if(bufferedReader != null) {
			bufferedReader.close();
		}
		if(bufferedWriter != null) {
			bufferedWriter.close();
		}
		}
		catch (IOException e) {
				System.out.println("error while saving resource");
				e.printStackTrace();
			}
		
	}
	
}
