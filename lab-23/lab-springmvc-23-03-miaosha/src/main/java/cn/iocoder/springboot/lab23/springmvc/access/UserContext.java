package cn.iocoder.springboot.lab23.springmvc.access;

import cn.iocoder.springboot.lab23.springmvc.domain.MiaoshaUser;

/**
 * 用户上下文环境，一个线程对应一个变量副本
 * @author Jaquez
 * @date 2021/05/17 10:18
 */
public class UserContext {
	
	private static ThreadLocal<MiaoshaUser> userHolder = new ThreadLocal<MiaoshaUser>();
	
	public static void setUser(MiaoshaUser user) {
		userHolder.set(user);
	}
	
	public static MiaoshaUser getUser() {
		return userHolder.get();
	}

}
