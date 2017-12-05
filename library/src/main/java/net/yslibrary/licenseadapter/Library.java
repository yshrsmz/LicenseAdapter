package net.yslibrary.licenseadapter;

import android.support.annotation.NonNull;
import android.support.annotation.WorkerThread;
import java.io.File;

public interface Library {
  @WorkerThread
  void load(@NonNull File cacheDir);

  boolean isLoaded();

  boolean hasContent();

  @NonNull
  String name();

  @NonNull
  String author();

  @NonNull
  License license();
}
