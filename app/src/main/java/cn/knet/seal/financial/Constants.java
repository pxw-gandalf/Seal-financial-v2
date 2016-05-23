package cn.knet.seal.financial;

import android.content.Context;
import android.os.Environment;

import java.io.File;

import de.greenrobot.event.EventBus;

/**
 * 常量辅助类
 *
 * ClassName: Constants <br/>
 * Date: 2016/5/19 15:08 <br/>
 *
 * @author: peixinwen@knet.cn
 * @version:
 * @since 1.0
 * @update:
 *
 */
public class Constants {

    private Context context;
    public static final String TOKEN = "token";
    public static final String UID = "uid";
    public static final String PWD = "pwd";
    public static final String PERMISSION = "permission";


    /**
     * 获取项目根cache目录，位于外置存储
     * @param save 是否保存数据标识
     * @return
     */
    public static String getBaseCacheDir(boolean save){
        // 存储于外置内存的项目缓存目录名称
        String baseCache = "knetfinacial";
        String msg = "写入失败，请确定允许SD卡相关权限";
        if(save){
            if(createBaseCacheDir()){
                return Environment.getExternalStorageDirectory() + File.separator + baseCache;
            }else{
                GlobalEvents gl = new GlobalEvents().setType(GlobalEvents.COMMON_UI_MSG);
                gl.obj = msg;
                EventBus.getDefault().post(gl);
            }
        }
        return Environment.getExternalStorageDirectory() + File.separator + baseCache;
    }

    /**
     * 创建项目根cache目录
     * @return
     */
    public static boolean createBaseCacheDir(){
        String path = getBaseCacheDir(false);
        File file = new File(path);
        return file.exists() || file.mkdirs();
    }

    public void SetGlobalContext(Context context){
        this.context = context;
    }

}
