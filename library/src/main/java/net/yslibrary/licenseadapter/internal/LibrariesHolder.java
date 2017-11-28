package net.yslibrary.licenseadapter.internal;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.support.annotation.NonNull;
import android.util.LruCache;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.Tasks;
import java.io.File;
import java.util.concurrent.Callable;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import net.yslibrary.licenseadapter.Library;
import net.yslibrary.licenseadapter.License;

public class LibrariesHolder extends AndroidViewModel {
  private static final Executor EXECUTOR = Executors.newSingleThreadExecutor();
  private static final String CACHE_DIR_NAME = "license-adapter-cache";

  private final LruCache<Library, License> cachedLicenses = new LruCache<>(25);

  public LibrariesHolder(@NonNull Application application) {
    super(application);
  }

  @NonNull
  public Task<License> load(final Library library) {
    if (library.isLoaded()) return Tasks.forResult(library.license());

    License cache = cachedLicenses.get(library);
    if (cache == null) {
      return Tasks.call(EXECUTOR, new Callable<License>() {
        @Override
        public License call() {
          library.load(new File(getApplication().getCacheDir(), CACHE_DIR_NAME));

          License license = library.license();
          cachedLicenses.put(library, license);
          return license;
        }
      });
    } else {
      return Tasks.forResult(cache);
    }
  }
}
