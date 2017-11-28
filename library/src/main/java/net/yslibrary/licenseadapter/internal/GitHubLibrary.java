package net.yslibrary.licenseadapter.internal;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.WorkerThread;
import android.text.TextUtils;
import android.util.Log;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import net.yslibrary.licenseadapter.BaseLibrary;
import net.yslibrary.licenseadapter.License;
import net.yslibrary.licenseadapter.Licenses;

public final class GitHubLibrary extends BaseLibrary {
  private static final String TAG = "GitHubLibrary";

  private final List<String> possibleLicenseUrls;

  private GitHubLibrary(@NonNull String name, @NonNull String author, @NonNull License license,
      @NonNull List<String> possibleLicenseUrls) {
    super(name, author, license);
    this.possibleLicenseUrls = possibleLicenseUrls;
  }

  @NonNull
  @WorkerThread
  private static String loadContents(@NonNull String url) throws IOException {
    BufferedReader in = null;
    try {
      return read(in = new BufferedReader(new InputStreamReader(new URL(url).openStream())));
    } catch (IOException e) {
      Log.d(TAG, "Failed to load license.", e);
      throw e;
    } finally {
      if (in != null) {
        in.close();
      }
    }
  }

  @Override
  public boolean isLoaded() {
    return !TextUtils.isEmpty(license.text);
  }

  @WorkerThread
  @Override
  protected void doLoad() {
    if (possibleLicenseUrls.isEmpty()) return;

    for (String url : possibleLicenseUrls) {
      try {
        license = new License.Builder(license).setUrl(url).setText(loadContents(url)).build();
        break;
      } catch (IOException ignored) {
        // Handled below
      }
    }

    if (!isLoaded()) {
      license = new License.Builder(license)
          .setText("Developer error: no license file found. "
              + "Searched for the following license files:\n" + possibleLicenseUrls)
          .build();
    }
  }

  @Override
  public boolean hasContent() {
    return true;
  }

  public static final class Builder {
    private static final String BASE = "https://raw.githubusercontent.com/";
    private static final String REPO_SPLIT = "/";

    private final String author;
    private final String name;
    private final String licenseName;

    private List<String> possibleLicenseUrls;

    public Builder(@NonNull String shortLink, @NonNull String licenseName) {
      author = parseRepoAuthor(shortLink);
      name = parseRepoName(shortLink);
      this.licenseName = licenseName;
    }

    @NonNull
    private static String parseRepoAuthor(@NonNull String fullRepo) {
      checkValidRepoUrl(fullRepo);
      return fullRepo.substring(0, fullRepo.indexOf(REPO_SPLIT));
    }

    @NonNull
    private static String parseRepoName(@NonNull String fullRepo) {
      checkValidRepoUrl(fullRepo);
      return fullRepo.substring(fullRepo.indexOf(REPO_SPLIT) + 1);
    }

    private static void checkValidRepoUrl(@NonNull String fullRepo) {
      int repoSplitIndex = fullRepo.indexOf(REPO_SPLIT);
      if (repoSplitIndex == -1 || repoSplitIndex != fullRepo.lastIndexOf(REPO_SPLIT)) {
        throw new IllegalArgumentException(
            "The GitHub repository url must be of the form `author/repo`.");
      }
    }

    @NonNull
    public Builder setRawLicenseUrl(@Nullable String url) {
      possibleLicenseUrls =
          TextUtils.isEmpty(url) ? Collections.<String>emptyList() : Collections.singletonList(url);
      return this;
    }

    @NonNull
    public Builder setRelativeLicenseUrl(@NonNull String url) {
      possibleLicenseUrls = new ArrayList<>();
      String fullBase = BASE + author + "/" + name + "/";

      if (url.contains(Licenses.FILE_AUTO)) {
        List<String> possibleFiles = Arrays.asList(
            Licenses.FILE_NO_EXTENSION, Licenses.FILE_TXT, Licenses.FILE_MD);
        for (String possibleFile : possibleFiles) {
          possibleLicenseUrls.add(fullBase + url.replace(Licenses.FILE_AUTO, possibleFile));
        }
      }

      return this;
    }

    @NonNull
    public GitHubLibrary build() {
      return new GitHubLibrary(name, author, new License.Builder(licenseName).build(),
          Collections.unmodifiableList(possibleLicenseUrls));
    }
  }
}
