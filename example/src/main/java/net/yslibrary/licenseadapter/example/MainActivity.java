package net.yslibrary.licenseadapter.example;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;
import net.yslibrary.licenseadapter.Library;
import net.yslibrary.licenseadapter.LicenseAdapter;
import net.yslibrary.licenseadapter.Licenses;

public class MainActivity extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    List<Library> licenses = new ArrayList<>();

    // libraries not hosted on GitHub(and you chose not to show license text)
    licenses.add(Licenses.noContent("Android SDK", "Google Inc.",
        "https://developer.android.com/sdk/terms.html"));
    licenses.add(Licenses.noContent("Fabric", "Google Inc.", "https://fabric.io/terms"));

    // this repository does not have license file
    licenses.add(Licenses.fromGitHub("gabrielemariotti/changeloglib", Licenses.LICENSE_APACHE_V2));

    licenses.add(Licenses.fromGitHubApacheV2("jakewharton/butterknife"));
    licenses.add(Licenses.fromGitHubApacheV2("hdodenhof/CircleImageView"));
    licenses.add(Licenses.fromGitHubApacheV2("google/dagger"));
    licenses.add(Licenses.fromGitHubApacheV2("evant/gradle-retrolambda"));
    licenses.add(Licenses.fromGitHubApacheV2("google/gson"));
    licenses.add(Licenses.fromGitHubApacheV2("immutables/immutables"));
    licenses.add(Licenses.fromGitHubApacheV2("square/leakcanary"));
    licenses.add(Licenses.fromGitHubApacheV2("square/okhttp"));
    licenses.add(Licenses.fromGitHubApacheV2("realm/realm-java"));
    licenses.add(Licenses.fromGitHubApacheV2("yqritc/RecyclerView-MultipleViewTypesAdapter"));
    licenses.add(Licenses.fromGitHubApacheV2("yshrsmz/LicenseAdapter"));
    licenses.add(Licenses.fromGitHubApacheV2("jakewharton/threetenabp"));
    licenses.add(Licenses.fromGitHubApacheV2("jakewharton/timber"));
    licenses.add(Licenses.fromGitHubApacheV2("grandcentrix/tray"));
    licenses.add(Licenses.fromGitHubApacheV2("twitter/twitter-text"));
    licenses.add(Licenses.fromGitHubMIT("jhy/jsoup"));
    licenses.add(Licenses.fromGitHubBSD("bumptech/glide"));
    licenses.add(Licenses.fromGitHubBSD("facebook/stetho"));

    // these 2 licenses have different branch name
    licenses.add(Licenses.fromGitHub("ReactiveX/RxAndroid", Licenses.NAME_APACHE_V2,
        "2.x/" + Licenses.FILE_AUTO));
    licenses.add(Licenses.fromGitHub("ReactiveX/RxJava", Licenses.NAME_APACHE_V2,
        "2.x/" + Licenses.FILE_AUTO));

    LicenseAdapter adapter = new LicenseAdapter(licenses);
    RecyclerView list = findViewById(R.id.list);
    list.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
    list.setAdapter(adapter);
  }
}
