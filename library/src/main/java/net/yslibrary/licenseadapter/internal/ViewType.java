package net.yslibrary.licenseadapter.internal;

import android.support.annotation.NonNull;

public enum ViewType {
  HEADER {
    @NonNull
    @Override
    public HeaderWrapper convert(@NonNull Wrapper item) {
      return (HeaderWrapper) item;
    }
  },
  CONTENT {
    @NonNull
    @Override
    public ContentWrapper convert(@NonNull Wrapper item) {
      return (ContentWrapper) item;
    }
  };

  @NonNull
  public abstract Wrapper convert(Wrapper item);
}
