package com.example.hh.sidebartest;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
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
    private Paint paint = new Paint();
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

        int height = getHeight();
        int width = getWidth();
        int singleHeight = height/firstLetters.length;

        for(int i = 0;i<firstLetters.length; i++){
            paint.setColor(Color.rgb(33, 65, 98));
            paint.setTypeface(Typeface.DEFAULT_BOLD);
            paint.setAntiAlias(true);
            paint.setTextSize(20);

            if(i == chooseLetterPosition){ // 选中的状态
                paint.setColor(Color.parseColor("#3399ff"));
                paint.setFakeBoldText(true);
            }
            // x坐标等于中间-字符串宽度的一半.
            float xPos = width /2 - paint.measureText(firstLetters[i]) /2 ;
            float yPos = singleHeight * i + singleHeight;
            canvas.drawText(firstLetters[i], xPos, yPos, paint);
            paint.reset(); // 重置画笔
        }
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {

        int action = event.getAction();
        // 点击y坐标所占总高度的比例*firstLetters数组的长度 就等于点击firstLetters中的position.
        // 点击字母表中的位置
        final int c = (int) (event.getY() / getHeight() * firstLetters.length);
        OnTouchingLetterListener listener = touchingLetterListener;
        switch (action)
        {
            case MotionEvent.ACTION_UP:     //按下后松手
                setBackgroundDrawable(new ColorDrawable(0x00000000));
                chooseLetterPosition = -1;
                invalidate();
                break;
            default:    // 其他动作 这里包括了 移动手指 保持按住状态 取消等
                if(chooseLetterPosition != c){ // 如果
                    if (c >= 0 && c < firstLetters.length){
                        if (listener != null) {
                            listener.onLetterChanged(firstLetters[c]);
                        }
                        chooseLetterPosition = c;
                        invalidate();
                    }
                }
                setBackgroundResource(R.drawable.sidebar_background);
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
