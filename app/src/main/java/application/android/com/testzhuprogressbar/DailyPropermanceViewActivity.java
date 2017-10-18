package application.android.com.testzhuprogressbar;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import application.android.com.testzhuprogressbar.cache.BytesLruUtils;
import application.android.com.testzhuprogressbar.utils.DensityUtil;

/**
 * Created by jinliang on 17-8-3.
 */

public class DailyPropermanceViewActivity extends Activity{

    private static final String TAG = DailyPropermanceViewActivity.class.getSimpleName();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daily_propermance_view);
//        getAndroiodScreenProperty();
         final DailyPerformenceChatView chatView =  (DailyPerformenceChatView)findViewById(R.id.default_activity_button);

        chatView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chatView.setUpdate();
                testHuHu();
            }
        });

    }

    private void testHuHu() {
        Log.i(TAG," testHuhu:" +System.currentTimeMillis()/1000 +"," +(int)(System.currentTimeMillis()));
        Log.i(TAG," dimp:"+ DensityUtil.px2dip(DailyPropermanceViewActivity.this,100));

    }

    public void getAndroiodScreenProperty(){
        WindowManager wm = (WindowManager) this.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics dm = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(dm);
        int width = dm.widthPixels;// 屏幕宽度（像素）
        int height= dm.heightPixels; // 屏幕高度（像素）
        float density = dm.density;//屏幕密度（0.75 / 1.0 / 1.5）
        int densityDpi = dm.densityDpi;//屏幕密度dpi（120 / 160 / 240）
        //屏幕宽度算法:屏幕宽度（像素）/屏幕密度
        int screenWidth = (int) (width/density);//屏幕宽度(dp)
        int screenHeight = (int)(height/density);//屏幕高度(dp)
        Log.i(TAG, screenWidth + "======" + screenHeight);
    }

    public void getJsonData(){

       String result = "{\"items\":[{\"vo2_max_walking\":-1,\"update_time\":1504527211523,\"dayId\":1504497600000,\"vo2_max_run\":0}]}";

        try {
            JSONObject jsonObject = new JSONObject(result);
            JSONArray jsonArray = jsonObject.optJSONArray("items");
            JSONObject item =null;
            int vo2MaxWalking = 0 ,vo2maxRunning = -1 ;
            for (int i = 0; i <jsonArray.length() ; i++) {
                 item = (JSONObject) jsonArray.get(i);
                vo2MaxWalking =item.optInt("vo2_max_walking");
                vo2maxRunning = item.optInt("vo2_max_run");
                Log.i(TAG,"vo2MaxWalking:"+vo2MaxWalking+",vo2maxRunning:"+vo2maxRunning
                +",update_time:"+ item.optLong("update_time")
                );
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }


    }

    private void testData(){
//        SimpleDateFormat format =  new SimpleDateFormat(" yyyy-MM-dd HH:mm:ss");
//        String time=" 2017-10-01 " +" 12:00:00 " ;

        SimpleDateFormat format =  new SimpleDateFormat(" yyyy-MM-dd ");
        String time="   2017-10-01   " ;
        Date date = null;
        try {
            date = format.parse(time);
            System.out.print("Format To times:"+date.getTime());

            Calendar cal = Calendar.getInstance();
            cal.setTimeInMillis(date.getTime());
            cal.set(Calendar.HOUR_OF_DAY, 12);
            cal.set(Calendar.SECOND, 0);
            cal.set(Calendar.MINUTE, 0);
            cal.set(Calendar.MILLISECOND, 0);
            Log.i(TAG, "currentTime:"+date.getTime()+",calendarTime:"+ cal.getTimeInMillis());

        } catch (ParseException e) {
            e.printStackTrace();
        }



    }
}
