package application.android.com.testzhuprogressbar.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;

import application.android.com.testzhuprogressbar.R;
import application.android.com.testzhuprogressbar.view.Vo2maxStatusView;

/**
 * Created by jinliang on 17-10-26.
 */

public class TestVo2maxStatusViewActivity extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.actiivty_test_vo2_max_status_view);
        Vo2maxStatusView vo2maxStatusView=(Vo2maxStatusView) findViewById(R.id.vo2_max_status_view );

    }
}
