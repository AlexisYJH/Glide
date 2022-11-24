package com.example.glide.lifecycle;

/**
 * @author AlexisYin
 */
//生命周期的被观察者
public interface Lifecycle {
    void addListener(LifecycleListener listener);
    void removeListener(LifecycleListener listener);
}
