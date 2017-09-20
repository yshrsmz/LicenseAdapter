package net.yslibrary.licenseadapter.internal;

import android.support.annotation.IdRes;
import android.view.View;

class Views {
  @SuppressWarnings("unchecked")
  public static <T extends View> T byId(final View view, @IdRes final int id) {
    return view.findViewById(id);
  }
}
