package com.example.sunny.bubble;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AbsListView;
import android.widget.GridLayout;

public class MainActivity extends AppCompatActivity {
    MyView[] view = new MyView[100];
    GridLayout layout;
    Bitmap nontouch,touch;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        layout = (GridLayout)findViewById(R.id.layout); // main layout
        layout.setColumnCount(7); // set number of a row's bubble
        Resources res = getResources();
        nontouch = BitmapFactory.decodeResource(res,R.drawable.nontouch); // read non touched buble bitmap
        touch = BitmapFactory.decodeResource(res,R.drawable.touch); // read touched buble bitmap
        //initialize screen of beginning
        for(int i=0; i<100; i++) {
            view[i] = new MyView(this); // define bubbles
            view[i].setLayoutParams(new AbsListView.LayoutParams(nontouch.getWidth(), nontouch.getHeight()));
            layout.addView(view[i]); // add bubble in main frame
        }
    }
    protected class MyView extends View {
        Resources res;
        Bitmap show;

        public MyView(Context context) {
            super(context);
            res = getResources();
            show = nontouch;
        }
        public void onDraw(Canvas canvas) {
            Paint pnt = new Paint();
            canvas.drawColor(Color.WHITE);
            canvas.drawBitmap(show,0,0,pnt);
        }
        @Override
        public boolean onTouchEvent(MotionEvent event) {
            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    show = touch; // if bubble touched, change bitmap
                    break;
            }
            invalidate(); // redisplay
            return true;
        }
    }
}
