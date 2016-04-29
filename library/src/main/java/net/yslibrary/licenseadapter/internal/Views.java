package net.yslibrary.licenseadapter.internal;

import android.os.Handler;
import android.support.annotation.IdRes;
import android.view.View;

/**
 * Created by yshrsmz on 2016/04/16.
 */
public class Views {

  @SuppressWarnings("unchecked")
  public static <T> T byId(final View view, @IdRes final int id) {
    return (T) view.findViewById(id);
  }

  public static void disableClick(final View view, final int delay) {
    view.setEnabled(false);
    new Handler().postDelayed(new Runnable() {
      @Override
      public void run() {
        view.setEnabled(true);
      }
    }, delay);
  }
}
