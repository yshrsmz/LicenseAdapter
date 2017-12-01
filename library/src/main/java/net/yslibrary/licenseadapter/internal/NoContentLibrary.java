package net.yslibrary.licenseadapter.internal;

import android.support.annotation.NonNull;
import android.text.TextUtils;
import net.yslibrary.licenseadapter.BaseLibrary;
import net.yslibrary.licenseadapter.License;

/**
 * Library without license text.
 */
public final class NoContentLibrary extends BaseLibrary {
  public NoContentLibrary(String name, String author, License license) {
    super(name, author, license);
    if (TextUtils.isEmpty(license.getUrl())) {
      throw new IllegalArgumentException("License url must not be null.");
    }
  }

  @Override
  public boolean isLoaded() {
    return true;
  }

  @NonNull
  @Override
  public License doLoad() {
    // There's no content
    return getLicense();
  }

  @Override
  public boolean hasContent() {
    return false;
  }
}
