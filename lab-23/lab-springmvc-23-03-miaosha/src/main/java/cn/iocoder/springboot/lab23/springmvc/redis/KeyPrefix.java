package cn.iocoder.springboot.lab23.springmvc.redis;

public interface KeyPrefix {
		
	public int expireSeconds();
	
	public String getPrefix();
	
}
