package net.yslibrary.licenseadapter;

import android.support.annotation.NonNull;
import android.support.annotation.WorkerThread;
import android.text.TextUtils;

public abstract class BaseLibrary implements Library {
  private final String name;
  private final String author;

  @NonNull protected License license;

  public BaseLibrary(@NonNull String name, @NonNull String author, @NonNull License license) {
    this.name = name;
    this.author = author;
    this.license = license;
  }

  @WorkerThread
  @Override
  public final void load() {
    if (hasContent() && TextUtils.isEmpty(license.text)) {
      doLoad();
    }
  }

  @WorkerThread
  protected abstract void doLoad();

  @NonNull
  @Override
  public String name() {
    return name;
  }

  @NonNull
  @Override
  public String author() {
    return author;
  }

  @NonNull
  @Override
  public License license() {
    return license;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    BaseLibrary that = (BaseLibrary) o;

    return name.equals(that.name) && author.equals(that.author) && license.equals(that.license);
  }

  @Override
  public int hashCode() {
    int result = name.hashCode();
    result = 31 * result + author.hashCode();
    result = 31 * result + license.hashCode();
    return result;
  }

  @Override
  public String toString() {
    return "BaseLibrary{" +
        "name='" + name + '\'' +
        ", author='" + author + '\'' +
        ", license=" + license +
        '}';
  }
}
