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

import org.modelio.api.log.ILogService;
import org.modelio.api.modelio.Modelio;
import org.modelio.api.module.DefaultModuleSession;
import org.modelio.api.module.ModuleException;
import org.modelio.vbasic.version.Version;

/**
 * Implementation of the IModuleSession interface. <br>
 * This default implementation may be inherited by the module developers in
 * order to simplify the code writing of the module session.
 */
public class OCaseSession extends DefaultModuleSession {

    /**
     * Constructor.
     * 
     * @param module
     *            the Module this session is instanciated for.
     */
    public OCaseSession(OCaseModule module) {
	super(module);
    }

    /**
     * @see org.modelio.api.module.DefaultModuleSession#start()
     */
    @Override
    public boolean start() throws ModuleException {
	// get the version of the module
	Version moduleVersion = this.module.getVersion();

	// get the Modelio log service
	ILogService logService = Modelio.getInstance().getLogService();

	String message = "Start of " + this.module.getName() + " " + moduleVersion;
	logService.info(this.module, message);
	return super.start();
    }

    public static boolean install(String modelioPath, String mdaPath) throws ModuleException {
	return DefaultModuleSession.install(modelioPath, mdaPath);
    }

}
