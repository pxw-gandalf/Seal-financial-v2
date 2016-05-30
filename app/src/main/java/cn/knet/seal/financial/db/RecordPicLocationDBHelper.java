/**
 * @desc  
 * @date 2016-1-14
 */
package cn.knet.seal.financial.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import cn.knet.seal.financial.bean.ReviewPicInfo;
import cn.knet.seal.financial.util.LogUtil;

/**
 * @desc 拍照时记录照片位置，存入数据库 
 * @date 2016-1-14-上午11:51:13
 */
public class RecordPicLocationDBHelper extends SQLiteOpenHelper {
    //表名
    private String TABLE_RECORD_NAME = "picInfo";

    //仍使用之前的老库
    public RecordPicLocationDBHelper(Context context, String name) {
        super(context, name, null, 1);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        LogUtil.e("db onCreate", "走创建数据的流程....");
        String sql2 = "create table " + TABLE_RECORD_NAME + " (_id integer primary key autoincrement, reviewID text, photoType text, " +
                "fileName text, lng text, lat text )";
        db.execSQL(sql2);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        
    }
    
    /**
     * 插入一条记录
     * @desc 
     * @date 2016-1-15-下午3:15:47
     * @param rpInfo void
     */
    public void insert(ReviewPicInfo rpInfo){
        SQLiteDatabase db = getWritableDatabase();
        db.insert(TABLE_RECORD_NAME, null, getContentValues(rpInfo));
    }
    
    public double[] query(ReviewPicInfo rpInfo) {
        SQLiteDatabase db = getReadableDatabase();
        double[] tmp = new double[2];
        Cursor cursor = db.rawQuery("select * from " + TABLE_RECORD_NAME + " where reviewID = ? and photoType = ? and fileName = ? ",
                new String[]{rpInfo.getReviewID(),rpInfo.getPhotoType(),rpInfo.getFileName()});
        if (cursor != null) {
            if (cursor.moveToNext()) {
                double lng = cursor.getDouble(4);
                double lat = cursor.getDouble(5);
                tmp[0] = lng;
                tmp[1] = lat;
            }
            cursor.close();
        }
        return tmp;
    }
    
    /**
     * 将rpInfo转化成ContentValues<BR>
     * 
     * @param rpInfo
     * @return ContentValues
     */
    private ContentValues getContentValues(ReviewPicInfo rpInfo) {
        ContentValues values = new ContentValues();
        values.put("reviewID", rpInfo.getReviewID());
        values.put("photoType", rpInfo.getPhotoType());
        values.put("fileName", rpInfo.getFileName());
        values.put("lng", rpInfo.getLng());
        values.put("lat", rpInfo.getLat());
        return values;
    }


    /**
     * @desc 删除一条数据
     * @date 2016-1-15-下午5:14:13
     * @param rpInfo void 
     */
    public void delet(ReviewPicInfo rpInfo) {
        SQLiteDatabase db = getReadableDatabase();
        db.execSQL("delete from " + TABLE_RECORD_NAME + " where reviewID = ? and photoType = ? and fileName = ? ", 
                new String[]{rpInfo.getReviewID(),rpInfo.getPhotoType(),rpInfo.getFileName()});
    }

}
