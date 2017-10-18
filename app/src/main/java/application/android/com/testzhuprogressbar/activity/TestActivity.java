package application.android.com.testzhuprogressbar.activity;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;

import java.text.DecimalFormat;
import java.util.Calendar;

import application.android.com.testzhuprogressbar.ExerciseLoadProgressView;
import application.android.com.testzhuprogressbar.R;
import application.android.com.testzhuprogressbar.view.RecoveryTimeCircleView;

/**
 * Created by jinliang on 17-7-19.
 */

public class TestActivity extends Activity {

    private static final String TAG = TestActivity.class.getSimpleName();
    private int  minValue = 150 ,
    maxValue =300 ,
    overReachValue = 500 ;
    private  volatile int currnetValue = 0 ;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

         RelativeLayout relativeLayout = (RelativeLayout) findViewById(R.id.rl_container);

//        RecoveryTimeCircleView circleView  =(RecoveryTimeCircleView)findViewById(R.id.recovery_time_circle);
//        circleView.setReceryHourTime(50,70);

        final ExerciseLoadProgressView view = (ExerciseLoadProgressView) findViewById(R.id.expand_activities_button);

        relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                currnetValue = currnetValue +20;
                if(currnetValue>=550){
                    currnetValue = 0;
                }
                view.setTraingLoadProgress( currnetValue,minValue,maxValue,overReachValue);
            }
        });
////         initData(4.0f);
//
//        getOnlyByCurrnetDay(System.currentTimeMillis());
//        testMathSin(30);
//        testMathSin(90);
//        testMathSin(180);
//        testSql();
//        new Thread(){
//
//            @Override
//            public void run() {
//                super.run();
//                try {
//                    Thread.sleep(4000);
//
//                    Calendar cal = Calendar.getInstance();
//                    cal.set(Calendar.HOUR_OF_DAY, 15);
//                    cal.set(Calendar.SECOND, 0);
//                    cal.set(Calendar.MINUTE, 0);
//                    cal.set(Calendar.MILLISECOND, 0);
//                    getOnlyByCurrnetDay(System.currentTimeMillis());
//
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            }
//        }.start();
//



        
    }




    private void testMathSin(int angle){
        Log.i(TAG," Math.sin(Math.toRadians("+angle+")):"+ Math.sin(Math.toRadians(angle)) );
        Log.i(TAG," Math.cos(Math.toRadians("+angle+")):"+ Math.cos(Math.toRadians(angle)) );


    }

    private void testSql(){
         String SQL_CREATE_TABLE_SPORT_THA_INFO_MODEL =
                "CREATE TABLE " + SportThaInfoEntity.TABLE_NAME + "(" +
                        SportThaInfoEntity.COLUMN_DAY_ID + " INTEGER PRIMARY KEY, " + // todo
                        SportThaInfoEntity.COLUMN_WTL_SUM + " INTEGER, " +
                        SportThaInfoEntity.COLUMN_WTL_SUM_OPTIMAL_MAX + " INTEGER, " +
                        SportThaInfoEntity.COLUMN_WTL_SUM_OPTIMAL_MIN + " INTEGER, " +
                        SportThaInfoEntity.COLUMN_WTL_SUM_OVERREACHING + " INTEGER, " +
                        SportThaInfoEntity.COLUMN_VO2_MAX_HREAD + " INTEGER, " +
                        SportThaInfoEntity.COLUMN_TRAINING_LOAD_TREAD + " INTEGER, " +
                        SportThaInfoEntity.COLUMN_WTL_STATUS + " INTEGER, " +
                        SportThaInfoEntity.COLUMN_FITNESS_CLASS + " INTEGER, " +
                        SportThaInfoEntity.COLUMN_FITNESS_LEVEL_INCREASE + " INTEGER, " +
                        SportThaInfoEntity.COLUMN_UPDATE_TIME + " INTEGER, " +
                        SportThaInfoEntity.COLUMN_DATE_STR + " TEXT  )" ;
        Log.i(TAG," create THA sql:"+ SQL_CREATE_TABLE_SPORT_THA_INFO_MODEL.toString());

    }

    public class SportThaInfoEntity{

        public static final String TABLE_NAME  = "sport_tha_info" ;

        public static final String COLUMN_DAY_ID =  "dayId" ;
        public static final String COLUMN_WTL_SUM=  "wtlSum" ;
        public static final String COLUMN_WTL_SUM_OPTIMAL_MAX= "wtlSumOptimalMax" ;
        public static final String COLUMN_WTL_SUM_OPTIMAL_MIN ="wtlSumOptimalMin" ;
        public static final String COLUMN_WTL_SUM_OVERREACHING = "wtlSumOverreaching" ;

        // add
        public static final String COLUMN_VO2_MAX_HREAD = "vo2MaxTread" ;
        public static final String COLUMN_TRAINING_LOAD_TREAD = "trainingLoadTrend" ;
        public static final String COLUMN_WTL_STATUS = "wtlStatus" ;
        public static final String COLUMN_FITNESS_CLASS= "fitnessClass" ;
        public static final String COLUMN_FITNESS_LEVEL_INCREASE = "fitnessLevelIncrease" ;

        public static final String COLUMN_UPDATE_TIME ="updateTime" ;
        public static final String COLUMN_DATE_STR ="dateStr";


    }


    private void getTestData(){

        Log.i(TAG, " current:" + System.currentTimeMillis());
        float recoveryTime =(((50 - (System.currentTimeMillis()- 1500968984088f)/1000/60)+30)/60);

    }
    private int levelInt ;
    private float levelFloat ;
    private void initData(float level){
        levelInt  =(int)Math.floor(level);
        levelFloat = level - levelInt;

    }

    public void getOnlyByCurrnetDay (long currnetTime){
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(currnetTime);
        cal.set(Calendar.HOUR_OF_DAY, 12);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.MILLISECOND, 0);
        Log.i(TAG, "currentTime:"+currnetTime+",calendarTime:"+ cal.getTimeInMillis());
    }
}
