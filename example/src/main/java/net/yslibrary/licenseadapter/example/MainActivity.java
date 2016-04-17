package net.yslibrary.licenseadapter.example;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;
import net.yslibrary.licenseadapter.BaseLicenseEntry;
import net.yslibrary.licenseadapter.LicenseAdapter;
import net.yslibrary.licenseadapter.Licenses;

public class MainActivity extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    List<BaseLicenseEntry> dataset = new ArrayList<>();

    dataset.add(Licenses.fromGitHub("google/dagger"));
    dataset.add(Licenses.fromGitHub("jakewharton/timber"));
    dataset.add(Licenses.fromGitHub("reactivex/rxjava"));
    dataset.add(Licenses.fromGitHub("reactivex/rxandroid"));
    dataset.add(Licenses.fromGitHub("square/retrofit"));
    dataset.add(Licenses.fromGitHub("yshrsmz/simple-preferences"));

    LicenseAdapter adapter = new LicenseAdapter(dataset);
    RecyclerView list = (RecyclerView) findViewById(R.id.list);
    list.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
    list.setAdapter(adapter);

    new LicenseTask().execute(dataset.toArray(new BaseLicenseEntry[dataset.size()]));
  }

  private class LicenseTask extends AsyncTask<BaseLicenseEntry, Void, Void> {

    @Override
    protected Void doInBackground(BaseLicenseEntry... params) {
      for (BaseLicenseEntry entry : params) {
        entry.load();
      }
      return null;
    }
  }
}
