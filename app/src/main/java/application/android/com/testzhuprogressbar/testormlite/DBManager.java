package application.android.com.testzhuprogressbar.testormlite;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import application.android.com.testzhuprogressbar.FitNessLevelEntity;

import static application.android.com.testzhuprogressbar.FitNessLevelEntity.GENDER_MAN;

/**
 * Created by jinliang on 17-7-19.
 *
 *  db manager
 */
public class DBManager {

    private static final String TAG = DBManager.class.getSimpleName();
    private String DB_NAME = "fitness_level_table.db";
    private Context mContext;

    public int getFitnessLevel(SQLiteDatabase db ){

        int sex  =GENDER_MAN ;
        int age  = 43 ;
        int vo2max = 40 ;
        StringBuilder stringBuilder = new StringBuilder() ;
        stringBuilder.append(" select * from " + FitNessLevelEntity.TABLE_NAME  + "  where " ) ;
        stringBuilder.append( FitNessLevelEntity.COLUMN_GENDER +"=?" ) ;
        stringBuilder.append(" and  " + FitNessLevelEntity.COLUMN_START_AGE +"<=?");
        stringBuilder.append(" and  "+ FitNessLevelEntity.COLUMN_END_AGE +">?") ;
        stringBuilder.append(" and "+ FitNessLevelEntity.COLUMN_START_VO2_MAX +"<=?");
        stringBuilder.append(" and "+ FitNessLevelEntity.COLUMN_END_VO2_MAX+">?");
        Cursor cursor = db.rawQuery(stringBuilder.toString(),
                new String[]{String.valueOf(sex),String.valueOf(age),String.valueOf(age)
                        ,String.valueOf(vo2max),String.valueOf(vo2max)});
        int  fitNessLevel = -100;
        if(cursor.moveToFirst()){
            fitNessLevel =cursor.getInt(cursor.getColumnIndex(FitNessLevelEntity.COLUMN_FITNESS_LEVEL));
        }
        cursor.close();
        Log.i(TAG,"-- fitNessLevel:"+ fitNessLevel);
        return fitNessLevel;
    }



    public DBManager(Context mContext) {
        this.mContext = mContext;
    }
    //把assets目录下的db文件复制到dbpath下
    public SQLiteDatabase initDBManager(String packName) {
        String dbPath = "/data/data/" + packName
                + "/databases/" + DB_NAME;
        if (!new File(dbPath).exists()) {
            try {
                FileOutputStream out = new FileOutputStream(dbPath);
                InputStream in = mContext.getAssets().open("db/"+DB_NAME);
                byte[] buffer = new byte[1024];
                int readBytes = 0;
                while ((readBytes = in.read(buffer)) != -1)
                    out.write(buffer, 0, readBytes);
                in.close();
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return SQLiteDatabase.openOrCreateDatabase(dbPath, null);
    }



}
