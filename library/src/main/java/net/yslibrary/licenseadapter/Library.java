package net.yslibrary.licenseadapter;

import androidx.annotation.NonNull;
import androidx.annotation.WorkerThread;

import java.io.File;

/**
 * An interface representing a library. Libraries have a name, author, and license. Furthermore,
 * their license may have content which can be loaded remotely.
 *
 * @see License
 */
public interface Library {
  /**
   * Fully load this library's content. If {@link #hasContent()} returns true, you should check to
   * see if the library is already {@link #isLoaded() loaded}. If not, load the license's full
   * legal text and other metadata here.
   * <p>
   * One you have fully loaded this library, it is recommended to cache it such that network calls
   * may be minimized. To do so, store any data necessary in the cache directory provided. If you
   * use this cache directory, check to see if you have the files needed and load the library from
   * them instead of the network if possible.
   * <p>
   * This method will be called on a background thread and should block until the library is fully
   * loaded.
   * <p>
   * Should an error occur loading the full library, you may throw an exception. The {@link
   * LicenseAdapter} will display a friendly error message and attempt to reload this library at a
   * later time.
   *
   * @param cacheDir the cache directory in which to store this loaded library
   */
  @WorkerThread
  void load(@NonNull File cacheDir);

  /**
   * Get whether or not this library has fully loaded. That is, if {@link #hasContent()} returns
   * true, is the license's full legal text loaded.
   *
   * @return true if this library is fully loaded, false otherwise
   */
  boolean isLoaded();

  /**
   * Get whether or not this library's license has content. That is, if the license has any text to
   * display. The license may still have a name and url, but no text if this method returns false.
   * If this method returns true, it's an indication that the license text must be loaded and
   * displayed.
   *
   * @return true if this library's license has content, false otherwise
   * @see #load(File)
   * @see #isLoaded()
   */
  boolean hasContent();

  /**
   * Get the name of this library.
   *
   * @return the library's name
   */
  @NonNull
  String getName();

  /**
   * Get the author of this library.
   *
   * @return the library's author
   */
  @NonNull
  String getAuthor();

  /**
   * Get this library's license.
   *
   * @return the library's license.
   * @see License
   */
  @NonNull
  License getLicense();
}
