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
package edu.casetools.ocase.modelio.menu;

import java.util.List;

import org.modelio.api.module.IModule;
import org.modelio.api.module.command.DefaultModuleCommandHandler;
import org.modelio.vcore.smkernel.mapi.MObject;

import edu.casetools.ocase.extensions.ontologies.OntologyFileDialog;

/**
 * The Class CreateOntologyFile creates a OWL ontology based on the context of
 * the project, from the contextual menu.
 */
public class CreateOntologyFile extends DefaultModuleCommandHandler {

    private OntologyFileDialog ontologyFileDialog;

    /**
     * Instantiates the class.
     */
    public CreateOntologyFile() {
	super();
	this.ontologyFileDialog = null;

    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.modelio.api.module.commands.DefaultModuleCommandHandler#accept(java
     * .util.List, org.modelio.api.module.IModule)
     */
    @Override
    public boolean accept(List<MObject> selectedElements, IModule module) {
	return true;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.modelio.api.module.commands.DefaultModuleCommandHandler#
     * actionPerformed (java.util.List, org.modelio.api.module.IModule)
     */
    @Override
    public void actionPerformed(List<MObject> selectedElements, IModule module) {
	if (null != this.ontologyFileDialog) {
	    this.ontologyFileDialog.openDialog();
	} else
	    this.ontologyFileDialog = new OntologyFileDialog();
    }

}
