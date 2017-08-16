package application.android.com.testzhuprogressbar.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import application.android.com.testzhuprogressbar.R;

/**
 * Created by jinliang on 17-7-19.
 */

public class Vo2MaxLevelCircleView extends View {
    private static final String TAG = Vo2MaxLevelCircleView.class.getSimpleName();
    private Paint mPaint ;
    private Bitmap mBitmap;

    private float mMarginTop=10f,mMarginLeft=10f,mMarginRight=10f,mMarginBottom=10f;


    private int vo2MaxLevel = 3 ;
    private int vo2MaxValue = 60 ;
    private float bigTextSize  =40f;
    private float smallTextSize  =20f;
    private String vo2maxUnit = "ml/kg/min" ;

    private float mCanvasHeigth =0f,mCanvasWidth = 0f ;
    private float mBitmapLeft=0f,mBitmapTop= 0f;
    public Vo2MaxLevelCircleView(Context context) {
        this(context,null);
    }

    public Vo2MaxLevelCircleView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public Vo2MaxLevelCircleView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initPaint();
    }

    private void initPaint(){
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setTextSize(40f);
        mPaint.setColor(Color.WHITE);
        mBitmap = ((BitmapDrawable) getResources().getDrawable(R.drawable.sport_result_vo2max_0))
                .getBitmap();

    }
    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        initMeasureData();
        canvas.drawBitmap(mBitmap,mBitmapLeft,mBitmapTop,mPaint);
        drawVo2MaxValue(canvas);
    }

    private void drawVo2MaxUnit(Canvas canvas, Rect rect) {
        String vo2MaxUnitStr = String.valueOf(vo2maxUnit);
        mPaint.setTextAlign(Paint.Align.LEFT);
        mPaint.setTextSize(smallTextSize);
        mPaint.getTextBounds(vo2MaxUnitStr, 0, vo2MaxUnitStr.length(), rect);
        float x=0f,y=0f;
        x =mMarginLeft + mCanvasWidth/2-rect.width()/2;
        y = mMarginTop + mCanvasHeigth/2 + 30;
        canvas.drawText(vo2MaxUnitStr,x,y,mPaint);
    }

    private void drawVo2MaxValue(Canvas canvas) {
        Rect rect = new Rect();
        String vo2MaxStr = String.valueOf(vo2MaxValue);
        mPaint.getTextBounds(vo2MaxStr, 0, vo2MaxStr.length(), rect);
        mPaint.setTextAlign(Paint.Align.LEFT);
        float x=0f,y=0f;
        x =mMarginLeft + mCanvasWidth/2- rect.width()/2;
        y = mMarginTop + mCanvasHeigth/2;
        canvas.drawText(vo2MaxStr,x,y,mPaint);

        drawVo2MaxUnit(canvas,rect);
    }

    private void initMeasureData() {
        mCanvasWidth = getWidth()- mMarginLeft - mMarginRight;
        mCanvasHeigth = getHeight()-mMarginTop- mMarginBottom;
        mBitmap = getBitmapByVo2maxLevel(vo2MaxLevel);
        mBitmapLeft = mMarginLeft + mCanvasWidth/2-mBitmap.getWidth()/2;
        mBitmapTop  =mMarginTop + mCanvasHeigth/2 -mBitmap.getHeight()/2;

    }

    private Bitmap getBitmapByVo2maxLevel(int level){

        Bitmap mBitmap =null;
        switch (level){

            case 0:
                mBitmap = ((BitmapDrawable) getResources().getDrawable(R.drawable.sport_result_vo2max_0))
                        .getBitmap();
                break;
            case 1:
                mBitmap = ((BitmapDrawable) getResources().getDrawable(R.drawable.sport_result_vo2max_1))
                        .getBitmap();
                break;
            case 2:
                mBitmap = ((BitmapDrawable) getResources().getDrawable(R.drawable.sport_result_vo2max_2))
                        .getBitmap();
                break;
            case 3:
                mBitmap = ((BitmapDrawable) getResources().getDrawable(R.drawable.sport_result_vo2max_3))
                        .getBitmap();
                break;
            case 4:
                mBitmap = ((BitmapDrawable) getResources().getDrawable(R.drawable.sport_result_vo2max_4))
                        .getBitmap();
                break;
            case 5:
                mBitmap = ((BitmapDrawable) getResources().getDrawable(R.drawable.sport_result_vo2max_5))
                        .getBitmap();
                break;
            case 6:
                mBitmap = ((BitmapDrawable) getResources().getDrawable(R.drawable.sport_result_vo2max_6))
                        .getBitmap();
                break;
            case 7:
                mBitmap = ((BitmapDrawable) getResources().getDrawable(R.drawable.sport_result_vo2max_7))
                        .getBitmap();
                break;
        }
        return mBitmap;

    }


}
