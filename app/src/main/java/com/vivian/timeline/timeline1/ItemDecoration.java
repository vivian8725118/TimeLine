package com.vivian.timeline.timeline1;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;
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

public class ItemDecoration extends RecyclerView.ItemDecoration {
    private Context mContext;
    private int distance;

    Drawable drawable;
    Drawable verticalLine;
    Drawable horizontalLine;


    public ItemDecoration(Context context, int distance) {
        mContext = context;
        this.distance = distance;
        verticalLine = ContextCompat.getDrawable(mContext, R.drawable.gray_line);
        drawable = ContextCompat.getDrawable(mContext, R.drawable.time);
        horizontalLine = ContextCompat.getDrawable(mContext, R.drawable.horizontal_line);
    }

    @Override
    public void getItemOffsets(Rect outRect, @NonNull View view, RecyclerView parent, @NonNull RecyclerView.State state) {
        outRect.left = distance;
        outRect.right = distance;
        outRect.bottom = 3 * distance;
        if (parent.getChildAdapterPosition(view) == 0) {
            outRect.top = distance;
        } else if (parent.getChildAdapterPosition(view) == 1) {
            outRect.top = 4 * distance;
        }

        if (parent.getChildAdapterPosition(view) % 2 == 0) {
            outRect.left = 20;
        } else {
            outRect.right = 20;
        }
    }

    @Override
    public void onDraw(@NonNull Canvas c, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        drawHorizontal(c, parent);
        drawVertical(c, parent);
    }

    public void drawHorizontal(Canvas c, RecyclerView parent) {
        final int top = parent.getPaddingTop();
        final int bottom = parent.getHeight() - parent.getPaddingBottom();
        final int parentWidth = parent.getMeasuredWidth();

        verticalLine.setBounds(parentWidth / 2 - 1, top, parentWidth / 2 + 1,bottom);
        verticalLine.draw(c);
    }

    public void drawVertical(Canvas c, RecyclerView parent) {
        final int parentWidth = parent.getMeasuredWidth();

        final int childCount = parent.getChildCount();
        for (int i = 0; i < childCount; i++) {//-1最后一个不画
            final View child = parent.getChildAt(i);

            final int top = child.getTop() + (child.getBottom() - child.getTop()) / 2 - drawable.getIntrinsicHeight() / 2;
            final int bottom = top + drawable.getIntrinsicHeight();

            int horizontalLineLeft = child.getRight();
            int horizontalLineRight = parentWidth / 2;
            int horizontalLineTop = child.getTop() + (child.getBottom() - child.getTop()) / 2;

            if (child.getLeft()<parentWidth/2) {
                horizontalLineLeft = parentWidth / 2;
                horizontalLineRight = child.getLeft();
            }

            horizontalLine.setBounds(horizontalLineLeft, horizontalLineTop, horizontalLineRight, horizontalLineTop + 2);
            horizontalLine.draw(c);

            int drawableLeft = parentWidth / 2 - drawable.getIntrinsicWidth() / 2;
            int drawableRight = parentWidth / 2 + drawable.getIntrinsicWidth() / 2;

            drawable.setBounds(drawableLeft, top, drawableRight, bottom);
            drawable.draw(c);
        }
    }
}