package net.yslibrary.licenseadapter.internal;

import net.yslibrary.licenseadapter.License;
import net.yslibrary.licenseadapter.LicenseEntry;

public class HeaderWrapper implements Wrapper {

  private final LicenseEntry entry;
  private boolean expanded;

  public HeaderWrapper(LicenseEntry entry) {
    this.entry = entry;
  }

  public int getItemCount() {
    return expanded ? 2 : 1;
  }

  public void open() {
    this.expanded = true;
  }

  public void close() {
    this.expanded = false;
  }

  @Override
  public boolean isExpanded() {
    return expanded;
  }

  @Override
  public void setExpanded(boolean expand) {
    this.expanded = expand;
  }

  public boolean hasContent() {
    return entry.hasContent();
  }

  @Override
  public LicenseEntry entry() {
    return entry;
  }

  public License license() {
    return entry.license();
  }

  @Override
  public ViewType type() {
    return ViewType.HEADER;
  }
}
