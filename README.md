* Jenkins plugin development

Jenkins plugins are developed using Extension points.

An Extension point is a category of objects belonging to the Jenkins model, like:
 SecurityRealm extension       Add a way to log in to Jenkins 
 Builder extension             Add a new build type or operation 
 BuildStep extension           Do something for every build  BuildStep    
 Publisher extension           Trigger some action after a build completes
 Trigger                       Trigger a build 
 Recorder                      Record some stat with every project build 
 ChangeLogAnnotator            Markup a ChangeLog message
 ManagementLink                Add a link to /manage

To create a plugin, use a class that implements (or a class that extends an implementation) the extension point interface (ExtensionPoint).
The Extension points all implements a method "perform()" that does the work.

The class extending the ExtensionPoint should also define an inner static class extending the corresponding descriptor (a class extending hudson.model.Descriptor) and maked with the @Extension annotation.
For example, for a class extension Builder, the corresponding descriptor class is BuildStepDescriptor<Builder>.

The descriptor makes the link between the plugin Form and the implementation of the plugin action.
When the user configures the project and enables the extension, the Extension is instanciated and the getDescriptor is called.
All descriptor have a "configure()" method that performs the configuration of the build step.

Displayed (dynamic) configuration parameters are set in a config.jelly file located in a package named after the Extension class in the src/main/resources directory.
A global.jelly can also be used for system-wide configuration properties.
A global index.jelly can also be used to add a general description to the plugin.
Helper htmls (help-<varName>.html) files located in the same directory can be used to associate an help message to the displayed fields.

When the configuration form is saved, Jenkins calls the constructor of the extension marked with the @DataBoundConstructor annotation, matching parameters by name.


* Writing a plugin

Plugins are developped using maven.
After the settings.xml is set with the proper plugin repository, a project can be initiated using "mvn -Pjenkins org.jenkins-ci.tools:maven-hpi-plugin:1.119:create"

This will create a directory named after the artifactId with a pom.xml pre-configured with the jenkins plugin that allows packaging the project as a .hpi file.

The Extensions are implemented in the "hudson.tasks" package.
Example:
  hudson.tasks.BuildTrigger
  hudson.tasks.BuildStep
  hudson.tasks.Notifier
  hudson.tasks.Publisher


