package com.mad.bubbles;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.view.View;

import java.util.Random;

public class TouchView extends View {

    Paint paint;
    Point p;
    Context ctx;
    Random rand = new Random();
    int color;
    int radius;

    public TouchView(Context context) {
        super(context);
        paint = new Paint();
        ctx = context;
    }

    /**
     * This function is called when the view is instantiated, e.g., when view is initially drawn or
     * when invalidate() is called (happens in performClick() here
     * source: https://stackoverflow.com/questions/11912406/view-ondraw-when-does-it-get-called
     * @param canvas
     */

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        if (p == null) {
            return;
        }

        paint.setColor(color);
        canvas.drawCircle(p.x, p.y, radius, paint);
    }

    @Override
    public boolean performClick() {
        createNewCircle();
        invalidate();
        return super.performClick();
    }

    /**
     * Creates a new circle, called when performClick() is invoked
     */
    private void createNewCircle() {

        int width = getWidth();
        int height = getHeight();
        int x = rand.nextInt(width);
        int y = rand.nextInt(height);
        p = new Point(x, y);
        radius = rand.nextInt(100);
        color = Color.argb(255, rand.nextInt(256), rand.nextInt(256), rand.nextInt(256));
    }
}
