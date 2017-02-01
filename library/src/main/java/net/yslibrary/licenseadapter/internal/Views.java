package net.yslibrary.licenseadapter.internal;

import android.support.annotation.IdRes;
import android.view.View;

class Views {
  @SuppressWarnings("unchecked")
  public static <T> T byId(final View view, @IdRes final int id) {
    return (T) view.findViewById(id);
  }
}
