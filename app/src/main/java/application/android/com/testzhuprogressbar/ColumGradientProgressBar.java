package application.android.com.testzhuprogressbar;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by jinliang on 17-7-3.
 *
 * step: yige progesstbar zhuxingtu
 *
 *
 *
 */

public class ColumGradientProgressBar extends View {

    /**
     * 空线条
     */
    private Paint mLinePaint ;
    private int mCircleLineStrokeWidth = 3;

    /**
     * 填充画笔
     */
    private Paint mFillPaint;

    private Paint mTextFontBig ;

    private Paint mTextFontSmall;

    /**
     *
     */

    private int progressLevelInt =0 ;
    private float progressLevelHalf = 0 ;

    /**
     * 画图起始点的偏移
     */

    private float mLeft = 10 ;
    private float mStepWidth =5;
    private float mStepTop = 10;
    private float mRight = 20 ;
    private float mTop = 40 ;
    private float mBottom = 60 ;


    private int columnSize = 5 ;
    public ColumGradientProgressBar(Context context) {
        this(context, null);
        initPaint(context);
    }

    public ColumGradientProgressBar(Context context, @Nullable AttributeSet attrs) {

        this(context, attrs, 0);
    }

    public ColumGradientProgressBar(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initPaint(context);
    }

    private void initPaint(Context mContext){

        int colorInt = mContext.getResources().getColor(R.color.colorPrimaryDark);
        // kong line
        mLinePaint = new Paint();
        mLinePaint.setAntiAlias(true);
        mLinePaint.setStrokeWidth(mCircleLineStrokeWidth);
        mLinePaint.setStyle(Paint.Style.STROKE);
        mLinePaint.setStrokeCap(Paint.Cap.BUTT);//
        mLinePaint.setColor(colorInt);

        // 填充画笔
        mFillPaint = new Paint();
        mFillPaint.setAntiAlias(true);
        mFillPaint.setStrokeWidth(mCircleLineStrokeWidth);
        mFillPaint.setStyle(Paint.Style.FILL);
        mFillPaint.setStrokeCap(Paint.Cap.BUTT);//
        mFillPaint.setColor(colorInt);


        mTextFontBig = new Paint();
        mTextFontBig.setAntiAlias(true);
        mTextFontBig.setTextSize(45f);
        mTextFontBig.setColor(colorInt);


        mTextFontSmall=new Paint();
        mTextFontSmall.setAntiAlias(true);
        mTextFontSmall.setTextSize(15f);
        mTextFontSmall.setColor(colorInt);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        getProgressLevel();

        if(progressLevelInt==0){
            drawRectProgress(canvas,mLeft,mTop,mRight,mBottom,mLinePaint,mFillPaint,0);
            drawRectProgress(canvas,mLeft+(mRight-mLeft+mStepWidth),mTop-mStepTop,mRight+(mRight-mLeft+mStepWidth),mBottom,mLinePaint,mFillPaint,0);
            drawRectProgress(canvas,mLeft+(mRight-mLeft+mStepWidth)*2,mTop-mStepTop*2,mRight+(mRight-mLeft+mStepWidth)*2,mBottom,mLinePaint,mFillPaint,0);
            drawRectProgress(canvas,mLeft+ (mRight-mLeft+mStepWidth)*3,mTop-mStepTop*3,mRight+(mRight-mLeft+mStepWidth)*3,mBottom,mLinePaint,mFillPaint,0);
            drawRectProgress(canvas,mLeft+(mRight-mLeft+mStepWidth)*4,mTop-mStepTop*4,mRight+(mRight-mLeft+mStepWidth)*4,mBottom,mLinePaint,mFillPaint,0);
        }else {
            for (int i = 0; i < columnSize ; i++) {
                if(i<progressLevelInt){
                    drawRectProgress(canvas,mLeft+(mRight-mLeft+mStepWidth)*i,mTop-mStepTop*i,mRight+(mRight-mLeft+mStepWidth)*i,mBottom,mLinePaint,mFillPaint,100);
                }else if(i==progressLevelInt){
                    drawRectProgress(canvas,mLeft+(mRight-mLeft+mStepWidth)*i,mTop-mStepTop*i,mRight+(mRight-mLeft+mStepWidth)*i,mBottom,mLinePaint,mFillPaint,progressLevelHalf);
                }else if(i> progressLevelInt){
                    drawRectProgress(canvas,mLeft+(mRight-mLeft+mStepWidth)*i,mTop-mStepTop*i,mRight+(mRight-mLeft+mStepWidth)*i,mBottom,mLinePaint,mFillPaint,0);
                }

            }

        }

        canvas.drawText("3.5",mLeft,mBottom+30,mTextFontBig);
        canvas.drawText("/5",mLeft+35,mBottom+30,mTextFontSmall);



    }

    private void drawRectProgress(Canvas canvas,float left, float top, float right, float bottom,  Paint mLinePaint,Paint mFillPaint,float percent){
        canvas.drawRect(left,top,right,bottom,mLinePaint);
        if(percent>0){ // > 0  不绘制
            canvas.drawRect(left,top,left +(right-left)*percent/100,bottom,mFillPaint);
        }
    }

    /**
     *
     * @param progressLevel
     */
    public void setProgressLevel(float progressLevel){
        progressLevelInt =(int) (progressLevel-0.5);
        progressLevelHalf =( progressLevel-progressLevelInt)*100;
    }

    public void getProgressLevel(){
        setProgressLevel(3.6f);
    }



}
