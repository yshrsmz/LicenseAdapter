package net.yslibrary.licenseadapter;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import java.util.List;
import net.yslibrary.licenseadapter.internal.GitHubLibrary;
import net.yslibrary.licenseadapter.internal.NoContentLibrary;
import net.yslibrary.licenseadapter.internal.NoLinkLibrary;

/**
 * Main entry point to get {@link Library}s and load them.
 */
public final class Licenses {

  // License names
  public static final String NAME_APACHE_V2 = "Apache License 2.0";
  public static final String NAME_MIT = "MIT License";
  public static final String NAME_BSD = "BSD 3-clause License";
  public static final String NAME_UNKNOWN = "Unknown";

  // License file names
  public static final String FILE_AUTO = "net:yslibrary:licenseadapter:license_file_auto";
  public static final String FILE_NO_EXTENSION = "LICENSE";
  public static final String FILE_TXT = "LICENSE.txt";
  public static final String FILE_MD = "LICENSE.md";

  private static final String DEF_BRANCH = "master";

  /**
   * Predefined Library for Apache License v2.0.
   */
  public static final License LICENSE_APACHE_V2 = new License.Builder(NAME_APACHE_V2)
      .setUrl("http://www.apache.org/licenses/LICENSE-2.0")
      .build();

  private Licenses() {
    throw new AssertionError("No instance for you!");
  }

  /**
   * Create Apache v2 Library from GitHub repository url.
   * LicenseAdapter will check if the repo has any of {@link Licenses#FILE_TXT},
   * {@link Licenses#FILE_MD} or {@link Licenses#FILE_NO_EXTENSION}
   *
   * @param gitRepo target library's GitHub repository. should be "user/repoName"
   * @return the generated {@link Library}
   */
  public static Library fromGitHubApacheV2(@NonNull String gitRepo) {
    return fromGitHub(gitRepo, NAME_APACHE_V2, DEF_BRANCH + "/" + FILE_AUTO);
  }

  /**
   * Create Apache v2 Library from GitHub repository url.
   *
   * @param gitRepo             target library's GitHub repository. should be "user/repoName"
   * @param relativeLicensePath relative path to the license file. you can use predefined {@link
   *                            Licenses#FILE_TXT}, {@link Licenses#FILE_MD}, {@link
   *                            Licenses#FILE_NO_EXTENSION} or {@link Licenses#FILE_AUTO}
   * @return the generated {@link Library}
   */
  public static Library fromGitHubApacheV2(@NonNull String gitRepo,
      @NonNull String relativeLicensePath) {
    return fromGitHub(gitRepo, NAME_APACHE_V2, relativeLicensePath);
  }

  /**
   * Create MIT Library from GitHub repository url.
   * LicenseAdapter will check if the repo has any of {@link Licenses#FILE_TXT},
   * {@link Licenses#FILE_MD} or {@link Licenses#FILE_NO_EXTENSION}
   *
   * @param gitRepo target library's GitHub repository. should be "user/repoName"
   * @return the generated {@link Library}
   */
  public static Library fromGitHubMIT(@NonNull String gitRepo) {
    return fromGitHub(gitRepo, NAME_MIT, DEF_BRANCH + "/" + FILE_AUTO);
  }

  /**
   * Create MIT Library from GitHub repository url.
   *
   * @param gitRepo             target library's GitHub repository. should be "user/repoName"
   * @param relativeLicensePath relative path to the license file. you can use predefined {@link
   *                            Licenses#FILE_TXT}, {@link Licenses#FILE_MD}, {@link
   *                            Licenses#FILE_NO_EXTENSION} or {@link Licenses#FILE_AUTO}
   * @return the generated {@link Library}
   */
  public static Library fromGitHubMIT(@NonNull String gitRepo,
      @NonNull String relativeLicensePath) {
    return fromGitHub(gitRepo, NAME_MIT, relativeLicensePath);
  }

  /**
   * Create BSD Library from GitHub repository url.
   * LicenseAdapter will check if the repo has any of {@link Licenses#FILE_TXT},
   * {@link Licenses#FILE_MD} or {@link Licenses#FILE_NO_EXTENSION}
   *
   * @param gitRepo target library's GitHub repository. should be "user/repoName"
   * @return the generated {@link Library}
   */
  public static Library fromGitHubBSD(@NonNull String gitRepo) {
    return fromGitHub(gitRepo, NAME_BSD, DEF_BRANCH + "/" + FILE_AUTO);
  }

  /**
   * Create BSD Library from GitHub repository url.
   *
   * @param gitRepo             target library's GitHub repository. should be "user/repoName"
   * @param relativeLicensePath relative path to the license file. you can use predefined {@link
   *                            Licenses#FILE_TXT}, {@link Licenses#FILE_MD} and {@link
   *                            Licenses#FILE_NO_EXTENSION}
   * @return the generated {@link Library}
   */
  public static Library fromGitHubBSD(@NonNull String gitRepo,
      @NonNull String relativeLicensePath) {
    return fromGitHub(gitRepo, NAME_BSD, relativeLicensePath);
  }

  /**
   * Create a {@link Library} from a GitHub repository url and license name.
   *
   * @param gitRepo             target library's GitHub repository, should be "user/repoName"
   * @param licenseName         License name to use. you can use the predefined {@link
   *                            Licenses#NAME_APACHE_V2}, {@link Licenses#NAME_MIT}, {@link
   *                            Licenses#NAME_BSD}, or your own.
   * @param relativeLicensePath Relative path to the license file. You can use the predefined
   *                            {@link
   *                            Licenses#FILE_TXT}, {@link Licenses#FILE_MD}, {@link
   *                            Licenses#FILE_NO_EXTENSION}, {@link Licenses#FILE_AUTO}, or your
   *                            own.
   * @return the generated {@link Library}
   */
  public static Library fromGitHub(@NonNull String gitRepo, @NonNull String licenseName,
      @NonNull String relativeLicensePath) {
    return new GitHubLibrary.Builder(gitRepo, licenseName)
        .setRelativeLicensePath(relativeLicensePath)
        .build();
  }

  /**
   * Create a {@link Library} from a GitHub repository url and user defined {@link License}.
   * This method can be used when the repository does not provide a license text file.
   * You can use the predefined {@link Licenses#LICENSE_APACHE_V2} or your own implementation.
   *
   * @param gitRepo target library's GitHub repository, should be "user/repoName"
   * @param license custom {@link License}
   * @return the generated {@link Library}
   */
  public static Library fromGitHub(@NonNull String gitRepo, @NonNull License license) {
    return new GitHubLibrary.Builder(gitRepo, license.name).setRawLicenseUrl(license.url).build();
  }

  /**
   * Create Library from provided name, author and url.
   * This method can be used when the library is not hosted on GitHub, and does not provide license
   * info.
   *
   * @param name   library's name
   * @param author author's name
   * @return the generated {@link Library} to be loaded with {@link #load(List)}
   */
  public static Library noContent(@NonNull String name, @NonNull String author,
      @NonNull String url) {
    return new NoContentLibrary(name, author,
        new License.Builder(NAME_UNKNOWN).setUrl(url).build());
  }

  /**
   * Create Library from provided name, author and text.
   * This method can be used when the library does not provide the license online.
   *
   * @param name   library's name
   * @param author author's name
   * @param text   the license
   * @return the generated {@link Library} to be loaded with {@link #load(List)}
   */
  public static Library noLink(@NonNull String name, @NonNull String author,
      @NonNull String licenseName, @Nullable String text) {
    return new NoLinkLibrary(name, author,
        new License.Builder(licenseName).setText(text).build());
  }
}
