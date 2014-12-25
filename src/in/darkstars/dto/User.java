package in.darkstars.dto;

import java.io.Serializable;
import java.net.InetAddress;

public class User implements Serializable {
	
	private String nickName;
	private String ipAddress;
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
	public String getIpAddress() {
		return ipAddress;
	}
	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}
	public Status getStatus() {
		return status;
	}
	public void setStatus(Status status) {
		this.status = status;
	}
	
	

}
