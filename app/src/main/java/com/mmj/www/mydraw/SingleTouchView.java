package com.mmj.www.mydraw;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.Nullable;
import android.text.method.Touch;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

public class SingleTouchView extends View {
    private Paint paint = new Paint();
    private Path path = new Path();
    private int paintColor = 0xFF000000;
    private Paint canvasPaint;
    private Canvas drawCanvas;
    private Bitmap canasBitmap;
    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        canasBitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
        drawCanvas = new Canvas(canasBitmap);
        canvasPaint = new Paint(Paint.DITHER_FLAG);
    }

    public SingleTouchView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        paint.setAntiAlias(true);
        paint.setStrokeWidth(10f);
        paint.setColor(paintColor);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeJoin(Paint.Join.ROUND);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float touchX = event.getX();
        float touchY = event.getY();
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                path.moveTo(touchX, touchY); break;
            case MotionEvent.ACTION_MOVE:
                path.lineTo(touchX, touchY); break;
            case MotionEvent.ACTION_UP:
                drawCanvas.drawPath(path, paint);
                path.reset();
                break;
                default:return false;
        }
        invalidate();
        return true;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawBitmap(canasBitmap,0,0, canvasPaint);
        canvas.drawPath(path, paint);

    }
    public void SetColor(String newColor){
        invalidate();
        paintColor = Color.parseColor(newColor);
        paint.setColor(paintColor);

    }
}
