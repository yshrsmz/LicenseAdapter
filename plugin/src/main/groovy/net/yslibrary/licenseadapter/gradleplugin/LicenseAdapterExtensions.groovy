package net.yslibrary.licenseadapter.gradleplugin

/**
 * Created by yshrsmz on 2017/01/05.*/
class LicenseAdapterExtensions {
  public static final String NAME = 'licenseAdapter'

  public File jsonOutput = new File("licenses.json")

  // include dependencies of a dependency
  public boolean includeIndirectDependency = true

  public String[] excludeDependencyIds = []

  public String testParam = "default"
}
