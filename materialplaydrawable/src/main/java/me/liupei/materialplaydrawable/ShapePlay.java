package me.liupei.materialplaydrawable;

import android.graphics.PointF;

/**
 * Created by liupei on 15/9/29.
 */
class ShapePlay implements Shape {

    @Override
    public PointF[] getLeft(int size) {
        PointF points[] = new PointF[4];
        points[0] = new PointF(0.5f * size, 0.1f * size);
        points[1] = new PointF(0.5f * size, 0.1f * size);
        points[2] = new PointF(0.5f * size, 0.7f * size);
        points[3] = new PointF((0.5f - 0.2f * (float) Math.sqrt(3)) * size, 0.7f * size);
        return points;
    }

    @Override
    public PointF[] getRight(int size) {
        PointF points[] = new PointF[4];
        points[0] = new PointF(0.5f * size, 0.1f * size);
        points[1] = new PointF(0.5f * size, 0.1f * size);
        points[2] = new PointF((0.5f + 0.2f * (float) Math.sqrt(3)) * size, 0.7f * size);
        points[3] = new PointF(0.5f * size, 0.7f * size);

        return points;
    }

}
