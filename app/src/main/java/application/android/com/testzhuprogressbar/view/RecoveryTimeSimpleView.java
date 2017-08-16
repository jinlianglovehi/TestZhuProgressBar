package application.android.com.testzhuprogressbar.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by jinliang on 17-7-24.
 */

//public class RecoveryTimeSimpleView extends View{

//    private Paint mPaint;
//    private int recoveryTimeHour;
//    private String recoveryUnitStr;
//
//    private float mMarginLeft,mMarginRight,mMarginTop,mMarginBottom;
//    private float mCanvasHeigth,mCanvasWidth;
//
//    public RecoveryTimeSimpleView(Context context) {
//        this(context,null);
//    }
//
//    public RecoveryTimeSimpleView(Context context, @Nullable AttributeSet attrs) {
//        this(context, attrs,0);
//    }
//
//    public RecoveryTimeSimpleView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
//        super(context, attrs, defStyleAttr);
//        initPaint();
//    }
//
//    private void initPaint(){
//        recoveryUnitStr = "恢复";
//        mPaint = new Paint();
//        mPaint.setAntiAlias(true);
//        mPaint.setColor(Color.GRAY);
//
//    }
//
//    @Override
//    public void draw(Canvas canvas) {
//        super.draw(canvas);
//        initMeasurexData(canvas);
//        drawGrayCircle(canvas) ;
//        drawRedProgress(canvas);
//    }
//
//    private void drawRedProgress(Canvas canvas) {
//
//
//    }
//    private void drawGrayCircle(Canvas canvas) {
//
//        float left= 0f,  top=0f,  right=0f,  bottom=0f;
//
//        left =
//        RectF rect = new RectF(left,top,right,bottom);
//        canvas.drawArc(rect,-60,60,false,mPaint);//set line
//    }
//
//    private void initMeasurexData(Canvas canvas) {
//        mCanvasHeigth = getHeight()-mMarginTop-mMarginBottom;
//        mCanvasWidth = getWidth() -mMarginRight-mMarginLeft;
//    }
//
//
//    private void getLevelProgress(int progressLevel){
//        recoveryTimeHour = progressLevel;
//
//    }
//
//
//    public void setRecoveryTimeHour(int timeHour){
//        recoveryTimeHour = timeHour;
//        postInvalidate();
//    }


//}
