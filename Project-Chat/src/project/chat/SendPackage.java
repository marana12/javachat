package project.chat;

import java.io.Serializable;
import java.util.ArrayList;

public class SendPackage implements Serializable{  //Package to Send
	private String user,password,nick,ip,message;
	
	private ArrayList<String>Ips;
	private ArrayList<String>Nics;

	/**
	 * @return the ips
	 */
	public ArrayList<String> getIps() {
		return Ips;
	}

	/**
	 * @param ips the ips to set
	 */
	public void setIps(ArrayList<String> ips) {
		Ips = ips;
	}

	/**
	 * @return the nick
	 */
	public String getNick() {
		return nick;
	}

	/**
	 * @param nick the nick to set
	 */
	public void setNick(String nick) {
		this.nick = nick;
	}

	/**
	 * @return the ip
	 */
	public String getIp() {
		return ip;
	}

	/**
	 * @param ip the ip to set
	 */
	public void setIp(String ip) {
		this.ip = ip;
	}

	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * @param message the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public ArrayList<String> getNics() {
		return Nics;
	}

	public void setNics(ArrayList<String> nics) {
		Nics = nics;
	}
	

	//public String toString() {
		//String s_nick = "";
	//	for(String x:Nics) {
	//	s_nick +=x ;
		//x +="\n";
	//	s_nick +="\n";
	//	}
		//return s_nick;
		
	//}

}
