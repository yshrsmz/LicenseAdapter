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

  /**
   * predefined LicenseEntry for Apache License v2.0
   */
  public static final License LICENSE_APACHE_V2 =
      new License(DEF_LICENSE_NAME, "http://www.apache.org/licenses/LICENSE-2.0");

  /**
   * create LicenseEntry from GitHub repository url.
   * Regard license as Apache v2, and License text file name as "LICENSE.txt"
   *
   * @param gitRepo target library's GitHub repository. should be "user/repoName"
   * @return GitHubLicenseEntry
   */
  public static GitHubLicenseEntry fromGitHub(String gitRepo) {
    return new GitHubLicenseEntry(DEF_LICENSE_NAME, gitRepo, DEF_BRANCH, null, LICENSE_TXT);
  }

  /**
   * create LicenseEntry from GitHub repository url & License text path.
   * Regard license as Apache v2
   *
   * @param gitRepo        target library's GitHub repository. should be "user/repoName"
   * @param relLicensePath relative path to the license file. you can use predefined {@link
   *                       Licenses.LICENSE_TXT}, {@link Licenses.LICENSE_MD}, {@link
   *                       Licenses.LICENSE_NO_EXTENSION}
   * @return GitHubLicenseEntry
   */
  public static GitHubLicenseEntry fromGitHub(String gitRepo, String relLicensePath) {
    return new GitHubLicenseEntry(DEF_LICENSE_NAME, gitRepo, DEF_BRANCH, null, relLicensePath);
  }

  /**
   * create LicenseEntry from GitHub repository url & user define {@link License} instance.
   * This method can be used when the repository does not provide license text file.
   * You can use predefined {@link Licenses.LICENSE_APACHE_V2} or your own implementation.
   *
   * @param gitRepo target library's GitHub repository. should be "user/repoName"
   * @param license user defined {@link License} instance
   * @return GitHubLicenseEntry
   */
  public static GitHubLicenseEntry fromGitHub(String gitRepo, License license) {
    return new GitHubLicenseEntry(null, gitRepo, DEF_BRANCH, license, null);
  }

  public static GitHubLicenseEntry fromGitHub(String gitRepo, String licenseName,
      String relLicensePath) {
    return new GitHubLicenseEntry(licenseName, gitRepo, DEF_BRANCH, null, relLicensePath);
  }

  /**
   * Create LicenseEntry from provided name, author and link.
   * This method can be used when the library is not hosted on GitHub, and does not provide license
   * info.
   *
   * @param name   Library's name
   * @param author author's name
   * @param link   url for the library hosting page
   * @return NoContentLicenseEntry
   */
  public static NoContentLicenseEntry noContent(String name, String author, String link) {
    return new NoContentLicenseEntry(name, author, link);
  }

  /**
   * Load provided licenses
   *
   * @param entries list of LicenseEntries to load
   */
  public static void load(List<LicenseEntry> entries) {
    new LoadLicenseTask().execute(entries.toArray(new LicenseEntry[entries.size()]));
  }
}
