package com.example.administrator.rxjavademo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {
   private Thread thread;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.btn).setOnClickListener(this);
         thread = Thread.currentThread();
    }

    public void sendEvent(){
        String[] strings = {"s","t","i","n","g"};
        Observable.from(strings)
                .map(new Func1<String, String>() {
                    @Override
                    public String call(String s) {
                        try {
                            Thread.sleep(10*1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        return s;
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
               .subscribe(new Action1<String>() {
                   @Override
                   public void call(String s) {
                       Toast.makeText(MainActivity.this, s, Toast.LENGTH_SHORT).show();
                   }
               });
    }

    @Override
    public void onClick(View v) {
        sendEvent();
    }
   
}
