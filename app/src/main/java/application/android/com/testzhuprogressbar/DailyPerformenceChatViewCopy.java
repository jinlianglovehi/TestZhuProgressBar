package application.android.com.testzhuprogressbar;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * Created by jinliang on 17-7-7.
 *  firstBeat 运动状态自定义控件
 */

public class DailyPerformenceChatViewCopy extends View {
    private static final String TAG = DailyPerformenceChatViewCopy.class.getSimpleName();
    private Paint mLineColorDeepPaint ;
    private Paint mLineColorLowPaint ;
    private Paint dataLinePaint ;
    private Paint mSelectStatusPaint;
    private Paint mFontPaint ;
    private int mCircleLineStrokeWidth = 5 ;

    private  float canvanWidth = 0f ;
    private float canvanHeigth = 0f ;

    private float marginLeft = 50 ;
    private float marginRight = 120 ;
    private float marginTop = 20 ;
    private float marginBottom = 50 ;

    /**
     * font margin
     */

    private float fontMargin = 20 ;

    /**
     * data
     */
    private float[] sportStatusData ;

    /**
     * 宽度 高度 梯度设置
     */
    private float mWidthStep = 0f ;
    private float mHeigthStep = 0f ;
    private int mSegemntWidthNum = 12 ;
    private int mSegemntHeigthNum = 5;

    private float circleRadiusSize = 10 ;

    private int[] dataStageNum = new int[mSegemntHeigthNum];

    /**
     * constart 约束条件
     */
    private float[] LowDividingLine = {-25,-15,-5,5,15} ;
    private float[] LargerDividingLine = {-15,-5,5,15,25} ;

    /**
     *  left string
     */

    private int[] leftTextList = {-20,-10,-5,5,10,20} ;
    private String[] rigthTextList = {"过度疲劳","疲劳","一般","良好","极佳"};

    /**
     *
     * @param context
     */
    private int mVerticalMaxY = 25 ;

    public DailyPerformenceChatViewCopy(Context context) {
        this(context,null);
    }

    public DailyPerformenceChatViewCopy(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public DailyPerformenceChatViewCopy(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initPaint();
    }

    private void initPaint(){

        int lineColor = Color.WHITE;
        mLineColorLowPaint = new Paint();
        mLineColorLowPaint.setAntiAlias(true);
        mLineColorLowPaint.setStrokeWidth(mCircleLineStrokeWidth);
        mLineColorLowPaint.setStyle(Paint.Style.STROKE);
        mLineColorLowPaint.setStrokeCap(Paint.Cap.ROUND);//
        mLineColorLowPaint.setColor(lineColor);
        mLineColorLowPaint.setAlpha(100);

        mLineColorDeepPaint = new Paint();
        mLineColorDeepPaint.setAntiAlias(true);
        mLineColorDeepPaint.setStrokeWidth(mCircleLineStrokeWidth);
        mLineColorDeepPaint.setStyle(Paint.Style.STROKE);
        mLineColorDeepPaint.setStrokeCap(Paint.Cap.ROUND);//
        mLineColorDeepPaint.setColor(lineColor);

        dataLinePaint = new Paint();
        dataLinePaint.setAntiAlias(true);
        dataLinePaint.setStrokeWidth(6f);
        dataLinePaint.setStyle(Paint.Style.STROKE);
        dataLinePaint.setStrokeCap(Paint.Cap.ROUND);//
        dataLinePaint.setColor(lineColor);

        mSelectStatusPaint= new Paint();
        mSelectStatusPaint.setAntiAlias(true);
        mSelectStatusPaint.setStrokeWidth(6f);
        mSelectStatusPaint.setStyle(Paint.Style.FILL);
        mSelectStatusPaint.setStrokeCap(Paint.Cap.ROUND);//
        mSelectStatusPaint.setColor(Color.GREEN);
        mSelectStatusPaint.setAlpha(100);

        mFontPaint= new Paint();
        mFontPaint.setAntiAlias(true);
        mFontPaint.setStrokeWidth(6f);
        mFontPaint.setStyle(Paint.Style.FILL);
        mFontPaint.setStrokeCap(Paint.Cap.ROUND);//
        mFontPaint.setColor(Color.WHITE);
        mFontPaint.setTextSize(35f);

    }

    /**
     *   step01 绘制周边的轨迹
         step02 刻度
         step03 数据绘制.
         step04 状态颜色标记
     */

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
//        getTestData();
        canvanWidth = getWidth()-marginRight-marginLeft ;
        canvanHeigth = getHeight()-marginBottom-marginTop;
        mWidthStep = canvanWidth/mSegemntWidthNum ;
        mHeigthStep = canvanHeigth/mSegemntHeigthNum;

        // rect
        canvas.drawRect(marginLeft,marginTop,getWidth()-marginRight,getHeight()-marginBottom,mLineColorDeepPaint);


        drawHorizontalLine(canvas);
        drawVerticalLine(canvas);
        //zero
        drawHorizonZeroLine(canvas);

        //drawData
        drawDataLine(canvas);

        // draw select color
        drawStageColor(canvas);

        // draw right text
        drawRightText(canvas);

        drawBottomText(canvas);

        drawLeftText(canvas);

    }

    private void drawLeftText(Canvas canvas){
        mFontPaint.setTextAlign(Paint.Align.RIGHT);
        canvas.drawText(String.valueOf(0),marginLeft-fontMargin,marginTop+canvanHeigth/2,mFontPaint);

        float x  = 0f ,y = 0f ;
        for (int i = 0; i < leftTextList.length; i++) {
            x = marginLeft-fontMargin;
            y = marginTop + canvanHeigth  - mHeigthStep * i ;
            canvas.drawText(String.valueOf(leftTextList[i]),x,y,mFontPaint);
        }

    }

    private void drawBottomText(Canvas canvas ){
        float startX =0f ,  startY=0f, stopX=0f, stopY=0f;
        for (int i = 0; i <= mSegemntWidthNum ; i++) {
            startX = marginLeft + mWidthStep * i ;
            startY  =marginTop + canvanHeigth ;
            stopX  =startX ;
            stopY = startY + 30 ;
            canvas.drawLine(startX,startY,stopX,stopY,mLineColorDeepPaint);
            canvas.drawText(String.valueOf(i),startX,stopY+30, mFontPaint);
        }



    }
    private void drawRightText(Canvas canvas){

        float x =0f,y= 0f ;
        for (int i = 0; i <rigthTextList.length ; i++) {
            x  = marginLeft+ canvanWidth + 10 ;
            y = marginTop+ canvanHeigth - mHeigthStep *(i +2/3);
            canvas.drawText(rigthTextList[i], x,y,mFontPaint );
        }



    }
    private void drawStageColor(Canvas canvas){
        if(sportStatusData!=null && sportStatusData.length>0){
            dataStageNum = new int[mSegemntHeigthNum];
            printFloatData(sportStatusData);
            for (int i = 0; i < sportStatusData.length; i++) {
                for (int j = 0; j < LowDividingLine.length; j++) {
                    if(sportStatusData[i]>=LowDividingLine[j] && sportStatusData[i]< LargerDividingLine[j]){
                        dataStageNum[j] = dataStageNum[j]+ 1 ;
                    }
                }
            }
            printIntData(dataStageNum);
            int drawIndex =getIndexOfMaxValue(dataStageNum);
            Log.i(TAG," drawIndex:"+ drawIndex);

            float left = 0f, top=0f, right=0f, bottom=0f;
            left = marginLeft ;
            top = marginTop + canvanHeigth - mHeigthStep* (drawIndex+1);
            bottom = marginTop+canvanHeigth -mHeigthStep*drawIndex;
            right = marginLeft+canvanWidth;
            canvas.drawRect(left,top,right,bottom,mSelectStatusPaint);
        }


    }

    public static int getIndexOfMaxValue(int[] arr){
         int max=arr[0];
         int index = 0 ;
         for(int i=1;i<arr.length;i++){
             if(arr[i]>max){
                 max=arr[i];
                 index = i  ;
             }
         }
         return index;
    }
    private void drawDataLine(Canvas canvas) {
        if(sportStatusData!=null && sportStatusData.length>0){
            float circleX = 0f ,circleY = 0f;
            mLineColorDeepPaint.setStyle(Paint.Style.FILL);
            float lastPaintX  =0f ,lastPaintY = 0f ;
            for (int i = 0; i < sportStatusData.length; i++) {
                circleX = marginLeft+ mWidthStep *(i+1);
                circleY = marginTop+ canvanHeigth/2 - (float) sportStatusData[i] * canvanHeigth/2/mVerticalMaxY  ;
                canvas.drawCircle(circleX,circleY,circleRadiusSize,mLineColorDeepPaint);
                if(i==0){
                    lastPaintX= circleX ;
                    lastPaintY= circleY;
                }else {
                    canvas.drawLine(lastPaintX,lastPaintY,circleX,circleY,dataLinePaint);
                    lastPaintX= circleX ;
                    lastPaintY= circleY;
                }
            }
            mLineColorDeepPaint.setStyle(Paint.Style.STROKE);
        }
    }

    private void drawHorizontalLine(Canvas canvas){

        float startX =0f, startY= 0f, stopX =0f,  stopY= 0f ;
        int  horizontalLineNums = mSegemntHeigthNum - 1 ;
        for (int i = 0; i < horizontalLineNums; i++) {
            startY = stopY =  marginTop + mHeigthStep*(i+1);
            canvas.drawLine(marginLeft,startY,getWidth()-marginRight,stopY,mLineColorLowPaint);
        }

    }

    private void drawVerticalLine(Canvas canvas){
        float startX =0f, startY= 0f, stopX =0f,  stopY= 0f ;
        int verticalLineNum = mSegemntWidthNum - 1 ;
        for (int i = 0; i < verticalLineNum; i++) {
            startX = stopX =  marginLeft + mWidthStep * (i+1);
            canvas.drawLine(startX,marginTop,stopX,getHeight()-marginBottom,mLineColorLowPaint);
        }
    }

    private void drawHorizonZeroLine(Canvas canvas ){
        float zeroY = marginTop + canvanHeigth /2 ;
        canvas.drawLine(marginLeft,zeroY,getWidth()-marginRight,zeroY,mLineColorDeepPaint);
    }


    /**
     * test
     */
    private void getTestData(){

        int  dataSize =(int) Math.random()*50+50 ;
        mSegemntWidthNum =dataSize;
        sportStatusData = new float[dataSize];
        for (int i = 0; i < dataSize; i++) {
            sportStatusData[i] = (float) Math.random()*10 ;
        }
        Log.i(TAG,sportStatusData.toString());
    }

    private void printFloatData(float[] data){
        StringBuffer sb = new StringBuffer() ;
        sb.append("[");
        for (int i = 0; i <data.length ; i++) {
         sb.append(data[i] +",");
        }
        sb.append("]");
        Log.i(TAG," printFloatData:"+ sb.toString());
    }

    private void printIntData(int[] data){
        StringBuffer sb = new StringBuffer() ;
        sb.append("[");
        for (int i = 0; i <data.length ; i++) {
            sb.append(data[i] +",");
        }
        sb.append("]");
        Log.i(TAG," printIntData:"+ sb.toString());
    }



}
