package application.android.com.testzhuprogressbar.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * Created by jinliang on 17-7-19.
 */

public class RecoveryTimeCircleView extends View {
    private static final String TAG = RecoveryTimeCircleView.class.getSimpleName();
    private Paint mCircleRedPaint, mFontPaint;
    private float mCircleLineStrokeWidth  =10f;

    private float mMarginLeft=0f,mMarginRigth=0f,mMarginTop=0f,mMarginBottom=0f;

    private float mCanvasHeigth=0f, mCanvasWidth = 0f ;
    private float radiusLength = 70f ;

    private int mSegments = 9 ;
    private int spaceFreeAngle = 60 ;// bottom open radio
    private int spaceAngleStep = 3;// gray radio
    private float colorAngleStep = 60;
    private float startAngle = 0;
    private int levelInt ;
    private float levelFloat ;
    private int remainingHourTime  ;

    private float biggTxtSize = 60f ;
    private float smallTxtSize = 30f;


    private String timeUnit ="hour";
    /**
     * center paint
     * @param context
     */
    private float mCenterX,mCenterY ;
    public RecoveryTimeCircleView(Context context) {
        this(context,null);
    }

    public RecoveryTimeCircleView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public RecoveryTimeCircleView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initPaint();
    }

    private void initPaint(){
        mCircleRedPaint = new Paint();
        mCircleRedPaint.setAntiAlias(true);
        mCircleRedPaint.setStrokeWidth(mCircleLineStrokeWidth);
        mCircleRedPaint.setStyle(Paint.Style.STROKE);
        mCircleRedPaint.setStrokeCap(Paint.Cap.BUTT);//
        mCircleRedPaint.setColor(Color.RED);


        mFontPaint = new Paint();
        mFontPaint.setAntiAlias(true);
        mFontPaint.setStyle(Paint.Style.STROKE);
        mFontPaint.setStrokeCap(Paint.Cap.ROUND);
        mFontPaint.setColor(Color.WHITE);
        mFontPaint.setTextSize(smallTxtSize);


    }
    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
//        getTestData();
        initData();
        drawCircleBack(canvas);

        drawHoutNumber(canvas);

    }

    private void initData(){
        mCanvasHeigth = getHeight()-mMarginTop-mMarginBottom;
        mCanvasWidth =getWidth()-mMarginLeft-mMarginRigth;
        colorAngleStep = (360-spaceFreeAngle-(mSegments-1)*spaceAngleStep)/mSegments;
        startAngle = 90+spaceFreeAngle/2;
        Log.i(TAG," colorAngleStep:"+ colorAngleStep +" ,startAngle:"+startAngle);
    }
    private void drawCircleBack(Canvas canvas){

        Log.i(TAG," levelInt:" +levelInt +",levelFloat:"+levelFloat);
        mCenterX = mMarginLeft+ mCanvasWidth/2;
        mCenterY = mMarginTop + mCanvasHeigth/2 ;
        RectF  rect = new RectF( mCenterX-radiusLength,mCenterY-radiusLength,mCenterX+radiusLength,mCenterY+radiusLength);
        float startCircleAngle =0, endCircleAngle =0 ;
        mCircleRedPaint.setColor(Color.GRAY);
        for (int i = 0; i <mSegments ; i++) {
            startCircleAngle = startAngle + (colorAngleStep+spaceAngleStep)*i;
            endCircleAngle =startCircleAngle + colorAngleStep;
            Log.i(TAG, " startCircleAngle:"+ startCircleAngle+",endCircleAngle:" + endCircleAngle);
            canvas.drawArc(rect, startCircleAngle, colorAngleStep, false, mCircleRedPaint);
        }

        drawRedColor(canvas,rect);
    }

    private void drawRedColor(Canvas canvas, RectF rect){
        mCircleRedPaint.setColor(Color.RED);
        float startCircleAngle =startAngle, endCircleAngle =0 ;
        for (int i = 0; i <levelInt ; i++) {
            startCircleAngle = startAngle + (colorAngleStep+spaceAngleStep)*i;
            endCircleAngle =startCircleAngle + colorAngleStep;
            Log.i(TAG, " startCircleAngle:"+ startCircleAngle+",endCircleAngle:" + endCircleAngle);
            canvas.drawArc(rect, startCircleAngle, colorAngleStep, false, mCircleRedPaint);
        }
        Log.i(TAG, " last red startCircleAngle:"+ startCircleAngle+",endCircleAngle:" + colorAngleStep*levelFloat );
        canvas.drawArc(rect, endCircleAngle+spaceAngleStep, colorAngleStep*levelFloat , false, mCircleRedPaint);
    }

    private void drawHoutNumber(Canvas canvas){
        Rect rect = new Rect();
        String houtStr = String.valueOf(remainingHourTime);
        mFontPaint.getTextBounds(houtStr, 0, houtStr.length(), rect);
        mFontPaint.setTextAlign(Paint.Align.CENTER);
        mFontPaint.setTextSize(biggTxtSize);
        float x = mMarginLeft + mCanvasWidth/2;
        float y = mMarginTop + mCanvasHeigth/2 + rect.height()/2 ;
        canvas.drawText(houtStr,x,y,mFontPaint);
        y = y+mCanvasHeigth/6;
        mFontPaint.setTextSize(smallTxtSize);
        mFontPaint.setTypeface(Typeface.DEFAULT);
        canvas.drawText(timeUnit,x,y,mFontPaint);

    }

    private void drawHourTxt(Canvas canvas){



    }

    public void setReceryHourTime(float  remainingTime ,float totalReceryHour){
        float step =totalReceryHour/mSegments;
        float levelTotal = remainingTime/step ;
        levelInt  =(int)Math.floor(levelTotal);
        levelFloat = levelTotal - levelInt;
        remainingHourTime = (int)remainingTime;
        postInvalidate();

    }

    private void getTestData(){
        float process =30 ;
        float total = 87 ;
        float step =total/mSegments;
        float levelTotal = process/step ;
        remainingHourTime  =30 ;
        levelInt  =(int)Math.floor(levelTotal);
        levelFloat = levelTotal - levelInt;
//        postInvalidate();
    }
}
