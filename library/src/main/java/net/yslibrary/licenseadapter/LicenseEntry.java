package net.yslibrary.licenseadapter;

/**
 * Created by a12897 on 2016/04/18.
 */
public interface LicenseEntry {

  void load();

  boolean isLoaded();

  boolean hasContent();

  String name();

  String version();

  String author();

  String link();

  License license();
}
