package application.android.com.testzhuprogressbar;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import static application.android.com.testzhuprogressbar.FitNessLevelEntity.GENDER_MAN;
import static application.android.com.testzhuprogressbar.FitNessLevelEntity.GENDER_WOMEN;

/**
 * Created by jinliang on 17-7-14.
 */

public class FitnessLevelSqliteHelper extends SQLiteOpenHelper {

    private static final String TAG = FitnessLevelSqliteHelper.class.getSimpleName();

    private static final String DATABASE_PATH = "/data/data/PACKAGE_NAME/databases/";
    private static final String DATABASE_NAME = "text.db";
    private static final int DATABASE_VERSION = 1;

    private Context mContext ;
    public FitnessLevelSqliteHelper(Context context) {
        this(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.mContext = context;
        Log.i(TAG, " FitnessLevelSqliteHelper method 01  ");
    }

    public FitnessLevelSqliteHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        Log.i(TAG, " FitnessLevelSqliteHelper method 02  ");
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.i(TAG, " FitnessLevelSqliteHelper onCreate ");
        db.execSQL(FitNessSqlUtils.getCreateFitnessLevelTableSql());
        db.beginTransaction();

//        s(int gender, int startAge,int endAge,int startVo2Max, int endVo2max ,int fitnessLevel
        ContentValues contentValues =null;
        /**
         * man
         */
         List<int[]> listData =new ArrayList<>();
        // int gender, int startAge,int endAge,int startVo2Max, int endVo2max ,int fitnessLevel

        /**
         *  man
         *  20-24
         */
        listData.add(new int[]{GENDER_MAN,20,24,0,32,1});
        listData.add(new int[]{GENDER_MAN,20,24,32,37,2});

        listData.add(new int[]{GENDER_MAN,20,24,38,43,3});
        listData.add(new int[]{GENDER_MAN,20,24,44,50,4});

        listData.add(new int[]{GENDER_MAN,20,24,51,56,5});
        listData.add(new int[]{GENDER_MAN,20,24,57,62,6});

        listData.add(new int[]{GENDER_MAN,20,24,62,62,10000,7});


        /**
         * man
         * 25-29
         */
        listData.add(new int[]{GENDER_MAN,25,29,0,31,1}) ;
        listData.add(new int[]{GENDER_MAN,25,29,31,35,2});

        listData.add(new int[]{GENDER_MAN,25,29,36,42,3});
        listData.add(new int[]{GENDER_MAN,25,29,43,48,4});

        listData.add(new int[]{GENDER_MAN,25,29,49,53,5});
        listData.add(new int[]{GENDER_MAN,25,29,54,59,6});

        listData.add(new int[]{GENDER_MAN,25,29,59,10000,7});


        /**
         * man
         * 30-34
         */

        listData.add(new int[]{GENDER_MAN,30,34,0,29,1});
        listData.add(new int[]{GENDER_MAN,30,34,29,34,2});

        listData.add(new int[]{GENDER_MAN,30,34,35,40,3});
        listData.add(new int[]{GENDER_MAN,30,34,41,45,4});

        listData.add(new int[]{GENDER_MAN,30,34,46,51,5});
        listData.add(new int[]{GENDER_MAN,30,34,52,56,6});

        listData.add(new int[]{GENDER_MAN,30,34,56,10000,7});


        /**
         * man 35-39
         */

        listData.add(new int[]{GENDER_MAN,35,39,0,28,1});
        listData.add(new int[]{GENDER_MAN,35,39,28,32,2});

        listData.add(new int[]{GENDER_MAN,35,39,33,38,3});
        listData.add(new int[]{GENDER_MAN,35,39,39,43,4});

        listData.add(new int[]{GENDER_MAN,35,39,44,48,5});
        listData.add(new int[]{GENDER_MAN,35,39,49,54,6});

        listData.add(new int[]{GENDER_MAN,35,39,54,10000,7});

        /**
         * man 40-44
         */
        listData.add(new int[]{GENDER_MAN,40,44,0,26,1});
        listData.add(new int[]{GENDER_MAN,40,44,26,31,2});

        listData.add(new int[]{GENDER_MAN,40,44,32,35,3});
        listData.add(new int[]{GENDER_MAN,40,44,36,41,4});

        listData.add(new int[]{GENDER_MAN,40,44,42,46,5});
        listData.add(new int[]{GENDER_MAN,40,44,47,51,6});

        listData.add(new int[]{GENDER_MAN,40,44,51,10000,7});


        /**
         * man 45-49
         */
        listData.add(new int[]{GENDER_MAN,45,49,0,25,1});
        listData.add(new int[]{GENDER_MAN,45,49,25,29,2});

        listData.add(new int[]{GENDER_MAN,45,49,30,34,3});
        listData.add(new int[]{GENDER_MAN,45,49,35,39,4});

        listData.add(new int[]{GENDER_MAN,45,49,40,43,5});
        listData.add(new int[]{GENDER_MAN,45,49,44,48,6});

        listData.add(new int[]{GENDER_MAN,45,49,48,10000,7});


        /**
         * man 50-54
         */
        listData.add(new int[]{GENDER_MAN,50,54,0,24,1});
        listData.add(new int[]{GENDER_MAN,50,54,24,27,2});

        listData.add(new int[]{GENDER_MAN,50,54,28,32,3});
        listData.add(new int[]{GENDER_MAN,50,54,33,36,4});

        listData.add(new int[]{GENDER_MAN,50,54,37,41,5});
        listData.add(new int[]{GENDER_MAN,50,54,42,46,6});

        listData.add(new int[]{GENDER_MAN,50,54,46,10000,7});


        /**
         * man 55-59
         */
        listData.add(new int[]{GENDER_MAN,55,59,0,22,1});
        listData.add(new int[]{GENDER_MAN,55,59,22,26,2});

        listData.add(new int[]{GENDER_MAN,55,59,27,30,3});
        listData.add(new int[]{GENDER_MAN,55,59,31,34,4});

        listData.add(new int[]{GENDER_MAN,55,59,35,39,5});
        listData.add(new int[]{GENDER_MAN,55,59,40,43,6});

        listData.add(new int[]{GENDER_MAN,55,59,43,10000,7});


        /**
         * man 60-65
         */
        listData.add(new int[]{GENDER_MAN,60,65,0,21,1});
        listData.add(new int[]{GENDER_MAN,60,65,21,24,2});

        listData.add(new int[]{GENDER_MAN,60,65,25,28,3});
        listData.add(new int[]{GENDER_MAN,60,65,29,32,4});

        listData.add(new int[]{GENDER_MAN,60,65,33,36,5});
        listData.add(new int[]{GENDER_MAN,60,65,37,40,6});

        listData.add(new int[]{GENDER_MAN,60,65,40,10000,7});

        /**
         * women 20-24
         */

        listData.add(new int[]{GENDER_WOMEN,20,24,0,27,1});
        listData.add(new int[]{GENDER_WOMEN,20,24,27,31,2});

        listData.add(new int[]{GENDER_WOMEN,20,24,32,36,3});
        listData.add(new int[]{GENDER_WOMEN,20,24,37,41,4});

        listData.add(new int[]{GENDER_WOMEN,20,24,42,46,5});
        listData.add(new int[]{GENDER_WOMEN,20,24,47,51,6});

        listData.add(new int[]{GENDER_WOMEN,20,24,51,100000,7});


        /**
         * women 25-29
         */

        listData.add(new int[]{GENDER_WOMEN,25,29,0,26,1});
        listData.add(new int[]{GENDER_WOMEN,25,29,26,30,2});

        listData.add(new int[]{GENDER_WOMEN,25,29,31,35,3});
        listData.add(new int[]{GENDER_WOMEN,25,29,36,40,4});

        listData.add(new int[]{GENDER_WOMEN,25,29,41,44,5});
        listData.add(new int[]{GENDER_WOMEN,25,29,45,49,6});

        listData.add(new int[]{GENDER_WOMEN,25,29,49,10000,7});


        /**
         * women 30-34
         */
        listData.add(new int[]{GENDER_WOMEN,30,34,0,25,1});
        listData.add(new int[]{GENDER_WOMEN,30,34,25,29,2});

        listData.add(new int[]{GENDER_WOMEN,30,34,30,33,3});
        listData.add(new int[]{GENDER_WOMEN,30,34,34,37,4});

        listData.add(new int[]{GENDER_WOMEN,30,34,38,42,5});
        listData.add(new int[]{GENDER_WOMEN,30,34,43,46,6});
        listData.add(new int[]{GENDER_WOMEN,30,34,46,10000,7});


        /**
         * women 35-39
         */
        listData.add(new int[]{GENDER_WOMEN,35,39,0,24,1});
        listData.add(new int[]{GENDER_WOMEN,35,39,24,27,2});

        listData.add(new int[]{GENDER_WOMEN,35,39,28,31,3});
        listData.add(new int[]{GENDER_WOMEN,35,39,32,35,4});

        listData.add(new int[]{GENDER_WOMEN,35,39,36,40,5});
        listData.add(new int[]{GENDER_WOMEN,35,39,41,44,6});

        listData.add(new int[]{GENDER_WOMEN,35,39,44,10000,7});


        /**
         *  women 40-44
         */
        listData.add(new int[]{GENDER_WOMEN,40,44,0,22,1});
        listData.add(new int[]{GENDER_WOMEN,40,44,22,25,2});

        listData.add(new int[]{GENDER_WOMEN,40,44,26,29,3});
        listData.add(new int[]{GENDER_WOMEN,40,44,30,33,4});

        listData.add(new int[]{GENDER_WOMEN,40,44,34,37,5});
        listData.add(new int[]{GENDER_WOMEN,40,44,38,41,6});

        listData.add(new int[]{GENDER_WOMEN,40,44,41,10000,7});


        /**
         * women 45-49
         */
        listData.add(new int[]{GENDER_WOMEN,45,49,0,21,1});
        listData.add(new int[]{GENDER_WOMEN,45,49,21,23,2});

        listData.add(new int[]{GENDER_WOMEN,45,49,24,27,3});
        listData.add(new int[]{GENDER_WOMEN,45,49,28,31,4});

        listData.add(new int[]{GENDER_WOMEN,45,49,32,35,5});
        listData.add(new int[]{GENDER_WOMEN,45,49,36,38,6});

        listData.add(new int[]{GENDER_WOMEN,45,49,38,10000,7});


        /**
         * women 50-54
         */
        listData.add(new int[]{GENDER_WOMEN,50,54,0,19,1});
        listData.add(new int[]{GENDER_WOMEN,50,54,19,22,2});

        listData.add(new int[]{GENDER_WOMEN,50,54,23,25,3});
        listData.add(new int[]{GENDER_WOMEN,50,54,26,29,4});

        listData.add(new int[]{GENDER_WOMEN,50,54,30,32,5});
        listData.add(new int[]{GENDER_WOMEN,50,54,33,36,6});

        listData.add(new int[]{GENDER_WOMEN,50,54,36,10000,7});


        /**
         * women 55-59
         */

        listData.add(new int[]{GENDER_WOMEN,55,59,0,18,1});
        listData.add(new int[]{GENDER_WOMEN,55,59,18,20,2});

        listData.add(new int[]{GENDER_WOMEN,55,59,21,23,3});
        listData.add(new int[]{GENDER_WOMEN,55,59,24,27,4});

        listData.add(new int[]{GENDER_WOMEN,55,59,28,30,5});
        listData.add(new int[]{GENDER_WOMEN,55,59,31,33,6});

        listData.add(new int[]{GENDER_WOMEN,55,59,33,10000,7});

        /**
         * women 60-65
         */

        listData.add(new int[]{GENDER_WOMEN,60,65,0,16,1});
        listData.add(new int[]{GENDER_WOMEN,60,65,16,18,2});

        listData.add(new int[]{GENDER_WOMEN,60,65,19,21,3});
        listData.add(new int[]{GENDER_WOMEN,60,65,22,24,4});

        listData.add(new int[]{GENDER_WOMEN,60,65,25,27,5});
        listData.add(new int[]{GENDER_WOMEN,60,65,28,30,6});

        listData.add(new int[]{GENDER_WOMEN,60,65,30,10000,7});


        int[] currnetInts  = null;
        for (int i = 0; i < listData.size(); i++) {
            currnetInts  =listData.get(i);
            contentValues = FitNessSqlUtils.getInsertOneContentValues(currnetInts[0],currnetInts[1]
                    ,currnetInts[2],currnetInts[3],
                    currnetInts[4],currnetInts[5]);
            db.insert(FitNessLevelEntity.TABLE_NAME, null, contentValues);
        }


        db.setTransactionSuccessful();
        db.endTransaction();

    }




    private void insertOneData(int gender, int startAge,int endAge,int startVo2Max, int endVo2max ,int fitnessLevel){


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
