package net.yslibrary.licenseadapter;

import android.support.annotation.NonNull;

/**
 * Library who's source code is available online.
 *
 * @see Library
 */
public interface OpenSourceLibrary extends Library {
  /**
   * @return a link to the source code or home page of the library. Example:
   * "https://github.com/yshrsmz/LicenseAdapter"
   */
  @NonNull
  String getSourceUrl();
}
