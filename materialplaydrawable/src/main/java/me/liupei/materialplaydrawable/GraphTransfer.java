package me.liupei.materialplaydrawable;

import android.graphics.Path;
import android.graphics.PointF;

/**
 * Created by liupei on 15/9/28.
 */
class GraphTransfer {


    public static Path pauseToStopLeft(int size, float progress) {
        return generate(new ShapePause().getLeft(size), new ShapeStop().getLeft(size), progress);
    }

    public static Path pauseToStopRight(int size, float progress) {
        return generate(new ShapePause().getRight(size), new ShapeStop().getRight(size), progress);
    }

    public static Path stopToPauseLeft(int size, float progress) {
        return generate(new ShapeStop().getLeft(size), new ShapePause().getLeft(size), progress);
    }

    public static Path stopToPauseRight(int size, float progress) {
        return generate(new ShapeStop().getRight(size), new ShapePause().getRight(size), progress);
    }

    public static Path pauseToPlayLeft(int size, float progress) {
        return generate(new ShapePause().getLeft(size), new ShapePlay().getLeft(size), progress);
    }

    public static Path pauseToPlayRight(int size, float progress) {
        return generate(new ShapePause().getRight(size), new ShapePlay().getRight(size), progress);
    }

    public static Path playToPauseLeft(int size, float progress) {
        return generate(new ShapePlay().getLeft(size), new ShapePause().getLeft(size), progress);
    }

    public static Path playToPauseRight(int size, float progress) {
        return generate(new ShapePlay().getRight(size), new ShapePause().getRight(size), progress);
    }

    public static Path stopToPlayLeft(int size, float progress) {
        return generate(new ShapeStop().getLeft(size), new ShapePlay().getLeft(size), progress);
    }

    public static Path stopToPlayRight(int size, float progress) {
        return generate(new ShapeStop().getRight(size), new ShapePlay().getRight(size), progress);
    }

    public static Path playToStopLeft(int size, float progress) {
        return generate(new ShapePlay().getLeft(size), new ShapeStop().getLeft(size), progress);
    }

    public static Path playToStopRight(int size, float progress) {
        return generate(new ShapePlay().getRight(size), new ShapeStop().getRight(size), progress);
    }

    private static Path generate(PointF[] a, PointF[] b, float progress) {

        Path p = new Path();
        p.moveTo(elevator(a[0].x, b[0].x, progress),
                elevator(a[0].y, b[0].y, progress));
        p.lineTo(elevator(a[1].x, b[1].x, progress),
                elevator(a[1].y, b[1].y, progress));
        p.lineTo(elevator(a[2].x, b[2].x, progress),
                elevator(a[2].y, b[2].y, progress));
        p.lineTo(elevator(a[3].x, b[3].x, progress),
                elevator(a[3].y, b[3].y, progress));
        p.close();
        return p;
    }

    private static float elevator(float from, float to, float progress) {
        return from + progress * (to - from);
    }


}
