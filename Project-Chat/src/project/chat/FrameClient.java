package project.chat;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;

public class FrameClient extends JFrame{
	
	
//-----CREATE THE FRAME OF CHAT--------
	public FrameClient() { 
		String nick_user=JOptionPane.showInputDialog("Nick Name: ");
		
		PageFrameClient mypage=new PageFrameClient(nick_user);
		
		setBounds(600,300,280,350);
		add(mypage);
		
		setVisible(true);
		
		addWindowListener(new SendOnline(nick_user));//make the frame online
		
		}	
	
}

