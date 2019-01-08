package net.yslibrary.licenseadapter.internal;

import android.text.TextUtils;
import androidx.annotation.NonNull;
import net.yslibrary.licenseadapter.BaseLibrary;
import net.yslibrary.licenseadapter.License;
import net.yslibrary.licenseadapter.OpenSourceLibrary;

/**
 * Library without license text.
 */
public final class NoContentLibrary extends BaseLibrary implements OpenSourceLibrary {
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

  @NonNull
  @Override
  public String getSourceUrl() {
    //noinspection ConstantConditions checked in constuctor
    return getLicense().getUrl();
  }
}
