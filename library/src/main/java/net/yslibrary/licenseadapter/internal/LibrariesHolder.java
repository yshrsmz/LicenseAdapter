package net.yslibrary.licenseadapter.internal;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.LruCache;
import java.io.File;
import java.lang.ref.WeakReference;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import net.yslibrary.licenseadapter.Library;
import net.yslibrary.licenseadapter.License;

public final class LibrariesHolder extends AndroidViewModel {
  public interface Listener {
    void onComplete(@Nullable License license, @Nullable Exception e);
  }

  private static final Executor EXECUTOR = Executors.newSingleThreadExecutor();
  private static final String CACHE_DIR_NAME = "license-adapter-cache";

  private final LruCache<Library, License> cachedLicenses = new LruCache<>(25);

  public LibrariesHolder(@NonNull Application application) {
    super(application);
  }

  public void load(final Library library, final Listener rawListener) {
    if (library.isLoaded()) rawListener.onComplete(library.license(), null);

    final WeakReference<Listener> listener = new WeakReference<>(rawListener);
    License cache = cachedLicenses.get(library);
    if (cache == null) {
      EXECUTOR.execute(new Runnable() {
        @Override
        public void run() {
          try {
            library.load(new File(getApplication().getCacheDir(), CACHE_DIR_NAME + File.separator +
                library.author() + File.separator + library.name()));

            License license = library.license();
            cachedLicenses.put(library, license);
            notify(license, null);
          } catch (Exception e) {
            notify(null, e);
          }
        }

        private void notify(@Nullable final License license, @Nullable final Exception e) {
          new Handler(Looper.getMainLooper()).post(new Runnable() {
            @Override
            public void run() {
              Listener rawListener = listener.get();
              if (rawListener != null) {
                rawListener.onComplete(license, e);
              }
            }
          });
        }
      });
    } else {
      rawListener.onComplete(cache, null);
    }
  }
}
