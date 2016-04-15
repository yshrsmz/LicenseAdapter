package net.yslibrary.licenseadapter;

import android.os.Parcel;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

public class GitLicenseEntry extends BaseLicenseEntry {

  public GitLicenseEntry(String licenseName, String gitRepo, String branch, License license, String relLicensePath) {
    super(null, branch, null, null, null);
    init(licenseName, gitRepo, branch, license, relLicensePath);
  }

  private void init(String licenseName, String gitRepo, String branch, License license, String relLicensePath) {
    mLibraryName = gitRepo.substring(gitRepo.indexOf("/") + 1);
    mLibraryAuthor = gitRepo.substring(0, gitRepo.indexOf("/"));
    mLibraryLink = "https://github.com/" + gitRepo;

    if (license == null) {
      String licenseLink
          = (mLibraryLink + "/" + (branch != null ? branch : "master") + "/")
          .replace("github.com", "raw.github.com")
          .replace("/tree/", "/")
          + relLicensePath;
      this.mLicense = new License(licenseName, licenseLink);
    } else {
      this.mLicense = license;
    }
  }

  @Override
  public void doLoad() {
    mLicense.mText = readFile(mLicense.mUrl);
  }

  private static String readFile(String link) {
    try {
      URL url = new URL(link);
      InputStreamReader reader = new InputStreamReader(url.openStream());
      BufferedReader in = new BufferedReader(reader);
      StringBuilder builder = new StringBuilder();
      String str;
      while ((str = in.readLine()) != null)
        builder.append(str).append("\n");
      in.close();
      return builder.toString();
    } catch (MalformedURLException e) {
      return e.getMessage();
    } catch (IOException e) {
      return e.getMessage();
    }
  }

  @Override
  public int describeContents() {
    return 0;
  }

  @Override
  public void writeToParcel(Parcel dest, int flags) {
    super.writeToParcel(dest, flags);
  }

  protected GitLicenseEntry(Parcel in) {
    super(in);
  }

  public static final Creator<GitLicenseEntry> CREATOR = new Creator<GitLicenseEntry>() {
    @Override
    public GitLicenseEntry createFromParcel(Parcel source) {
      return new GitLicenseEntry(source);
    }

    @Override
    public GitLicenseEntry[] newArray(int size) {
      return new GitLicenseEntry[size];
    }
  };
}
