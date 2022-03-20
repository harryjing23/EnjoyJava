package com.harry.myapplication.rxjava.thread;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.harry.myapplication.R;

import java.util.Objects;
import java.util.concurrent.TimeUnit;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableEmitter;
import io.reactivex.rxjava3.core.ObservableOnSubscribe;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.core.Scheduler;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.Function;
import io.reactivex.rxjava3.internal.disposables.EmptyDisposable;
import io.reactivex.rxjava3.internal.operators.observable.ObservableSubscribeOn;
import io.reactivex.rxjava3.plugins.RxJavaPlugins;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class ThreadActivity extends AppCompatActivity {
    private static final String TAG = "ThreadActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thread);
    }

    /**
     * 以Scheduler.io()为例来分析源码，其他Scheduler逻辑也类似
     */
    private void test() {
        Observable
                .create(new ObservableOnSubscribe<String>() {
                    @Override
                    public void subscribe(@NonNull ObservableEmitter<String> emitter) throws Throwable {

                    }
                })
                // 第二步。返回类型为ObservableSubscribeOn，将IoScheduler包装到ObservableSubscribeOn里
                .subscribeOn(
                        // 第一步。源码new了IoScheduler，经过多层包装，最终走到对线程池的管控
                        Schedulers.io())
                // 第三步。走Observable.subscribe()
                // 进而调用ObservableSubscribeOn.subscribeActual()等等，经过层层包装，最终用线程池执行了Runnable
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull String s) {

                    }

                    @Override
                    public void onError(@NonNull Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    // --------------------Schedulers.io()的hook相关----------------------

    // 下面是Schedulers.io()源码。非常熟悉的形式，也是全局hook
//    public static Scheduler io() {
//        return RxJavaPlugins.onIoScheduler(IO);
//    }

    // 下面是RxJavaPlugins.onIoScheduler()源码。由此可见，onIoHandler不为null时就有hook
//    public static Scheduler onIoScheduler(@NonNull Scheduler defaultScheduler) {
//        Function<? super Scheduler, ? extends Scheduler> f = onIoHandler;
//        if (f == null) {
//            return defaultScheduler;
//        }
//        return apply(f, defaultScheduler);
//    }

    // 下面是RxJavaPlugins.setIoSchedulerHandler()源码。这里对onIoHandler进行了赋值
//    public static void setIoSchedulerHandler(@Nullable Function<? super Scheduler, ? extends Scheduler> handler) {
//        if (lockdown) {
//            throw new IllegalStateException("Plugins can't be changed anymore");
//        }
//        onIoHandler = handler;
//    }

    // 设置IoScheduler的hook
    private void hookScheduler() {
        RxJavaPlugins.setIoSchedulerHandler(new Function<Scheduler, Scheduler>() {
            @Override
            public Scheduler apply(Scheduler scheduler) throws Throwable {
                // 记录了每次调用Schedulers.io()的日志
                Log.d(TAG, "apply: " + scheduler);
                return scheduler;
            }
        });
    }

    // --------------------Schedulers的IO--------------------------
    // 由Schedulers.io()源码可知，IO就是实际的Scheduler
    // 下面是Schedulers的静态代码块的源码。对Schedulers的各种策略进行初始化，RxJavaPlugins的init方法是hook用，实际起作用的是里面的new
    // 通过IOTask.get()->new IoScheduler->IoScheduler.start()->CachedWorkerPool就创建了线程池Executors.newScheduledThreadPool
//    static {
//        SINGLE = RxJavaPlugins.initSingleScheduler(new Schedulers.SingleTask());
//
//        COMPUTATION = RxJavaPlugins.initComputationScheduler(new Schedulers.ComputationTask());
//
//        IO = RxJavaPlugins.initIoScheduler(new Schedulers.IOTask());
//
//        TRAMPOLINE = TrampolineScheduler.instance();
//
//        NEW_THREAD = RxJavaPlugins.initNewThreadScheduler(new Schedulers.NewThreadTask());
//    }

    // --------------------subscribeOn()给上面代码切换线程---------------------

    // 下面是ObservableCreate.subscribeOn()源码。用ObservableSubscribeOn将IoScheduler包装了一层
//    public final Observable<T> subscribeOn(@NonNull Scheduler scheduler) {
//        Objects.requireNonNull(scheduler, "scheduler is null");
//        return RxJavaPlugins.onAssembly(new ObservableSubscribeOn<>(this, scheduler));
//    }

    // 下面是ObservableSubscribeOn.subscribeActual()源码。
//    public void subscribeActual(final Observer<? super T> observer) {
    // 将自定义Observer包装成SubscribeOnObserver
//        final ObservableSubscribeOn.SubscribeOnObserver<T> parent = new ObservableSubscribeOn.SubscribeOnObserver<>(observer);
//
//        observer.onSubscribe(parent);
//
    // scheduler就是IoScheduler。SubscribeTask是个Runnable任务，可以被IoScheduler的线程池执行
    // 里面去调用SubscribeOnObserver，进而调用自定义Observer。这行代码完成了用线程池去执行Runnable任务
//        parent.setDisposable(scheduler.scheduleDirect(new ObservableSubscribeOn.SubscribeTask(parent)));
//    }

    // 下面是SubscribeTask源码。
//    final class SubscribeTask implements Runnable {
//        private final ObservableSubscribeOn.SubscribeOnObserver<T> parent;
//
//        SubscribeTask(ObservableSubscribeOn.SubscribeOnObserver<T> parent) {
//            this.parent = parent;
//        }
//
//        @Override
//        public void run() {
    // source就是subscribeOn()中new ObservableSubscribeOn时传进来的ObservableCreate，所以里面的发射器也是在IO线程中执行的
//            source.subscribe(parent);
//        }
//    }

    // 下面是IoScheduler.scheduleDirect()源码。
//    public Disposable scheduleDirect(@NonNull Runnable run, long delay, @NonNull TimeUnit unit) {
    // createWork()在IoScheduler中new EventLoopWorker
//        final Worker w = createWorker();
//
//        final Runnable decoratedRun = RxJavaPlugins.onSchedule(run);
//
    // DisposeTask将Runnable包装了一层，使其可以用dispose中断
//        Scheduler.DisposeTask task = new Scheduler.DisposeTask(decoratedRun, w);
//
    // 调用EventLoopWorker.schedule()
//        w.schedule(task, delay, unit);
//
//        return task;
//    }

    // 下面是EventLoopWorker.schedule()源码。
//     public Disposable schedule(@NonNull Runnable action, long delayTime, @NonNull TimeUnit unit) {
//         if (tasks.isDisposed()) {
//             // don't schedule, we are unsubscribed
//             return EmptyDisposable.INSTANCE;
//         }
//
    // 这里面就将Runnable交给了threadWorker.scheduleActual()，里面就是线程池ScheduledExecutorService
//         return threadWorker.scheduleActual(action, delayTime, unit, tasks);
//     }


    // ----------------------observeOn()给下面代码切换线程

    private void test1() {
        Observable
                .create(new ObservableOnSubscribe<String>() {
                    @Override
                    public void subscribe(@NonNull ObservableEmitter<String> emitter) throws Throwable {

                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull String s) {

                    }

                    @Override
                    public void onError(@NonNull Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}