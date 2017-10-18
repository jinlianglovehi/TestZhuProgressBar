package application.android.com.testzhuprogressbar;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.NinePatch;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Random;

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

    private float canvanWidth = 0f ;
    private float canvanHeigth = 0f ;

    private float marginLeft = 50 ;
    private float marginRight = 70 ;
    private float marginTop = 10 ;
    private float marginBottom = 50 ;

    public static int invalidPointValue = 127 ;
    public static int invalideLessPaintValue = -20 ;
    public static int invalieBiggerPaintValue = 20 ;

    private  float dataLineFontSize = 45f;

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
    private float mSegemntWidthNum = 1f ;
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

    private int SEGMENT_BOTTOM = 6 ; // bottom segment ;
    private float totalDistance = 0f;

    private String hasNoDataResult = "" ;
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

        mUnit = getResources().getString(R.string.sport_history_item_distance_desc);
        mTitle = getResources().getString(R.string.first_beat_sport_status);
        rigthTextList =getResources().getStringArray(R.array.dally_performence_level_status);
        hasNoDataResult = getResources().getString(R.string.daily_propermance_has_no_data);

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
//        getTestData();
        canvanWidth = getWidth()-marginRight-marginLeft ;
        canvanHeigth = getHeight()-marginBottom-marginTop;
        if(mSegemntWidthNum==1){
            mWidthStep = 0;
        }else{
            mWidthStep = canvanWidth/(mSegemntWidthNum-1) ;
        }

        mHeigthStep = canvanHeigth/mSegemntHeigthNum;

        drawBackColor(canvas);
        // rect
        canvas.drawRect(marginLeft,marginTop,getWidth()-marginRight,getHeight()-marginBottom,mLineColorDeepPaint);

        drawChartViewTitle(canvas);

        drawChartViewUnit(canvas);

        mFontPaint.setTextSize(normalFontSize);
        drawHorizontalLine(canvas);
        drawVerticalLine(canvas);
        //zero
//        drawHorizonZeroLine(canvas);

        //drawData
        drawDataLine(canvas);

        // draw select color
//        drawStageColor(canvas);

        // draw right text
        mFontPaint.setTextSize(normalFontSize);
        drawSecondLeftText(canvas);

        drawBottomText(canvas,totalDistance);

        drawSecondRightText(canvas);

//        if(totalDistance>=1){
//            drawStageBgColor(canvas);
//        }
    }


    private void drawBackColor(Canvas canvas){
//        mSelectStatusPaint.setColor(Color.RED);
//        drawItemStageColor(canvas,0);
//        drawItemStageColor(canvas,1);
//        mSelectStatusPaint.setColor(Color.YELLOW);
//        drawItemStageColor(canvas,2);
//        mSelectStatusPaint.setColor(Color.GREEN);
//        drawItemStageColor(canvas,3);
//        drawItemStageColor(canvas,4);
        drawNinePathBitmap(canvas);

    }
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
        if(endIndex==sportStatusData.length-1){
            float verticalLineNum = mSegemntWidthNum-((int)Math.round(mSegemntWidthNum +0.5) - 1) ;
            right = marginLeft+mWidthStep * (endIndex-1 +verticalLineNum );
        }else{
            right = marginLeft+mWidthStep * (endIndex);
        }

        canvas.drawRect(left,top,right,bottom,mSelectStatusPaint);

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
//        canvas.drawText(mTitle,x,y,mFontPaint);

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
    private void drawSecondRightText(Canvas canvas){
        mFontPaint.setTextAlign(Paint.Align.LEFT);
        // dispatch
//        canvas.drawText(String.valueOf(0),marginLeft-fontMargin,marginTop+canvanHeigth/2,mFontPaint);
        Rect rect = new Rect();
        float x  = 0f ,y = 0f ;
        int fontHeight=0,fontWidth = 0 ;
        for (int i = 0; i < leftTextList.length; i++) {
            //x = marginLeft-fontMargin;
            x  = marginLeft+ canvanWidth +5 ;
            mFontPaint.getTextBounds(leftTextList[i], 0, leftTextList[i].length(), rect);
            fontHeight = rect.height();
            y = marginTop + canvanHeigth  - mHeigthStep * i + fontHeight/2;
            canvas.drawText(leftTextList[i],x,y,mFontPaint);
        }

    }

    private void drawBottomText(Canvas canvas ,float totalDistance){

        initSegmentBottom();
        float startX =0f ,  startY=0f, stopX=0f, stopY=0f;
        Rect rect = new Rect();
        int levelNumeWdith= 0;
        float bottomSegment= 0f ;
        float bottomWidthSegment = canvanWidth/SEGMENT_BOTTOM;
        String result  ="" ;
        if(totalDistance>=6){
            bottomSegment = Math.abs(totalDistance-1)/SEGMENT_BOTTOM;

            for (int i = 0; i <=SEGMENT_BOTTOM  ; i++) {
                startX = marginLeft + bottomWidthSegment *i ;
                startY  =marginTop + canvanHeigth ;
                stopX  =startX ;
                stopY = startY + 6 ;
                canvas.drawLine(startX,startY,stopX,stopY,mLineColorDeepPaint);
                if(totalDistance<1){
                    result = getDistanceFormat(bottomSegment*i);
                }else{
                    result = getDistanceFormat(1+bottomSegment*i);
                }

                Log.i(TAG," drawBottomText startX:"+ startX +",result:"+ result);
                mFontPaint.getTextBounds(result, 0, result.length(), rect);
                levelNumeWdith = rect.width()/2;
                canvas.drawText(result,startX-levelNumeWdith,stopY+10, mFontPaint);
            }
        }else if(totalDistance>=1 && totalDistance<6){
            mSegemntWidthNum  = (int)totalDistance ;
            for (int i = 1; i <= mSegemntWidthNum+1 ; i++) {
                if(i==(mSegemntWidthNum+1)){

                    startX = marginLeft + mWidthStep * (i-2)  +
                            mWidthStep*(totalDistance-mSegemntWidthNum);
                    result = getDistanceFormat(totalDistance);
                }else{
                    startX = marginLeft + mWidthStep * (i-1) ;
                    result = String.valueOf(i);
                }
                startY  =marginTop + canvanHeigth ;
                stopX  =startX ;
                stopY = startY + 6 ;
                canvas.drawLine(startX,startY,stopX,stopY,mLineColorDeepPaint);

                mFontPaint.getTextBounds(result, 0, result.length(), rect);
                levelNumeWdith = rect.width()/2;
                canvas.drawText(result,startX-levelNumeWdith,stopY+10, mFontPaint);
            }
        }

    }

    /**
     * init segment bottom
     */
    private void initSegmentBottom() {
        if(totalDistance>=6){
            SEGMENT_BOTTOM = 6 ;
        }else if(totalDistance<=1){
            SEGMENT_BOTTOM =1 ;
        }
    }

    private  String getDistanceFormat(float number){
        BigDecimal bd = new BigDecimal(number);
        bd = bd.setScale(2,BigDecimal.ROUND_HALF_UP);
        return bd.toString();
    }
    private void drawSecondLeftText(Canvas canvas){

        float x =0f,y= 0f ;
        mFontPaint.setTextAlign(Paint.Align.RIGHT);
        Rect rect = new Rect();
        int fontHeight=0,fontWidth = 0 ;
//        for (int i = 0; i <rigthTextList.length ; i++) {
//            x  = marginLeft+ canvanWidth + 10 ;
//            mFontPaint.getTextBounds(rigthTextList[i], 0, rigthTextList[i].length(), rect);
//            fontHeight = rect.height();
//            y = marginTop+ canvanHeigth - mHeigthStep *(i+1)- fontHeight/2;
//            canvas.drawText(rigthTextList[i], x,y,mFontPaint );
//        }


        // line01
        // x  = marginLeft+ canvanWidth + 10 ;
//        x = marginLeft-fontMargin;

        mFontPaint.getTextBounds(rigthTextList[0], 0, rigthTextList[0].length(), rect);
        fontHeight = rect.height();
        x = marginLeft-5;
        y = marginTop+ canvanHeigth - mHeigthStep *(1)- fontHeight/2;
        canvas.drawText(rigthTextList[0], x,y,mFontPaint );

        // line2
        mFontPaint.getTextBounds(rigthTextList[1], 0, rigthTextList[1].length(), rect);
        fontHeight = rect.height();
        y = marginTop+ canvanHeigth - (float) (2.5)*mHeigthStep - fontHeight/2;
        canvas.drawText(rigthTextList[1], x,y,mFontPaint );

        // line3
        mFontPaint.getTextBounds(rigthTextList[2], 0, rigthTextList[2].length(), rect);
        fontHeight = rect.height();
        y = marginTop+ canvanHeigth - (float) (4)*mHeigthStep - fontHeight/2;
        canvas.drawText(rigthTextList[2], x,y,mFontPaint );

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
            drawItemStageColor(canvas,drawIndex);

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

    private void drawItemStageColor(Canvas canvas,int drawIndex){
        if(drawIndex<0){
            return ;
        }
        float left = 0f, top=0f, right=0f, bottom=0f;
        left = marginLeft ;
        top = marginTop + canvanHeigth - mHeigthStep* (drawIndex+1);
        bottom = marginTop+canvanHeigth -mHeigthStep*drawIndex;
        right = marginLeft+canvanWidth;
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
        if(sportStatusData!=null && sportStatusData.length>0 && totalDistance>=1){
            dataLinePaint.setColor(Color.WHITE);
            dataLinePaint.setTextSize(dataLineFontSize);
            float circleX = 0f ,circleY = 0f;
            float circleTestY = 0f ;
            mLineColorDeepPaint.setStyle(Paint.Style.FILL);
            float lastPaintX  =0f ,lastPaintY = 0f ;
            int currnetPaintY = invalidPointValue ;
            int totalDistanceInt = (int)(totalDistance-0.5);
            for (int i = 0; i < sportStatusData.length; i++) {
                if(i==sportStatusData.length-1){
                    circleX = marginLeft+ mWidthStep *(totalDistanceInt-1)+ mWidthStep * (totalDistance-totalDistanceInt);
                }else{
                    circleX = marginLeft+ mWidthStep *(i);
                }

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
        mLineColorLowPaint.setColor(Color.BLACK);
        for (int i = 0; i < horizontalLineNums; i++) {
            startY = stopY =  marginTop + mHeigthStep*(i+1);
            canvas.drawLine(marginLeft,startY,getWidth()-marginRight,stopY,mLineColorLowPaint);
        }

    }

    private void drawNinePathBitmap(Canvas canvas){
        drawGreenNinePath(canvas);
        drawRedNinePath(canvas);
        drawYellowNinePath(canvas);
    }

    private void drawGreenNinePath(Canvas canvas){
        float left, top ,right ,bottom ;
        left = marginLeft ;
        top = marginTop;
        right = marginLeft+canvanWidth ;
        bottom = marginTop + 2* mHeigthStep;
        Bitmap bmp_9 = BitmapFactory.decodeResource(getResources(),
                R.drawable.sport_result_state_bg_green);
        NinePatch np = new NinePatch(bmp_9, bmp_9.getNinePatchChunk(), null);
        Rect rect = new Rect((int) left, (int) top, (int) right,(int) bottom);
        np.draw(canvas, rect);
    }

    private void drawRedNinePath(Canvas canvas){
        float left, top ,right ,bottom ;
        left = marginLeft;
        top = marginTop + 3* mHeigthStep;
        right = marginLeft + canvanWidth;
        bottom = marginTop + 5* mHeigthStep;
        Bitmap bmp_9 = BitmapFactory.decodeResource(getResources(),
                R.drawable.sport_result_state_bg_red);
        NinePatch np = new NinePatch(bmp_9, bmp_9.getNinePatchChunk(), null);
        Rect rect = new Rect((int) left, (int) top, (int) right,(int) bottom);
        np.draw(canvas, rect);
    }

    private void drawYellowNinePath(Canvas canvas){
        float left, top ,right ,bottom ;
        left = marginLeft;
        top = marginTop + 2* mHeigthStep;
        right = marginLeft + canvanWidth;
        bottom = marginTop + 3* mHeigthStep;
        Bitmap bmp_9 = BitmapFactory.decodeResource(getResources(),
                R.drawable.sport_result_state_bg_yellow);
        NinePatch np = new NinePatch(bmp_9, bmp_9.getNinePatchChunk(), null);
        Rect rect = new Rect((int) left, (int) top, (int) right,(int) bottom);
        np.draw(canvas, rect);
    }
    private void drawVerticalLine(Canvas canvas){
        mLineColorLowPaint.setColor(Color.WHITE);
        mLineColorLowPaint.setAlpha(150);
        float startX =0f, startY= 0f, stopX =0f,  stopY= 0f ;
        int verticalLineNum = (int)Math.round(mSegemntWidthNum +0.5) - 1 ;
        Log.i(TAG,"verticalLineNum"+ verticalLineNum + " , number:"+ (Math.round(mSegemntWidthNum +0.5)));
        for (int i = 0; i < verticalLineNum; i++) {
            if(i==verticalLineNum-1){
                startX = stopX =  marginLeft + mWidthStep * (mSegemntWidthNum+i-verticalLineNum);
                Log.i(TAG,"verticalLineNum: index:" + i+ " ,"+ ( mSegemntWidthNum-verticalLineNum +i)
                        +",mSegemntWidthNum:"+ mSegemntWidthNum+",verticalLineNum:"+verticalLineNum

                );
            }else{
                startX = stopX =  marginLeft + mWidthStep * (i+1);
                Log.i(TAG,"verticalLineNum: index:" +i+" ,"+ (i+1));
            }

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
        mSegemntWidthNum = totalDistance =5.99f;
        Log.i(TAG," getTestData distance: "+ totalDistance);
        int dataSize = (int) Math.round(totalDistance+0.5);
        int[] sportStatusData = new int[dataSize];
        for (int i = 0; i < dataSize; i++) {
            sportStatusData[i] = (int)(Math.random()*15) ;
        }
        setSportStatusData(sportStatusData,totalDistance);
        Log.i(TAG,sportStatusData.toString());
    }



    public void setSportStatusData(int[] data,double distance){
        if(data==null || data.length==0){
            return ;
        }
        mSegemntWidthNum = (float) distance;
        totalDistance=(float) distance ;
        sportStatusData = data ;
        Log.i(TAG,"----dailyPormenceData---distance:"+ distance);
        printIntData(data);

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

    public void setUpdate(){
        getTestData();
//        postInvalidate();
    }
}
