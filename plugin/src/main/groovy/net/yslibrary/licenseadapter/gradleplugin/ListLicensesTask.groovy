package net.yslibrary.licenseadapter.gradleplugin

import groovy.util.slurpersupport.GPathResult
import org.gradle.api.DefaultTask
import org.gradle.api.Project
import org.gradle.api.artifacts.Configuration
import org.gradle.api.artifacts.Dependency
import org.gradle.api.artifacts.ExternalDependency
import org.gradle.api.artifacts.ResolvedArtifact
import org.gradle.api.logging.Logger
import org.gradle.api.logging.Logging
import org.gradle.api.tasks.TaskAction
import org.xml.sax.helpers.DefaultHandler

/**
 * Created by yshrsmz on 2017/01/05.*/
class ListLicensesTask extends DefaultTask {
  public static final String NAME = "listLicenses"

  ListLicensesTask() {
    description = "list licenses of project's dependencies"
    group = 'Help'
  }

  @TaskAction
  def listLicenses() {
    LicenseAdapterExtensions ext = project.extensions.findByType(LicenseAdapterExtensions)
    println("Test plugin ${ext.testParam}")

    Map<Project, Set<Configuration>> projectConfigs = project.allprojects.collectEntries { proj ->
      [proj,
       proj.configurations.plus(proj.buildscript.configurations)
           .findAll { it.name.matches(/^(?:release\w*)?[cC]ompile$/) } as Set]
      // compile, releaseCompile, releaseProductionCompile, and so on.
    }

    Set<String> deps = projectConfigs.collectMany { project, configurations ->
      return configurations.collectMany {
        config -> return config.dependencies.collect { "$it.group:$it.name" }
      }
    } as Set

    Set<DependencyInfo> actualDeps = projectConfigs.collectMany { project, configurations ->
      return configurations.collectMany { config ->
        return config.resolvedConfiguration.resolvedArtifacts
            .findAll { ext.includeIndirectDependency || deps.contains("$it.moduleVersion.id.group:$it.moduleVersion.id.name") }
            .findAll { !ext.excludeDependencyIds.contains("$it.moduleVersion.id.group:$it.moduleVersion.id.name")}
            .collect { DependencyInfo.fromArtifact(it) }
      }
    } as Set

    actualDeps.each { depInfo ->
      if (depInfo.version == 'unspecified') {
        return
      }

      Dependency pomDependency = project.dependencies.create("${depInfo.artifactIdentifier()}@pom")
      Configuration pomConfiguration = project.configurations.detachedConfiguration(pomDependency)

      pomConfiguration.resolve().each {
        project.logger.info("POM: ${it}")
      }

      File pStream
      try {
        pStream = pomConfiguration.resolve().asList().first()
      } catch (Exception e) {
        project.logger.warn("Unable to retrieve license for ${depInfo.artifactIdentifier()}")
        return
      }

      XmlSlurper slurper = new XmlSlurper(true, false)
      slurper.setErrorHandler(new DefaultHandler())
      GPathResult xml = slurper.parse(pStream)

      depInfo.dependencyName = xml.name.text()
      depInfo.dependencyUrl = xml.url.text()

      xml.licenses.license.each {
        if (!depInfo.licenseName) {
          depInfo.licenseName = it.name.text().trim()
          depInfo.licenseUrl = it.url.text().trim()
        }
      }

      println("${depInfo.toString()}")
    }


  }
}
