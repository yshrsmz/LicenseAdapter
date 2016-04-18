package net.yslibrary.licenseadapter;

import android.os.Parcel;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

public class GitHubLicenseEntry extends BaseLicenseEntry {

  public static final Creator<GitHubLicenseEntry> CREATOR = new Creator<GitHubLicenseEntry>() {
    @Override
    public GitHubLicenseEntry createFromParcel(Parcel source) {
      return new GitHubLicenseEntry(source);
    }

    @Override
    public GitHubLicenseEntry[] newArray(int size) {
      return new GitHubLicenseEntry[size];
    }
  };

  public GitHubLicenseEntry(String licenseName, String gitRepo, String branch, License license,
      String relLicensePath) {
    super(null, branch, null, null, null);
    init(licenseName, gitRepo, branch, license, relLicensePath);
  }

  protected GitHubLicenseEntry(Parcel in) {
    super(in);
  }

  private static String readFile(String link) {
    try {
      URL url = new URL(link);
      InputStreamReader reader = new InputStreamReader(url.openStream());
      BufferedReader in = new BufferedReader(reader);
      StringBuilder builder = new StringBuilder();
      String str;
      while ((str = in.readLine()) != null) builder.append(str).append("\n");
      in.close();
      return builder.toString();
    } catch (MalformedURLException e) {
      return e.getMessage();
    } catch (IOException e) {
      return e.getMessage();
    }
  }

  private void init(String licenseName, String gitRepo, String branch, License license,
      String relLicensePath) {
    name = gitRepo.substring(gitRepo.indexOf("/") + 1);
    author = gitRepo.substring(0, gitRepo.indexOf("/"));
    link = "https://github.com/" + gitRepo;

    if (license == null) {
      String licenseLink =
          (link + "/" + (branch != null ? branch : "master") + "/").replace("github.com",
              "raw.github.com").replace("/tree/", "/") + relLicensePath;
      this.license = new License(licenseName, licenseLink);
    } else {
      this.license = license;
    }
  }

  @Override
  protected void doLoad() {
    license.text = readFile(license.url);
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
