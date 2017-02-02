package net.yslibrary.licenseadapter;

import android.os.Parcelable;

public interface LicenseEntry extends Parcelable {
  void load();

  boolean isLoaded();

  boolean hasContent();

  String name();

  String version();

  String author();

  @Deprecated
  String link();

  String url();

  License license();
}
