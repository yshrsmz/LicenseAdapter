package net.yslibrary.licenseadapter.internal;

import android.support.annotation.NonNull;
import net.yslibrary.licenseadapter.Library;
import net.yslibrary.licenseadapter.License;

public class HeaderWrapper implements Wrapper {

  private final Library entry;
  private boolean expanded;

  public HeaderWrapper(Library entry) {
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
  public Library entry() {
    return entry;
  }

  public License license() {
    return entry.license();
  }

  @NonNull
  @Override
  public ViewType type() {
    return ViewType.HEADER;
  }
}
