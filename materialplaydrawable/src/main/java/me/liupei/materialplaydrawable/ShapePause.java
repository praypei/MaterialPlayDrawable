package me.liupei.materialplaydrawable;

import android.graphics.PointF;

/**
 * Created by liupei on 15/9/29.
 */
class ShapePause implements Shape {

    @Override
    public PointF[] getLeft(int size) {
        PointF points[] = new PointF[4];
        points[0] = new PointF(0.2f * size, 0.2f * size);
        points[1] = new PointF(0.4f * size, 0.2f * size);
        points[2] = new PointF(0.4f * size, 0.8f * size);
        points[3] = new PointF(0.2f * size, 0.8f * size);
        return points;
    }

    @Override
    public PointF[] getRight(int size) {
        PointF points[] = new PointF[4];
        points[0] = new PointF(0.6f * size, 0.2f * size);
        points[1] = new PointF(0.8f * size, 0.2f * size);
        points[2] = new PointF(0.8f * size, 0.8f * size);
        points[3] = new PointF(0.6f * size, 0.8f * size);
        return points;
    }
}
