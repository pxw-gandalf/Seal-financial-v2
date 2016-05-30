
package cn.knet.seal.financial.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

import cn.knet.seal.financial.bean.UploadState;
import cn.knet.seal.financial.bean.UploadTask;
import cn.knet.seal.financial.util.LogUtil;

/**
 * 上传文件的数据库辅助类
 * 
 * ClassName: UploadDBHelper <br/>  
 * Date: 2015年4月23日 下午1:45:18 <br/> 
 *  
 * @author yangying@knet.cn 
 * @version 1.0
 * @since 1.0
 * @update
 *      1016/01/07 增加数据类型字段
 */
public class UploadDBHelper extends SQLiteOpenHelper {
    /**
     * debug tag.
     */
    private static final String TAG = "UploadDBHelper";

    /**
     * table name : download
     */
    private static final String TABLE_NAME = "upload";
    
    /** 拍照时记录地理位置 **/
    private static final String TABLE_RECORD_NAME = "record";

    /**
     * 表中字段[插入数据库时系统生成的id]
     */
    private static final String FIELD_ID = "_id";

    /**
     * 表中字段[上传状态]
     */
    private static final String FIELD_UPLOAD_STATE = "uploadState";

    /**
     * 表中字段[文件放置路径]
     */
    private static final String FIELD_FILEPATH = "filepath";

    /**
     * 表中字段[文件名]
     */
    private static final String FIELD_FILENAME = "filename";
    /**
     * 表中字段[文件的md5]
     */
    private static final String FIELD_FILE_MD5 = "filemd5";
    /**
     * 表中字段[title]
     */
    private static final String FIELD_TITLE = "title";
    /**
     * 表中字段[预览小图]
     */
    private static final String FIELD_THUMBNAIL = "thumbnail";

    /**
     * 表中字段[已完成文件大小]
     */
    private static final String FIELD_FINISHED_SIZE = "finishedSize";

    /**
     * 表中字段[文件总大小]
     */
    private static final String FIELD_TOTAL_SIZE = "totalSize";

    private static final String FIELD_REVIEW_ID = "reviewId";
    
    private static final String FIELD_REVIEW_TYPE = "reviewType";
    
    private static final String FIELD_OP_TYPE = "opType";
    /** 数据类型 **/
    private static final String FIELD_DATA_TYPE = "dataType";
    /**
     * Constructor
     * 
     * @param context Context
     * @param name 数据库文件名（.db）由调用者提供
     */
    public UploadDBHelper(Context context, String name) {
        super(context, name, null, 2);
    }

    

    /**
     * 当数据库被首次创建时执行该方法<BR>
     * 创建表等初始化操作在该方法中执行，调用execSQL方法创建表
     * 
     * @param db SQLiteDatabase
     * @see SQLiteOpenHelper#onCreate(SQLiteDatabase)
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        int version = db.getVersion();
        LogUtil.e("db onCreate", "version ------> " + version);
//        Log.i(TAG, "create table.");
        StringBuffer buffer = new StringBuffer("create table ");
        buffer.append(TABLE_NAME);
        buffer.append("(");
        buffer.append(FIELD_ID);
        buffer.append(" integer primary key autoincrement, ");
        buffer.append(FIELD_FILE_MD5);
        buffer.append(" text unique,");
        buffer.append(FIELD_UPLOAD_STATE);
        buffer.append(" text,");
        buffer.append(FIELD_FILEPATH);
        buffer.append(" text, ");
        buffer.append(FIELD_FILENAME);
        buffer.append(" text, ");
        buffer.append(FIELD_TITLE);
        buffer.append(" text, ");
        buffer.append(FIELD_THUMBNAIL);
        buffer.append(" text, ");
        buffer.append(FIELD_FINISHED_SIZE);
        buffer.append(" integer, ");
        buffer.append(FIELD_TOTAL_SIZE);
        buffer.append(" integer, ");
        buffer.append(FIELD_REVIEW_ID);
        buffer.append(" text, ");
        buffer.append(FIELD_REVIEW_TYPE);
        buffer.append(" text, ");
        buffer.append(FIELD_OP_TYPE);
        buffer.append(" text ,");
        buffer.append(FIELD_DATA_TYPE);
        buffer.append(" text)");
        String sql = buffer.toString();
        db.execSQL(sql);
        LogUtil.e("db onCreate", "done ------> " + version);
    }

    /**
     * 当打开数据库时传入的版本号与当前的版本号不同时会调用该方法。<BR>
     * 
     * @param db The database.
     * @param oldVersion The old database version.
     * @param newVersion The new database version
     * @see SQLiteOpenHelper#onUpgrade(SQLiteDatabase,
     *      int, int)
     */

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        LogUtil.e("db onUpgrade", "start ------> " + oldVersion);
        if (oldVersion == 1){
            StringBuffer buffer = new StringBuffer("create table ");
            buffer.append(TABLE_NAME);
            buffer.append("(");
            buffer.append(FIELD_ID);
            buffer.append(" integer primary key autoincrement, ");
            buffer.append(FIELD_FILE_MD5);
            buffer.append(" text unique,");
            buffer.append(FIELD_UPLOAD_STATE);
            buffer.append(" text,");
            buffer.append(FIELD_FILEPATH);
            buffer.append(" text, ");
            buffer.append(FIELD_FILENAME);
            buffer.append(" text, ");
            buffer.append(FIELD_TITLE);
            buffer.append(" text, ");
            buffer.append(FIELD_THUMBNAIL);
            buffer.append(" text, ");
            buffer.append(FIELD_FINISHED_SIZE);
            buffer.append(" integer, ");
            buffer.append(FIELD_TOTAL_SIZE);
            buffer.append(" integer, ");
            buffer.append(FIELD_REVIEW_ID);
            buffer.append(" text, ");
            buffer.append(FIELD_REVIEW_TYPE);
            buffer.append(" text, ");
            buffer.append(FIELD_OP_TYPE);
            buffer.append(" text, ");
            buffer.append(FIELD_DATA_TYPE);
            buffer.append(" text) ");

            String sql = buffer.toString();
            
            db.execSQL("ALTER TABLE " + TABLE_NAME +" RENAME TO t_temp");
            db.execSQL(sql);
            db.execSQL("insert into "+ TABLE_NAME +"(" + FIELD_ID + "," 
                                                       + FIELD_FILE_MD5 + "," 
                                                       + FIELD_UPLOAD_STATE + "," 
                                                       + FIELD_FILEPATH + ","
                                                       + FIELD_FILENAME + ","
                                                       + FIELD_TITLE + ","
                                                       + FIELD_THUMBNAIL + ","
                                                       + FIELD_FINISHED_SIZE + ","
                                                       + FIELD_TOTAL_SIZE + ","
                                                       + FIELD_REVIEW_ID + ","
                                                       + FIELD_REVIEW_TYPE + ","
                                                       + FIELD_OP_TYPE + ","
                                                       + FIELD_DATA_TYPE
                                                       + ")"  
                + "select " + FIELD_ID + "," 
                            + FIELD_FILE_MD5 + "," 
                            + FIELD_UPLOAD_STATE + "," 
                            + FIELD_FILEPATH + ","
                            + FIELD_FILENAME + ","
                            + FIELD_TITLE + ","
                            + FIELD_THUMBNAIL + ","
                            + FIELD_FINISHED_SIZE + ","
                            + FIELD_TOTAL_SIZE + ","
                            + FIELD_REVIEW_ID + ","
                            + FIELD_REVIEW_TYPE + ","
                            + FIELD_OP_TYPE + ","
                            + "\"\"" +
                    " from t_temp");
            db.execSQL("DROP TABLE t_temp");
          }
    }

    /**
     * 存入一条上传任务（直接存入数据库）<BR>
     * 
     * @param downloadTask DownloadTask
     */
    public void insert(UploadTask downloadTask) {
        SQLiteDatabase db = getWritableDatabase();
        db.insert(TABLE_NAME, null, getContentValues(downloadTask));
    }

    /**
     * 根据url查询数据库中相应的下载任务<BR>
     * 
     * @param fileMd5
     * @return DownloadTask
     */
    public UploadTask query(String fileMd5) {
        SQLiteDatabase db = getReadableDatabase();
        UploadTask dlTask = null;
        Cursor cursor = db.query(TABLE_NAME, new String[] {
                FIELD_FILE_MD5, FIELD_UPLOAD_STATE, FIELD_FILEPATH, FIELD_FILENAME, FIELD_TITLE,
                FIELD_THUMBNAIL, FIELD_FINISHED_SIZE, FIELD_TOTAL_SIZE,FIELD_REVIEW_ID,FIELD_REVIEW_TYPE,FIELD_OP_TYPE , FIELD_DATA_TYPE
        }, FIELD_FILE_MD5 + "=?", new String[] {
            fileMd5
        }, null, null, null);
        if (cursor != null) {
            if (cursor.moveToNext()) {
                dlTask = new UploadTask(cursor.getString(0), cursor.getString(2),
                        cursor.getString(3), cursor.getString(4), cursor.getString(5));
                dlTask.setUploadState(UploadState.valueOf(cursor.getString(1)));
                dlTask.setFinishedSize(cursor.getInt(6));
                dlTask.setTotalSize(cursor.getInt(7));
                dlTask.setReviewId(cursor.getString(8));
                dlTask.setReviewType(cursor.getString(9));
                dlTask.setOpType(cursor.getString(10));
                dlTask.setDataType(cursor.getString(11));
            }
            cursor.close();
        }
        return dlTask;
    }

    /**
     * 
     * queryUnDownloaded: 查询待下载的任务列表,增序排列<br/>
     * 
     * Date: 2015年5月5日 下午1:27:28 <br/>
     * @author yangying@knet.cn 
     *  
     * @return
     */
    public List<UploadTask> queryUnDownloaded() {
        List<UploadTask> tasks = new ArrayList<UploadTask>();
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.query(TABLE_NAME, new String[] {
                FIELD_FILE_MD5, FIELD_UPLOAD_STATE, FIELD_FILEPATH, FIELD_FILENAME, FIELD_TITLE,
                FIELD_THUMBNAIL, FIELD_FINISHED_SIZE, FIELD_TOTAL_SIZE,FIELD_REVIEW_ID,FIELD_REVIEW_TYPE,FIELD_OP_TYPE,FIELD_DATA_TYPE
        }, FIELD_UPLOAD_STATE + "<> 'FINISHED'", null, null, null, "_id asc");
        if (cursor != null) {
            while (cursor.moveToNext()) {
                UploadTask dlTask = new UploadTask(cursor.getString(0), cursor.getString(2),
                        cursor.getString(3), cursor.getString(4), cursor.getString(5));
                dlTask.setUploadState(UploadState.valueOf(cursor.getString(1)));
                dlTask.setFinishedSize(cursor.getInt(6));
                dlTask.setTotalSize(cursor.getInt(7));
                dlTask.setReviewId(cursor.getString(8));
                dlTask.setReviewType(cursor.getString(9));
                dlTask.setOpType(cursor.getString(10));
                dlTask.setDataType(cursor.getString(11));
                tasks.add(dlTask);
            }
            cursor.close();
        }
        return tasks;
    }

    /**
     * 更新下载任务<BR>
     * 
     * @param downloadTask DownloadTask
     */
    public void update(UploadTask downloadTask) {
        SQLiteDatabase db = getWritableDatabase();

        db.update(TABLE_NAME, getContentValues(downloadTask), FIELD_FILE_MD5 + "=?", new String[] {
            downloadTask.getFileMd5()
        });
    }

    /**
     * 从数据库中删除一条下载任务<BR>
     * 
     * @param downloadTask DownloadTask
     */
    public void delete(UploadTask downloadTask) {
        SQLiteDatabase db = getWritableDatabase();
        db.delete(TABLE_NAME, FIELD_FILE_MD5 + "=?", new String[] {
            downloadTask.getFileMd5()
        });
    }

    /**
     * 将UploadTask转化成ContentValues<BR>
     * 
     * @param uploadTask uploadTask
     * @return ContentValues
     */
    private ContentValues getContentValues(UploadTask uploadTask) {
        ContentValues values = new ContentValues();
        values.put(FIELD_FILE_MD5, uploadTask.getFileMd5());
        values.put(FIELD_UPLOAD_STATE, uploadTask.getUploadState().toString());
        values.put(FIELD_FILEPATH, uploadTask.getFilePath());
        values.put(FIELD_FILENAME, uploadTask.getFileName());
        values.put(FIELD_TITLE, uploadTask.getTitle());
        values.put(FIELD_THUMBNAIL, uploadTask.getThumbnail());
        values.put(FIELD_FINISHED_SIZE, uploadTask.getFinishedSize());
        values.put(FIELD_TOTAL_SIZE, uploadTask.getTotalSize());
        values.put(FIELD_REVIEW_ID, uploadTask.getReviewId());
        values.put(FIELD_REVIEW_TYPE, uploadTask.getReviewType());
        values.put(FIELD_OP_TYPE, uploadTask.getOpType());
        values.put(FIELD_DATA_TYPE, uploadTask.getDataType());
        return values;
    }
}
