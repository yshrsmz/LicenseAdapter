package net.yslibrary.licenseadapter.internal;

import android.app.Application;
import android.os.Handler;
import android.os.Looper;
import android.util.LruCache;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.AndroidViewModel;
import net.yslibrary.licenseadapter.Library;
import net.yslibrary.licenseadapter.License;

import java.io.File;
import java.lang.ref.WeakReference;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public final class LibrariesHolder extends AndroidViewModel {
  public interface Listener {
    void onComplete(@NonNull License license, @Nullable Exception e);
  }

  private static final Executor EXECUTOR = Executors.newSingleThreadExecutor();
  private static final String CACHE_DIR_NAME = "license-adapter-cache";

  private final LruCache<Library, License> cachedLicenses = new LruCache<>(25);

  public LibrariesHolder(@NonNull Application application) {
    super(application);
  }

  public void load(final Library library, Listener rawListener) {
    if (library.isLoaded()) rawListener.onComplete(library.getLicense(), null);

    final WeakReference<Listener> listener = new WeakReference<>(rawListener);
    License cache = cachedLicenses.get(library);
    if (cache == null) {
      EXECUTOR.execute(new Runnable() {
        @Override
        public void run() {
          try {
            library.load(new File(getApplication().getCacheDir(), CACHE_DIR_NAME + File.separator +
                library.getAuthor() + File.separator + library.getName()));

            License license = library.getLicense();
            cachedLicenses.put(library, license);
            notify(license, null);
          } catch (Exception e) {
            notify(library.getLicense(), e);
          }
        }

        private void notify(@NonNull final License license, @Nullable final Exception e) {
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
