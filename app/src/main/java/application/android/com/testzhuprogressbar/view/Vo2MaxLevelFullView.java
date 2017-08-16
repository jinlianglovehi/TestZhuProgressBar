package application.android.com.testzhuprogressbar.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import application.android.com.testzhuprogressbar.R;

/**
 * Created by jinliang on 17-8-14.
 */

public class Vo2MaxLevelFullView  extends View{

    private static final String TAG = Vo2MaxLevelFullView.class.getSimpleName();

    private float mMarginLeft =20;
    private float mMarginRigth =20;
    private float mMarginTop=20;
    private float mMarginBottom =20 ;

    private float mCanvasWidth =0f ;
    private float mCanvasHeight =0f ;

    private float mScreenCenterX=0f ;
    private float mScreenCenterY =0f;


    private float mSectorViewWidth = 35;
    private Paint mPaint , mSectorViewPaint;

    /**
     * rata angle
     */
    private float leftRateAngle = 0f;
    private float rightRateAngle = 0f ;

    private Bitmap triangleBitmap;

     private float leftStartAngle = 148;
    private float rightStartAngle =23 ;


    private boolean leftHasDataStaus,rigthHasDataStatus ;

    private int alphaInt = 50 ;
    private int[]  colorArr;
    public Vo2MaxLevelFullView(Context context) {
        this(context, null);
    }

    public Vo2MaxLevelFullView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public Vo2MaxLevelFullView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initPatin();
    }

    private void initPatin(){
        mPaint = new Paint();
        mPaint.setColor(Color.RED);
        mPaint.setAntiAlias(true);
        mPaint.setTextAlign(Paint.Align.CENTER);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeCap(Paint.Cap.BUTT);//

        mSectorViewPaint = new Paint();
        mSectorViewPaint.setAntiAlias(true);
        mSectorViewPaint.setStrokeWidth(mSectorViewWidth);
        mSectorViewPaint.setTextAlign(Paint.Align.RIGHT);
        mSectorViewPaint.setStyle(Paint.Style.STROKE);
        mSectorViewPaint.setStrokeCap(Paint.Cap.BUTT);//
        mSectorViewPaint.setColor(Color.RED);
        mSectorViewPaint.setAlpha(150);

        triangleBitmap  = BitmapFactory.decodeResource(getResources(), R.drawable.rade_triangle);

        colorArr = new int[7];

        colorArr[0] = getResources().getColor(R.color.max_vo2_max_level_1);
        colorArr[1] = getResources().getColor(R.color.max_vo2_max_level_2);
        colorArr[2] = getResources().getColor(R.color.max_vo2_max_level_3);
        colorArr[3] = getResources().getColor(R.color.max_vo2_max_level_4);
        colorArr[4] = getResources().getColor(R.color.max_vo2_max_level_5);
        colorArr[5] = getResources().getColor(R.color.max_vo2_max_level_6);
        colorArr[6] = getResources().getColor(R.color.max_vo2_max_level_7);
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        initMeasurceData();

        // out circle
//        mPaint.setColor(Color.RED);
        mPaint.setStyle(Paint.Style.STROKE);
//        canvas.drawCircle(mScreenCenterX,mScreenCenterY+12,mCanvasWidth/2,mPaint);
        //
        drawSectorView(canvas);

        // inner circle
        mPaint.setColor(Color.BLACK);
        mPaint.setStyle(Paint.Style.FILL);
        canvas.drawCircle(mScreenCenterX,mScreenCenterY+24,mCanvasWidth*13/30,mPaint);

        // sanjiao
        drawRigthVo2MaxTriangleLevel(canvas);

        drawVLeftvo2maxTriangleLevel(canvas);
    }


    private void drawSectorView(Canvas canvas) {

        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeCap(Paint.Cap.BUTT);//

        float RCircle = mCanvasWidth/2 - mSectorViewWidth/2;
        float left = mScreenCenterX-RCircle;
        float top = mScreenCenterY+12-RCircle ;
        float right = mScreenCenterX+RCircle;
        float bottom = mScreenCenterY+12+RCircle;

        mSectorViewPaint.setColor(Color.GREEN);
        RectF rect = new RectF(left, top,right,bottom);
//        canvas.drawRect(rect,mPaint);
        for (int i = 0; i < 7 ; i++) {
            mSectorViewPaint.setColor(colorArr[i]);
            if(leftHasDataStaus){
                mSectorViewPaint.setAlpha(255);
                canvas.drawArc(rect, (float)(leftStartAngle+10*i), (float) 9, false, mSectorViewPaint);
            }else{

                mSectorViewPaint.setAlpha(255*alphaInt/100);
                canvas.drawArc(rect, (float)(leftStartAngle+10*i), (float) 9, false, mSectorViewPaint);
            }

            if(rigthHasDataStatus){
                mSectorViewPaint.setAlpha(255);
                canvas.drawArc(rect, (float)(rightStartAngle-10*i), (float)9, false, mSectorViewPaint);
            }else{
                mSectorViewPaint.setAlpha(255*alphaInt/100);
                canvas.drawArc(rect, (float)(rightStartAngle-10*i), (float)9, false, mSectorViewPaint);
            }


        }
    }

    /**
     *  绘制左侧
     * @param canvas
     */
//
    private float triangR = 0f;  ;

    private void drawVLeftvo2maxTriangleLevel(Canvas canvas){
        triangR =  mCanvasWidth/2+ triangleBitmap.getWidth()/4;
        Matrix matrix = new Matrix() ;
        matrix.postRotate(90 + leftStartAngle + leftRateAngle);
        float x = mScreenCenterX- (float)(triangR*Math.cos(Math.toRadians(((leftStartAngle-9+leftRateAngle-180)))));
        float y  =mScreenCenterY- (float)(triangR*Math.sin(Math.toRadians((leftStartAngle-9+leftRateAngle-180))));
        matrix.postTranslate(x,y);
        canvas.drawBitmap(triangleBitmap,matrix,mSectorViewPaint);

    }

    /**
     * 绘制右侧
     * @param canvas
     */
    private void drawRigthVo2MaxTriangleLevel(Canvas canvas){
        triangR =  mCanvasWidth/2+ triangleBitmap.getWidth()/4;
        Matrix matrix = new Matrix() ;
        matrix.postRotate(90 + rightStartAngle- rightRateAngle);
        float x = mScreenCenterX+ (float)(triangR*Math.cos(Math.toRadians(rightStartAngle+9-rightRateAngle)));
        float y  =mScreenCenterY+ (float)(triangR*Math.sin(Math.toRadians(rightStartAngle+9-rightRateAngle)));
        matrix.postTranslate(x,y);
        canvas.drawBitmap(triangleBitmap,matrix,mSectorViewPaint);
    }

    private void initMeasurceData(){

        mCanvasWidth  =getWidth()-mMarginLeft-mMarginRigth;
        mCanvasHeight = getHeight()-mMarginTop-mMarginBottom;
        mScreenCenterX = mMarginLeft + mCanvasWidth/2;
        mScreenCenterY = mMarginTop + mCanvasHeight/2;

    }

    public void setRightLevel(int rightPercent ,boolean isRightHasData, int leftPercent , boolean isleftHasData){

        rightRateAngle = 69 * rightPercent/100 ;
        leftRateAngle = 69 *leftPercent /100 ;
        leftHasDataStaus = isleftHasData;
        rigthHasDataStatus = isRightHasData;
        postInvalidate();

    }
}
