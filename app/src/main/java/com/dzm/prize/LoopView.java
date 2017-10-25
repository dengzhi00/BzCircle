package com.dzm.prize;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * Created by 83642 on 2017/10/20.
 */

public class LoopView extends View{

    private static final String TAG = LoopView.class.getName();

    //控件宽度
    private int measuredWidth;
    //控件高度
    private int measuredHeight;
    //圆弧半径
    private int radius;
    //可见圆弧---圆的一半及周长的一半
    private int halfCircumference;
    //可见文本最大高度
    private int maxTextHeight;
    //
    private float lineSpacingMultiplier;
    //可见数量
    private int itemsVisibleCount;

    public LoopView(Context context) {
        super(context);
        init(context);
    }

    public LoopView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public LoopView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context){
        lineSpacingMultiplier = 2f;
        itemsVisibleCount = 9;
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        remeasure();
    }

    private void remeasure() {

        measuredWidth = getMeasuredWidth();
        measuredHeight = getMeasuredHeight();

        if(measuredWidth == 0 || measuredHeight == 0){
            Log.d(TAG,"measuredWidth : "+measuredWidth+"  measuredHeight:"+measuredHeight);
            return;
        }

        radius = measuredHeight/2;
        halfCircumference = (int) (2*Math.PI*radius/2);

        maxTextHeight = (int) (halfCircumference/(lineSpacingMultiplier*(itemsVisibleCount -1)));



    }


}
