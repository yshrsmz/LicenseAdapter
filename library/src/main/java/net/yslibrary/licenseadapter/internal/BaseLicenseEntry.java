package net.yslibrary.licenseadapter.internal;

import android.os.Parcel;
import net.yslibrary.licenseadapter.License;
import net.yslibrary.licenseadapter.LicenseEntry;

public abstract class BaseLicenseEntry implements LicenseEntry {
  protected String name;
  protected String version;
  protected String author;
  protected String url;

  protected License license;

  public BaseLicenseEntry(String libraryName, String libraryVersion, String libraryAuthor,
      String libraryUrl, License license) {
    this.name = libraryName;
    this.version = libraryVersion;
    this.author = libraryAuthor;
    this.url = libraryUrl;
    this.license = license;
  }

  protected BaseLicenseEntry(Parcel in) {
    name = in.readString();
    version = in.readString();
    author = in.readString();
    url = in.readString();
    license = in.readParcelable(License.class.getClassLoader());
  }

  @Override
  public void writeToParcel(Parcel dest, int flags) {
    dest.writeString(name);
    dest.writeString(version);
    dest.writeString(author);
    dest.writeString(url);
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
    return url;
  }

  @Override
  public String url() {
    return url;
  }

  @Override
  public License license() {
    return license;
  }

  protected abstract void doLoad();

  @Override
  public String toString() {
    return String.format("\n***** LIBRARY *****\n%s\n%s\n%s\n%s\n%s\n", name, version, author, url,
        license.toString());
  }
}
