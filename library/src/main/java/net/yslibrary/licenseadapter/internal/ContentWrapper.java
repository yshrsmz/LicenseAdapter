package net.yslibrary.licenseadapter.internal;

import android.support.annotation.NonNull;
import net.yslibrary.licenseadapter.Library;

public class ContentWrapper implements Wrapper {

  private final Library entry;
  private boolean expanded;

  public ContentWrapper(Library entry) {
    this.entry = entry;
  }

  @NonNull
  @Override
  public ViewType type() {
    return ViewType.CONTENT;
  }

  @Override
  public Library entry() {
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
