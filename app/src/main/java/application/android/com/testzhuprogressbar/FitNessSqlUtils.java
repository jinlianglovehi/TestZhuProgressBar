package application.android.com.testzhuprogressbar;

import android.content.ContentValues;
import android.util.Log;

/**
 * Created by jinliang on 17-7-14.
 */

public class FitNessSqlUtils {

    public static String  getCreateFitnessLevelTableSql(){
        StringBuilder sb = new StringBuilder() ;
        sb.append(" create table  if not exists  " + FitNessLevelEntity.TABLE_NAME ) ;
        sb.append(" (" + FitNessLevelEntity.COLUMN_ID +" integer PRIMARY KEY AUTOINCREMENT NOT NULL ") ;
        sb.append("  ," + FitNessLevelEntity.COLUMN_GENDER +" integer ") ;
        sb.append("  ," + FitNessLevelEntity.COLUMN_START_AGE +" integer ") ;
        sb.append("  ," + FitNessLevelEntity.COLUMN_END_AGE +" integer ") ;
        sb.append("  ," + FitNessLevelEntity.COLUMN_START_VO2_MAX +" integer ") ;
        sb.append("  ," + FitNessLevelEntity.COLUMN_END_VO2_MAX +" integer ") ;
        sb.append("  ," + FitNessLevelEntity.COLUMN_FITNESS_LEVEL +" integer ") ;
        sb.append(" ) ") ;
        return sb.toString();
    }

    public static ContentValues getInsertOneContentValues(int gender, int startAge,int endAge,int startVo2Max, int endVo2max ,int fitnessLevel) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(FitNessLevelEntity.COLUMN_GENDER,gender);
        contentValues.put(FitNessLevelEntity.COLUMN_START_AGE,startAge);
        contentValues.put(FitNessLevelEntity.COLUMN_END_AGE,endAge);
        contentValues.put(FitNessLevelEntity.COLUMN_START_VO2_MAX,startVo2Max);
        contentValues.put(FitNessLevelEntity.COLUMN_END_VO2_MAX,endVo2max);
        contentValues.put(FitNessLevelEntity.COLUMN_FITNESS_LEVEL,fitnessLevel);
        return contentValues;
    }

}
