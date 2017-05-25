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
        int singleHeight = height/firstLetters.length; // 每个字符高度

        for(int i = 0;i<firstLetters.length; i++){
            paint.setColor(Color.rgb(33, 65, 98));
            paint.setTypeface(Typeface.DEFAULT_BOLD);
            paint.setAntiAlias(true); // 抗锯齿
            paint.setTextSize(20);

            if(i == chooseLetterPosition){ // 选中的状态
                paint.setColor(Color.parseColor("#3399ff"));
                paint.setFakeBoldText(true);
            }
            // x坐标等于中间-字符串宽度的一半. paint.measureText() 获取宽度
            float xPos = width /2 - paint.measureText(firstLetters[i]) /2 ;
            float yPos = singleHeight * i + singleHeight;
            canvas.drawText(firstLetters[i], xPos, yPos, paint);
            paint.reset(); // 重置画笔
        }
    }

    /*
    1）public boolean dispatchTouchEvent(MotionEvent ev)  这个方法用来分发TouchEvent

    2）public boolean onInterceptTouchEvent(MotionEvent ev) 这个方法用来拦截TouchEvent

    3）public boolean onTouchEvent(MotionEvent ev) 这个方法用来处理TouchEvent

    当TouchEvent发生时，首先Activity将TouchEvent传递给最顶层的View， TouchEvent最先到达最顶层 view 的 dispatchTouchEvent ，
    然后由  dispatchTouchEvent 方法进行分发，如果dispatchTouchEvent返回true ，则交给这个view的onTouchEvent处理，
    如果dispatchTouchEvent返回 false ，则交给这个 view 的 interceptTouchEvent 方法来决定是否要拦截这个事件，
    如果 interceptTouchEvent 返回 true ，也就是拦截掉了，则交给它的 onTouchEvent 来处理，如果 interceptTouchEvent 返回 false ，
    那么就传递给子 view ，由子 view 的 dispatchTouchEvent 再来开始这个事件的分发。如果事件传递到某一层的子 view 的 onTouchEvent 上了，
    这个方法返回了 false ，那么这个事件会从这个 view 往上传递，都是 onTouchEvent 来接收。而如果传递到最上面的 onTouchEvent 也返回 false 的话，
    这个事件就会“消失”，而且接收不到下一次事件。
     */

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
