
package cn.iocoder.springboot.lab80.oss.common;

import org.apache.http.HttpStatus;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 返回结果包装类
 *
 * @author Jaquez
 * @date 2021/10/05 14:11
 */
public class ResultWrapper extends HashMap<String, Object> {

	private static final long serialVersionUID = 1L;
	
	public ResultWrapper() {
		put("code", HttpStatus.SC_OK);
		put("msg", "success");
	}
	
	public static ResultWrapper error() {
		return error(HttpStatus.SC_INTERNAL_SERVER_ERROR, "未知异常，请联系管理员");
	}
	
	public static ResultWrapper error(String msg) {
		return error(HttpStatus.SC_INTERNAL_SERVER_ERROR, msg);
	}
	
	public static ResultWrapper error(int code, String msg) {
		ResultWrapper r = new ResultWrapper();
		r.put("code", code);
		r.put("msg", msg);
		return r;
	}

	public static ResultWrapper ok(String msg) {
		ResultWrapper r = new ResultWrapper();
		r.put("msg", msg);
		return r;
	}
	
	public static ResultWrapper ok(Map<String, Object> map) {
		ResultWrapper r = new ResultWrapper();
		r.putAll(map);
		return r;
	}

	public static ResultWrapper ok(List<Map<String, Object>> list) {
		ResultWrapper r = new ResultWrapper();
		r.put("result",list);
		return r;
	}
	
	public static ResultWrapper ok() {
		return new ResultWrapper();
	}

	@Override
	public ResultWrapper put(String key, Object value) {
		super.put(key, value);
		return this;
	}
}
