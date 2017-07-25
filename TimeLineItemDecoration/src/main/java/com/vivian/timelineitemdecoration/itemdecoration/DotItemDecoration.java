package com.vivian.timelineitemdecoration.itemdecoration;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.annotation.IntDef;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.view.ViewGroup;

import com.vivian.timelineitemdecoration.R;
import com.vivian.timelineitemdecoration.util.Util;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import static android.R.attr.drawableRight;

/**
 * *          _       _
 * *   __   _(_)_   _(_) __ _ _ __
 * *   \ \ / / \ \ / / |/ _` | '_ \
 * *    \ V /| |\ V /| | (_| | | | |
 * *     \_/ |_| \_/ |_|\__,_|_| |_|
 * <p>
 * Created by vivian on 2017/6/9.
 */

public class DotItemDecoration extends RecyclerView.ItemDecoration {
    public static final int STYLE_DRAW = 0;
    public static final int STYLE_RESOURCE = 1;

    //you can choose to draw or use resource as divider
    @Retention(RetentionPolicy.SOURCE)
    @IntDef({STYLE_DRAW, STYLE_RESOURCE})
    public @interface ItemStyle {

    }

    public static final int HORIZONTAL = 0;
    public static final int VERTICAL = 1;

    //you can choose the orientation of item decoration
    @Retention(RetentionPolicy.SOURCE)
    @IntDef({VERTICAL, HORIZONTAL})
    public @interface Orientation {

    }

    @ItemStyle
    private int mStyle = STYLE_DRAW;
    @Orientation
    private int mOrientation = VERTICAL;
    private Context mContext;

    private int mTopDistance = 40;
    private int mItemInterval = 20;
    private int mItemPaddingLeft = mItemInterval;//default
    private int mItemPaddingRight = mItemInterval;

    Paint mLinePaint;
    private int mLineWidth = 4;
    private int mLineColor = Color.WHITE;

    Drawable mDrawable;
    private int mDotRes = R.drawable.dot;

    Paint mDotPaint;
    private int mDotPaddingTop = 20;
    private int mDotRadius = 5;
    private int mDotColor = Color.WHITE;

    String mEnd = "END";
    Paint mTextPaint;
    Rect mTextRect;
    private int mTextColor = Color.WHITE;
    private int mTextSize = 18;

    private int mDotPaddingText = 10;//the distance between dot and text
    private int mBottomDistance = 30;//the distance between dot and the bottom of last child

    private boolean mDotInItemCenter =false;

    private SpanIndexListener mSpanIndexListener;

    public DotItemDecoration(Context context) {
        mContext = context;

        mTextPaint = new Paint();
        mTextRect = new Rect();
        mLinePaint = new Paint();
        mDotPaint = new Paint();
    }

    public void setSpanIndexListener(SpanIndexListener spanIndexListener) {
        this.mSpanIndexListener = spanIndexListener;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        int itemCount = parent.getAdapter().getItemCount();
        int currentPosition = parent.getChildAdapterPosition(view);

        if (mOrientation == VERTICAL) {
            outRect.left = mItemPaddingLeft;
            outRect.right = mItemPaddingRight;
            outRect.bottom = mItemInterval;
            if (parent.getChildAdapterPosition(view) == 0) {
                outRect.top = mTopDistance;
            } else if (parent.getChildAdapterPosition(view) == 1) {
                outRect.top = 2 * mTopDistance;
            } else if (parent.getAdapter() != null && (currentPosition == itemCount - 1 || currentPosition == itemCount - 2)) {
                outRect.bottom = outRect.height() + mItemInterval + mBottomDistance + mDotPaddingText + mTextSize + mTextRect.height() + mDotRadius;
            }
        } else {
            outRect.top = mItemPaddingLeft;
            outRect.bottom = mItemPaddingRight;
            outRect.right = mItemInterval;
            if (parent.getChildAdapterPosition(view) == 0) {
                outRect.left = mTopDistance;
            } else if (parent.getChildAdapterPosition(view) == 1) {
                outRect.left = 2 * mTopDistance;
            } else if (parent.getAdapter() != null && (currentPosition == itemCount - 1 || currentPosition == itemCount - 2)) {
                outRect.right = outRect.width() + mItemInterval + mBottomDistance + mDotPaddingText + mTextSize + mTextRect.width() + mDotRadius;
            }
        }

        if(mSpanIndexListener==null)
            return;
        int spanIndex=-1;
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        if (layoutParams instanceof StaggeredGridLayoutManager.LayoutParams) {
            StaggeredGridLayoutManager.LayoutParams staggerLayoutParams = (StaggeredGridLayoutManager.LayoutParams) layoutParams;
            spanIndex = staggerLayoutParams.getSpanIndex();

        }else if(layoutParams instanceof GridLayoutManager.LayoutParams){
            GridLayoutManager.LayoutParams mLayoutParams= (GridLayoutManager.LayoutParams) layoutParams;
            spanIndex = mLayoutParams.getSpanIndex();
        }

        if (spanIndex != -1) {
            mSpanIndexListener.onSpanIndexChange(view, spanIndex);
        }

    }

    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        if (mStyle == STYLE_RESOURCE) {
            mDrawable = ContextCompat.getDrawable(mContext, mDotRes);
        }

        mTextPaint.setColor(mTextColor);
        mTextPaint.setTextSize(mTextSize);

        mLinePaint.setColor(mLineColor);
        mLinePaint.setStrokeWidth(mLineWidth);

        mDotPaint.setColor(mDotColor);

        if (mOrientation == VERTICAL) {
            drawCenterVerticalLine(c, parent);
            drawVerticalItem(c, parent);
        } else {
            drawCenterHorizontalLine(c, parent);
            drawHorizontalItem(c, parent);
        }
    }

    public void drawCenterVerticalLine(Canvas c, RecyclerView parent) {
        final int top = parent.getPaddingTop();
        final int parentWidth = parent.getMeasuredWidth();
        int bottom;

        final int childCount = parent.getChildCount();
        View lastChild = parent.getChildAt(childCount - 1);
        if (childCount > 1) {
            View child = parent.getChildAt(childCount - 2);
            bottom = mBottomDistance + (child.getBottom() > lastChild.getBottom() ? child.getBottom() : lastChild.getBottom());
        } else {
            bottom = mBottomDistance + lastChild.getBottom();
        }

        c.drawLine(parentWidth / 2, top, parentWidth / 2, bottom, mLinePaint);
    }

    public void drawVerticalItem(Canvas c, RecyclerView parent) {
        final int parentWidth = parent.getMeasuredWidth();
        final int childCount = parent.getChildCount();
        for (int i = 0; i < childCount; i++) {
            final View child = parent.getChildAt(i);

            int top = child.getTop() + mDotPaddingTop;
            int bottom;
            int drawableLeft;
            if (mStyle == STYLE_RESOURCE) {
                bottom = top + mDrawable.getIntrinsicHeight();
                drawableLeft = parentWidth / 2 - mDrawable.getIntrinsicWidth() / 2;
                int drawableRight = parentWidth / 2 + mDrawable.getIntrinsicWidth() / 2;

                mDrawable.setBounds(drawableLeft, top, drawableRight, bottom);
                mDrawable.draw(c);
            } else {
                drawableLeft = parentWidth / 2;

                if(mDotInItemCenter){
                    c.drawCircle(drawableLeft,(child.getTop()+child.getBottom())/2+mDotPaddingTop,mDotRadius,mDotPaint);
                }else{

                    c.drawCircle(drawableLeft, top, mDotRadius, mDotPaint);
                }
                //c.drawCircle(drawableLeft, top, mDotRadius, mDotPaint);
            }

            if (i == childCount - 1) {
                View lastChild = parent.getChildAt(i - 1);
                if (lastChild.getBottom() < child.getBottom()) {
                    top = child.getBottom() + mBottomDistance;
                    bottom = child.getBottom() + (mStyle == STYLE_RESOURCE ? mDrawable.getIntrinsicHeight() : mDotRadius);
                } else {
                    top = lastChild.getBottom() + mBottomDistance;
                    bottom = lastChild.getBottom() + (mStyle == STYLE_RESOURCE ? mDrawable.getIntrinsicHeight() : mDotRadius);
                }
                if (mStyle == STYLE_RESOURCE) {
                    mDrawable.setBounds(drawableLeft, top, drawableRight, bottom);
                    mDrawable.draw(c);
                } else {

                    c.drawCircle(drawableLeft, top, mDotRadius, mDotPaint);
                }

                mTextPaint.getTextBounds(mEnd, 0, mEnd.length(), mTextRect);
                mTextPaint.setTextSize(mTextSize);
                c.drawText(mEnd, parentWidth / 2 - mTextRect.width() / 2, bottom + mBottomDistance + mDotPaddingText + mTextSize, mTextPaint);
            }
        }
    }

    public void drawCenterHorizontalLine(Canvas c, RecyclerView parent) {
        final int left = parent.getPaddingLeft();
        final int parentHeight = parent.getMeasuredHeight();
        int right;

        final int childCount = parent.getChildCount();
        View lastChild = parent.getChildAt(childCount - 1);
        if (childCount > 1) {
            View child = parent.getChildAt(childCount - 2);
            right = mBottomDistance + (child.getRight() > lastChild.getRight() ? child.getRight() : lastChild.getRight());
        } else {
            right = mBottomDistance + lastChild.getRight();
        }

        c.drawLine(left, parentHeight / 2, right, parentHeight / 2, mLinePaint);
    }

    public void drawHorizontalItem(Canvas c, RecyclerView parent) {
        final int parentHeight = parent.getMeasuredHeight();
        final int childCount = parent.getChildCount();

        for (int i = 0; i < childCount; i++) {
            final View child = parent.getChildAt(i);
            int left = child.getLeft() + mDotPaddingTop;
            int right;
            int drawableLeft;

            if (mStyle == STYLE_RESOURCE) {
                right = left + mDrawable.getIntrinsicWidth();
                drawableLeft = parentHeight / 2 - mDrawable.getIntrinsicWidth() / 2;
                int drawableRight = parentHeight / 2 + mDrawable.getIntrinsicWidth() / 2;

                mDrawable.setBounds(drawableLeft, left, drawableRight, right);
                mDrawable.draw(c);
            } else {
                drawableLeft = parentHeight / 2;

                if(mDotInItemCenter){

                    c.drawCircle((child.getLeft()+child.getRight())/2+mDotPaddingTop, drawableLeft, mDotRadius, mDotPaint);
                }else{
                    c.drawCircle(left, drawableLeft, mDotRadius, mDotPaint);
                }
            }

            if (i == childCount - 1) {
                View lastChild = parent.getChildAt(i - 1);
                if (lastChild.getRight() < child.getRight()) {
                    left = child.getRight() + mBottomDistance;
                    right = child.getRight() + (mStyle == STYLE_RESOURCE ? mDrawable.getIntrinsicWidth() : mDotRadius);
                } else {
                    left = lastChild.getRight() + mBottomDistance;
                    right = lastChild.getRight() + (mStyle == STYLE_RESOURCE ? mDrawable.getIntrinsicWidth() : mDotRadius);
                }
                if (mStyle == STYLE_RESOURCE) {
                    mDrawable.setBounds(left, drawableLeft, drawableRight, right);
                    mDrawable.draw(c);
                } else {
                    c.drawCircle(left, drawableLeft, mDotRadius, mDotPaint);
                }

                mTextPaint.getTextBounds(mEnd, 0, mEnd.length(), mTextRect);
                mTextPaint.setTextSize(mTextSize);
                c.drawText(mEnd, right + mBottomDistance + mDotPaddingText + mTextSize, parentHeight / 2 + mTextRect.height() / 2, mTextPaint);
            }
        }
    }

    public static class Builder {
        private Context mContext;
        private DotItemDecoration itemDecoration;

        public Builder(Context context) {
            this.mContext = context;
            itemDecoration = new DotItemDecoration(context);
        }

        public Builder setOrientation(@Orientation int orientation) {
            itemDecoration.mOrientation = orientation;
            return this;
        }

        public Builder setItemStyle(@ItemStyle int itemStyle) {
            itemDecoration.mStyle = itemStyle;
            return this;
        }

        public Builder setTopDistance(float distance) {
            itemDecoration.mTopDistance = Util.Dp2Px(mContext, distance);
            return this;
        }

        public Builder setItemPaddingLeft(float itemPaddingLeft) {
            itemDecoration.mItemPaddingLeft = Util.Dp2Px(mContext, itemPaddingLeft);
            return this;
        }

        public Builder setItemPaddingRight(float itemPaddingRight) {
            itemDecoration.mItemPaddingRight = Util.Dp2Px(mContext, itemPaddingRight);
            return this;
        }

        public Builder setItemInterVal(float interval) {
            itemDecoration.mItemInterval = Util.Dp2Px(mContext, interval);
            return this;
        }

        public Builder setLineWidth(float lineWidth) {
            itemDecoration.mLineWidth = Util.Dp2Px(mContext, lineWidth);
            return this;
        }

        public Builder setLineColor(int lineColor) {
            itemDecoration.mLineColor = lineColor;
            return this;
        }

        public Builder setDotPaddingTop(int paddingTop){
            itemDecoration.mDotPaddingTop= Util.Dp2Px(mContext,paddingTop);
            return this;
        }

        public Builder setDotRes(int dotRes) {
            itemDecoration.mDotRes = dotRes;
            return this;
        }

        public Builder setDotRadius(int dotRadius) {
            itemDecoration.mDotRadius = Util.Dp2Px(mContext, dotRadius);
            return this;
        }

        public Builder setDotColor(int dotColor) {
            itemDecoration.mDotColor = dotColor;
            return this;
        }

        public Builder setTextColor(int textColor) {
            itemDecoration.mTextColor = textColor;
            return this;
        }

        public Builder setTextSize(float textSize) {
            itemDecoration.mTextSize = Util.Sp2Px(mContext, textSize);
            return this;
        }

        public Builder setDotPaddingText(float dotPaddingText) {
            itemDecoration.mDotPaddingText = Util.Dp2Px(mContext, dotPaddingText);
            return this;
        }
        public Builder setDotInItemOrientationCenter(boolean tag){
            itemDecoration.mDotInItemCenter =tag;
            return this;
        }

        public Builder setBottomDistance(float bottomDistance) {
            itemDecoration.mBottomDistance = Util.Dp2Px(mContext, bottomDistance);
            return this;
        }

        public Builder setEndText(String end) {
            itemDecoration.mEnd = end;
            return this;
        }

        public DotItemDecoration create() {
            return itemDecoration;
        }
    }
}