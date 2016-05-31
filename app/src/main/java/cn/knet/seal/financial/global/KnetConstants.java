package cn.knet.seal.financial.global;

import android.content.Context;
import android.os.Environment;

import java.io.File;

import de.greenrobot.event.EventBus;

/**
 * 常量辅助类
 *
 * ClassName: KnetConstants <br/>
 * Date: 2016/5/19 15:08 <br/>
 *
 * @author: peixinwen@knet.cn
 * @version:
 * @since 1.0
 * @update:
 *
 */
public class KnetConstants {

    private Context context;
    public static final String TOKEN = "token";
    public static final String UID = "uid";
    public static final String PWD = "pwd";
    public static final String PERMISSION = "permission";
    public static final String IS_LOGIN = "isLogin";
    public static final String DB_NAME = "upload.db";

    /** 首页头部 */
    public static final int VIEW_TYPE_HOME_HEADER = 0x4B000001;
    /** 首页操作区 */
    public static final int VIEW_TYPE_HOME_OPERATE = 0x4B000002;
    /** 下户列表 */
    public static final int VIEW_TYPE_HOME_LIST = 0x4B000003;


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
                EventBus.getDefault().post(new StringMsgEvents(msg));
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
