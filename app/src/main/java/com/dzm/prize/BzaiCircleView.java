package com.dzm.prize;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * Created by 83642 on 2017/10/24.
 */

public class BzaiCircleView extends View implements ViewPager.OnPageChangeListener{

    private static final double BZAI_CIRCLE = 0.552284749831;

    private XPoint mXpoint2, mXpoint4;
    private YPoint mYpoint1, mYpoint3;
    private Path mPath;
    private Paint paint;
    private Paint mPaintCircle;

    private float radius;
    //圆与圆之间的间距
    private int div;
    //圆贝塞尔曲线的 顶点位置
    private float mc;

    private int mCount;

    public BzaiCircleView(Context context) {
        super(context);
        init(context, null);
    }

    public BzaiCircleView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public BzaiCircleView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        radius = 100f;
        div = 50;
        mc = (float) (radius * BZAI_CIRCLE);
        mYpoint1 = new YPoint(0, radius, mc);
        mYpoint3 = new YPoint(0, -radius, mc);
        mXpoint2 = new XPoint(radius, 0, mc);
        mXpoint4 = new XPoint(-radius, 0, mc);
        mPath = new Path();
        paint = new Paint();
        paint.setColor(Color.parseColor("#FF5231"));

        mPaintCircle = new Paint();
        mPaintCircle.setColor(Color.parseColor("#FF5231"));
        mPaintCircle.setStyle(Paint.Style.STROKE);
        mPaintCircle.setStrokeWidth(3);
        mPaintCircle.setAntiAlias(true);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.save();
        canvas.translate(radius, radius);
        for (int i = 0; i < mCount; i++) {
            canvas.drawCircle(i * (div + 2 * radius), 0, radius, mPaintCircle);
        }

        mPath.reset();
        mPath.moveTo(mYpoint1.x, mYpoint1.y);
        mPath.cubicTo(mYpoint1.right.x, mYpoint1.right.y, mXpoint2.bottom.x, mXpoint2.bottom.y, mXpoint2.x, mXpoint2.y);
        mPath.cubicTo(mXpoint2.top.x, mXpoint2.top.y, mYpoint3.right.x, mYpoint3.right.y, mYpoint3.x, mYpoint3.y);
        mPath.cubicTo(mYpoint3.left.x, mYpoint3.left.y, mXpoint4.top.x, mXpoint4.top.y, mXpoint4.x, mXpoint4.y);
        mPath.cubicTo(mXpoint4.bottom.x, mXpoint4.bottom.y, mYpoint1.left.x, mYpoint1.left.y, mYpoint1.x, mYpoint1.y);
        canvas.drawPath(mPath, paint);
        canvas.restore();

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
//        super.onMeasure(widthMeasureSpec,  heightMeasureSpec);
        setMeasuredDimension((int) (mCount*(radius*2+div)-div), (int) radius*2);
    }

    public void setViewPager(ViewPager viewPager) {
        viewPager.addOnPageChangeListener(this);
        mCount = viewPager.getAdapter().getCount();
//        invalidate();
        requestLayout();
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        Log.d("BzaiCircleView", "positionOffset:" + positionOffset + "  position:" + position + "  positionOffsetPixels" + positionOffsetPixels);
        if (positionOffset >= 0 && positionOffset <= 0.2) {
            model1(positionOffset,position);
        } else if (positionOffset > 0.2 && positionOffset <= 0.4) {
            model2(positionOffset,position);
        } else if (positionOffset > 0.4 && positionOffset <= 0.8) {
            model3(positionOffset,position);
        } else if (positionOffset > 0.8) {
            model4(positionOffset,position);
        }
        invalidate();
    }

    private void model1(float positionOffset,int position) {//0~0.2
        mXpoint2.setX(radius + (div + 2 * radius) * (position + positionOffset));
        mXpoint4.setX(-radius + (2 * radius + div) * position);
        mYpoint1.setY(radius);
        mYpoint3.setY(-radius);
    }

    private void model2(float positionOffset,int position) {//0.2~0.5
        mXpoint2.setX(radius + (div + 2 * radius) * (position + positionOffset));
        mYpoint1.setY(radius * (1 - positionOffset + 0.2f));
        mYpoint3.setY(-radius * (1 - positionOffset + 0.2f));
        mYpoint1.setX((2 * radius + div) * (positionOffset - 0.2f + position));
        mYpoint3.setX((2 * radius + div) * (positionOffset - 0.2f + position));
        mXpoint4.setX(-radius + (2 * radius + div) * (positionOffset - 0.2f + position));
    }

    private void model3(float positionOffset,int position) {//0.5~0.8
        mXpoint2.setX(radius + (div + 2 * radius) * (position + positionOffset));
        mXpoint4.setX(-radius + (2 * radius + div) * (positionOffset - 0.2f + position));
        mYpoint1.setX((2 * radius + div) * (positionOffset - 0.2f + position));
        mYpoint3.setX((2 * radius + div) * (positionOffset - 0.2f + position));
    }

    private void model4(float positionOffset,int position) {//0.8-1.0
        mXpoint2.setX(radius + (div + 2 * radius) * (position + positionOffset));
        mXpoint4.setX(-radius + (2 * radius + div) * (positionOffset - 0.2f + position) + (2 * radius + div) * (positionOffset - 0.8f));
        mYpoint1.setX((2 * radius + div) * (positionOffset - 0.2f + position) + (2 * radius + div) * (positionOffset - 0.8f));
        mYpoint3.setX((2 * radius + div) * (positionOffset - 0.2f + position) + (2 * radius + div) * (positionOffset - 0.8f));

        mYpoint1.setY(radius * 0.8f + radius * (positionOffset - 0.8f));
        mYpoint3.setY(-radius * 0.8f - radius * (positionOffset - 0.8f));
    }


    @Override
    public void onPageSelected(int position) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }


    private class XPoint {
        public float x;
        public float y;
        public float mc;
        public PointF bottom;
        public PointF top;

        XPoint(float x, float y, float mc) {
            this.x = x;
            this.y = y;
            this.mc = mc;
            bottom = new PointF();
            top = new PointF();
            bottom.y = y + mc;
            bottom.x = x;
            top.y = y - mc;
            top.x = x;
        }

        void setX(float x) {
            this.x = x;
            bottom.x = x;
            top.x = x;
        }
    }

    private class YPoint {

        //上下部分的 中间坐标点x，及p1或p7的x
        public float x;
        //上下部分的 中间坐标点y，及p1或p7的y
        public float y;
        //radius*c
        public float mc;
        //上下部分的 左坐标点，及p0,p8
        public PointF left;
        //上下部分的 右坐标点，及p2,p6
        public PointF right;

        public YPoint(float x, float y, float mc) {
            this.x = x;
            this.y = y;
            this.mc = mc;
            left = new PointF();
            right = new PointF();
            left.x = x - mc;
            left.y = y;
            right.x = x + mc;
            right.y = y;
        }

        void setY(float y) {
            this.y = y;
            left.y = y;
            right.y = y;
        }

        void setX(float x) {
            this.x = x;
            left.x = x - mc;
            right.x = x + mc;
        }

    }
}
