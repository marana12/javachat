package project.chat;

import java.awt.event.*;
import java.io.ObjectOutputStream;
import java.net.*;

//------------SEND ONLINE SIGNAL----------------

public class SendOnline extends WindowAdapter {
	private String nick;
	SendOnline(String nick_name){
		this.nick=nick_name;
	}
	
	public void windowOpened(WindowEvent e) {
		 try {
			 Socket socket=new Socket("192.168.33.1",1500);//ip of server
			 System.out.println(InetAddress.getLocalHost());
			 
			 SendPackage data=new SendPackage();
			 data.setNick(nick);
			 data.setMessage(" online");
			 ObjectOutputStream data_stream=new ObjectOutputStream(socket.getOutputStream());
			 data_stream.writeObject(data);
			 socket.close();
			 
		 }catch(Exception e2) {
			 
		 }
	}

}
