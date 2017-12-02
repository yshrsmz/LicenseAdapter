package net.yslibrary.licenseadapter.internal;

import android.support.annotation.NonNull;
import net.yslibrary.licenseadapter.BaseLibrary;
import net.yslibrary.licenseadapter.License;

/**
 * Library without license text
 */
public final class NoContentLibrary extends BaseLibrary {
  public NoContentLibrary(String name, String author, License license) {
    super(name, author, license);
  }

  @Override
  public boolean isLoaded() {
    return true;
  }

  @NonNull
  @Override
  public License doLoad() {
    // There's no content
    return license();
  }

  @Override
  public boolean hasContent() {
    return false;
  }
}
