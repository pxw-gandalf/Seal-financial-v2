package cn.knet.seal.financial.bean.response;


import cn.knet.seal.financial.bean.KnetBaseBean;

/**
 * @author pxw
 * @desc 视频录制
 * @date 2016/4/11 14:13
 * @update
 */
public class ReviewVideoInfo extends KnetBaseBean{
    private static final long serialVersionUID = 4701930688967816443L;

    /** 视频的地址，可以是网络，也可以是本地 */
    private String videoUrl;
    /** 缩略图地址 */
    private String thumbnailUrl;
    /** 是地址，本地地址时会添加前缀，此地址不加前缀 */
    private String mediaThumbsRealUrl;
    /** 视频标题 */
    private String mediaTitle;
    /** 视频时长 */
    private int videoLength;
    /** 视频md5 */
    private String mediaMD5;
    /** 缩略图md5 */
    private String thumbnailMD5;
    /** 缩略图名 */
    private String thumbnailTitle;
    /** 视频Id */
    private String videoId;

    public String getVideoId() {
        return videoId;
    }

    public void setVideoId(String videoId) {
        this.videoId = videoId;
    }

    public String getMediaThumbsRealUrl() {
        return mediaThumbsRealUrl;
    }

    public void setMediaThumbsRealUrl(String mediaThumbsRealUrl) {
        this.mediaThumbsRealUrl = mediaThumbsRealUrl;
    }

    public String getThumbnaliTitle() {
        return thumbnailTitle;
    }

    public void setThumbnaliTitle(String thumbnailTitle) {
        this.thumbnailTitle = thumbnailTitle;
    }

    public String getMediaMD5() {
        return mediaMD5;
    }

    public void setMediaMD5(String mediaMD5) {
        this.mediaMD5 = mediaMD5;
    }

    public String getThumbnailMD5() {
        return thumbnailMD5;
    }

    public void setThumbnailMD5(String thumbnailMD5) {
        this.thumbnailMD5 = thumbnailMD5;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }

    public String getThumbnailUrl() {
        return thumbnailUrl;
    }

    public void setThumbnailUrl(String thumbnailUrl) {
        this.thumbnailUrl = thumbnailUrl;
    }

    public String getMediaTitle() {
        return mediaTitle;
    }

    public void setMediaTitle(String mediaTitle) {
        this.mediaTitle = mediaTitle;
    }

    public int getVideoLength() {
        return videoLength;
    }

    public void setVideoLength(int videoLength) {
        this.videoLength = videoLength;
    }

    @Override
    public String toString() {
        return "ReviewMediaInfo{" +
            "videoUrl='" + videoUrl + '\'' +
            ", thumbnailUrl='" + thumbnailUrl + '\'' +
            ", mediaTitle='" + mediaTitle + '\'' +
            ", videoLength=" + videoLength +
            '}';
    }
}
