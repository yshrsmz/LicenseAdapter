package net.yslibrary.licenseadapter;

import java.util.List;
import net.yslibrary.licenseadapter.internal.LoadLicenseTask;

/**
 * Created by yshrsmz on 2016/04/15.
 */
public class Licenses {

  public static final String LICENSE_NAME_APACHE_V2 = "Apache License V2.0";
  public static final String LICENSE_NAME_MIT = "MIT LICENSE";
  public static final String LICENSE_NAME_BSD = "BSD LICENSE";

  public static final String LICENSE_NO_EXTENSION = "LICENSE";
  public static final String LICENSE_TXT = "LICENSE.txt";
  public static final String LICENSE_MD = "LICENSE.md";
  private static final String DEF_BRANCH = "master";
  private static final String DEF_LICENSE_NAME = LICENSE_NAME_APACHE_V2;

  public static final License LICENSE_APACHE_V2 =
      new License(DEF_LICENSE_NAME, "http://www.apache.org/licenses/LICENSE-2.0");

  public static GitHubLicenseEntry fromGitHub(String gitRepo) {
    return new GitHubLicenseEntry(DEF_LICENSE_NAME, gitRepo, DEF_BRANCH, null, LICENSE_TXT);
  }

  public static GitHubLicenseEntry fromGitHub(String gitRepo, String relLicensePath) {
    return new GitHubLicenseEntry(DEF_LICENSE_NAME, gitRepo, DEF_BRANCH, null, relLicensePath);
  }

  public static GitHubLicenseEntry fromGitHub(String gitRepo, License license) {
    return new GitHubLicenseEntry(null, gitRepo, DEF_BRANCH, license, null);
  }

  public static NoContentLicenseEntry noContent(String name, String author, String link) {
    return new NoContentLicenseEntry(name, author, link);
  }

  public static GitHubLicenseEntry fromGitHub(String gitRepo, String licenseName,
      String relLicensePath) {
    return new GitHubLicenseEntry(licenseName, gitRepo, DEF_BRANCH, null, relLicensePath);
  }

  public static void load(List<LicenseEntry> entries) {
    new LoadLicenseTask().execute(entries.toArray(new LicenseEntry[entries.size()]));
  }
}
