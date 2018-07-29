package project.chat;

import java.awt.BorderLayout;
import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;



public class FrameServer extends JFrame implements Runnable{
	private	JTextArea textarea;
	Socket incoming=null;
	
	//Make a Server Frame
	public FrameServer() {
		setBounds(1200,300,280,350);				
		
		JPanel mypage= new JPanel();
		
		mypage.setLayout(new BorderLayout());
		
		textarea=new JTextArea();
		textarea.setEditable(false);
		
		mypage.add(textarea,BorderLayout.CENTER);
		
		add(mypage);
		
		setVisible(true);
		
		
		Thread thread1=new Thread(this);
		thread1.start();
		
	}
	//Conection from Clients
	@Override
	public void run() {
		//List of Users
		ArrayList<Login> nlog=new ArrayList();
		
		Login nlog1=new Login();
		nlog1.setUser("Julio");
		nlog1.setPassword("julio1234");
		Login nlog2=new Login();
		nlog2.setUser("Tal");
		nlog2.setPassword("Tal1234");
		Login nlog3=new Login();
		nlog3.setUser("Itai");
		nlog3.setPassword("itai1234");

		nlog.add(0, nlog1);
		nlog.add(1, nlog2);
		nlog.add(2, nlog3);
		
		try {
			 
			
			ServerSocket server=new ServerSocket(1500);
			
			String nick,ip,message,username,password;
			
			ArrayList<String>listIp=new ArrayList<String>();
			ArrayList<String>listNick=new ArrayList<String>();
			
			SendPackage recive_package=new SendPackage();
			
			while(true) {
				//accept the conection with the client
				
				Socket mysocket=server.accept();
				
			
			ObjectInputStream data_package=new ObjectInputStream(mysocket.getInputStream());
			 DataOutputStream outToClient = new DataOutputStream (mysocket.getOutputStream());

			 //Recive the Package from the Client
				recive_package=(SendPackage) data_package.readObject();
				
				
				message=recive_package.getMessage();
				
				//check the purpose of the connection
				if(!message.equals(" online")) {
					
					
			//-----------CHECK THE AUTENTICATION----------------
				if(message.equals("to login")) {
					username=recive_package.getUser();
					password=recive_package.getPassword();
					
					int cnt=0;
					for (int x=0;x<nlog.size();x++) {
						
						if((!nlog.get(x).getUser().equals(username)) && (!nlog.get(x).getPassword().equals(password)))
								cnt++;
						else {
							cnt=0;
							break;
						}
						}
					if(cnt!=0) {
						outToClient.writeBytes("fault log\n");
					}else 
						outToClient.writeBytes("you are login\n");
					
					
					
				}
				//-----SEND THE MESSAGE TO THE RECIPIENT CLIENT-----
				else {
				nick=recive_package.getNick();
				ip=recive_package.getIp();
				textarea.append("\n"+ nick +" send: '"+ message +"'  to: "+ ip);
				
				//make a connection with the recipient
				Socket sendRecipient=new Socket (ip,9090);
				ObjectOutputStream resendPackage =new ObjectOutputStream(sendRecipient.getOutputStream());
				resendPackage.writeObject(recive_package);
				
				resendPackage.close();
				sendRecipient.close();
				mysocket.close();
				System.out.println(mysocket.getInetAddress().getHostAddress()+" Send The msg to ip :" + ip);
				}		
					} 
				
				else {
					
					//----------------DETECT WHO IS ONLINE-------------------
					
					InetAddress locate=mysocket.getInetAddress();
					System.out.println("Inet:"+mysocket.getInetAddress().getHostAddress()+" Local: "+ mysocket.getLocalAddress().getHostAddress());
					String RemoteIp=  locate.getHostAddress();
					String RemoteNick=recive_package.getNick();
					listIp.add(RemoteIp);
					listNick.add(RemoteNick);
					recive_package.setIps(listIp);
					recive_package.setNics(listNick);
					
					textarea.append("\t"+ RemoteNick+" Contected\n");
					
					
					//send to all client who is conected
					for(String z:listIp) {
						System.out.println("Send Online to: "+ z );
						
						Socket sendRecipient=new Socket (z,9090);
						 
						ObjectOutputStream resendPackage =new ObjectOutputStream(sendRecipient.getOutputStream());
						
						
						resendPackage.writeObject(recive_package);
						
						resendPackage.close();
						sendRecipient.close();
						mysocket.close();
					}
					
					
					//-------------------------------------------------
				}
			}
			
		} catch (IOException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
