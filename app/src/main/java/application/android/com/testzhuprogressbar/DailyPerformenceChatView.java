package application.android.com.testzhuprogressbar;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import java.util.Arrays;

/**
 * Created by jinliang on 17-7-7.
 *  firstBeat 运动状态自定义控件
 */

public class DailyPerformenceChatView extends View {
    private static final String TAG = DailyPerformenceChatView.class.getSimpleName();
    private Paint mLineColorDeepPaint ;
    private Paint mLineColorLowPaint ;
    private Paint dataLinePaint ;
    private Paint mSelectStatusPaint;
    private Paint mFontPaint ;
    private int mCircleLineStrokeWidth = 1 ;

    private  float canvanWidth = 0f ;
    private float canvanHeigth = 0f ;

    private float marginLeft = 50 ;
    private float marginRight = 70 ;
    private float marginTop = 40 ;
    private float marginBottom = 50 ;

    public static int invalidPointValue = -100 ;
    public static int invalideLessPaintValue = -20 ;
    public static int invalieBiggerPaintValue = 20 ;

    /**
     * font margin
     */

    private float fontMargin = 5;

    /**
     * data
     */
    private int[] sportStatusData ;

    /**
     * 宽度 高度 梯度设置
     */
    private float mWidthStep = 0f ;
    private float mHeigthStep = 0f ;
    private int mSegemntWidthNum = 12 ;
    private int mSegemntHeigthNum = 5;

    private float circleRadiusSize = 3 ;

    private int[] dataStageNum = new int[mSegemntHeigthNum];

    /**
     * constart 约束条件
     */
    private float[] LowDividingLine = {-25,-15,-5,5,15} ;
    private float[] LargerDividingLine = {-15,-5,5,15,25} ;

    /**
     *  left string
     */

    private String[] leftTextList = {"-20","-10","-5","+5","+10","+20"} ;
    private String[] rigthTextList ;

    private String mTitle ="" ;
    private String mUnit ="" ;

    /**
     *
     * @param context
     */
    private float normalFontSize = 10f ;
    private float titleFontSize = 25f ;
    private float unitFontSize =15f ;
    private int mVerticalMaxY = 25 ;

    private int SEGMENT_FIVE = 5 ;

    private int SEGMENT_TEN = 10 ;


    private String hasNoDataResult = "未检测到数据" ;
    public DailyPerformenceChatView(Context context) {
        this(context,null);
    }

    public DailyPerformenceChatView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public DailyPerformenceChatView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initPaint();
    }

    private void initPaint(){

        mUnit ="danwei";
        mTitle = getResources().getString(R.string.first_beat_sport_status);
        rigthTextList =getResources().getStringArray(R.array.dally_performence_level_status);

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
        dataLinePaint.setStrokeWidth(2f);
        dataLinePaint.setStyle(Paint.Style.STROKE);
        dataLinePaint.setStrokeCap(Paint.Cap.ROUND);//
        dataLinePaint.setColor(lineColor);

        mSelectStatusPaint= new Paint();
        mSelectStatusPaint.setAntiAlias(true);
        mSelectStatusPaint.setStrokeWidth(2f);
        mSelectStatusPaint.setStyle(Paint.Style.FILL);
        mSelectStatusPaint.setStrokeCap(Paint.Cap.ROUND);//
        mSelectStatusPaint.setColor(Color.GREEN);
        mSelectStatusPaint.setAlpha(100);

        mFontPaint= new Paint();
        mFontPaint.setAntiAlias(true);
        mFontPaint.setStrokeWidth(2f);
        mFontPaint.setStyle(Paint.Style.FILL);
        mFontPaint.setStrokeCap(Paint.Cap.ROUND);//
        mFontPaint.setColor(Color.WHITE);
        mFontPaint.setTextSize(normalFontSize);

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
        getTestData();
        canvanWidth = getWidth()-marginRight-marginLeft ;
        canvanHeigth = getHeight()-marginBottom-marginTop;
        mWidthStep = canvanWidth/mSegemntWidthNum ;
        mHeigthStep = canvanHeigth/mSegemntHeigthNum;

        // rect
        canvas.drawRect(marginLeft,marginTop,getWidth()-marginRight,getHeight()-marginBottom,mLineColorDeepPaint);

        drawChartViewTitle(canvas);
        drawChartViewUnit(canvas);

        mFontPaint.setTextSize(normalFontSize);
        drawHorizontalLine(canvas);
        drawVerticalLine(canvas);
        //zero
        drawHorizonZeroLine(canvas);

        //drawData
        drawDataLine(canvas);

        // draw select color
//        drawStageColor(canvas);

        // draw right text
        mFontPaint.setTextSize(normalFontSize);
        drawRightText(canvas);

        drawBottomText(canvas);

        drawLeftText(canvas);

        drawStageBgColor(canvas);


    }


    /**
     *
     * @param canvas
     */
    private void drawStageBgColor(Canvas canvas){
        if(sportStatusData!=null && sportStatusData.length>0){
            printIntData(sportStatusData);
            int currnetStage =0,startStage=0,startIndex=0,endIndex=0;
            int currentValue = 0;
            for (int i = 0; i < sportStatusData.length; i++) {
                currentValue = sportStatusData[i];
                if(i==0){
                    startIndex = endIndex = 0;
                    currnetStage = startStage = getCurrentStageByPointY(currentValue);
                }else{
                    currnetStage = getCurrentStageByPointY(currentValue);
                    endIndex = i ;
                }
                if(currentValue!=startStage){
                    drawItemStageColor(canvas,startStage,startIndex,endIndex);
                    startIndex = i ;
                    startStage = currnetStage;
                }
            }
        }
    }

    private int getCurrentStageByPointY(int dataPointY){

        if(dataPointY<invalideLessPaintValue ){ // <-20
            return invalidPointValue ;
        }else if (dataPointY < -10){
            return 0;
        }else if (dataPointY < -5){
            return 1 ;
        }else if(dataPointY <5 ){
            return 2 ;
        }else if (dataPointY <10){
            return 3 ;
        }else if (dataPointY <=20){
            return 4 ;
        }else {
            return invalidPointValue ;
        }

    }
    /**
     * draw title
     * @param canvas
     */
    private void drawChartViewTitle(Canvas canvas) {
        mFontPaint.setTextSize(titleFontSize);
        mFontPaint.setTextAlign(Paint.Align.LEFT);
        float x  = 0f ,y = 0f ;
        x = marginLeft +10 ;
        y = marginTop - 10 ;
        canvas.drawText(mTitle,x,y,mFontPaint);

    }


    /**
     * draw unit
     * @param canvas
     */
    private void drawChartViewUnit(Canvas canvas){
        mFontPaint.setTextSize(unitFontSize);
        mFontPaint.setTextAlign(Paint.Align.RIGHT);
        float x  = 0f ,y = 0f ;
        x = marginLeft + canvanWidth ;
        y = marginTop +canvanHeigth +30 ;
        canvas.drawText(mUnit,x,y,mFontPaint);

    }
    private void drawLeftText(Canvas canvas){
        mFontPaint.setTextAlign(Paint.Align.RIGHT);
        canvas.drawText(String.valueOf(0),marginLeft-fontMargin,marginTop+canvanHeigth/2,mFontPaint);
        Rect rect = new Rect();
        float x  = 0f ,y = 0f ;
        int fontHeight=0,fontWidth = 0 ;
        for (int i = 0; i < leftTextList.length; i++) {
            x = marginLeft-fontMargin;
            mFontPaint.getTextBounds(leftTextList[i], 0, leftTextList[i].length(), rect);
            fontHeight = rect.height();
            y = marginTop + canvanHeigth  - mHeigthStep * i + fontHeight/2;
            canvas.drawText(leftTextList[i],x,y,mFontPaint);
        }

    }

    private void drawBottomText(Canvas canvas ){
        float startX =0f ,  startY=0f, stopX=0f, stopY=0f;
        Rect rect = new Rect();
        int levelNumeWdith= 0;
        for (int i = 1; i <= mSegemntWidthNum+1 ; i++) {
            startX = marginLeft + mWidthStep * (i-1) ;
            startY  =marginTop + canvanHeigth ;
            stopX  =startX ;
            stopY = startY + 6 ;
            canvas.drawLine(startX,startY,stopX,stopY,mLineColorDeepPaint);
            mFontPaint.getTextBounds(String.valueOf(i), 0, String.valueOf(i).length(), rect);
            levelNumeWdith = rect.width()/2;
            canvas.drawText(String.valueOf(i),startX-levelNumeWdith,stopY+10, mFontPaint);
        }



    }
    private void drawRightText(Canvas canvas){

        float x =0f,y= 0f ;
        mFontPaint.setTextAlign(Paint.Align.LEFT);
        Rect rect = new Rect();
        int fontHeight=0,fontWidth = 0 ;
        for (int i = 0; i <rigthTextList.length ; i++) {
            x  = marginLeft+ canvanWidth + 10 ;
            mFontPaint.getTextBounds(rigthTextList[i], 0, rigthTextList[i].length(), rect);
            fontHeight = rect.height();
            y = marginTop+ canvanHeigth - mHeigthStep *(i)- fontHeight/2;
            canvas.drawText(rigthTextList[i], x,y,mFontPaint );
        }



    }
    private void drawStageColor(Canvas canvas){
        if(sportStatusData!=null && sportStatusData.length>0){
            dataStageNum = new int[mSegemntHeigthNum];
            printIntData(sportStatusData);
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
//            drawItemStageColor(canvas,drawIndex);

//            if(drawIndex>2){
//                int otherStage = Math.max(dataStageNum[0],dataStageNum[1]);
//                if(otherStage>=3){ // >=3 draw
//                    drawItemStageColor(canvas, Arrays.binarySearch(dataStageNum, otherStage));
//                }
//            }else if(drawIndex<=1 ){
//                int otherStage = Math.max(dataStageNum[3],dataStageNum[4]);
//                if(otherStage>=3){ // >=3 draw
//                    drawItemStageColor(canvas,Arrays.binarySearch(dataStageNum, otherStage));
//                }
//            }
        }


    }

    /**
     * chonggou
     * @param canvas
     * @param
     */
    private void drawItemStageColor(Canvas canvas,int stageIndex, int startIndex,int endIndex){
        if(stageIndex<0){
            return ;
        }
        float left = 0f, top=0f, right=0f, bottom=0f;
        left = marginLeft + mWidthStep * (startIndex) ;
        top = marginTop + canvanHeigth - mHeigthStep* (stageIndex+1);
        bottom = marginTop+canvanHeigth -mHeigthStep*stageIndex;
        right = marginLeft+mWidthStep * (endIndex);
        canvas.drawRect(left,top,right,bottom,mSelectStatusPaint);

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
            float circleTestY = 0f ;
            mLineColorDeepPaint.setStyle(Paint.Style.FILL);
            float lastPaintX  =0f ,lastPaintY = 0f ;
            int currnetPaintY = invalidPointValue ;
            for (int i = 0; i < sportStatusData.length; i++) {
                circleX = marginLeft+ mWidthStep *(i);
                currnetPaintY = sportStatusData[i];
                circleTestY = marginTop+ canvanHeigth/2 - (float) currnetPaintY/mVerticalMaxY * canvanHeigth/2 ;
                circleY = getDataPaintHeigth(currnetPaintY);
                Log.i(TAG,"drawDataLine circleY :"+ circleY + ",circleTestY:"+ circleTestY);
                if((currnetPaintY>=invalideLessPaintValue)&& (currnetPaintY<=invalieBiggerPaintValue)){
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
            }
            mLineColorDeepPaint.setStyle(Paint.Style.STROKE);
        }else{
            // when has no data , draw text ;
            mFontPaint.setTextSize(titleFontSize);
            float hasNoDataX = marginLeft +canvanWidth/2 ;
            float hasNoDataY = marginTop +canvanHeigth/2-10;
            mFontPaint.setTextAlign(Paint.Align.CENTER);
            canvas.drawText(hasNoDataResult,hasNoDataX,hasNoDataY,mFontPaint);
        }
    }

    private float getDataPaintHeigth(int dataPointY){

        float y = marginTop + canvanHeigth ;
        if(dataPointY<invalideLessPaintValue ){ // <-20
            return invalidPointValue ;
        }else if (dataPointY < -10){
            y =  y- (dataPointY-invalideLessPaintValue) * mHeigthStep/SEGMENT_TEN ;
            return y;
        }else if (dataPointY < -5){
            y =  y- mHeigthStep - (dataPointY+10)* mHeigthStep/SEGMENT_FIVE ;
            return y ;
        }else if(dataPointY <5 ){
            y = y- mHeigthStep *2 - (dataPointY+5) * mHeigthStep/SEGMENT_TEN;
            return y ;
        }else if (dataPointY <10){
            y  = y- mHeigthStep*3  - (dataPointY-5)*mHeigthStep/SEGMENT_FIVE;
            return y ;
        }else if (dataPointY <=20){
            y = y-mHeigthStep *4  - (dataPointY-10)* mHeigthStep/SEGMENT_TEN;
            return y ;
        }else {
            return invalidPointValue ;
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

        int dataSize  =10;
        sportStatusData = new int[dataSize];
        for (int i = 0; i < dataSize; i++) {
            sportStatusData[i] = (int)(Math.random()*15) ;
        }
//        sportStatusData[7] = -30;
//        sportStatusData[4] = 30 ;
        mSegemntWidthNum = dataSize;
        Log.i(TAG,sportStatusData.toString());
    }

    public void setSportStatusData(int[] data){
        if(data==null || data.length==0){
            return ;
        }
        mSegemntWidthNum = data.length;
        sportStatusData = data ;
        postInvalidate(); //update data ;

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
