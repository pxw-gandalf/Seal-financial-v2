
package cn.knet.seal.financial.bean;

/**
 * 上传状态
 * 
 * ClassName: UploadState <br/>  
 * Date: 2015年4月22日 下午4:54:18 <br/> 
 *  
 * @author yangying@knet.cn 
 * @version 1.0
 * @since 1.0
 */
public enum UploadState {
    /**
     * init
     */
//    INITIALIZE,
    /**
     * wait
     */
    WAIT,
    /**
     * uploading
     */
    UPLOADING,
    /**
     * upload failed, the reason may be network error, file io error etc.
     */
    FAILED,
    /**
     * upload finished
     */
    FINISHED,

    /**
     * upload paused
     */
    PAUSE,
    /**
     * upload stop
     */
    STOP
}
