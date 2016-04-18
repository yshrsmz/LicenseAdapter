package net.yslibrary.licenseadapter;

import android.os.Parcel;

/**
 * License without license text
 */
public class NoContentLicenseEntry extends BaseLicenseEntry {

  public static final Creator<NoContentLicenseEntry> CREATOR =
      new Creator<NoContentLicenseEntry>() {
        @Override
        public NoContentLicenseEntry createFromParcel(Parcel source) {
          return new NoContentLicenseEntry(source);
        }

        @Override
        public NoContentLicenseEntry[] newArray(int size) {
          return new NoContentLicenseEntry[size];
        }
      };

  public NoContentLicenseEntry(String name, String author, String url) {
    super(name, null, author, url, new License(null, url));
  }

  protected NoContentLicenseEntry(Parcel in) {
    super(in);
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

  @Override
  public boolean hasContent() {
    return false;
  }
}