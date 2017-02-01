package net.yslibrary.licenseadapter;

import android.os.Parcelable;

public interface LicenseEntry extends Parcelable {
  void load();

  boolean hasContent();

  String name();

  String version();

  String author();

  License license();
}
