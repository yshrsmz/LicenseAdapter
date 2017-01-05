package net.yslibrary.licenseadapter.gradleplugin

import org.gradle.api.artifacts.ResolvedArtifact

/**
 * Created by yshrsmz on 2017/01/05.*/
class DependencyInfo {
  public String group = ""
  public String name = ""
  public String version = ""

  public String filename = ""

  public String dependencyName = ""
  public String dependencyUrl = ""

  public String licenseName = ""
  public String licenseUrl = ""

  static DependencyInfo fromArtifact(ResolvedArtifact artifact) {
    def result = new DependencyInfo()

    result.group = artifact.moduleVersion.id.group
    result.name = artifact.moduleVersion.id.name
    result.version = artifact.moduleVersion.id.version
    result.filename = artifact.file

    return result
  }

  String artifactIdentifier() {
    return "$group:$name:$version"
  }

  @Override
  String toString() {
    return "$group:$name:$version\n$dependencyName($dependencyUrl)\n$licenseName($licenseUrl)"
  }
}
