package cn.knet.seal.financial;

import android.app.Application;

import com.lzy.okhttputils.OkHttpUtils;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;

import cn.knet.seal.financial.global.KnetCrashException;

/**
 * Application
 * 
 * ClassName: KnetFinancialApplication <br/>  
 * Date: 2015年1月28日 下午2:19:36 <br/> 
 *  
 * @author yangying@knet.cn 
 * @version 1.0
 * @since 1.0
 */
public class KnetFinancialApplication extends Application {
    
    private static final String TAG = KnetFinancialApplication.class.getSimpleName();
    private static KnetFinancialApplication knetFinancialApplication;

    @Override
    public void onCreate() {
        super.onCreate();
        knetFinancialApplication = this;
        // 初始化imageLoader
        initUniversalImageLoader();
        // 初始化crash
//        initCrashHandler();
        // 初始化隐藏媒体文件
        initNoMediaFile();
        // 初始化okHttp
        OkHttpUtils.init(this);
        OkHttpUtils.getInstance().setConnectTimeout(5000);

    }


    private void initNoMediaFile() {

    }

    private void initCrashHandler() {
        KnetCrashException catchHandler = KnetCrashException.getInstance();
        catchHandler.init(getApplicationContext());
    }

    private void initUniversalImageLoader() {
        ImageLoaderConfiguration.Builder config = new ImageLoaderConfiguration.Builder(getApplicationContext());
        config.threadPriority(Thread.NORM_PRIORITY - 2);
        config.denyCacheImageMultipleSizesInMemory();
        config.diskCacheFileNameGenerator(new Md5FileNameGenerator());
        config.diskCacheSize(50 * 1024 * 1024); // 50 MiB
        config.tasksProcessingOrder(QueueProcessingType.LIFO);
        ImageLoader.getInstance().init(config.build());
    }

    public static KnetFinancialApplication getInstance(){
        return knetFinancialApplication;
    }

}
