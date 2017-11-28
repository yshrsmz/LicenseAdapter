package net.yslibrary.licenseadapter.internal;

import net.yslibrary.licenseadapter.BaseLibrary;
import net.yslibrary.licenseadapter.License;

/**
 * License without license text
 */
public final class NoContentLibrary extends BaseLibrary {
  public NoContentLibrary(String name, String author, License license) {
    super(name, author, license);
  }

  @Override
  public boolean isLoaded() {
    return true;
  }

  @Override
  public void doLoad() {
    // There's no content
  }

  @Override
  public boolean hasContent() {
    return false;
  }
}
