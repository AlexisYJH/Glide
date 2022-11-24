package com.example.glide;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

import com.example.glide.lifecycle.LifecycleListener;
import com.example.glide.lifecycle.RequestManagerFragment;
import com.example.glide.options.GlideApp;


public class MainActivity extends AppCompatActivity {
    private static final String URL = "https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fc-ssl.duitang.com%2Fuploads%2Fitem%2F201910%2F12%2F20191012161320_EArHW.thumb.400_0.jpeg&refer=http%3A%2F%2Fc-ssl.duitang.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=auto?sec=1671788142&t=53f1606e6259933c9b8900cb7ff16c5c";
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageView = findViewById(R.id.image);

        /*Glide.with(this)
                .load(URL)
                .into(imageView);*/

        /*DrawableCrossFadeFactory factory = new DrawableCrossFadeFactory.Builder()
                .setCrossFadeEnabled(true)
                .build();*/

        /*GlideApp.with(this)
                .asBitmap()
                //.asDrawable()
                //.asFile()
                .load(URL)
                .applyAvatar(144*3)
                //.transition(DrawableTransitionOptions.with(factory))
                //.transition(DrawableTransitionOptions.withCrossFade())
                .listener(new RequestListener<Bitmap>() {
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Bitmap> target, boolean isFirstResource) {
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(Bitmap resource, Object model, Target<Bitmap> target, DataSource dataSource, boolean isFirstResource) {
                        Log.d("TAG", "onResourceReady: " + resource.getWidth());
                        return false;
                    }
                })
                .into(imageView);*/

        /*FutureTarget<Drawable> target = GlideApp.with(this).asDrawable().load(URL).applyAvatar(144*3).submit();
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    //用于拿到资源，对资源做特殊处理
                    Drawable drawable = target.get();
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            imageView.setImageDrawable(drawable);
                        }
                    });
                } catch (ExecutionException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();*/

        GlideApp.with(this)
                .fade()
                .load(URL)
                .applyAvatar(144*3)
                .into(imageView);


        /*
        Glide生命周期管理：创建一个无视图的Fragment，添加到当前的Activity，用来实现生命周期的绑定
        RequestManagerFragment中有一个生命周期的感知者ActivityFragmentLifecycle
        ActivityFragmentLifecycle存放了一组生命周期监听者LifecycleListener
        RequestManager实现了LifecycleListener，实现了Request和Activity生命周期的关联
        */
        RequestManagerFragment fragment = new RequestManagerFragment();
        FragmentManager fm = getSupportFragmentManager();
        fm.beginTransaction().add(fragment, RequestManagerFragment.FRAGMENT_TAG).commitAllowingStateLoss();
        fragment.getActivityFragmentLifecycle().addListener(new LifecycleListener() {
            @Override
            public void onStart() {
                Log.d("TAG", "MyLifecycleFragment onStart");
            }

            @Override
            public void onDestroy() {
                Log.d("TAG", "MyLifecycleFragment onDestroy");
            }
        });

    }
}