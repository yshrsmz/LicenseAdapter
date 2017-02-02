package net.yslibrary.licenseadapter;

import android.os.Parcel;
import android.os.Parcelable;

public class License implements Parcelable {
  public static final Creator<License> CREATOR = new Creator<License>() {
    public License createFromParcel(Parcel source) {
      return new License(source);
    }

    public License[] newArray(int size) {
      return new License[size];
    }
  };
  public String name;
  public String url;
  public String text;

  protected License(String name, String url, String text) {
    this.name = name;
    this.url = url;
    this.text = text;
  }

  protected License(Parcel in) {
    name = in.readString();
    url = in.readString();
    text = in.readString();
  }

  @Override
  public int describeContents() {
    return 0;
  }

  @Override
  public void writeToParcel(Parcel dest, int flags) {
    dest.writeString(name);
    dest.writeString(url);
    dest.writeString(text);
  }

  public boolean isLoaded() {
    return text != null;
  }

  @Override
  public String toString() {
    return String.format("\n***** LICENSE *****\nName = %s\nUrl = %s\nText = %s\n", name, url,
        text);
  }

  public static class Builder {
    private String name;
    private String url;
    private String text;

    public Builder setName(String name) {
      this.name = name;
      return this;
    }

    public Builder setUrl(String url) {
      this.url = url;
      return this;
    }

    public Builder setText(String text) {
      this.text = text;
      return this;
    }

    public License build() {
      return new License(name, url, text);
    }
  }
}
