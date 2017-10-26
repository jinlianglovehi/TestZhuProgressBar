package application.android.com.testzhuprogressbar.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * Created by jinliang on 17-10-26.
 */

public class Vo2maxStatusView extends View {


    private static final String TAG = Vo2maxStatusView.class.getSimpleName();
    private Paint mPaint ;

    private float marginLeft =10f;
    private float marginRigth = 10f ;
    private float marginTop = 10f ;
    private float marginBottom = 10f ;
    private float radius = 100f;

    private int percentValue =90 ;

    private float centerX,centerY =0f ;
    public Vo2maxStatusView(Context context) {
        this(context, null);
    }

    public Vo2maxStatusView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public Vo2maxStatusView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initPatin();
    }

    private void initPatin() {
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setStrokeWidth(3f);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeCap(Paint.Cap.BUTT);//
        mPaint.setColor(Color.RED);

    }


    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        Log.i(TAG,"draw");

        centerX= (getWidth()-marginLeft-marginRigth)/2 ;
        centerY = (getHeight()-marginTop-marginBottom)/2 ;

        RectF oval = new RectF(centerX-radius, centerY-marginTop,
                centerX-marginRigth,centerY- marginBottom);
        canvas.drawArc(oval,150,240,false,mPaint);



    }
}
