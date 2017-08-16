package application.android.com.testzhuprogressbar;

/**
 * Created by jinliang on 17-7-5.
 */

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.support.annotation.FloatRange;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * 运动负荷
 */
public class ExerciseLoadProgressView extends View{

    private static final String TAG = ExerciseLoadProgressView.class.getSimpleName();
    private Paint mLinePaint;

    private Paint mFontPaint;
    private Bitmap triangleBitmap;
    private int  normalLineWidth  = 10 ;
    private int  selectedLineWidth = 20 ;

    private float bitmapTriangleRadius = 200 ;
    private int currnetRateAngle = 0 ; // currnet rate angle

    private String neardays;
    private int weeklyTrainLoadSum;

    // this number is in trainLoad books
    private int minValue=150,maxValue=300,overReachValue=500;

    private String suggess;

    private int mNearDaysFontSize = 20 ;
    private int mTrainLoadNumFontSize = 50;
    private int mSuggessFontSize = 20;

    private int intervalAngleValue =2 ;
    private int[] rotationAngleArray =new int[3] ;

    float mMarginLeft = 10,mMarginRight=10,mMarginTop = 10,mMarginBottom =10 ;
    float x = 0,y =0;
    float circleX = 0f , circleY = 0f ;

    float mCanvasHeight =0f ,mCanvasWidth = 0f ;
    public ExerciseLoadProgressView(Context context) {
        this(context,null);
    }

    public ExerciseLoadProgressView(Context context, @Nullable AttributeSet attrs) {
        this(context,attrs,0);
    }

    public ExerciseLoadProgressView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initPaint();
    }

    private void initPaint(){
        int lineColor = Color.WHITE;

        mLinePaint = new Paint();
        mLinePaint.setAntiAlias(true);
        mLinePaint.setStrokeWidth(normalLineWidth);
        mLinePaint.setStyle(Paint.Style.STROKE);
        mLinePaint.setStrokeCap(Paint.Cap.BUTT);//
        mLinePaint.setColor(lineColor);


        mFontPaint = new Paint();
        mFontPaint.setAntiAlias(true);
        mFontPaint.setStyle(Paint.Style.STROKE);
        mFontPaint.setColor(Color.WHITE);
        mFontPaint.setTextSize(40);

        triangleBitmap  = BitmapFactory.decodeResource(getResources(),R.drawable.rade_triangle);
    }
    private float circleWidth = 0 ;
    private float circleHeigth = 0 ;
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        initMessageData(); // init measure data
        getComputerRateAngle(); // init angle info
        drawArcTreeColor(canvas);

        // 计算当前的旋转角度

        drawTriangleBitmap(canvas);

        mFontPaint.setTextAlign(Paint.Align.LEFT);
        drawNearDays(canvas,neardays);
        drawTrainLoadNum(canvas,String.valueOf(weeklyTrainLoadSum));
        drawSuggess(canvas,suggess);
    }

    private void initMessageData(){


        rotationAngleArray[0]  = 180/rotationAngleArray.length;

        rotationAngleArray[1] = 180/rotationAngleArray.length - 2*intervalAngleValue;

        rotationAngleArray[2]= 180/rotationAngleArray.length;
        mCanvasHeight = getHeight()*3/4 *2 ;
        mCanvasWidth = getWidth();

    }
    private void drawNearDays(Canvas canvas,String neardays){
        if(neardays==null || neardays.length()==0){
            return ;
        }
        Rect rect = new Rect();
        mFontPaint.setTextSize(mNearDaysFontSize);
        mFontPaint.getTextBounds(neardays, 0, neardays.length(), rect);
        int levelNumeWdith= rect.width();
        canvas.drawText(neardays,mCanvasWidth/2-levelNumeWdith/2, (float) (mCanvasHeight/2-circleHeigth*0.6),mFontPaint);// set number text

    }

    private void drawTrainLoadNum(Canvas canvas,String  trainLoadNum ){
        if(trainLoadNum==null || trainLoadNum.length()==0){
            return ;
        }
        Rect rect = new Rect();
        mFontPaint.setTextSize(mTrainLoadNumFontSize);
        mFontPaint.getTextBounds(trainLoadNum, 0, trainLoadNum.length(), rect);
        int levelNumeWdith= rect.width();
        canvas.drawText(trainLoadNum,mCanvasWidth/2-levelNumeWdith/2,(float) (mCanvasHeight/2+circleHeigth*0.05),mFontPaint);// set number text

    }

    private void drawSuggess(Canvas canvas, String suggess){

        if(suggess==null || suggess.length()==0){
            return ;
        }
        Rect rect = new Rect();
        mFontPaint.setTextSize(mSuggessFontSize);
        mFontPaint.getTextBounds(suggess, 0, suggess.length(), rect);
        int levelNumeWdith= rect.width();
        canvas.drawText(suggess,mCanvasWidth/2-levelNumeWdith/2,(float) (mCanvasHeight/2+circleHeigth*0.3),mFontPaint);// set number text

    }

    private void drawTriangleBitmap(Canvas canvas){
        mLinePaint.setTextAlign(Paint.Align.CENTER);
        Matrix matrix = new Matrix() ;
        matrix.postRotate(270+currnetRateAngle);
        circleHeigth = circleHeigth/2-triangleBitmap.getHeight()/2;
        float r = mCanvasHeight/2;
        currnetRateAngle = currnetRateAngle>=180?180:currnetRateAngle;
        Log.i(TAG," currnetRateAngle:"+currnetRateAngle);

        if(currnetRateAngle==0){
            y = mCanvasHeight/2+ triangleBitmap.getWidth()/2;
        }else if(currnetRateAngle>0 && currnetRateAngle< 180){
            y = mCanvasHeight/2 - (float)(r*Math.sin(Math.toRadians(currnetRateAngle)));
            if(y>=(mCanvasHeight/2-triangleBitmap.getWidth()/2)){
                y = mCanvasHeight/2-triangleBitmap.getWidth()/2;
            }
        }else if(currnetRateAngle>=180){
            y = mCanvasHeight/2-triangleBitmap.getWidth()/2;

        }
        x = mCanvasWidth/2 - (float)(r*Math.cos(Math.toRadians(currnetRateAngle)));
        Log.i(TAG," draw triangle bitmap x :"+ x +",Y:"+ y +",r:"+r+ ", currnetRateAngle:"+currnetRateAngle +",MathSin:"+Math.sin(Math.toRadians(currnetRateAngle)));
        matrix.postTranslate(x,y);
        canvas.drawBitmap(triangleBitmap,matrix,mLinePaint);

    }
    /**
     *
     * @return
     */
    private int getComputerRateAngle(){

        if(weeklyTrainLoadSum<=minValue){
            currnetRateAngle  = rotationAngleArray[0]*weeklyTrainLoadSum/minValue ;
        }else if (weeklyTrainLoadSum <=maxValue){
            currnetRateAngle  = rotationAngleArray[0] + intervalAngleValue+
                    rotationAngleArray[0]*(weeklyTrainLoadSum-minValue)/(maxValue-minValue);
        }else if(weeklyTrainLoadSum <=overReachValue){
            currnetRateAngle = rotationAngleArray[0] + intervalAngleValue+rotationAngleArray[1] + intervalAngleValue
                    + rotationAngleArray[2]*(weeklyTrainLoadSum-maxValue)/(overReachValue-maxValue);
        }else {
           currnetRateAngle = rotationAngleArray[0] +2*intervalAngleValue +  rotationAngleArray[1]
                   +  rotationAngleArray[2];
        }
        return currnetRateAngle;
    };

    private void drawArcTreeColor(Canvas canvas){
        RectF oval = new RectF(mMarginLeft, mMarginTop,
                mCanvasWidth-mMarginRight,mCanvasHeight-mMarginBottom);
        circleWidth = oval.width();
        circleHeigth = oval.height();
        Log.i(TAG,"circleWidth:"+circleWidth +",circleHeight:"+ circleHeigth);
        mLinePaint.setColor(Color.YELLOW);
        mLinePaint.setStrokeWidth(getLineWidthByAngle(currnetRateAngle,0,60));
        canvas.drawArc(oval,-180,rotationAngleArray[0],false,mLinePaint);//set line

        mLinePaint.setColor(Color.GREEN);
        mLinePaint.setStrokeWidth(getLineWidthByAngle(currnetRateAngle,60,120));
        canvas.drawArc(oval,  -180+ rotationAngleArray[0]+ intervalAngleValue,rotationAngleArray[1],false,mLinePaint);//set line

        mLinePaint.setColor(Color.RED);
        mLinePaint.setStrokeWidth(getLineWidthByAngle(currnetRateAngle,120,180));
        canvas.drawArc(oval,-rotationAngleArray[1],rotationAngleArray[1],false,mLinePaint);//set line
    }

    private float getLineWidthByAngle(int currnetRateAngle, int startAngle,int endAngle){

        if((currnetRateAngle<=endAngle && currnetRateAngle>=startAngle) || (currnetRateAngle>=180 && endAngle==180)){
            return selectedLineWidth;
        }else{
            return normalLineWidth;
        }
    };


    /**
     * @param weeklyTrainLoadSum 当前设置的数值
     * @param minValue  最小数值分割线
     * @param maxValue 最大数值分割线
     * @param overReachValue over 数值分割线
     */
    public void setTraingLoadProgress(int weeklyTrainLoadSum, int minValue,int maxValue,int overReachValue){
        neardays = "最近七天";
        suggess = "建议休息";
        this.weeklyTrainLoadSum = weeklyTrainLoadSum;
        this.minValue = minValue;
        this.maxValue = maxValue;
        this.overReachValue = overReachValue;
        postInvalidate();
    }


}
