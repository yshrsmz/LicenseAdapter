package net.yslibrary.licenseadapter;

import android.support.annotation.NonNull;
import android.support.annotation.WorkerThread;

public interface Library {
  @WorkerThread
  void load();

  boolean hasContent();

  @NonNull
  String name();

  @NonNull
  String author();

  @NonNull
  License license();
}
