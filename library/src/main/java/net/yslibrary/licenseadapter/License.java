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

  public License(String name, String url) {
    this.name = name;
    this.url = url;
    text = null;
  }

  protected License(Parcel in) {
    this.name = in.readString();
    this.url = in.readString();
    this.text = in.readString();
  }

  @Override
  public int describeContents() {
    return 0;
  }

  @Override
  public void writeToParcel(Parcel dest, int flags) {
    dest.writeString(this.name);
    dest.writeString(this.url);
    dest.writeString(this.text);
  }

  public boolean isLoaded() {
    return text != null;
  }

  @Override
  public String toString() {
    return String.format("\n***** LICENSE *****\nName = %s\nUrl = %s\nText = %s\n", name, url,
        text);
  }
}
