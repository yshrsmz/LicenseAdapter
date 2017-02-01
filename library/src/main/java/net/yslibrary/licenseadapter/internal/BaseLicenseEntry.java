package net.yslibrary.licenseadapter.internal;

import android.os.Parcel;
import net.yslibrary.licenseadapter.License;
import net.yslibrary.licenseadapter.LicenseEntry;

public abstract class BaseLicenseEntry implements LicenseEntry {
  protected String name;
  protected String version;
  protected String author;

  protected License license;

  public BaseLicenseEntry(String libraryName, String libraryVersion, String libraryAuthor, License license) {
    this.name = libraryName;
    this.version = libraryVersion;
    this.author = libraryAuthor;
    this.license = license;
  }

  protected BaseLicenseEntry(Parcel in) {
    name = in.readString();
    version = in.readString();
    author = in.readString();
    license = in.readParcelable(License.class.getClassLoader());
  }

  @Override
  public void writeToParcel(Parcel dest, int flags) {
    dest.writeString(name);
    dest.writeString(version);
    dest.writeString(author);
    dest.writeParcelable(license, flags);
  }

  @Override
  public int describeContents() {
    return 0;
  }

  public void load() {
    if (license.isLoaded()) return;

    doLoad();
  }

  @Override
  public String name() {
    return name;
  }

  @Override
  public String version() {
    return version;
  }

  @Override
  public String author() {
    return author;
  }

  @Override
  public License license() {
    return license;
  }

  protected abstract void doLoad();

  @Override
  public String toString() {
    return String.format("***** LIBRARY *****\n%s\n%s\n%s\n%s\n", name, version, author, license.toString());
  }
}
