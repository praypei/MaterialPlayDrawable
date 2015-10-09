package me.liupei.materialplaydrawable;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Transformation;

/**
 * Created by liupei on 15/9/23.
 */
public class MaterialPlayDrawable extends Drawable {

    private Paint mPaint;
    private Context mContext;
    private int mSize = 100;
    private float mProgress = 0f;
    private Animation mAnimation;
    private View mParent;

    private int status = STATUS_STOP;

    public static final int STATUS_STOP = 0;
    public static final int STATUS_PLAY = 1;
    public static final int STATUS_PAUSE = 2;

    private int process = PROCESS_STOP_PLAY;

    private static final int PROCESS_STOP_PLAY = 0x00;
    private static final int PROCESS_PLAY_STOP = 0x01;
    private static final int PROCESS_PLAY_PAUSE = 0x02;
    private static final int PROCESS_PAUSE_PLAY = 0x03;
    private static final int PROCESS_PAUSE_STOP = 0x04;
    private static final int PROCESS_STOP_PAUSE = 0x05;

    public MaterialPlayDrawable(Context context, View parent, int size) {
        this(context, parent);
        mSize = size;
    }

    public MaterialPlayDrawable(Context context, View parent) {
        mContext = context;
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        mPaint.setStrokeWidth(1);
        mParent = parent;
        setupAnimation();
    }

    @Override
    public void setAlpha(int alpha) {
        if (alpha != this.mPaint.getAlpha()) {
            this.mPaint.setAlpha(alpha);
            this.invalidateSelf();
        }
    }

    @Override
    public int getIntrinsicWidth() {
        return mSize;
    }

    @Override
    public int getIntrinsicHeight() {
        return mSize;
    }

    @Override
    public void draw(Canvas canvas) {


        float degree = 0;


        switch (process) {
            case PROCESS_PAUSE_STOP:
            case PROCESS_STOP_PLAY:
            case PROCESS_PAUSE_PLAY:
                degree = 90 * mProgress;
                break;
            case PROCESS_PLAY_STOP:
            case PROCESS_PLAY_PAUSE:
            case PROCESS_STOP_PAUSE:
                degree = 90 + 90 * mProgress;
                break;
        }

        canvas.rotate(degree, mSize / 2f, mSize / 2f);
        mPaint.setColor(Color.WHITE);
        canvas.drawPath(handleAPath(), mPaint);
        canvas.drawPath(handleBPath(), mPaint);

    }

    @Override
    public void setColorFilter(ColorFilter colorFilter) {
        this.mPaint.setColorFilter(colorFilter);
        this.invalidateSelf();
    }

    @Override
    public int getOpacity() {
        return -3;
    }

    public void setColor(int color) {
        this.mPaint.setColor(color);
        invalidateSelf();
    }

    private void setProgress(float i) {
        this.mProgress = i;
        invalidateSelf();
    }

    private Path handleAPath() {
        Path p = null;

        switch (process) {
            case PROCESS_PAUSE_STOP:

                p = GraphTransfer.pauseToStopLeft(mSize, mProgress);

                break;

            case PROCESS_STOP_PAUSE:

                p = GraphTransfer.stopToPauseLeft(mSize, mProgress);

                break;

            case PROCESS_STOP_PLAY:

                p = GraphTransfer.stopToPlayLeft(mSize, mProgress);

                break;

            case PROCESS_PLAY_STOP:

                p = GraphTransfer.playToStopLeft(mSize, mProgress);

                break;

            case PROCESS_PLAY_PAUSE:

                p = GraphTransfer.playToPauseLeft(mSize, mProgress);

                break;

            case PROCESS_PAUSE_PLAY:

                p = GraphTransfer.pauseToPlayLeft(mSize, mProgress);

                break;

            default:

                p = GraphTransfer.pauseToStopLeft(mSize, mProgress);

                break;
        }

        return p;

    }

    private Path handleBPath() {
        Path p = null;

        switch (process) {
            case PROCESS_PAUSE_STOP:

                p = GraphTransfer.pauseToStopRight(mSize, mProgress);

                break;

            case PROCESS_STOP_PAUSE:

                p = GraphTransfer.stopToPauseRight(mSize, mProgress);

                break;

            case PROCESS_STOP_PLAY:

                p = GraphTransfer.stopToPlayRight(mSize, mProgress);

                break;

            case PROCESS_PLAY_STOP:

                p = GraphTransfer.playToStopRight(mSize, mProgress);

                break;

            case PROCESS_PLAY_PAUSE:

                p = GraphTransfer.playToPauseRight(mSize, mProgress);

                break;

            case PROCESS_PAUSE_PLAY:

                p = GraphTransfer.pauseToPlayRight(mSize, mProgress);

                break;

            default:

                p = GraphTransfer.pauseToStopRight(mSize, mProgress);

                break;
        }

        return p;
    }

    private void start() {
        mAnimation.reset();
        mParent.startAnimation(mAnimation);
    }

    private void setupAnimation() {
        final Animation animation = new Animation() {
            @Override
            protected void applyTransformation(float interpolatedTime, Transformation t) {
                setProgress(interpolatedTime);
            }
        };
        animation.setDuration(400);
        animation.setRepeatMode(Animation.RESTART);
        animation.setInterpolator(new DecelerateInterpolator());
        mAnimation = animation;
    }

    /**
     * switch to another status without animation
     *
     * @param status STATUS_PLAY or STATUS_PAUSE or STATUS_STOP
     */
    public void setStatus(int status) {
        if (status != this.status) {

            switch (status) {
                case STATUS_PAUSE:
                    this.process = PROCESS_PAUSE_PLAY;
                    this.status = status;
                    break;
                case STATUS_STOP:
                    this.process = PROCESS_STOP_PLAY;
                    this.status = status;
                    break;
                case STATUS_PLAY:
                    this.process = PROCESS_PLAY_PAUSE;
                    this.status = status;
                    break;
            }

            setProgress(0f);
        }
    }

    /**
     * get the drawable status
     *
     * @return status STATUS_PLAY or STATUS_PAUSE or STATUS_STOP
     */
    public int getStatus() {
        return this.status;
    }

    /**
     * switch to another status with animation
     *
     * @param status STATUS_PLAY or STATUS_PAUSE or STATUS_STOP
     */
    public void switchStatus(int status) {

        if (status != this.status) {

            switch (this.status) {
                case STATUS_PAUSE:
                    switch (status) {
                        case STATUS_STOP:
                            process = PROCESS_PAUSE_STOP;
                            start();
                            break;
                        case STATUS_PLAY:
                            process = PROCESS_PAUSE_PLAY;
                            start();
                            break;
                    }
                    this.status = status;
                    break;
                case STATUS_STOP:
                    switch (status) {
                        case STATUS_PAUSE:
                            process = PROCESS_STOP_PAUSE;
                            start();
                            break;
                        case STATUS_PLAY:
                            process = PROCESS_STOP_PLAY;
                            start();
                            break;
                    }
                    this.status = status;
                    break;
                case STATUS_PLAY:
                    switch (status) {
                        case STATUS_STOP:
                            process = PROCESS_PLAY_STOP;
                            start();
                            break;
                        case STATUS_PAUSE:
                            process = PROCESS_PLAY_PAUSE;
                            start();
                            break;
                    }
                    this.status = status;
                    break;
            }

        }

    }
}
