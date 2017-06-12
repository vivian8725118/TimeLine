package com.vivian.timeline.timeline2;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.View;

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

    public ItemDecoration2(Context context, int distance) {
        mContext = context;
        this.distance = distance;
        verticalLine = ContextCompat.getDrawable(mContext, R.drawable.white_line);
        drawable = ContextCompat.getDrawable(mContext, R.drawable.dot);
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        outRect.left = 20;
        outRect.right = 20;
        outRect.bottom = distance;
        if (parent.getChildAdapterPosition(view) == 0) {
            outRect.top =0;
        } else if (parent.getChildAdapterPosition(view) == 1) {
            outRect.top = 2*distance;
        }
    }

    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        drawHorizontal(c, parent);
        drawVertical(c, parent);
    }

    public void drawHorizontal(Canvas c, RecyclerView parent) {
        final int top = parent.getPaddingTop();
        final int bottom = parent.getHeight() - parent.getPaddingBottom();
        final int parentWidth = parent.getMeasuredWidth();

        final int childCount = parent.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View child = parent.getChildAt(i);

            //中间的线直接画到底的话可以用bottom，根据child选择中间画线就换成child.getBottom()
            verticalLine.setBounds(parentWidth / 2 - 1, top, parentWidth / 2 + 1, bottom);
            verticalLine.draw(c);
        }
    }

    public void drawVertical(Canvas c, RecyclerView parent) {
        final int parentWidth = parent.getMeasuredWidth();

        final int childCount = parent.getChildCount();
        for (int i = 0; i < childCount; i++) {//-1最后一个不画
            final View child = parent.getChildAt(i);

            final int top = child.getTop()+10;
            final int bottom = top + drawable.getIntrinsicHeight();

            int drawableLeft = parentWidth / 2 - drawable.getIntrinsicWidth() / 2;
            int drawableRight = parentWidth / 2 + drawable.getIntrinsicWidth() / 2;

            drawable.setBounds(drawableLeft, top, drawableRight, bottom);
            drawable.draw(c);
        }
    }
}