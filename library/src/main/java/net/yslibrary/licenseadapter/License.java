package net.yslibrary.licenseadapter;

import android.os.Parcel;
import android.os.Parcelable;

public class License implements Parcelable {
  public String mName;
  public String mUrl;
  public String mText;

  @Override
  public int describeContents() {
    return 0;
  }

  @Override
  public void writeToParcel(Parcel dest, int flags) {
    dest.writeString(this.mName);
    dest.writeString(this.mUrl);
    dest.writeString(this.mText);
  }

  public License(String name, String url) {
    mName = name;
    mUrl = url;
    mText = null;
  }

  protected License(Parcel in) {
    this.mName = in.readString();
    this.mUrl = in.readString();
    this.mText = in.readString();
  }

  public boolean isLoaded() {
    return mText != null;
  }

  public static final Creator<License> CREATOR = new Creator<License>() {
    public License createFromParcel(Parcel source) {
      return new License(source);
    }

    public License[] newArray(int size) {
      return new License[size];
    }
  };

  @Override
  public String toString() {
    return String.format("\n***** LICENSE *****\nName = %s\nUrl = %s\nText = %s\n", mName, mUrl,
        mText);
  }
}
