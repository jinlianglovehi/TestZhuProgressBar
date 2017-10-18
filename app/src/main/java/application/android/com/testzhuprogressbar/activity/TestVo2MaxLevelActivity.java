package application.android.com.testzhuprogressbar.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;

import application.android.com.testzhuprogressbar.R;
import application.android.com.testzhuprogressbar.view.Vo2MaxLevelFullView;

/**
 * Created by jinliang on 17-8-14.
 */

public class TestVo2MaxLevelActivity extends Activity {

    private static final String TAG = TestVo2MaxLevelActivity.class.getSimpleName();

    private int rightPercent = 0 ;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_vo2_max_level);
        final Vo2MaxLevelFullView vo2MaxLevelFullView = (Vo2MaxLevelFullView) findViewById(R.id.vo2_max_level_full_view);

        vo2MaxLevelFullView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rightPercent ++ ;
                if(rightPercent>7){
                    rightPercent = 0 ;
                }
                vo2MaxLevelFullView.setLeftAndRightLevel(rightPercent,true,rightPercent,false);
            }
        });
//        testRecoveryTime();
    }

    private void test(){
        StringBuilder sb = new StringBuilder();
        sb.append("(");
        for (int i = 0; i < 3; i++) {
            sb.append("?").append(",");
        }
        sb.replace(sb.length()-1,sb.length(),"");
        sb.append(")");

        Log.i(TAG, " sbContent:"+ sb.toString());
    }

    private void testRecoveryTime(){

//        08-16 09:30:16.910  1901  1920 D HmSport : com.huami.watch.newsport.common.manager.DataManager -- get config : FirstBeatConfig{lastVo2Max=0.0, currentVo2Max=0.0, recoveryTime=337, sportFinishTime=1502844194777, maxSportVo2max=0.0, maxRidingVo2Max=0.0}
        long remainTime = System.currentTimeMillis() - 1502844194777l;
       long   recoveryTime = 337 * 60000 - remainTime; ;
        int  hour = (int )((recoveryTime +1800000 ) /3600000);

        Log.i(TAG," beforeHour:"+ hour + " , :"+ recoveryTime /3600000);

        float recoveryTimeFLoat =(((337 - (System.currentTimeMillis()- 1502844194777l)/1000/60)+30)/60);

        Log.i(TAG,"'afterHour:"+ (int)recoveryTimeFLoat);



    }
}
