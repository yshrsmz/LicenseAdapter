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

    // These libraries aren't hosted on GitHub (and you chose not to show the license text)
    licenses.add(Licenses.noContent("Android SDK", "Google Inc.",
        "https://developer.android.com/sdk/terms.html"));
    licenses.add(Licenses.noContent("Fabric", "Google Inc.", "https://fabric.io/terms"));

    licenses.add(Licenses.fromGitHubApacheV2("JakeWharton/ButterKnife"));
    licenses.add(Licenses.fromGitHubApacheV2("Hdodenhof/CircleImageView"));
    licenses.add(Licenses.fromGitHubApacheV2("Google/Dagger"));
    licenses.add(Licenses.fromGitHubApacheV2("Google/Gson"));
    licenses.add(Licenses.fromGitHubApacheV2("Immutables/Immutables"));
    licenses.add(Licenses.fromGitHubApacheV2("Square/LeakCanary"));
    licenses.add(Licenses.fromGitHubApacheV2("Square/OkHttp"));
    licenses.add(Licenses.fromGitHubApacheV2("Realm/Realm-Java"));
    licenses.add(Licenses.fromGitHubApacheV2("yqritc/RecyclerView-MultipleViewTypesAdapter"));
    licenses.add(Licenses.fromGitHubApacheV2("yshrsmz/LicenseAdapter"));
    licenses.add(Licenses.fromGitHubApacheV2("JakeWharton/ThreeTenABP"));
    licenses.add(Licenses.fromGitHubApacheV2("JakeWharton/Timber"));
    licenses.add(Licenses.fromGitHubApacheV2("GrandCentrix/Tray"));
    licenses.add(Licenses.fromGitHubApacheV2("Twitter/Twitter-Text"));
    licenses.add(Licenses.fromGitHubMIT("Jhy/Jsoup"));
    licenses.add(Licenses.fromGitHubBSD("Bumptech/Glide"));
    licenses.add(Licenses.fromGitHubBSD("Facebook/Stetho"));

    // This repository does not have license file
    licenses.add(Licenses.fromGitHub("GabrieleMariotti/ChangelogLib", Licenses.LICENSE_APACHE_V2));

    // These 2 licenses have a different branch name
    licenses.add(Licenses.fromGitHubApacheV2("ReactiveX/RxAndroid", "2.x/" + Licenses.FILE_AUTO));
    licenses.add(Licenses.fromGitHubApacheV2("ReactiveX/RxJava", "2.x/" + Licenses.FILE_AUTO));

    licenses.add(Licenses.noLink("Library without a link to the license, like Google Play Services",
        "Author", "License name", "License content"));

    RecyclerView list = findViewById(R.id.list);
    list.setLayoutManager(new LinearLayoutManager(this));
    list.setHasFixedSize(true);
    list.setAdapter(new LicenseAdapter(licenses));
  }
}
