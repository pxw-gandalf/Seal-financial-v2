
package cn.knet.seal.financial.bean;

import android.text.TextUtils;

import java.io.Serializable;

/**
 * 上传任务类
 * 
 * ClassName: UploadTask <br/>  
 * Date: 2015年4月22日 下午5:01:52 <br/> 
 *  
 * @author yangying@knet.cn 
 * @version 1.0
 * @since 1.0
 */
public class UploadTask implements Serializable {

    /**
     * download url
     */
//    private String url;

    /**  
     * serialVersionUID:TODO 
     */
    private static final long serialVersionUID = 4238364862929101765L;

    /**
     * 文件名
     */
    private String fileName;

    private String title;

    private String thumbnail;
    
    private String thumbnail2;
    
    /**
     * 文件路径
     */
    private String filePath;
    
    private String fileMd5;

    /**
     * download finished Size
     */
    private int finishedSize;

    /**
     * total Size
     */
    private int totalSize;

    /**
     * finished percent
     */
    private int percent;

    private int speed;
    
    /**
     * download state
     */
    private volatile UploadState uploadState;
    
    private String reviewId;
    
    private String reviewType;
    
    private String opType;
    
    /** 新增，文件上传扩展 **/
    private String dataType;
    
    /**
     * @return 返回 dataType。
     */
    public String getDataType() {
        return dataType;
    }

    /**
     * @param dataType 要设置的 dataType。
     */
    public void setDataType(String dataType) {
        this.dataType = dataType;
    }

    public String getOpType() {
        return opType;
    }

    public void setOpType(String opType) {
        this.opType = opType;
    }

    public String getReviewId() {
        return reviewId;
    }

    public void setReviewId(String reviewId) {
        this.reviewId = reviewId;
    }

    public String getReviewType() {
        return reviewType;
    }

    public void setReviewType(String reviewType) {
        this.reviewType = reviewType;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getFileMd5() {
        return fileMd5;
    }

    public void setFileMd5(String fileMd5) {
        this.fileMd5 = fileMd5;
    }

    public String getThumbnail2() {
        return thumbnail2;
    }

    public void setThumbnail2(String thumbnail2) {
        this.thumbnail2 = thumbnail2;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
    
    /**
     * DownloadTask constructor for create a new download task.
     * 
     * @param filePath if filePath is null, we will use the default download
     *            path "/sdcard/download"
     * @param fileName file name, must input
     * @param title task title for display.Can be null
     * @param thumbnail task thumbnail image,should be a uri string. Can be null
     */
    public UploadTask(String fileMd5, String filePath, String fileName, String title, String thumbnail) {
        if (TextUtils.isEmpty(fileMd5)) {
            throw new IllegalArgumentException("invalid fileMd5");
        }
        if (TextUtils.isEmpty(filePath)) {
            throw new IllegalArgumentException("invalid filePath");
        }
        if (TextUtils.isEmpty(fileName)) {
            throw new IllegalArgumentException("invalid fileName");
        }
        if (TextUtils.isEmpty(title)) {
            throw new IllegalArgumentException("invalid title");
        }
        this.fileMd5 = fileMd5;
        this.fileName = fileName;
        this.title = title;
        this.thumbnail = thumbnail;
        this.filePath = filePath;
    }

    /**
     * get fileName
     * 
     * @return the fileName
     */
    public String getFileName() {
        return fileName;
    }

    /**
     * set fileName
     * 
     * @param fileName the fileName to set
     */
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    /**
     * get filePath
     * 
     * @return the filePath
     */
    public String getFilePath() {
        return filePath;
    }

    /**
     * set filePath
     * 
     * @param filePath the filePath to set
     */
    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    /**
     * get finishedSize
     * 
     * @return the finishedSize
     */
    public int getFinishedSize() {
        return finishedSize;
    }

    /**
     * set finishedSize
     * 
     * @param finishedSize the finishedSize to set
     */
    public void setFinishedSize(int finishedSize) {
        this.finishedSize = finishedSize;
    }

    /**
     * get totalSize
     * 
     * @return the totalSize
     */
    public int getTotalSize() {
        return totalSize;
    }

    /**
     * set totalSize
     * 
     * @param totalSize the totalSize to set
     */
    public void setTotalSize(int totalSize) {
        this.totalSize = totalSize;
    }

    /**
     * get percent
     * 
     * @return the percent
     */
    public int getPercent() {
        return percent;
    }

    /**
     * set download percent
     * 
     * @param percent
     */
    public void setPercent(int percent) {
        this.percent = percent;
    }
    
    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public UploadState getUploadState() {
        return uploadState;
    }

    public void setUploadState(UploadState uploadState) {
        this.uploadState = uploadState;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((fileName == null) ? 0 : fileName.hashCode());
        result = prime * result + ((filePath == null) ? 0 : filePath.hashCode());
        result = prime * result + ((fileMd5 == null) ? 0 : fileMd5.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        UploadTask other = (UploadTask) obj;
        if (fileName == null) {
            if (other.fileName != null)
                return false;
        } else if (!fileName.equals(other.fileName))
            return false;
        if (filePath == null) {
            if (other.filePath != null)
                return false;
        } else if (!filePath.equals(other.filePath))
            return false;
        if (fileMd5 == null) {
            if (other.fileMd5 != null)
                return false;
        } else if (!fileMd5.equals(other.fileMd5))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "DownloadTask [fileMd5=" + fileMd5 + ", finishedSize=" + finishedSize + ", totalSize="
                + totalSize + ", dlPercent=" + percent + ", uploadState=" + uploadState
                + ", fileName=" + fileName + ", title=" + title + "]";
    }

}
