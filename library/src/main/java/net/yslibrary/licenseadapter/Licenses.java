package net.yslibrary.licenseadapter;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import net.yslibrary.licenseadapter.internal.GitHubLibrary;
import net.yslibrary.licenseadapter.internal.NoContentLibrary;
import net.yslibrary.licenseadapter.internal.NoLinkLibrary;

import java.util.List;

/**
 * Main entry point to get {@link Library}s and load them.
 */
public final class Licenses {
  /**
   * The Apache license 2.0 name to be used with {@link License#License(String, String, String)}.
   *
   * @see #NAME_MIT
   * @see #NAME_BSD
   * @see #NAME_UNKNOWN
   */
  public static final String NAME_APACHE_V2 = "Apache License 2.0";
  /**
   * The MIT license name to be used with {@link License#License(String, String, String)}.
   *
   * @see #NAME_APACHE_V2
   * @see #NAME_BSD
   * @see #NAME_UNKNOWN
   */
  public static final String NAME_MIT = "MIT License";
  /**
   * The BSD 3-clause license name to be used with {@link License#License(String, String, String)}.
   *
   * @see #NAME_APACHE_V2
   * @see #NAME_MIT
   * @see #NAME_UNKNOWN
   */
  public static final String NAME_BSD = "BSD 3-clause License";
  /**
   * A placeholder license name for when it is unknown. Commonly used with {@link #noContent(String,
   * String, String)}
   *
   * @see #NAME_APACHE_V2
   * @see #NAME_MIT
   * @see #NAME_BSD
   */
  public static final String NAME_UNKNOWN = "Unknown";

  /**
   * A placeholder license file name used to tell LicenseAdapter to automatically look for the
   * license file. It's most common use is with {@link #fromGitHubApacheV2(String, String)}.
   *
   * @see #FILE_NO_EXTENSION
   * @see #FILE_TXT
   * @see #FILE_MD
   */
  public static final String FILE_AUTO = "net:yslibrary:licenseadapter:license_file_auto";
  /**
   * The license file name with no extension.
   *
   * @see #FILE_AUTO
   * @see #FILE_TXT
   * @see #FILE_MD
   */
  public static final String FILE_NO_EXTENSION = "LICENSE";
  /**
   * The license file name with a {@code .txt} extension.
   *
   * @see #FILE_AUTO
   * @see #FILE_NO_EXTENSION
   * @see #FILE_MD
   */
  public static final String FILE_TXT = "LICENSE.txt";
  /**
   * The license file name with a {@code .md} extension.
   *
   * @see #FILE_AUTO
   * @see #FILE_NO_EXTENSION
   * @see #FILE_TXT
   */
  public static final String FILE_MD = "LICENSE.md";

  /**
   * Predefined Apache {@link License} which directly references the license itself instead of its
   * copy on GitHub. Commonly used when a library doesn't provide a license file.
   *
   * @see #fromGitHub(String, License)
   */
  public static final License LICENSE_APACHE_V2 = new License.Builder(NAME_APACHE_V2)
      .setUrl("https://www.apache.org/licenses/LICENSE-2.0")
      .build();

  private static final String DEF_BRANCH = "master";

  private Licenses() {
    throw new AssertionError("No instance for you!");
  }

  /**
   * Creates a new library from the {@link #NAME_APACHE_V2 Apache License 2.0}. The library may be
   * used with a {@link LicenseAdapter} to populate a {@link RecyclerView}. The GitHub url must be
   * in the short format "author/name" where author is the GitHub user of this library and name is
   * the name of the repository. Both of these fields are case insensitive.
   * <p>
   * This methods assumes the license file is on the "master" branch and is named one of {@link
   * #FILE_NO_EXTENSION}, {@link #FILE_TXT}, or {@link #FILE_MD}. If the license file is not on the
   * master branch or does not use a common file name, use {@link #fromGitHubApacheV2(String,
   * String)} instead.
   *
   * @param shortUrl library's GitHub repository in short url format: "user/repoName"
   * @return the generated {@link Library} used in {@link LicenseAdapter#LicenseAdapter(List)}
   * @see Library
   * @see LicenseAdapter
   * @see #fromGitHubApacheV2(String, String)
   * @see #NAME_APACHE_V2
   */
  public static Library fromGitHubApacheV2(@NonNull String shortUrl) {
    return fromGitHub(shortUrl, DEF_BRANCH + "/" + FILE_AUTO, NAME_APACHE_V2);
  }

  /**
   * Creates a new library from the {@link #NAME_APACHE_V2 Apache License 2.0} with a custom
   * relative license path.
   * <p>
   * The relative path starts after the library's name. For example, if a library's license file is
   * located at {@code github.com/author/name/my/custom/path/LICENSE.weirdextension}, the relative
   * license path would be "my/custom/path/LICENSE.weirdextension".
   *
   * @param shortUrl            library's GitHub repository in short url format: "user/repoName"
   * @param relativeLicensePath relative license path.
   * @return the generated {@link Library} used in {@link LicenseAdapter#LicenseAdapter(List)}
   * @see #fromGitHubApacheV2(String)
   */
  public static Library fromGitHubApacheV2(@NonNull String shortUrl,
                                           @NonNull String relativeLicensePath) {
    return fromGitHub(shortUrl, relativeLicensePath, NAME_APACHE_V2);
  }

  /**
   * Creates a new library from the {@link #NAME_MIT MIT License}.
   *
   * @param shortUrl library's GitHub repository in short url format: "user/repoName"
   * @return the generated {@link Library} used in {@link LicenseAdapter#LicenseAdapter(List)}
   * @see #fromGitHubApacheV2(String)
   */
  public static Library fromGitHubMIT(@NonNull String shortUrl) {
    return fromGitHub(shortUrl, DEF_BRANCH + "/" + FILE_AUTO, NAME_MIT);
  }

  /**
   * Creates a new library from the {@link #NAME_MIT MIT License} with a custom relative license
   * path.
   *
   * @param shortUrl            library's GitHub repository in short url format: "user/repoName"
   * @param relativeLicensePath relative license path.
   * @return the generated {@link Library} used in {@link LicenseAdapter#LicenseAdapter(List)}
   * @see #fromGitHubApacheV2(String, String)
   */
  public static Library fromGitHubMIT(@NonNull String shortUrl,
                                      @NonNull String relativeLicensePath) {
    return fromGitHub(shortUrl, relativeLicensePath, NAME_MIT);
  }

  /**
   * Creates a new library from the {@link #NAME_BSD BSD License}.
   *
   * @param shortUrl library's GitHub repository in short url format: "user/repoName"
   * @return the generated {@link Library} used in {@link LicenseAdapter#LicenseAdapter(List)}
   * @see #fromGitHubApacheV2(String)
   */
  public static Library fromGitHubBSD(@NonNull String shortUrl) {
    return fromGitHub(shortUrl, DEF_BRANCH + "/" + FILE_AUTO, NAME_BSD);
  }

  /**
   * Creates a new library from the {@link #NAME_BSD BSD License} with a custom relative license
   * path.
   *
   * @param shortUrl            library's GitHub repository in short url format: "user/repoName"
   * @param relativeLicensePath relative license path.
   * @return the generated {@link Library} used in {@link LicenseAdapter#LicenseAdapter(List)}
   * @see #fromGitHubApacheV2(String, String)
   */
  public static Library fromGitHubBSD(@NonNull String shortUrl,
                                      @NonNull String relativeLicensePath) {
    return fromGitHub(shortUrl, relativeLicensePath, NAME_BSD);
  }

  /**
   * Creates a new library from a custom license.
   *
   * @param shortUrl library's GitHub repository in short url format: "user/repoName"
   * @param license  custom license implementation
   * @return the generated {@link Library} used in {@link LicenseAdapter#LicenseAdapter(List)}
   * @see #fromGitHubApacheV2(String)
   * @see License
   */
  public static Library fromGitHub(@NonNull String shortUrl, @NonNull License license) {
    return new GitHubLibrary.Builder(shortUrl, license.getName()).setRawLicenseUrl(license.getUrl())
        .build();
  }

  /**
   * Creates a new library from a custom license name.
   *
   * @param shortUrl            library's GitHub repository in short url format: "user/repoName"
   * @param relativeLicensePath relative license path.
   * @param licenseName         custom license name
   * @return the generated {@link Library} used in {@link LicenseAdapter#LicenseAdapter(List)}
   * @see #fromGitHubApacheV2(String)
   * @see #fromGitHubApacheV2(String, String)
   */
  public static Library fromGitHub(@NonNull String shortUrl, @NonNull String relativeLicensePath,
                                   @NonNull String licenseName) {
    return new GitHubLibrary.Builder(shortUrl, licenseName).setRelativeLicensePath(
        relativeLicensePath).build();
  }

  /**
   * Creates a new library with a name, author, and license url, but without any license text.
   *
   * @param name   library name
   * @param author name of the library's author
   * @param url    link to the library's website
   * @return the generated {@link Library} used in {@link LicenseAdapter#LicenseAdapter(List)}
   * @see #fromGitHubApacheV2(String)
   * @see License
   */
  public static Library noContent(@NonNull String name, @NonNull String author,
                                  @NonNull String url) {
    return new NoContentLibrary(name, author,
        new License.Builder(NAME_UNKNOWN).setUrl(url).build());
  }

  /**
   * Creates a new library with a name, author, and license name, but no license text.
   *
   * @param name        library name
   * @param author      name of the library's author
   * @param licenseName name of the license
   * @param text        content of the license
   * @return the generated {@link Library} used in {@link LicenseAdapter#LicenseAdapter(List)}
   * @see #fromGitHubApacheV2(String)
   * @see License
   */
  public static Library noLink(@NonNull String name, @NonNull String author,
                               @NonNull String licenseName, @Nullable String text) {
    return new NoLinkLibrary(name, author, new License.Builder(licenseName).setText(text).build());
  }
}
