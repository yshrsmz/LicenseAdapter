package net.yslibrary.licenseadapter;

import android.os.Parcel;
import android.os.Parcelable;

public abstract class BaseLicenseEntry implements Parcelable {
  public String mLibraryName;
  public String mLibraryVersion;
  public String mLibraryAuthor;
  public String mLibraryLink;

  public License mLicense;

  public BaseLicenseEntry(String libraryName, String libraryVersion, String libraryAuthor, String libraryLink, License license) {
    this.mLibraryName = libraryName;
    this.mLibraryVersion = libraryVersion;
    this.mLibraryAuthor = libraryAuthor;
    this.mLibraryLink = libraryLink;
    this.mLicense = license;
  }

  public BaseLicenseEntry() {
    mLibraryName = null;
    mLibraryVersion = null;
    mLibraryAuthor = null;
    mLibraryLink = null;
    mLicense = null;
  }

  protected BaseLicenseEntry(Parcel in) {
    mLibraryName = in.readString();
    mLibraryVersion = in.readString();
    mLibraryAuthor = in.readString();
    mLibraryLink = in.readString();
    mLicense = in.readParcelable(License.class.getClassLoader());
  }

  @Override
  public void writeToParcel(Parcel dest, int flags) {
    dest.writeString(mLibraryName);
    dest.writeString(mLibraryVersion);
    dest.writeString(mLibraryAuthor);
    dest.writeString(mLibraryLink);
    dest.writeParcelable(mLicense, flags);
  }

  @Override
  public int describeContents() {
    return 0;
  }

  public boolean isLoaded() {
    return mLicense.isLoaded();
  }

  public void load() {
    if (mLicense.isLoaded())
      return;

    doLoad();
  }

  public abstract void doLoad();

  @Override
  public String toString() {
    return String.format("\n***** LIBRARY *****\n%s\n%s\n%s\n%s\n%s\n", mLibraryName,
        mLibraryVersion, mLibraryAuthor, mLibraryLink, mLicense.toString());
  }
}
