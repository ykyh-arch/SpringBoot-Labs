
package cn.iocoder.springboot.lab33.shirodemo.dataobject;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * SysLog
 * @author Jaquez
 * @date 2021/10/06 11:24
 */
@Data
@TableName("sys_log")
public class SysLog implements Serializable {

	private static final long serialVersionUID = 1L;

	@TableId
	private Long id;
	//用户名
	private String username;
	//用户操作
	private String operation;
	//请求方法
	private String method;
	//请求参数
	private String params;
	//执行时长(毫秒)
	private Long time;
	//IP地址
	private String ip;
	//创建时间
	private Date createDate;

}
