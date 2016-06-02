/*
 * Copyright 2015 @author Unai Alegre 
 * 
 * This file is part of OCASE (Ontologies for Context-Aware Systems Engineering), a module 
 * of Modelio that helps the automatic generation of ontologies based on the Situational 
 * Parameters gathered during the requirements elicitation phase of a Context-Aware System (C-AS),
 *  using RCASE (Requirements for Context-Aware Systems Engineering) tool. 
 * 
 * OCASE is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * OCASE is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with OCASE.  If not, see <http://www.gnu.org/licenses/>.
 * 
 */
package edu.casetools.ocase.module.impl;

import org.modelio.api.model.IModelingSession;
import org.modelio.api.module.AbstractJavaModule;
import org.modelio.api.module.IModuleAPIConfiguration;
import org.modelio.api.module.IModuleSession;
import org.modelio.api.module.IModuleUserConfiguration;
import org.modelio.api.module.IParameterEditionModel;
import org.modelio.metamodel.mda.ModuleComponent;

/**
 * Implementation of the IModule interface. <br>
 * All Modelio java modules should inherit from this class.
 * 
 */
@SuppressWarnings("all")
public class OCaseModule extends AbstractJavaModule {

    private OCasePeerModule peerModule = null;

    private OCaseSession session = null;

    /**
     * Builds a new module.
     * <p>
     * <p>
     * This constructor must not be called by the user. It is automatically
     * invoked by Modelio when the module is installed, selected or started.
     * 
     * @param modelingSession
     *            the modeling session this module is deployed into.
     * @param model
     *            the model part of this module.
     * @param moduleConfiguration
     *            the module configuration, to get and set parameter values from
     *            the module itself.
     * @param peerConfiguration
     *            the peer module configuration, to get and set parameter values
     *            from another module.
     */
    public OCaseModule(IModelingSession modelingSession, ModuleComponent moduleComponent,
	    IModuleUserConfiguration moduleConfiguration, IModuleAPIConfiguration peerConfiguration) {
	super(modelingSession, moduleComponent, moduleConfiguration);
	this.session = new OCaseSession(this);
	this.peerModule = new OCasePeerModule(this, peerConfiguration);
    }

    @Override
    public OCasePeerModule getPeerModule() {
	return this.peerModule;
    }

    /**
     * Return the session attached to the current module.
     * <p>
     * <p>
     * This session is used to manage the module lifecycle by declaring the
     * desired implementation on start, select... methods.
     */
    @Override
    public IModuleSession getSession() {
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
	return "/res/icons/module_16.png";
    }

}
