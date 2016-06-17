package cn.knet.seal.financial.global;

import android.content.Context;
import android.os.Environment;

import com.nostra13.universalimageloader.core.DisplayImageOptions;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import cn.knet.seal.financial.R;
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

    public static DisplayImageOptions options = new DisplayImageOptions.Builder()
            .showImageOnLoading(R.mipmap.moren)                    //加载时的状态
            .showImageForEmptyUri(R.mipmap.moren)                  //网络链接失败
            .showImageOnFail(R.mipmap.moren)                       //加载失败时
            .cacheInMemory(true)                                   //是否在内存中缓存
            .cacheOnDisk(true)                                     //是否缓存在SD卡
            .considerExifParams(true).build();                     //自动辨认图片方向（如倒置手机时）

    private Context context;
    public static final String TOKEN = "token";
    public static final String UID = "uid";
    public static final String PWD = "pwd";
    public static final String PERMISSION = "permission";
    public static final String IS_LOGIN = "isLogin";
    public static final String IS_EXIST_REVIEWED_BANK = "isExistReviewedBank";
    public static final String DB_NAME = "upload.db";
    public static final int REVIEW_MAX_PHOTO_SIZE = 10;


    /** 首页头部 */
    public static final int VIEW_TYPE_HOME_HEADER = 0x4B000001;
    /** 首页操作区 */
    public static final int VIEW_TYPE_HOME_OPERATE = 0x4B000002;
    /** 下户列表 */
    public static final int VIEW_TYPE_HOME_LIST = 0x4B000003;
    /** 最新待接单提示 */
    public static final int VIEW_TYPE_HOME_TIPS = 0x4B000004;
    /** 下户照片展示 */
    public static final int VIEW_TYPE_REVIEW_SHOW_PHOTO = 0x4B000005;
    /** 下户照片拍摄 */
    public static final int VIEW_TYPE_REVIEW_TAKE_PHOTO = 0x4B000006;

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
     * 下户数据缓存目录
     * @param save
     * @return
     */
    public static String getReviewCacheDir(boolean save){
        String reviewCache = "review_cache";
        if(save){
            return getBaseCacheDir(true) + File.separator + reviewCache;
        }
        return getBaseCacheDir(false) + File.separator + reviewCache;
    }

    /**
     * 获取下户照片存储路径
     * @param save
     * @param orderId 可信贷订单id
     * @param bankId  银行id
     * @return 路径
     */
    public static String getReviewImageCache(boolean save, String orderId ,String bankId){
        String path =  getReviewCacheDir(save)
                + File.separator + orderId
                + File.separator + bankId
                + File.separator + "image";
        File file = new File(path);
        if(save && !file.exists()){
            file.mkdirs();
        }
        return path;
    }

    /**
     * 大图存储路径
     * @param save
     * @param orderId
     * @param bankId
     * @return
     */
    public static String getReviewBigImageCache(boolean save,String photoType, String orderId ,String bankId){
        String path = getReviewImageCache(save,orderId,bankId)
                + File.separator + photoType
                + File.separator + "big";
        File file = new File(path);
        if(save && !file.exists()){
            file.mkdirs();
        }
        return path;
    }

    /**
     * 小图存储路径
     * @param save
     * @param orderId
     * @param bankId
     * @return
     */
    public static String getReviewSmallImageCache(boolean save,String photoType, String orderId ,String bankId){
        String path = getReviewImageCache(save,orderId,bankId)
                + File.separator + photoType
                + File.separator + "small";
        File file = new File(path);
        if(save && !file.exists()){
            file.mkdirs();
        }
        return path;
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
