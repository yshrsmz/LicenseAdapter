package net.yslibrary.licenseadapter.internal;

import android.content.Context;
import android.content.res.Resources;
import android.util.TypedValue;
import androidx.annotation.AttrRes;

class Utils {

  static int getIntValueFromAttribute(Context context, @AttrRes int color) {
    TypedValue tv = new TypedValue();
    Resources.Theme theme = context.getTheme();
    theme.resolveAttribute(color, tv, true);
    return tv.data;
  }
}
