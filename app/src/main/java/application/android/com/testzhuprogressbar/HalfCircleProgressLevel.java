package application.android.com.testzhuprogressbar;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * Created by jinliang on 17-7-4.
 * 自定义绘制运动能力的半圆进度progressBar
 */

public class HalfCircleProgressLevel extends View {

    private static final String TAG = HalfCircleProgressLevel.class.getSimpleName();
    private Paint mLinePaint ;
    private Paint mProgressLevelPaint;

    private Paint currnetLevelPaint;
    private int currentRataAngle = 0 ;
    private int currentLevel = 0; // set current font number ;
    private String currnetLevelStr = "" ;
    private String unitStr  ="ml/kg/min" ;

    private float mCurrnetLevelFontSize = 120f;
    private float mCurrnetUnitFontSize = 50f ;

    private int mCircleLineStrokeWidth = 5;
    private int mProgressStrokeWidth = 20 ;
    private Bitmap remindBitmap  =null;

    public HalfCircleProgressLevel(Context context) {
        this(context,null);
    }

    public HalfCircleProgressLevel(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public HalfCircleProgressLevel(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initPaint();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        float x = (getWidth() - getHeight() / 2) / 2;
        float y = getHeight() / 4;
        RectF oval = new RectF( x, y,
                getWidth() - x, getHeight() - y);
        canvas.drawArc(oval,-180,180,false,mLinePaint);//set line
        canvas.drawArc(oval,-180,currentRataAngle,false,mProgressLevelPaint);// set progress level


        // huiah
        canvas.drawBitmap(remindBitmap,getWidth()/2-remindBitmap.getWidth()/2, getHeight()/3,mLinePaint);// set up or down icon
        currnetLevelStr = currentLevel+"" ;
        Rect rect = new Rect();
        currnetLevelPaint.setTextSize(mCurrnetLevelFontSize);
        currnetLevelPaint.getTextBounds(String.valueOf(currentLevel), 0, currnetLevelStr.length(), rect);
        int levelNumeWdith= rect.width();
        canvas.drawText(String.valueOf(currentLevel),getWidth()/2-levelNumeWdith/2,getHeight()/2,currnetLevelPaint);// set number text
        currnetLevelPaint.setTextSize(mCurrnetUnitFontSize);
        currnetLevelPaint.getTextBounds(unitStr, 0, unitStr.length(), rect);
        levelNumeWdith= rect.width();
        canvas.drawText(unitStr,getWidth()/2-levelNumeWdith/2,getHeight()/2+60,currnetLevelPaint);

    }

    private void drawArcAndProgressLevel(Canvas canvas){


    }

    private void initPaint(){

        int lineColor = Color.WHITE;
        int progressColor =Color.YELLOW;

        mLinePaint = new Paint();
        mLinePaint.setAntiAlias(true);
        mLinePaint.setStrokeWidth(mCircleLineStrokeWidth);
        mLinePaint.setStyle(Paint.Style.STROKE);
        mLinePaint.setStrokeCap(Paint.Cap.ROUND);//
        mLinePaint.setColor(lineColor);

        mProgressLevelPaint = new Paint();
        mProgressLevelPaint.setAntiAlias(true);
        mProgressLevelPaint.setStrokeWidth(mProgressStrokeWidth);
        mProgressLevelPaint.setStyle(Paint.Style.STROKE);
        mProgressLevelPaint.setStrokeCap(Paint.Cap.ROUND);//
        mProgressLevelPaint.setColor(progressColor);

        remindBitmap  = BitmapFactory.decodeResource(getResources(),R.drawable.rade_triangle);

        currnetLevelPaint = new Paint() ;
        currnetLevelPaint.setAntiAlias(true);
        currnetLevelPaint.setTextSize(mCurrnetLevelFontSize);
        currnetLevelPaint.setColor(Color.WHITE);

    }

    /**
     *
     * @param level : progress Level
     * @param total : full level
     * @param unit: set unit
     */
    public void setProgressLevel(int level, int total,String unit){
        unitStr = unit;
        if(total<=0){
            return ;
        }
        currentRataAngle = level/total * 180 ;
        postInvalidate();
    }
//    public void getTestProgressLevel(){
//        currentRataAngle = 90 ;
//    }
}
