package com.example.glide.lifecycle;

import androidx.fragment.app.Fragment;

/**
 * @author AlexisYin
 */
public class RequestManagerFragment extends Fragment {
    public static final String FRAGMENT_TAG = "blank_fragment";
    ActivityFragmentLifecycle activityFragmentLifecycle;

    public RequestManagerFragment() {
        super();
        activityFragmentLifecycle = new ActivityFragmentLifecycle();
    }

    public ActivityFragmentLifecycle getActivityFragmentLifecycle() {
        return activityFragmentLifecycle;
    }

    @Override
    public void onStart() {
        super.onStart();
        activityFragmentLifecycle.onStart();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        activityFragmentLifecycle.onDestroy();
    }

}
