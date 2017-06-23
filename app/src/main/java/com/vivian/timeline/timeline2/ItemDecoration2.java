package com.vivian.timeline.timeline2;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.view.ViewGroup;

import com.vivian.timeline.R;

/**
 * *          _       _
 * *   __   _(_)_   _(_) __ _ _ __
 * *   \ \ / / \ \ / / |/ _` | '_ \
 * *    \ V /| |\ V /| | (_| | | | |
 * *     \_/ |_| \_/ |_|\__,_|_| |_|
 * <p>
 * Created by vivian on 2017/6/9.
 */

public class ItemDecoration2 extends RecyclerView.ItemDecoration {
    private Context mContext;
    private int distance;

    Drawable drawable;
    Drawable verticalLine;

    Paint textPaint;
    Rect textRect;
    String end = "END";
    boolean isLeft;

    public ItemDecoration2(Context context, int distance) {
        mContext = context;
        this.distance = distance;
        verticalLine = ContextCompat.getDrawable(mContext, R.drawable.white_line);
        drawable = ContextCompat.getDrawable(mContext, R.drawable.dot);
        textPaint = new Paint();
        textPaint.setColor(0xFFFFFFFF);
        textPaint.setTextSize(18);
        textRect = new Rect();
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        outRect.left = 20;
        outRect.right = 20;
        outRect.bottom = distance;
        if (parent.getChildAdapterPosition(view) == 0) {
            outRect.top = 0;
        } else if (parent.getChildAdapterPosition(view) == 1) {
            outRect.top = 2 * distance;
        }
    }

    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        drawHorizontal(c, parent);
        drawVertical(c, parent);
    }

    public void drawHorizontal(Canvas c, RecyclerView parent) {
        final int top = parent.getPaddingTop();
        final int parentWidth = parent.getMeasuredWidth();

        final int childCount = parent.getChildCount();
        View lastChild = parent.getChildAt(childCount - 1);
        View child = parent.getChildAt(childCount - 2);
        int bottom = child.getBottom() > lastChild.getBottom() ? child.getBottom() : lastChild.getBottom();
        verticalLine.setBounds(parentWidth / 2 - 1, top, parentWidth / 2 + 1, bottom);
        verticalLine.draw(c);
    }

    public void drawVertical(Canvas c, RecyclerView parent) {
        final int parentWidth = parent.getMeasuredWidth();

        final int childCount = parent.getChildCount();
        for (int i = 0; i < childCount; i++) {//-1最后一个不画
            final View child = parent.getChildAt(i);

            int top = child.getTop() + 10;
            int bottom = top + drawable.getIntrinsicHeight();

            int drawableLeft = parentWidth / 2 - drawable.getIntrinsicWidth() / 2;
            int drawableRight = parentWidth / 2 + drawable.getIntrinsicWidth() / 2;

            drawable.setBounds(drawableLeft, top, drawableRight, bottom);
            drawable.draw(c);

            ViewGroup.LayoutParams layoutParams = child.getLayoutParams();
            if (layoutParams instanceof StaggeredGridLayoutManager.LayoutParams) {
                StaggeredGridLayoutManager.LayoutParams staggerLayoutParams = (StaggeredGridLayoutManager.LayoutParams) layoutParams;
                isLeft = staggerLayoutParams.getSpanIndex() == 0;
                child.setBackground(ContextCompat.getDrawable(mContext, isLeft?R.drawable.pop_left:R.drawable.pop_right));
            }

            if (i == childCount - 1) {
                View lastChild = parent.getChildAt(i - 1);
                if (lastChild.getBottom() < child.getBottom()) {
                    top = child.getBottom();
                    bottom = child.getBottom() + drawable.getIntrinsicHeight();
                } else {
                    top = lastChild.getBottom();
                    bottom = lastChild.getBottom() + drawable.getIntrinsicHeight();
                }
                drawable.setBounds(drawableLeft, top, drawableRight, bottom);
                drawable.draw(c);

                textPaint.getTextBounds(end, 0, end.length(), textRect);
                c.drawText(end, parentWidth / 2 - textRect.width() / 2, bottom + 30, textPaint);
            }
        }
    }
}