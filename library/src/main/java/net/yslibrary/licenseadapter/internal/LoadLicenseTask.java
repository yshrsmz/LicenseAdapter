package net.yslibrary.licenseadapter.internal;

import android.os.AsyncTask;
import net.yslibrary.licenseadapter.Library;

public class LoadLicenseTask extends AsyncTask<Library, Void, Void> {
  @Override
  protected Void doInBackground(Library... params) {
    for (Library library : params) {
      library.load();
    }
    return null;
  }
}
