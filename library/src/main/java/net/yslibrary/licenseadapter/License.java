package net.yslibrary.licenseadapter;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

/**
 * An immutable model object representing a library's license.
 *
 * @see Library
 */
public final class License {
  private final String name;
  private final String text;
  private final String url;

  private License(@NonNull String name, @Nullable String text, @Nullable String url) {
    this.name = name;
    this.url = url;
    this.text = text;
  }

  /**
   * @return the license name
   */
  @NonNull
  public String getName() {
    return name;
  }

  /**
   * @return the license's full legal text
   */
  @Nullable
  public String getText() {
    return text;
  }

  /**
   * @return the license's url, either on the creator's website or the copy in a library
   */
  @Nullable
  public String getUrl() {
    return url;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    License license = (License) o;

    return name.equals(license.name)
        && (text == null ? license.text == null : text.equals(license.text))
        && (url == null ? license.url == null : url.equals(license.url));
  }

  @Override
  public int hashCode() {
    int result = name.hashCode();
    result = 31 * result + (text == null ? 0 : text.hashCode());
    result = 31 * result + (url == null ? 0 : url.hashCode());
    return result;
  }

  @Override
  public String toString() {
    return "License{" +
        "name='" + name + '\'' +
        ", text='" + text + '\'' +
        ", url='" + url + '\''
        + '}';
  }

  /**
   * The builder used to construct new license objects.
   *
   * @see License
   */
  public static final class Builder {
    private final String name;

    private String text;
    private String url;

    /**
     * Constructs a new builder with the license name.
     *
     * @param name the license name
     */
    public Builder(@NonNull String name) {
      this.name = name;
    }

    /**
     * Constructs a new builder which copies an existing license's fields.
     *
     * @param license the license to copy
     */
    public Builder(@NonNull License license) {
      name = license.getName();
      text = license.getText();
      url = license.getUrl();
    }

    /**
     * Set the license's text. It should be the full legal text, not a shortened file header.
     *
     * @param text the license text
     * @return the builder for chaining purposes
     */
    @NonNull
    public Builder setText(@Nullable String text) {
      this.text = text;
      return this;
    }

    /**
     * Set the license's url. It can be the original license on the license creator's website or a
     * copy of the license in the library using it.
     *
     * @param url the license's url
     * @return the builder for chaining purposes
     */
    @NonNull
    public Builder setUrl(@Nullable String url) {
      this.url = url;
      return this;
    }

    /**
     * @return the complete license
     * @see License
     */
    @NonNull
    public License build() {
      return new License(name, text, url);
    }
  }
}
