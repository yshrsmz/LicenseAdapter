package net.yslibrary.licenseadapter.internal;

import net.yslibrary.licenseadapter.Library;

public interface Wrapper {
  ViewType type();

  Library entry();

  boolean isExpanded();

  void setExpanded(boolean expand);
}
