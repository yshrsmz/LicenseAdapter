package net.yslibrary.licenseadapter.example;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;
import net.yslibrary.licenseadapter.LicenseAdapter;
import net.yslibrary.licenseadapter.LicenseEntry;
import net.yslibrary.licenseadapter.Licenses;

public class MainActivity extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    List<LicenseEntry> licenses = new ArrayList<>();

    licenses.add(Licenses.noContent("Android SDK", "Google Inc.",
        "https://developer.android.com/sdk/terms.html"));
    licenses.add(Licenses.noContent("Fabric", "Twitter", "https://fabric.io/terms"));
    licenses.add(Licenses.fromGitHub("jakewharton/butterknife"));
    licenses.add(Licenses.fromGitHub("gabrielemariotti/changeloglib", Licenses.LICENSE_APACHE_V2));
    licenses.add(Licenses.fromGitHub("hdodenhof/CircleImageView"));
    licenses.add(Licenses.fromGitHub("google/dagger", Licenses.FILE_TXT));
    licenses.add(
        Licenses.fromGitHub("bumptech/glide", Licenses.NAME_BSD, Licenses.FILE_NO_EXTENSION));
    licenses.add(Licenses.fromGitHub("evant/gradle-retrolambda"));
    licenses.add(Licenses.fromGitHub("google/gson", Licenses.FILE_NO_EXTENSION));
    licenses.add(Licenses.fromGitHub("immutables/immutables", Licenses.FILE_NO_EXTENSION));
    licenses.add(Licenses.fromGitHub("jhy/jsoup", Licenses.NAME_MIT, Licenses.FILE_NO_EXTENSION));
    licenses.add(Licenses.fromGitHub("square/leakcanary"));
    licenses.add(Licenses.fromGitHub("square/okhttp"));
    licenses.add(Licenses.fromGitHub("realm/realm-java", Licenses.FILE_NO_EXTENSION));
    licenses.add(Licenses.fromGitHub("yqritc/RecyclerView-MultipleViewTypesAdapter",
        Licenses.FILE_NO_EXTENSION));
    licenses.add(Licenses.fromGitHub("yshrsmz/LicenseAdapter", Licenses.FILE_NO_EXTENSION));
    licenses.add(Licenses.fromGitHub("ReactiveX/RxAndroid", Licenses.FILE_NO_EXTENSION));
    licenses.add(Licenses.fromGitHub("ReactiveX/RxJava", Licenses.FILE_NO_EXTENSION));
    licenses.add(
        Licenses.fromGitHub("facebook/stetho", Licenses.NAME_BSD, Licenses.FILE_NO_EXTENSION));
    licenses.add(Licenses.fromGitHub("jakewharton/threetenabp"));
    licenses.add(Licenses.fromGitHub("jakewharton/timber"));
    licenses.add(Licenses.fromGitHub("grandcentrix/tray"));
    licenses.add(Licenses.fromGitHub("twitter/twitter-text", Licenses.FILE_NO_EXTENSION));
    //licenses.add(Licenses.fromGitHub("jakewharton/timber", Licenses.FILE_TXT));
    //licenses.add(Licenses.fromGitHub("jakewharton/butterknife", Licenses.FILE_TXT));

    LicenseAdapter adapter = new LicenseAdapter(licenses);
    RecyclerView list = (RecyclerView) findViewById(R.id.list);
    list.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
    list.setAdapter(adapter);

    Licenses.load(licenses);
  }
}
