package com.harry.myapplication.rxjava;

import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.harry.myapplication.R;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableSource;
import io.reactivex.rxjava3.core.ObservableTransformer;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.Function;
import io.reactivex.rxjava3.schedulers.Schedulers;

/**
 * Rxjava是用响应式编程思想（"从起点到终点"的链式编程，每个事件都可以对数据进行处理再传给下一个事件）来解决一连串的先后顺序事件。例如网络请求
 */
public class DownloadActivity extends AppCompatActivity {

    private ProgressDialog mProgressDialog;
    public static final String PATH = "https://t7.baidu.com/it/u=1595072465,3644073269&fm=193&f=GIF";
    private ImageView mImageView;
    private Disposable mDisposable;

    /**
     * 普通写法的网络请求。每个人的实现方式都可能不同，不易管理
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_download);

        mImageView = (ImageView) findViewById(R.id.iv_show);
    }

    public void download(View view) {
        mProgressDialog = new ProgressDialog(this);
        mProgressDialog.setTitle("下载中。。。");
        mProgressDialog.show();

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    URL url = new URL(PATH);
                    // 是GET请求，所以直接连接即可
                    HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                    httpURLConnection.setConnectTimeout(5000);
                    int responseCode = httpURLConnection.getResponseCode();
                    if (responseCode == HttpURLConnection.HTTP_OK) {
                        InputStream inputStream = httpURLConnection.getInputStream();
                        Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                        // 切换到主线程
                        Message message = mHandler.obtainMessage();
                        message.obj = bitmap;
                        mHandler.sendMessage(message);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(@NonNull Message msg) {
            Bitmap bitmap = (Bitmap) msg.obj;
            mImageView.setImageBitmap(bitmap);
            mProgressDialog.dismiss();
        }
    };


    /**
     * RxJava是从Observable作为起点，流向Observer作为终点
     */

    public void rxDownload(View view) {
        // 起点
        Observable
                // 分发。### 第二步
                .just(PATH)
                // map转换。是将一种数据类型转换成另一种数据类型。### 第三步
                .map(
                        // 第一个泛型是上层事件的数据类型，第二个泛型是转换成的数据类型
                        new Function<String, Bitmap>() {
                            // 参数是上一个事件传下来的类型，返回值是传给下一个事件的类型
                            @Override
                            public Bitmap apply(String s) throws Throwable {
                                URL url = new URL(PATH);
                                // 是GET请求，所以直接连接即可
                                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                                httpURLConnection.setConnectTimeout(5000);
                                int responseCode = httpURLConnection.getResponseCode();
                                if (responseCode == HttpURLConnection.HTTP_OK) {
                                    InputStream inputStream = httpURLConnection.getInputStream();
                                    Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                                    return bitmap;
                                }
                                return null;
                            }
                        })
                // ### 第四步
                .map(new Function<Bitmap, Bitmap>() {
                    @Override
                    public Bitmap apply(Bitmap bitmap) throws Throwable {
                        // ...给Bitmap加水印的代码
                        return bitmap;
                    }
                })
                // 为上面的所有事件分配为IO线程
                .subscribeOn(Schedulers.io())
                // 为下面的所有事件分配为主线程
                .observeOn(AndroidSchedulers.mainThread())

                // 也可以用compose代替上面两个线程切换
//                .compose(rxud())

                // 订阅。使终点和起点关联起来
                .subscribe(
                        // 终点Observer（也可以用简化版的Consumer）。泛型的类型是上一层事件的参数类型
                        new Observer<Bitmap>() {
                            // 订阅开始。代码在哪个线程，就执行在哪，在subscribeOn切换线程的前面 ### 第一步
                            @Override
                            public void onSubscribe(@io.reactivex.rxjava3.annotations.NonNull Disposable d) {

                                mProgressDialog = new ProgressDialog(DownloadActivity.this);
                                mProgressDialog.setTitle("downloading");
                                mProgressDialog.show();

                                // Disposable在Activity的onDestroy时要dispose
                                // 因为事件在分发时会先看isDispose()，Activity退出时所有事件还没执行完，可能内存泄漏
                                mDisposable = d;
                            }

                            // 拿到事件。参数是Observer的泛型。### 第五步
                            @Override
                            public void onNext(@io.reactivex.rxjava3.annotations.NonNull Bitmap s) {
                                mImageView.setImageBitmap(s);
                            }

                            // 错误事件
                            @Override
                            public void onError(@io.reactivex.rxjava3.annotations.NonNull Throwable e) {

                            }

                            // 完成事件。### 第六步
                            @Override
                            public void onComplete() {
                                if (mProgressDialog != null) {
                                    mProgressDialog.dismiss();
                                }
                            }
                        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // 调用dispose()
        if (mDisposable != null && !mDisposable.isDisposed()) {
            mDisposable.dispose();
        }
    }

    public final static <UD> ObservableTransformer<UD, UD> rxud() {
        // ObservableTransformer是将一个Observable添加额外操作然后转换成另一个Observable。即upstream转成downstream
        return new ObservableTransformer<UD, UD>() {
            @Override
            public ObservableSource<UD> apply(Observable<UD> upstream) {
                return upstream
                        // 为上面的所有事件分配为IO线程
                        .subscribeOn(Schedulers.io())
                        // 为下面的所有事件分配为主线程
                        .observeOn(AndroidSchedulers.mainThread())

                        // 甚至还可以继续追加事件
                        .map(new Function<UD, UD>() {
                            @Override
                            public UD apply(UD ud) throws Throwable {
                                // ...处理数据的操作
                                return ud;
                            }
                        });
            }
        };
    }
}