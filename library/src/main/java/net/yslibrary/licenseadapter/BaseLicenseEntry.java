package net.yslibrary.licenseadapter;

import android.os.Parcel;
import android.os.Parcelable;

public abstract class BaseLicenseEntry implements LicenseEntry, Parcelable {
  public String name;
  public String version;
  public String author;
  public String link;

  public License license;

  public BaseLicenseEntry(String libraryName, String libraryVersion, String libraryAuthor,
      String libraryLink, License license) {
    this.name = libraryName;
    this.version = libraryVersion;
    this.author = libraryAuthor;
    this.link = libraryLink;
    this.license = license;
  }

  public BaseLicenseEntry() {
    name = null;
    version = null;
    author = null;
    link = null;
    license = null;
  }

  protected BaseLicenseEntry(Parcel in) {
    name = in.readString();
    version = in.readString();
    author = in.readString();
    link = in.readString();
    license = in.readParcelable(License.class.getClassLoader());
  }

  @Override
  public void writeToParcel(Parcel dest, int flags) {
    dest.writeString(name);
    dest.writeString(version);
    dest.writeString(author);
    dest.writeString(link);
    dest.writeParcelable(license, flags);
  }

  @Override
  public int describeContents() {
    return 0;
  }

  public boolean isLoaded() {
    return license.isLoaded();
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
  public String link() {
    return link;
  }

  @Override
  public License license() {
    return license;
  }

  protected abstract void doLoad();

  @Override
  public String toString() {
    return String.format("\n***** LIBRARY *****\n%s\n%s\n%s\n%s\n%s\n", name, version, author, link,
        license.toString());
  }
}
