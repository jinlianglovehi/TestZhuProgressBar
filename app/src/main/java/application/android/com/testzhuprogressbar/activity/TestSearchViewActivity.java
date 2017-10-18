package application.android.com.testzhuprogressbar.activity;

import android.app.Activity;
import android.nfc.Tag;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import application.android.com.testzhuprogressbar.R;
import application.android.com.testzhuprogressbar.animalview.SearchView;

/**
 * Created by jinliang on 17-8-16.
 *
 * @author jinliang
 */

public class TestSearchViewActivity extends Activity  implements View.OnClickListener{


    private static final String TAG = TestSearchViewActivity.class.getSimpleName();
    private SearchView search_view;
    private Button btn_atart;
    private Button btn_end;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.actiivty_test_search_view);
        initView();
        initComputerData();
    }

    private void initView() {
        search_view = (SearchView) findViewById(R.id.search_view);
        search_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                search_view.setStartSearch();
            }
        });

//        //代码中设置相关属性
//        search_view.setCircleVaule(80);
//        search_view.setBackgroundColor(Color.GREEN);
//        search_view.setPaintColor(Color.WHITE);
//        search_view.setPaintStrokeWidth(20);
//        search_view.setDivisor(2.5f);

        btn_atart = (Button) findViewById(R.id.btn_atart);
        btn_atart.setOnClickListener(this);
        btn_end = (Button) findViewById(R.id.btn_end);
        btn_end.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_atart:
                search_view.setStartSearch();
                break;
            case R.id.btn_end:
                search_view.setEndSearch();
                break;
        }
    }

    private void initComputerData(){

       int result1 = (int)(46.7+0.5);
        int result2 = (int)(47.4+0.5);
        Log.i(TAG," result1:"+ result1 +",result2:"+ result2);

    }
}
