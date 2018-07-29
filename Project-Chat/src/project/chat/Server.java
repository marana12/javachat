package project.chat;

import java.net.InetAddress;
import java.net.UnknownHostException;

import javax.swing.*;

public class Server {
	public static void main(String[] args) throws UnknownHostException {
		// TODO Auto-generated method stub
		System.out.println(InetAddress.getLocalHost());
	FrameServer myframe=new FrameServer();
	
	myframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 

}
}
