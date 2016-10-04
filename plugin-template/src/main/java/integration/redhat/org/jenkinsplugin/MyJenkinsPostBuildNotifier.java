package integration.redhat.org.jenkinsplugin;

import org.kohsuke.stapler.DataBoundConstructor;
import hudson.Extension;
import hudson.Launcher;
import hudson.model.BuildListener;
import hudson.model.AbstractBuild;
import hudson.model.AbstractProject;
import hudson.tasks.BuildStepDescriptor;
import hudson.tasks.BuildStepMonitor;
import hudson.tasks.Notifier;
import hudson.tasks.Publisher;


import net.sf.json.JSONObject;

import org.kohsuke.stapler.StaplerRequest;

public class MyJenkinsPostBuildNotifier extends Notifier 
{
	private final String recipient;

	@DataBoundConstructor
	public MyJenkinsPostBuildNotifier(final String recipient) {
	this.recipient = recipient;
	}

	public String getRecipient() {
		return recipient;
	}

	@Override
	public boolean perform(
		@SuppressWarnings("rawtypes") final AbstractBuild build,
		final Launcher launcher, final BuildListener listener) {
		
		// logic to be executed
		listener.getLogger().println("Notifing the recipient " + getRecipient());

		return true;
	}

	@Override
	public DescriptorImpl getDescriptor() {
		return (DescriptorImpl) super.getDescriptor();
	}

	public BuildStepMonitor getRequiredMonitorService() {
		return BuildStepMonitor.NONE;
	}

	
	
	@Extension
	public static class DescriptorImpl extends BuildStepDescriptor<Publisher> {

		/**
		 * Global configuration information variables. If you don't want fields
		 * to be persisted, use <tt>transient</tt>.
		 */
		private String recipient;
		
		public void setRecipient(String recipient) {
			this.recipient = recipient;
		}
		/**
		 * In order to load the persisted global configuration, you have to call
		 * load() in the constructor.
		 */
		public DescriptorImpl() {
			load();
		}
	
		@Override
		public boolean configure(StaplerRequest req, JSONObject formData)
				throws FormException {
			// To persist global configuration information, set that to
			// properties and call save().
			recipient = formData.getString("recipient");
			setRecipient(recipient);
			
			save();
			return super.configure(req, formData);
		}
	
		@Override
		public boolean isApplicable(@SuppressWarnings("rawtypes") Class<? extends AbstractProject> jobType) {
			// Indicates that this builder can be used with all kinds of project types.
			return true;
		}
	
		@Override
		public String getDisplayName() {
			return "Mthirion Notifier";
		}
	

	}

}
