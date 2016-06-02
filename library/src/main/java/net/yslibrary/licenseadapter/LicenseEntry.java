package net.yslibrary.licenseadapter;

import android.os.Parcelable;

/**
 * Created by yshrsmz on 2016/04/18.
 */
public interface LicenseEntry extends Parcelable {

  void load();

  boolean isLoaded();

  boolean hasContent();

  String name();

  String version();

  String author();

  String link();

  License license();
}
