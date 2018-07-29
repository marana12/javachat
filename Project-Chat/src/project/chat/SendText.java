package project.chat;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.*;

public class SendText   implements ActionListener  {
	private JTextField field1;
	private JComboBox ip;
	private JComboBox nicks;
	private JLabel nick;
	private JTextArea fieldchat;
	private	InetAddress address ;
	public SendText(JTextField _field,JLabel _nick,JComboBox _ip,JComboBox _nicks,JTextArea _fieldchat) {
		this.field1=_field;
		this.nick=_nick;
		this.ip=_ip;
		this.nicks=_nicks;
		this.fieldchat=_fieldchat;
		

	}
	

	@Override
	public void actionPerformed(ActionEvent e) {
		

		
		fieldchat.append("\n You: "+ field1.getText());

		//Get The IP from ComboBox
		String n_ip;
		int index=nicks.getSelectedIndex();
		n_ip=(String) ip.getItemAt(index);
	
		
			
		
		try {
			//--------SEND PACKAGE NICK , IP , MESSAGE TO SERVER-----------
			
			Socket mysocket=new Socket("192.168.33.1",1500);//ip of server
			 System.out.println(InetAddress.getLocalHost());
			SendPackage data =new SendPackage();
			
			data.setNick(nick.getText());
			data.setIp(n_ip);
			data.setMessage(field1.getText());
			 
			ObjectOutputStream data_package=new ObjectOutputStream(mysocket.getOutputStream());
			
			data_package.writeObject(data);
			
			
			mysocket.close();
			field1.setText("");
			
			//------------------------------------------------------------------	
		} catch (UnknownHostException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			System.out.println(e1.getMessage());	
}
	}
	
	

}
