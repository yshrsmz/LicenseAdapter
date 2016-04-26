package net.yslibrary.licenseadapter.internal;

/**
 * Created by yshrsmz on 2016/04/26.
 */
public enum ViewType {
  HEADER {
    @Override
    public HeaderWrapper convert(Wrapper item) {
      return (HeaderWrapper) item;
    }
  },
  CONTENT {
    @Override
    public ContentWrapper convert(Wrapper item) {
      return (ContentWrapper) item;
    }
  };

  public abstract Wrapper convert(Wrapper item);
}
