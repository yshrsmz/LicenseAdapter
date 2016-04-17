package net.yslibrary.licenseadapter;

/**
 * Created by a12897 on 2016/04/15.
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

  public static GitLicenseEntry fromGitHub(String gitRepo) {
    return new GitLicenseEntry(DEF_LICENSE_NAME, gitRepo, DEF_BRANCH, null, LICENSE_TXT);
  }

  public static GitLicenseEntry fromGitHub(String gitRepo, String relLicensePath) {
    return new GitLicenseEntry(DEF_LICENSE_NAME, gitRepo, DEF_BRANCH, null, relLicensePath);
  }

  public static GitLicenseEntry fromGitHub(String gitRepo, License license) {
    return new GitLicenseEntry(null, gitRepo, DEF_BRANCH, license, null);
  }

  public static GitLicenseEntry fromGitHub(String gitRepo, String licenseName,
      String relLicensePath) {
    return new GitLicenseEntry(licenseName, gitRepo, DEF_BRANCH, null, relLicensePath);
  }
}
