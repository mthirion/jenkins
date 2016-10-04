package integration.redhat.org.jenkinsplugin;
import hudson.Launcher;
import hudson.Extension;
import hudson.FilePath;
import hudson.util.FormValidation;
import hudson.model.AbstractProject;
import hudson.model.Run;
import hudson.model.TaskListener;
import hudson.tasks.Builder;
import hudson.tasks.BuildStepDescriptor;
import jenkins.tasks.SimpleBuildStep;
import net.sf.json.JSONObject;
import org.kohsuke.stapler.DataBoundConstructor;
import org.kohsuke.stapler.StaplerRequest;


/* This class will represent a Build step */
public class MyJenkinsBuildStep extends Builder implements SimpleBuildStep {

	
	/* ---------------
	   FORM PARAMETERS 
	   ---------------
	*/
    private final String var1;
    private final String var2;

    /**
     * We'll use this from the <tt>config.jelly</tt>.
     */
    public String getVar1() {
        return var1;
    }
    public String getVar2() {
        return var2;
    }  
    
    // Fields in config.jelly must match the parameter names in the "DataBoundConstructor"
    @DataBoundConstructor
    public MyJenkinsBuildStep(String var1, String var2) {
        this.var1 = var1;
        this.var2 = var2;
    }

  

	/* -----------
	   MAIN ACTION 
	   -----------
	*/
    @Override
    public void perform(Run<?,?> build, FilePath workspace, Launcher launcher, TaskListener listener) {
        // This is the "build" method

    	// use the console logger
    	listener.getLogger().println("build with: ");
    	listener.getLogger().println("var1 : " + this.getVar1());
    	listener.getLogger().println("var2 : " + this.getVar2());

    	    	
    }

    // Overridden for better type safety.
    // If your plugin doesn't really define any property on Descriptor,
    // you don't have to do this.
    @Override
    public DescriptorImpl getDescriptor() {
        return (DescriptorImpl)super.getDescriptor();
    }

    
    
    
    /**
     * Descriptor for {@link JenkinsBuilder}. Used as a singleton.
     * The class is marked as public so that it can be accessed from views.
     */
    @Extension // This indicates to Jenkins that this is an implementation of an extension point.
    public static final class DescriptorImpl extends BuildStepDescriptor<Builder> {
        /**
         * To persist global configuration information,
         * simply store it in a field and call save().
         *
         * <p>
         * If you don't want fields to be persisted, use <tt>transient</tt>.
         */
        private String apimanUrl;
        private String organizationId;
        
        public void setApimanUrl(String apimanurl){
        	this.apimanUrl = apimanurl;
        }
        
        public void setOrnganizationId(String orgnaizationid){
        	this.organizationId = orgnaizationid;
        }
        

        /**
         * In order to load the persisted global configuration, you have to 
         * call load() in the constructor.
         */
        public DescriptorImpl() {
            load();
        }


        public boolean isApplicable(Class<? extends AbstractProject> aClass) {
            // Indicates that this builder can be used with all kinds of project types 
            return true;
        }

        /**
         The name of the plugin as it will appear on the plugin list
         */
        public String getDisplayName() {
            return "Mthirion Jenkins BuildStep plugin";
        }
        
        

        @Override
        public boolean configure(StaplerRequest req, JSONObject formData) throws FormException {

        	this.apimanUrl = formData.getString(apimanUrl);
        	this.organizationId = formData.getString(organizationId);
        	
            // req.bindJSON(this, formData);
            // this methods binds the json objects to the target type (here this class)
            // setters methods are used to fill the json properties into object properties 
            
            // To persist global configuration information,
            // set that to properties and call save().
            save();
            
            // do some validation then configure using super.
            return super.configure(req,formData);
        }

    }
}

