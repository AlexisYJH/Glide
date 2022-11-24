package com.example.glide.lifecycle;

import java.util.ArrayList;
import java.util.List;

/**
 * @author AlexisYin
 */
public class ActivityFragmentLifecycle implements Lifecycle{
    private List<LifecycleListener> lifecycleListeners = new ArrayList<>();

    @Override
    public void addListener(LifecycleListener listener) {
        lifecycleListeners.add(listener);
    }

    @Override
    public void removeListener(LifecycleListener listener) {
        lifecycleListeners.remove(listener);
    }

    public void onStart() {
        for (LifecycleListener listener: lifecycleListeners) {
            listener.onStart();
        }
    }

    public void onDestroy() {
        for (LifecycleListener listener: lifecycleListeners) {
            listener.onDestroy();
        }
    }


}
