package com.getset.career.guidance;

import android.view.View;
import android.webkit.WebView;

import java.lang.reflect.Field;

public class MyOnFocusChangeListener implements View.OnFocusChangeListener {

    protected float mScale;

    public MyOnFocusChangeListener(float scale) {
        mScale = scale;
    }

    /**
     * Implement this method because we want to apply scale when focus changed.
     * @param v the View we want to apply scale when focus changed.
     * @param hasFocus has a focus or not.
     */
    public void onFocusChange(View v, boolean hasFocus) {
        if (hasFocus) {
            // We must try all cases because mDefaultScale will change on different versions of Android.
            try {
                Field defaultScale = WebView.class.getDeclaredField("mDefaultScale");
                defaultScale.setAccessible(true);
                defaultScale.setFloat(v, mScale);
            } catch (SecurityException e) {
                e.printStackTrace();
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (NoSuchFieldException e) {
                try {
                    Field zoomManager;
                    zoomManager = WebView.class.getDeclaredField("mZoomManager");
                    zoomManager.setAccessible(true);
                    Object zoomValue = zoomManager.get(v);
                    Field defaultScale = zoomManager.getType().getDeclaredField("mDefaultScale");
                    defaultScale.setAccessible(true);
                    defaultScale.setFloat(zoomValue, mScale);
                } catch (SecurityException e1) {
                    e1.printStackTrace();
                } catch (IllegalArgumentException e1) {
                    e.printStackTrace();
                } catch (IllegalAccessException e1) {
                    e.printStackTrace();
                } catch (NoSuchFieldException e1) {
                    try {
                        Field mProviderField = WebView.class.getDeclaredField("mProvider");
                        mProviderField.setAccessible(true);
                        Object webviewclassic = mProviderField.get(v);
                        Field zoomManager = webviewclassic.getClass().getDeclaredField("mZoomManager");
                        zoomManager.setAccessible(true);
                        Object zoomValue = zoomManager.get(webviewclassic);
                        Field defaultScale = zoomManager.getType().getDeclaredField("mDefaultScale");
                        defaultScale.setAccessible(true);
                        defaultScale.setFloat(zoomValue, mScale);
                    } catch (Exception e2) {
                        e2.printStackTrace();
                    }
                }
            }
        }
    }
}
