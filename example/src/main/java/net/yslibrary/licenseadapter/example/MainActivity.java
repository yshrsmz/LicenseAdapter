package net.yslibrary.licenseadapter.example;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;
import net.yslibrary.licenseadapter.GitHubLicenseEntry;
import net.yslibrary.licenseadapter.LicenseAdapter;
import net.yslibrary.licenseadapter.LicenseEntry;
import net.yslibrary.licenseadapter.Licenses;

public class MainActivity extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    List<LicenseEntry> licenses = new ArrayList<>();

    // libraries not hosted on GitHub(and you chose not to show license text)
    licenses.add(Licenses.noContent("Android SDK", "Google Inc.",
        "https://developer.android.com/sdk/terms.html"));
    licenses.add(Licenses.noContent("Fabric", "Twitter", "https://fabric.io/terms"));

    // this repository does not have license file
    licenses.add(Licenses.fromGitHub("gabrielemariotti/changeloglib", Licenses.LICENSE_APACHE_V2));

    licenses.add(Licenses.fromGitHubApacheV2("jakewharton/butterknife"));
    licenses.add(Licenses.fromGitHubApacheV2("hdodenhof/CircleImageView"));
    licenses.add(Licenses.fromGitHubApacheV2("google/dagger"));
    licenses.add(Licenses.fromGitHubApacheV2("evant/gradle-retrolambda"));
    licenses.add(Licenses.fromGitHubApacheV2("google/gson", Licenses.FILE_NO_EXTENSION));
    licenses.add(Licenses.fromGitHubApacheV2("immutables/immutables", Licenses.FILE_NO_EXTENSION));
    licenses.add(Licenses.fromGitHubApacheV2("square/leakcanary"));
    licenses.add(Licenses.fromGitHubApacheV2("square/okhttp"));
    licenses.add(Licenses.fromGitHubApacheV2("realm/realm-java", Licenses.FILE_NO_EXTENSION));
    licenses.add(Licenses.fromGitHubApacheV2("yqritc/RecyclerView-MultipleViewTypesAdapter",
        Licenses.FILE_NO_EXTENSION));
    licenses.add(Licenses.fromGitHubApacheV2("yshrsmz/LicenseAdapter", Licenses.FILE_NO_EXTENSION));
    licenses.add(Licenses.fromGitHubApacheV2("jakewharton/threetenabp"));
    licenses.add(Licenses.fromGitHubApacheV2("jakewharton/timber"));
    licenses.add(Licenses.fromGitHubApacheV2("grandcentrix/tray"));
    licenses.add(Licenses.fromGitHubApacheV2("twitter/twitter-text", Licenses.FILE_NO_EXTENSION));

    licenses.add(Licenses.fromGitHubMIT("jhy/jsoup", Licenses.FILE_NO_EXTENSION));
    licenses.add(Licenses.fromGitHubBSD("bumptech/glide", Licenses.FILE_NO_EXTENSION));
    licenses.add(Licenses.fromGitHubBSD("facebook/stetho", Licenses.FILE_NO_EXTENSION));

    // these 2 licenses have different branch name
    licenses.add(
        new GitHubLicenseEntry(Licenses.NAME_APACHE_V2, "ReactiveX/RxAndroid", "1.x/", null,
            Licenses.FILE_NO_EXTENSION));
    licenses.add(new GitHubLicenseEntry(Licenses.NAME_APACHE_V2, "ReactiveX/RxJava", "1.x/", null,
        Licenses.FILE_NO_EXTENSION));

    LicenseAdapter adapter = new LicenseAdapter(licenses);
    RecyclerView list = (RecyclerView) findViewById(R.id.list);
    list.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
    list.setAdapter(adapter);

    Licenses.load(licenses);
  }
}
