package project.chat;


import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.PasswordAuthentication;
import java.net.Socket;

import javax.swing.JFrame;
import javax.swing.*;

public class FrameAutentication extends JFrame implements ActionListener{
	private JFrame frame;
	private JPanel panel;
	private JTextField user;
	private JPasswordField pass;
	private JLabel us,pas;
	private JButton log;
	private boolean accept;
	
	
	
	
public FrameAutentication() {
	
	//Create a Autentication Frame (Log In)
	
	frame=new JFrame("Login Chat");
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	frame.setBounds(600,300,500,350);
	frame.setVisible(true);
	
	
	 panel =new JPanel();
	
	us=new JLabel("User: ");
	
	user=new JTextField(10);
	
	
	pas=new JLabel("Password: ");
	
	pass=new JPasswordField(10);
	log=new JButton("Login");
	
	panel.add(us);
	panel.add(user);
	panel.add(pas);
	panel.add(pass);


	panel.add(log);
	

	frame.add(panel);
	log.addActionListener(this);
	
	
	

}


//The event of button log
@Override
public void actionPerformed(ActionEvent e) {
	// TODO Auto-generated method stub
	Object botonpress=e.getSource();
	if(botonpress==log) {
		
	
		//Checks if the Labels are Empty
	    if ((user.getText().equals(null) )|| (pass.getPassword().equals(null))) {
	    	JOptionPane.showMessageDialog(null,"The text label are empty!!!");
	    	}
	    
	    else {
	    	//convert a char (PasswordField) to string
			String password="";
			char [] ch_pass=pass.getPassword();
			for(int x=0;x<ch_pass.length;x++)
				password+=ch_pass[x];
			
	SendPackage tolog=new SendPackage();
    Socket clientSocket;
    String replyFromServer;

    //Conect to Server
	try {
		clientSocket = new Socket( "192.168.33.1", 1500);
	
	tolog.setUser(user.getText());
	tolog.setPassword(password);
	tolog.setMessage("to login");
	
	ObjectOutputStream data_package=new ObjectOutputStream(clientSocket.getOutputStream());
	InputStreamReader inputStreamReader = new InputStreamReader(clientSocket.getInputStream());
    BufferedReader inFromServer = new BufferedReader(inputStreamReader);
//Send the data to Server
	data_package.writeObject(tolog);
	
//Recive a request from a Server	
	replyFromServer = inFromServer.readLine();
	if(replyFromServer.equals("fault log")) {
		
		JOptionPane.showMessageDialog(null, "One or more of the authentication details you provided is incorrect");
		clientSocket.close();
	}
	else {
	
		//open the chat
		clientSocket.close();
	frame.setVisible(false);
	
	FrameClient myframe=new FrameClient(); 
	myframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	
			}
	
	
	
	
	 	 

	} catch (IOException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}


	}
    
	}
}




										}
