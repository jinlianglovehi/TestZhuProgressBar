package application.android.com.testzhuprogressbar;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by jinliang on 17-7-18.
 *  recovery time view
 */
public class RecoveryTimeView extends View {


    private Paint mPaint ;
    private Paint mTxtPaint;
    private int segments = 5;
    private float mMarginLeft = 10 ,mMarginTop = 10 ;
    private float mMarginRight = 20 ;
    private float mCanvasWidth = 0f ;
    private float mCanvasHeigth = 0f ;
    private float mLineWidth  = 5f ;
    private float mBlackWidth = 2f;
    private float mReactWidthStep = 0f;

    private int[] drawColorArray ;

    private float normalTxtFontSize = 25f ;
    private float biggerTxtFontSize = 50f ;

    private float mProgressLevelHeigth = 15;
    /**
     * level Progress bar
     * @param context
     */

    private int levelInt  =0 ;
    private float levelFloat =0f, currnetLevel;
    public RecoveryTimeView(Context context) {
        this(context,null);
    }

    public RecoveryTimeView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public RecoveryTimeView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initPaint();
    }

    private void initPaint(){
        drawColorArray  = new int[]{getResources().getColor(R.color.te_color_step_01),
                getResources().getColor(R.color.te_color_step_02),
                getResources().getColor(R.color.te_color_step_03),
                getResources().getColor(R.color.te_color_step_04),
                getResources().getColor(R.color.te_color_step_05)} ;
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setStrokeWidth(mLineWidth);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setStrokeCap(Paint.Cap.BUTT);//
        mPaint.setColor(Color.GRAY);

        mTxtPaint = new Paint();
        mTxtPaint.setAntiAlias(true);
        mTxtPaint.setColor(Color.WHITE);
        mTxtPaint.setStyle(Paint.Style.STROKE);
        mTxtPaint.setTextSize(biggerTxtFontSize);

    }

    private  float left=0f,top=0f, right=0f, bottom=0f;
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        getTestData();
        mCanvasWidth =getWidth()-mMarginLeft-mMarginRight;
        mReactWidthStep =(mCanvasWidth-(segments-1)*mBlackWidth)/segments;

        drawGrayInfo(canvas);
        drawColor(canvas);
        drawTextInfo(canvas);
    }

    private void drawTextInfo(Canvas mCanvas){
        Rect rect = new Rect();
        mTxtPaint.setTextSize(biggerTxtFontSize);
        mTxtPaint.setTextAlign(Paint.Align.LEFT);
        String levelStr = String.valueOf(currnetLevel);
        mTxtPaint.getTextBounds(levelStr, 0, levelStr.length(), rect);
        float fontX  =0f ,fontY = 0f ;
        fontX = mMarginLeft + mCanvasWidth/2 - rect.width()/2 ;
        fontY = mMarginTop+mProgressLevelHeigth +rect.height()+30 ;
        mCanvas.drawText(levelStr,fontX,fontY ,mTxtPaint);

        // /5
        mTxtPaint.setTextSize(normalTxtFontSize);
        String rightTxt = "/"+ segments;
        fontX = mMarginLeft + mCanvasWidth/2 + rect.width()/2;
        mTxtPaint.getTextBounds(rightTxt, 0, rightTxt.length(), rect);
        mCanvas.drawText(rightTxt,fontX,fontY ,mTxtPaint);


    }

    private void drawGrayInfo(Canvas mCanvas){
        mPaint.setColor(Color.GRAY);
        for (int i = 0; i < segments; i++) {
            left = mMarginLeft + (mReactWidthStep+mBlackWidth)*i;
            right = mMarginLeft + mReactWidthStep* (i+1) + mBlackWidth *(i);
            mCanvas.drawRect(left,mMarginTop,right,mMarginTop+mProgressLevelHeigth,mPaint);
        }

    }
    private void drawColor(Canvas mCanvas){

        if(levelInt==0 && levelFloat==0){
            return ;
        }
        for (int i = 0; i <levelInt ; i++) {
            left = mMarginLeft + (mReactWidthStep+mBlackWidth)*i;
            right = mMarginLeft + mReactWidthStep* (i+1) + mBlackWidth *(i);
            mPaint.setColor(drawColorArray[i]);
            mCanvas.drawRect(left,mMarginTop,right,mMarginTop+mProgressLevelHeigth,mPaint);
        }

        int next = levelInt + 1 ;
        left = mMarginLeft + (mReactWidthStep+mBlackWidth)*levelInt;
        right = mMarginLeft + mReactWidthStep* (levelInt) + mBlackWidth *(levelInt) + mReactWidthStep * levelFloat;

        mPaint.setColor(drawColorArray[next]);
        mCanvas.drawRect(left,mMarginTop,right,mMarginTop+mProgressLevelHeigth,mPaint);
    }

    private void getTestData(){
        float level = 3.7f;
        currnetLevel = level;
        levelInt  =(int)Math.floor(level);
        levelFloat = level - levelInt;
    }

    public void setProgressLevel(float level){
        currnetLevel =level;
        levelInt  =(int)Math.floor(level);
        levelFloat = level - levelInt;
        postInvalidate();
    }
}
