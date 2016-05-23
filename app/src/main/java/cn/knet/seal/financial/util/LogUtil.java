package cn.knet.seal.financial.util;


import android.util.Log;

/**
 * 日志输入工具类
 *
 * ClassName: LogUtil <br/>
 * Date: 2016/5/19 15:05 <br/>
 *
 * @author: peixinwen@knet.cn
 * @version:
 * @since 1.0
 * @update:
 *	规定，调试统一使用d级别日志; 错误输出使用e级别
 */
public class LogUtil {

	/** 默认是开发调试模式，在开发完毕之后将其置为false即可屏蔽所有日志 **/
	private static boolean isDebug = true;
	
	/**
	 * 打印i级别
	 * @param tag
	 * @param msg
	 */
	public static void i(String tag, String msg){
		if(isDebug){
			Log.i(tag, msg);
		}
	}
	
	public static void i(Object object, String msg){
		if(isDebug){
			Log.i(object.getClass().getSimpleName(), msg);
		}
	}
	
	/**
	 * 打印e级别
	 * @param tag
	 * @param msg
	 */
	public static void e(String tag, String msg){
		if(isDebug){
			Log.e(tag, msg);
		}
	}
	
	public static void e(Object object, String msg){
		if(isDebug){
			Log.e(object.getClass().getSimpleName(), msg);
		}
	}

	/**
	 * 打印d级别
	 * @param tag
	 * @param msg
	 */
	public static void d(String tag, String msg){
		if(isDebug){
			Log.d(tag, msg);
		}
	}

	public static void d(Object object, String msg){
		if(isDebug){
			Log.d(object.getClass().getSimpleName(), msg);
		}
	}
}
