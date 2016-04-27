package net.yslibrary.licenseadapter.internal;

import net.yslibrary.licenseadapter.LicenseEntry;

/**
 * Created by yshrsmz on 2016/04/26.
 */
public class ContentWrapper implements Wrapper {

  private final LicenseEntry entry;
  private boolean expanded;

  public ContentWrapper(LicenseEntry entry) {
    this.entry = entry;
  }

  @Override
  public ViewType type() {
    return ViewType.CONTENT;
  }

  @Override
  public LicenseEntry entry() {
    return entry;
  }

  @Override
  public boolean isExpanded() {
    return expanded;
  }

  @Override
  public void setExpanded(boolean expand) {
    this.expanded = expand;
  }
}
