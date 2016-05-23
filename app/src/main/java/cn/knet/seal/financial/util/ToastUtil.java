/**
 * 
 */
package cn.knet.seal.financial.util;

import android.content.Context;
import android.widget.Toast;

import cn.knet.seal.financial.KnetFinancialApplication;

/**
 * Toast工具类，
 * 可以不断弹出，不会等待上一条时间停止后弹出
 *
 * ClassName: ToastUtil <br/>
 * Date: 2016/5/19 17:19 <br/>
 *
 * @author: peixinwen@knet.cn
 * @version:
 * @since 1.0
 * @update:
 *
 */
public class ToastUtil {
    private static Toast toast;

	public static void show(Context context, int resId){
		Toast localToast = Toast.makeText(context, resId, Toast.LENGTH_SHORT);// duration=0
//		localToast.setGravity(Gravity.CENTER, 0, 0);
		localToast.show();
	}
	   public static void showToast(String text){
	        if(toast == null){
	            toast = Toast.makeText(KnetFinancialApplication.getInstance().getBaseContext(), text,
						Toast.LENGTH_SHORT);
	        }
	        toast.setText(text);
	        toast.show();
	    }
}
