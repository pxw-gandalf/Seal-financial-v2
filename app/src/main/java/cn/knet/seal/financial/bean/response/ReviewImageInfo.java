/**
 *
 */
package cn.knet.seal.financial.bean.response;

import android.graphics.Bitmap;

import cn.knet.seal.financial.bean.KnetBaseBean;

/**
 * imageView 图片
 *
 * ClassName: ImageItem <br/>
 * Date: 2015年3月14日 上午10:16:45 <br/>
 *
 * @author yangying@knet.cn
 * @version 1.0
 * @since 1.0
 */
public class ReviewImageInfo extends KnetBaseBean {

    private static final long serialVersionUID = 3216797616737061368L;

    /**
     * 图片url
     */
    private String id;
    //小图fileid
    private String imageUrlSmall;
    //中图fileid
    private String imageUrlMiddle;
    //原图fileid
    private String imageUrlOrignal;
    //小图
    private Bitmap imageSmall;
    //中图
    private Bitmap imageMiddle;
    //原图文件md5
    private String fileMd5Original;
    //原图文件路径
    private String filePathOriginal;
    //原图文件名
    private String fileNameOriginal;
    //小图文件本地路径
    private String filePathSmall;
    //标题
    private String title;
    //图片的类型
    private String type;
    //图片经纬度
    private double lng;
    private double lat;


    public ReviewImageInfo() {

    }
    public ReviewImageInfo(String imageUrlSmall, String imageUrlMiddle, String imageUrlOrignal){
        this.imageUrlSmall = imageUrlSmall;
        this.imageUrlMiddle = imageUrlMiddle;
        this.imageUrlOrignal = imageUrlOrignal;
    }



    /**
     * @return 返回 longtitude。
     */
    public double getLng() {
        return lng;
    }
    /**
     * @param longtitude 要设置的 longtitude。
     */
    public void setLng(double lng) {
        this.lng = lng;
    }
    /**
     * @return 返回 latitude。
     */
    public double getLat() {
        return lat;
    }
    /**
     * @param latitude 要设置的 latitude。
     */
    public void setLat(double lat) {
        this.lat = lat;
    }
    public String getFilePathSmall() {
        return filePathSmall;
    }
    public void setFilePathSmall(String filePathSmall) {
        this.filePathSmall = filePathSmall;
    }
    public String getFilePathOriginal() {
        return filePathOriginal;
    }
    public void setFilePathOriginal(String filePathOriginal) {
        this.filePathOriginal = filePathOriginal;
    }
    public String getFileNameOriginal() {
        return fileNameOriginal;
    }
    public void setFileNameOriginal(String fileNameOriginal) {
        this.fileNameOriginal = fileNameOriginal;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getImageUrlSmall() {
        return imageUrlSmall;
    }

    public void setImageUrlSmall(String imageUrlSmall) {
        this.imageUrlSmall = imageUrlSmall;
    }

    public String getImageUrlMiddle() {
        return imageUrlMiddle;
    }

    public void setImageUrlMiddle(String imageUrlMiddle) {
        this.imageUrlMiddle = imageUrlMiddle;
    }

    public String getImageUrlOrignal() {
        return imageUrlOrignal;
    }

    public void setImageUrlOrignal(String imageUrlOrignal) {
        this.imageUrlOrignal = imageUrlOrignal;
    }

    public Bitmap getImageSmall() {
        return imageSmall;
    }

    public void setImageSmall(Bitmap imageSmall) {
        this.imageSmall = imageSmall;
    }
    public Bitmap getImageMiddle() {
        return imageMiddle;
    }
    public void setImageMiddle(Bitmap imageMiddle) {
        this.imageMiddle = imageMiddle;
    }
    public String getFileMd5Original() {
        return fileMd5Original;
    }
    public void setFileMd5Original(String fileMd5Original) {
        this.fileMd5Original = fileMd5Original;
    }
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }

}
