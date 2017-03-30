package com.yushilei.myapp.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Path;
import android.graphics.Region;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.View;

/**
 * @auther by yushilei.
 * @time 2017/3/30-17:50
 * @desc
 */

public class ClipRegionView extends View {

    private TextPaint mPaint = new TextPaint();

    public ClipRegionView(Context context, AttributeSet attrs) {
        super(context, attrs);
        // TODO Auto-generated constructor stub  
    }

    @Override
    protected void onDraw(Canvas canvas) {
        // TODO Auto-generated method stub  
        super.onDraw(canvas);
        canvas.drawColor(Color.LTGRAY);
        mPaint.setTextSize(40);
        drawBg(canvas);
        drawDiff(canvas);
        drawReplace(canvas);
        drawUnion(canvas);
        drawXor(canvas);
        drawReverse(canvas);
        drawIntersect(canvas);
    }

    //背景
    private void drawScene(Canvas canvas, String op) {
        canvas.clipRect(0, 0, 300, 300);

        canvas.drawColor(Color.WHITE);

        mPaint.setColor(Color.RED);
        canvas.drawLine(0, 0, 300, 300, mPaint);

        mPaint.setColor(Color.GREEN);
        canvas.drawCircle(90, 210, 90, mPaint);

        mPaint.setColor(Color.BLUE);
        canvas.drawText(op, 150, 150, mPaint);
    }

    //背景
    private void drawBg(Canvas canvas) {
        canvas.save();
        canvas.translate(0, 0);
        drawScene(canvas, "背景");
        canvas.restore();
    }

    //相减
    private void drawDiff(Canvas canvas) {
        canvas.save();
        canvas.translate(300, 0);
        canvas.clipRect(0, 0, 180, 180);
        canvas.clipRect(120, 120, 300, 300, Region.Op.DIFFERENCE);
        drawScene(canvas, "DIFFERENCE-相减");
        canvas.restore();

    }

    //取代
    private void drawReplace(Canvas canvas) {
        Path path = new Path();
        canvas.save();
        canvas.translate(0, 300);
        path.reset();
       // canvas.clipPath(path); // makes the clip empty
        path.addCircle(150, 150, 150, Path.Direction.CCW);
        canvas.clipPath(path, Region.Op.REPLACE);
        drawScene(canvas, "REPLACE-取代");
        canvas.restore();

//        canvas.save();
//        canvas.translate(0, 300);
//        canvas.clipRect(0, 0, 180, 180);
//        canvas.clipRect(120, 120, 300, 300, Region.Op.REPLACE);
//        drawScene(canvas, "REPLACE-取代");
//        canvas.restore();
    }

    //并集
    private void drawUnion(Canvas canvas) {
        canvas.save();
        canvas.translate(300, 300);
        canvas.clipRect(0, 0, 180, 180);
        canvas.clipRect(120, 120, 300, 300, Region.Op.UNION);
        drawScene(canvas, "UNION-并集");
        canvas.restore();
    }

    //存异去同XOR
    private void drawXor(Canvas canvas) {
        canvas.save();
        canvas.translate(0, 600);
        canvas.clipRect(0, 0, 180, 180);
        canvas.clipRect(120, 120, 300, 300, Region.Op.XOR);
        drawScene(canvas, "XOR-存异去同");
        canvas.restore();
    }

    //Difference的相反运算，将后画的部分中去掉先前的部分
    private void drawReverse(Canvas canvas) {
        canvas.save();
        canvas.translate(300, 600);
        canvas.clipRect(0, 0, 180, 180);
        canvas.clipRect(120, 120, 300, 300,
                Region.Op.REVERSE_DIFFERENCE);
        drawScene(canvas, "REVERSE_DIFFERENCE");
        canvas.restore();

//      canvas.save();
//      canvas.translate(160, 310);
//      canvas.clipRect(0, 0, 60, 60);
//      canvas.clipRect(40, 40, 300, 300,
//      Region.Op.INTERSECT);
//      drawScene(canvas);
//      canvas.restore();
    }

    //交集
    private void drawIntersect(Canvas canvas) {
        canvas.save();
        canvas.translate(0, 900);
        canvas.clipRect(0, 0, 180, 180);
        canvas.clipRect(120, 120, 300, 300,
                Region.Op.INTERSECT);
        drawScene(canvas, "INTERSECT-交集");
        canvas.restore();
    }
}
