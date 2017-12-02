package net.yslibrary.licenseadapter;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

public final class License {
  @NonNull public final String name;
  @Nullable public final String url;
  @Nullable public final String text;

  private License(@NonNull String name, @Nullable String url, @Nullable String text) {
    this.name = name;
    this.url = url;
    this.text = text;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    License license = (License) o;

    return name.equals(license.name)
        && (url == null ? license.url == null : url.equals(license.url))
        && (text == null ? license.text == null : text.equals(license.text));
  }

  @Override
  public int hashCode() {
    int result = name.hashCode();
    result = 31 * result + (url == null ? 0 : url.hashCode());
    result = 31 * result + (text == null ? 0 : text.hashCode());
    return result;
  }

  @Override
  public String toString() {
    return "License{" +
        "name='" + name + '\'' +
        ", url='" + url + '\'' +
        ", text='" + text + '\''
        + '}';
  }

  public static final class Builder {
    private final String name;

    private String url;
    private String text;

    public Builder(@NonNull String name) {
      this.name = name;
    }

    public Builder(@NonNull License license) {
      name = license.name;
      url = license.url;
      text = license.text;
    }

    @NonNull
    public Builder setUrl(@Nullable String url) {
      this.url = url;
      return this;
    }

    @NonNull
    public Builder setText(@Nullable String text) {
      this.text = text;
      return this;
    }

    @NonNull
    public License build() {
      return new License(name, url, text);
    }
  }
}
