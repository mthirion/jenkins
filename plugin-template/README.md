* Jenkins plugin template

This is a template that can be used to develop Jenkins plugins

* Description

This plugin template defines:

- A build step, implemented with "MyJenkinsBuildStep"
  This build step uses 2 variables defined in the corresponding config.jelly and associated with 2 help files
  The only thing that the build step does is to print the value of the 2 variables

- A post-build Notifier, implemented with "MyJenkinsPostBuildNotifier"
  The step uses a "recipient" variable defines in a config.jelly and associated with an helper file
  The Notifier only logs the value of the recipient


