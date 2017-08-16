package application.android.com.testzhuprogressbar.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Html;
import android.widget.TextView;

import application.android.com.testzhuprogressbar.R;

/**
 * Created by jinliang on 17-7-21.
 */

public class TestVo2MaxActivity extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test_watch_page_activity);
//        ((TextView)findViewById(R.id.vo2_max)).setText(Html.fromHtml(getString(R.string.first_beat_help_vo2_max_html)));
    }
}
