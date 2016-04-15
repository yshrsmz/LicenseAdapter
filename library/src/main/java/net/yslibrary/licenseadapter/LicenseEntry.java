package net.yslibrary.licenseadapter;

import android.os.Parcel;

public class LicenseEntry extends BaseLicenseEntry {

  public LicenseEntry(String name, String author, String url) {
    super(name, null, author, url, new License(null, url));
  }

  @Override
  public void doLoad() {
  }

  @Override
  public int describeContents() {
    return 0;
  }

  @Override
  public void writeToParcel(Parcel dest, int flags) {
    super.writeToParcel(dest, flags);
  }

  protected LicenseEntry(Parcel in) {
    super(in);
  }

  public static final Creator<LicenseEntry> CREATOR = new Creator<LicenseEntry>() {
    @Override
    public LicenseEntry createFromParcel(Parcel source) {
      return new LicenseEntry(source);
    }

    @Override
    public LicenseEntry[] newArray(int size) {
      return new LicenseEntry[size];
    }
  };
}