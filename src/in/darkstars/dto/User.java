package in.darkstars.dto;

import java.net.InetAddress;

public class User {
	
	private String nickName;
	private InetAddress ipAddress;
	public enum	Status {
		Available,
		Offline,
		Busy,
		Away
	}
	private Status status;
	
	
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	public InetAddress getIpAddress() {
		return ipAddress;
	}
	public void setIpAddress(InetAddress ipAddress) {
		this.ipAddress = ipAddress;
	}
	public Status getStatus() {
		return status;
	}
	public void setStatus(Status status) {
		this.status = status;
	}
	
	

}
