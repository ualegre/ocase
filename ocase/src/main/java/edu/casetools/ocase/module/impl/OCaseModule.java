package edu.casetools.ocase.module.impl;

import org.modelio.api.module.AbstractJavaModule;
import org.modelio.api.module.context.IModuleContext;
import org.modelio.api.module.lifecycle.IModuleLifeCycleHandler;
import org.modelio.api.module.parameter.IParameterEditionModel;

import edu.casetools.ocase.module.api.OCaseResources;

/**
 * Implementation of the IModule interface. <br>
 * All Modelio java modules should inherit from this class.
 * 
 */
public class OCaseModule extends AbstractJavaModule {

    private OCasePeerModule peerModule = null;

    private OCaseSession session = null;

    private static OCaseModule instance;

    /**
     * Builds a new module.
     * <p>
     * <p>
     * This constructor must not be called by the user. It is automatically
     * invoked by Modelio when the module is installed, selected or started.
     * 
     * @param moduleContext
     *            context of the module, needed to access Modelio features.
     */
    public OCaseModule(IModuleContext moduleContext) {
	super(moduleContext);
	this.session = new OCaseSession(this);
	this.peerModule = new OCasePeerModule(this, moduleContext.getPeerConfiguration());
	instance = this;
    }

    public OCaseModule getInstance() {
	return instance;
    }

    @Override
    public OCasePeerModule getPeerModule() {
	return this.peerModule;
    }

    /**
     * Return the lifecycle handler attached to the current module.
     * <p>
     * <p>
     * This handler is used to manage the module lifecycle by declaring the
     * desired implementation on start, select... methods.
     */
    @Override
    public IModuleLifeCycleHandler getLifeCycleHandler() {
	return this.session;
    }

    /**
     * @see org.modelio.api.module.AbstractJavaModule#getParametersEditionModel()
     */
    @Override
    public IParameterEditionModel getParametersEditionModel() {
	if (this.parameterEditionModel == null) {
	    this.parameterEditionModel = super.getParametersEditionModel();
	}
	return this.parameterEditionModel;
    }

    @Override
    public String getModuleImagePath() {
	return OCaseResources.ICON_MODULE;
    }

    @Override
    public int hashCode() {
	final int prime = 31;
	int result = super.hashCode();
	result = prime * result + ((peerModule == null) ? 0 : peerModule.hashCode());
	result = prime * result + ((session == null) ? 0 : session.hashCode());
	return result;
    }

    @Override
    public boolean equals(Object obj) {
	if (this == obj)
	    return true;
	if (!super.equals(obj))
	    return false;
	if (getClass() != obj.getClass())
	    return false;
	OCaseModule other = (OCaseModule) obj;
	if (peerModule == null) {
	    if (other.peerModule != null)
		return false;
	} else if (!peerModule.equals(other.peerModule))
	    return false;
	if (session == null) {
	    if (other.session != null)
		return false;
	} else if (!session.equals(other.session))
	    return false;
	return true;
    }
}
