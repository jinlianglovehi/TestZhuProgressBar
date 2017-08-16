package application.android.com.testzhuprogressbar.realtimeguidance;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by jinliang on 17-8-2.
 */

public class RealTimeGuideDBManager {

    private static final String TAG = RealTimeGuideDBManager.class.getSimpleName();
    private Context mContext ;
    private static final String DB_NAME = "real_time_guidance_table.db" ;

    private static final String SELECET_ITEM_BY_PHRASE_NUMBER =" select *  from real_time_guidance_table where phrase_number = ?" ;


    public RealTimeGuideDBManager(Context mContext) {
        this.mContext = mContext;
    }
    //把assets目录下的db文件复制到dbpath下
    public SQLiteDatabase initDBManager(String packName) {
        String dbPath = "/data/data/" + packName
                + "/databases/" + DB_NAME;
        Log.i(TAG, " dbPath:"+ dbPath);
        if (!new File(dbPath).exists()) {
            try {
                FileOutputStream out = new FileOutputStream(dbPath);
                InputStream in = mContext.getAssets().open(DB_NAME);
                byte[] buffer = new byte[1024];
                int readBytes = 0;
                while ((readBytes = in.read(buffer)) != -1){
                    Log.i(TAG," db  insert batch ");
                    out.write(buffer, 0, readBytes);
                }

                in.close();
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return SQLiteDatabase.openOrCreateDatabase(dbPath, null);
    }


    /**
     * select item by phrase_number
     * @param db
     * @param phraseNumber
     * @return
     */
    public RealTimeGuidanceModel selectItemByPhraseNumber(SQLiteDatabase db ,
                                               int phraseNumber ){
        RealTimeGuidanceModel model =  null ;
        Cursor cursor = db.rawQuery(SELECET_ITEM_BY_PHRASE_NUMBER,
                new String[]{String.valueOf(phraseNumber)});
        int index ;
        if(cursor.moveToFirst()){
            model =new RealTimeGuidanceModel();
            index = cursor.getColumnIndex(RealTimeGuideEntity.PHRASE_NUMBER);
            if(index>=0){
                model.setPhraseNumber(cursor.getInt(index));
            }
            index = cursor.getColumnIndex(RealTimeGuideEntity.GUIDE_DESC);
            Log.i(TAG," desc:"+ index);
            if(index>=0){
                model.setGuideDesc(cursor.getString(index));
            }
            index = cursor.getColumnIndex(RealTimeGuideEntity.GUIDE_TITLE);
            if(index>=0){
                model.setGuideTitle(cursor.getString(index));
            }
        }
        cursor.close();
        return model ;

    }

    public static class RealTimeGuideEntity  {

        public static final String TABLE_NAME  = "real_time_guidance_table" ;
        public static final String PHRASE_NUMBER = "phrase_number" ;
        public static final String GUIDE_TITLE = "guide_title" ;
        public static final String GUIDE_DESC  = "guide_desc" ;
    }


}
