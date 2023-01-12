package application;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

public class Controller implements Initializable{
	
	@FXML
	Button send_btn;
	@FXML
	TextField msg_field;
	@FXML
	ScrollPane chat_field;
	@FXML
	VBox vbox_field;
	
	private Server server;
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		try {
			server = new Server(new ServerSocket(1234));
		}
		catch(IOException e) {
			e.printStackTrace();
			System.out.println("Error while creating server");
		}
		vbox_field.heightProperty().addListener((ChangeListener<? super Number>) new ChangeListener<Number>() {

			@Override
			public void changed(ObservableValue<? extends Number> arg0, Number arg1, Number arg2) {
				chat_field.setVvalue((Double) arg2);
			}
			
		});
//		server.recieveMessageFromClient(vbox_field);
		send_btn.setOnAction( new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				String message = msg_field.getText();
				if(!message.isEmpty()) {
					HBox hbox = new HBox();
					hbox.setAlignment(Pos.CENTER_RIGHT);
					hbox.setPadding(new Insets(5 , 5 , 5, 10));
					
					Text text = new Text(message);
					TextFlow textFlow = new TextFlow(text);
					
					textFlow.setPadding(new Insets( 5 , 10 , 5 , 10));
					text.setFill(Color.color(0.934, 0.945, 0.996));
					
					hbox.getChildren().add(textFlow);
					vbox_field.getChildren().add(hbox);
					
//					server.setMessageToClient(message);
					msg_field.clear();
				}
				
			}
			
		});
	}
	public static void addLabel(String messageFromClient , VBox vbox) {
		HBox hbox = new HBox();
		hbox.setAlignment(Pos.CENTER_LEFT);
		hbox.setPadding(new Insets(5 , 5,  5 , 10));
		
		Text text = new Text(messageFromClient);
		TextFlow textFlow = new TextFlow(text);
		
		textFlow.setPadding(new Insets( 5 , 10 , 5 , 10));
		text.setFill(Color.color(0.934, 0.945, 0.996));
		
		hbox.getChildren().add(textFlow);
		
		Platform.runLater(new Runnable() {

			@Override
			public void run() {
				vbox.getChildren().add(hbox);
				
			}
			
		});
		
	}

}
