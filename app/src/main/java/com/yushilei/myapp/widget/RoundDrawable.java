package com.yushilei.myapp.widget;

import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;

/**
 * @auther by yushilei.
 * @time 2017/3/14-16:31
 * @desc
 */

public class RoundDrawable extends Drawable {
    private Paint paint;

    public RoundDrawable() {
        this.paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    }

    @Override
    public void draw(@NonNull Canvas canvas) {
        Rect bounds = getBounds();

        int x = bounds.width() / 2;
        int y = bounds.height() / 2;
        canvas.drawCircle(x, y, Math.min(x, y), paint);
    }

    @Override
    public void setAlpha(int alpha) {
        paint.setAlpha(alpha);
    }

    @Override
    public void setColorFilter(ColorFilter colorFilter) {
        paint.setColorFilter(colorFilter);
    }

    @Override
    public int getOpacity() {
        return PixelFormat.TRANSLUCENT;
    }
}
