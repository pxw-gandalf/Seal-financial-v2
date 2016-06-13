package com.yalantis.phoenix.refresh_view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;

import com.yalantis.phoenix.R;

/**
 * ClassName:  <br/>
 * Date: 2016/6/13 14:48 <br/>
 *
 * @author: peixinwen@knet.cn
 * @version:
 * @update:
 * @since 1.0
 */
public class Drawing extends View implements Runnable {

    Bitmap[] bitmap = new Bitmap[9];
    //创建一个画笔
    Paint paint = new Paint();
    //保存图片切换的变量
    int temp = 0;
    //创建线程
    Thread t = new Thread(this);

    public Drawing(Context context) {
        super(context);
        bitmap[0] = BitmapFactory.decodeResource(this.getResources(), R.drawable.a0001);
        bitmap[1] = BitmapFactory.decodeResource(this.getResources(), R.drawable.a0003);
        bitmap[2] = BitmapFactory.decodeResource(this.getResources(), R.drawable.a0005);
        bitmap[3] = BitmapFactory.decodeResource(this.getResources(), R.drawable.a0007);
        bitmap[4] = BitmapFactory.decodeResource(this.getResources(), R.drawable.a0009);
        bitmap[5] = BitmapFactory.decodeResource(this.getResources(), R.drawable.a0011);
        bitmap[6] = BitmapFactory.decodeResource(this.getResources(), R.drawable.a0013);
        bitmap[7] = BitmapFactory.decodeResource(this.getResources(), R.drawable.a0015);
        bitmap[8] = BitmapFactory.decodeResource(this.getResources(), R.drawable.a0017);
        bitmap[9] = BitmapFactory.decodeResource(this.getResources(), R.drawable.a0019);
        /*bitmap[2] = BitmapFactory.decodeResource(this.getResources(),R.drawable.a0005);
        bitmap[2] = BitmapFactory.decodeResource(this.getResources(),R.drawable.a0005);
        bitmap[2] = BitmapFactory.decodeResource(this.getResources(),R.drawable.a0005);
        bitmap[2] = BitmapFactory.decodeResource(this.getResources(),R.drawable.a0005);
        bitmap[2] = BitmapFactory.decodeResource(this.getResources(),R.drawable.a0005);
        bitmap[2] = BitmapFactory.decodeResource(this.getResources(),R.drawable.a0005);
        bitmap[2] = BitmapFactory.decodeResource(this.getResources(),R.drawable.a0005);
        bitmap[2] = BitmapFactory.decodeResource(this.getResources(),R.drawable.a0005);*/
        //开始线程
        t.start();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //设置画布颜色
        canvas.drawColor(Color.GRAY);
        //绘制图片
        canvas.drawBitmap(bitmap[temp], 50, 20, paint);
    }

    @Override
    public void run() {
        while (true) {
            temp++;
            if (temp == 5) {
                temp = 0;
            }
            //刷新屏幕，间隔70毫秒
            this.postInvalidate();
            try {
                Thread.sleep(70);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
