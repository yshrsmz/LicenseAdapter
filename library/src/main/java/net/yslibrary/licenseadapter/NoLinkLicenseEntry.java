package net.yslibrary.licenseadapter;

import android.os.Parcel;
import net.yslibrary.licenseadapter.internal.BaseLicenseEntry;

/**
 * License without license url
 */
public class NoLinkLicenseEntry extends BaseLicenseEntry {
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

  public NoLinkLicenseEntry(String name, String author, String text) {
    super(name, null, author, new License.Builder().setText(text).build());
  }

  protected NoLinkLicenseEntry(Parcel in) {
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
    return true;
  }
}