package com.example.hh.sidebartest;

import android.content.Context;
import android.graphics.Canvas;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by haohe on 2017/4/17 0017.
 */

public class SideBar extends View {

    private OnTouchingLetterListener touchingLetterListener;

    private int chooseLetterPosition = -1;
    public static String[] firstLetters = { "A", "B", "C", "D", "E", "F", "G", "H", "I",
            "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V",
            "W", "X", "Y", "Z", "#" };

    public SideBar(Context context) {
        super(context);
    }

    public SideBar(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public SideBar(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    //重构绘图
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {

        int action = event.getAction();
        switch (action)
        {
            case MotionEvent.ACTION_UP:

                break;
            default:
                break;
        }

        return true;
    }

    public interface OnTouchingLetterListener{
        void onLetterChanged(String s);
    }

    public void setTouchLetterListener(OnTouchingLetterListener touchingLetterListener){
        this.touchingLetterListener = touchingLetterListener;
    }


}
