package project.chat;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.*;
import java.net.*;
import java.util.ArrayList;

import javax.swing.*;


public class PageFrameClient extends JPanel implements Runnable{
	
	
	private JTextField field1;
	private JLabel nick;
	private JComboBox<String> Nicks;
	private JComboBox<String> Ips;
	private JTextArea fieldchat;
	private int n_ip=0;
	private JButton mybutton;

	public PageFrameClient(String nick_user){ 
		
		
	//------------MAKE THE CHAT FRAME-----------------
		
		JLabel n_nick=new JLabel("Nick: ");
		
		

		
		add(n_nick);
		nick=new JLabel();
		nick.setText(nick_user);

		add(nick);
		
		
		JLabel text=new JLabel(" Online: ");
		add(text);

		Nicks=new JComboBox<String>();
		add(Nicks);
		Ips=new JComboBox<String>();
		add(Ips);
		Ips.setVisible(false);
		
		
			
		fieldchat=new JTextArea(12,20);
		
		fieldchat.setEditable(false);
			add(fieldchat);
	
		field1=new JTextField(20);
		
			add(field1);		
			


		
			
		mybutton=new JButton("Send");
		
		SendText myevent=new SendText(field1,nick,Ips,Nicks,fieldchat);//
		
		mybutton.addActionListener(myevent);//Button to send all the data
		
		add(mybutton);	
		mybutton.setEnabled(false);
		
		//Check if the JTextField is Empty
		//to Activate the Button "SEND"
		field1.addKeyListener(new KeyAdapter(){    
            public void keyReleased( KeyEvent e ) { 
            	
                mybutton.setEnabled(text.getText().length() != 0);
              }
            }
		);
	

	//-------------------------------------------
		

		Thread thread=new Thread(this);
		thread.start();
		
	}


	@Override
	public void run() {
		
		try {
			ServerSocket s_client=new ServerSocket(9090);
			
			
			SendPackage recivePackage;
			
			
			while(true) {
				//-------------STREAM FROM SERVER TO CLIENT---------------
				
				Socket client=s_client.accept();
				ObjectInputStream entryStream=new ObjectInputStream(client.getInputStream());
				
				recivePackage=(SendPackage) entryStream.readObject();
				//-------------RECIVE THE MESSAGE---------------
				if(!recivePackage.getMessage().equals(" online")) {
					fieldchat.append("\n"+ recivePackage.getNick() + ": "+ recivePackage.getMessage());
					
					
				}	
				//----------------------------------------------
				else {
					
				//---------ADD IPS TO COMBOBOX------------
				ArrayList<String>MenuIps=new ArrayList<String>();
				ArrayList<String>MenuNicks=new ArrayList<String>();
				
				MenuIps=recivePackage.getIps();
				MenuNicks=recivePackage.getNics();
				
				
				Nicks.removeAllItems();
				Ips.removeAllItems();
				
													
				
				for(int z=0;z< MenuIps.size();z++) {
					Nicks.addItem(MenuNicks.get(z));
					Ips.addItem(MenuIps.get(z));
				}
				

				
				if(!MenuIps.get(n_ip).equals(InetAddress.getLocalHost().getHostAddress())) {
				
				String text=MenuNicks.get(n_ip) +" Online";
				fieldchat.append("\n\t"+ text);
				
				}
				
				n_ip ++;
				
			
				}
				//----------------------------------------	
				
				
				continue;
				
				//-------------------------------------------------------
				
			}
			
		}catch(Exception e) {
			
			System.out.println(e.getMessage());
		}
		
	}


}

