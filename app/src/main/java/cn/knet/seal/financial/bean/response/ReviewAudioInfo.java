/**
 * @desc  
 * @date 2015-12-22
 */
package cn.knet.seal.financial.bean.response;


import cn.knet.seal.financial.bean.KnetBaseBean;

/**
 * @desc 下户录音
 * @date 2015-12-22-上午11:38:50
 * @author peixinwen@knet.cn
 * 
 */
public class ReviewAudioInfo extends KnetBaseBean {
    /**  
     * serialVersionUID:TODO 
     */
    private static final long serialVersionUID = 4701930688967816443L;
    
    private int id;
    /** 音频长度 **/
    private int audioLength;
    /** 文件md5值 **/
    private String audioMD5;
    /** 文件路径 **/
    private String filePath;
    /** 保存的文件名 **/
    private String fileName;
    /** 服务器音频地址 **/
    private String audioId;


    /**
     * @return 返回 id。
     */
    public int getId() {
        return id;
    }
    /**
     * @param id 要设置的 id。
     */
    public void setId(int id) {
        this.id = id;
    }
    /**
     * @return 返回 filePath。
     */
    public String getFilePath() {
        return filePath;
    }
    /**
     * @param filePath 要设置的 filePath。
     */
    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }
    /**
     * @return 返回 audioId。
     */
    public String getAudioId() {
        return audioId;
    }
    /**
     * @param audioId 要设置的 audioId。
     */
    public void setAudioId(String audioId) {
        this.audioId = audioId;
    }
    /**
     * @return 返回 fileName。
     */
    public String getFileName() {
        return fileName;
    }
    /**
     * @param fileName 要设置的 fileName。
     */
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
    /**
     * @return 返回 audioLength。
     */
    public int getAudioLength() {
        return audioLength;
    }
    /**
     * @param audioLength 要设置的 audioLength。
     */
    public void setAudioLength(int audioLength) {
        this.audioLength = audioLength;
    }
    /**
     * @return 返回 audioMD5。
     */
    public String getAudioMD5() {
        return audioMD5;
    }
    /**
     * @param audioMD5 要设置的 audioMD5。
     */
    public void setAudioMD5(String audioMD5) {
        this.audioMD5 = audioMD5;
    }


}
