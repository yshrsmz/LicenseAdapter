package net.yslibrary.licenseadapter;

import android.os.Parcel;
import android.text.TextUtils;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Arrays;
import java.util.List;
import net.yslibrary.licenseadapter.internal.BaseLicenseEntry;

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

  private static String readFile(String licenseUrl) {
    try {
      URL url = new URL(licenseUrl);
      InputStreamReader reader = new InputStreamReader(url.openStream());
      BufferedReader in = new BufferedReader(reader);
      StringBuilder builder = new StringBuilder();
      String str;
      while ((str = in.readLine()) != null) {
        builder.append(str).append("\n");
      }
      in.close();
      return builder.toString();
    } catch (IOException e) {
      return e.getMessage();
    }
  }

  private void init(String licenseName, String gitRepo, String branch, License license,
      String relLicensePath) {
    name = gitRepo.substring(gitRepo.indexOf("/") + 1);
    author = gitRepo.substring(0, gitRepo.indexOf("/"));
    url = "https://github.com/" + gitRepo;

    if (license == null) {
      String licenseUrl =
          (url + "/" + (branch != null ? branch : "master") + "/").replace("github.com",
              "raw.githubusercontent.com").replace("/tree/", "/") + relLicensePath;
      this.license = License.builder().setName(licenseName).setUrl(licenseLink).build();
    } else {
      this.license = license;
    }
  }

  @Override
  protected void doLoad() {
    String[] splitUrl = license.url.split("/");
    if (splitUrl[splitUrl.length - 1].contains(Licenses.FILE_AUTO)) {
      List<String> possiblePaths =
          Arrays.asList(Licenses.FILE_NO_EXTENSION, Licenses.FILE_TXT, Licenses.FILE_MD);

      for (String relativePath : possiblePaths) {
        String possibleUrl = license.url.replace(Licenses.FILE_AUTO, relativePath);
        String response = readFile(possibleUrl);
        if (!response.equals(possibleUrl)) {
          license.text = response;
          license.url = possibleUrl;
          break;
        }
      }

      if (TextUtils.isEmpty(license.text)) {
        license.text =
            "No license file found. Searched for the following license files: " + possiblePaths;
      }
    } else {
      license.text = readFile(license.url);
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

  @Override
  public boolean hasContent() {
    return true;
  }
}
