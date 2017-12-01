package net.yslibrary.licenseadapter;

import android.support.annotation.NonNull;
import android.support.annotation.RestrictTo;
import android.support.annotation.WorkerThread;
import android.text.TextUtils;
import android.util.Log;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.concurrent.TimeUnit;

/**
 * A library which handles loading and caching of licenses.
 */
public abstract class BaseLibrary implements Library {
  private static final String TAG = "LicenseAdapter";

  private static final String CACHE_FILE_NAME = "license.txt";
  private static final int CACHE_TIMEOUT_DAYS = 14;

  private final String name;
  private final String author;

  private License license;

  /**
   * Initializes this abstract library with an immutable library name and author and mutable
   * license.
   * <p>
   * The license text may be loaded or not. If it isn't loaded, an attempt to load it will be made
   * if necessary through {@link #doLoad()}.
   *
   * @param name    the library's name
   * @param author  the library's author
   * @param license the library's license
   * @see License
   */
  protected BaseLibrary(@NonNull String name, @NonNull String author, @NonNull License license) {
    this.name = name;
    this.author = author;
    this.license = license;
  }

  @RestrictTo(RestrictTo.Scope.LIBRARY_GROUP)
  protected static String read(@NonNull BufferedReader in) throws IOException {
    StringBuilder builder = new StringBuilder();
    String line;
    while ((line = in.readLine()) != null) {
      builder.append(line).append("\n");
    }
    return builder.toString();
  }

  private static void cacheLicense(@NonNull File cache, @NonNull License license) {
    if (TextUtils.isEmpty(license.getText())) return;

    FileOutputStream stream = null;
    try {
      File dir = cache.getParentFile();
      if (!dir.exists() && !dir.mkdirs() || !cache.exists() && !cache.createNewFile()) {
        Log.d(TAG, "Failed to create cache file: " + cache);
        return;
      }

      stream = new FileOutputStream(cache);
      stream.write((license.getUrl() + "\n" + license.getText()).getBytes());
    } catch (IOException e) {
      Log.d(TAG, "Couldn't cache license: " + cache, e);
    } finally {
      try {
        if (stream != null) {
          stream.close();
        }
      } catch (IOException e) {
        Log.e(TAG, "Couldn't close cache stream: " + cache, e);
      }
    }
  }

  private void loadLicenseFromCache(File cache) {
    FileInputStream stream = null;
    try {
      stream = new FileInputStream(cache);
      String encodedLicense = read(new BufferedReader(new InputStreamReader(stream)));

      if (TextUtils.isEmpty(encodedLicense)) {
        loadAndCache(cache);
      } else {
        int encodedSplit = encodedLicense.indexOf("\n");
        license = new License.Builder(license)
            .setText(encodedLicense.substring(encodedSplit + 1, encodedLicense.length()))
            .setUrl(encodedLicense.substring(0, encodedSplit))
            .build();
      }
    } catch (IOException e) {
      Log.d(TAG, "Couldn't open cache steam. Cleaning up and performing fresh load: " + cache, e);
      delete(cache);

      loadAndCache(cache);
    } finally {
      try {
        if (stream != null) {
          stream.close();
        }
      } catch (IOException e) {
        Log.e(TAG, "Couldn't close cache steam: " + cache, e);
      }
    }
  }

  private void delete(File cache) {
    if (!cache.delete()) {
      Log.e(TAG, "Couldn't delete cache file: " + cache + ". Performing fresh load.");
    }
  }

  @WorkerThread
  @Override
  public final void load(@NonNull File cacheDir) {
    if (hasContent() && !isLoaded()) {
      File cache = new File(cacheDir, CACHE_FILE_NAME);
      if (cache.exists()) {
        if (TimeUnit.MILLISECONDS.toDays(System.currentTimeMillis() - cache.lastModified())
            >= CACHE_TIMEOUT_DAYS) {
          delete(cache);
          loadAndCache(cache);
        } else {
          loadLicenseFromCache(cache);
        }
      } else {
        loadAndCache(cache);
      }
    }
  }

  private void loadAndCache(File cache) {
    license = doLoad();
    cacheLicense(cache, license);
  }

  /**
   * Loads a fresh copy of the license.
   * <p>
   * In-memory and disk caching will be handled for you. Simply request the license's full legal
   * text from wherever it may be acquired, network or otherwise. If this library doesn't have
   * license text or it's short and already loaded in the original license, simply return the
   * existing license through {@link #getLicense()}. Otherwise, block the current thread until the
   * license is loaded.
   *
   * @return the fully loaded license
   * @see #load(File)
   * @see License
   */
  @WorkerThread
  @NonNull
  protected abstract License doLoad();

  @NonNull
  @Override
  public final String getName() {
    return name;
  }

  @NonNull
  @Override
  public final String getAuthor() {
    return author;
  }

  @NonNull
  @Override
  public License getLicense() {
    return license;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    BaseLibrary that = (BaseLibrary) o;

    return name.equals(that.name) && author.equals(that.author);
  }

  @Override
  public int hashCode() {
    int result = name.hashCode();
    result = 31 * result + author.hashCode();
    return result;
  }

  @Override
  public String toString() {
    return "BaseLibrary{" +
        "name='" + name + '\'' +
        ", author='" + author + '\'' +
        ", license=" + license +
        '}';
  }
}
