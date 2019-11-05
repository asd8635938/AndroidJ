package com.example.jy.jieyou.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.TextView;

public class DrawableTextView extends TextView {
    public DrawableTextView(Context context) {
        this(context, null);
    }

    public DrawableTextView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public DrawableTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        Drawable[] drawables = getCompoundDrawables();
        Drawable leftDrawable = drawables[0];
        if (leftDrawable != null) {
            int leftDrawableWidth = leftDrawable.getIntrinsicWidth();
            int drawablePadding = getCompoundDrawablePadding();
            int textWidth = (int) getPaint().measureText(getText().toString().trim());
            int bodyWidth = leftDrawableWidth + drawablePadding + textWidth;
            canvas.save();
            canvas.translate((getWidth() - bodyWidth) / 2, 0);
        }
        super.onDraw(canvas);
    }
}
