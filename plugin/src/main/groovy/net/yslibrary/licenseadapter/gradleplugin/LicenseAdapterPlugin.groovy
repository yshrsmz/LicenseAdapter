package net.yslibrary.licenseadapter.gradleplugin

import org.gradle.api.Plugin
import org.gradle.api.Project

class LicenseAdapterPlugin implements Plugin<Project> {

  @Override
  void apply(Project project) {
    project.extensions.add(LicenseAdapterExtensions.NAME, LicenseAdapterExtensions)
    project.tasks.create(ListLicensesTask.NAME, ListLicensesTask)
  }
}