/*
 * Copyright 2015 @author Unai Alegre 
 * 
 * This file is part of OCASE (Ontologies for Context-Aware Systems Engineering), a module 
 * of Modelio that helps the automatic generation of ontologies based on the Situational 
 * Parameters gathered during the requirements elicitation phase of a Context-Aware System (C-AS),
 *  using R-CASE (Requirements for Context-Aware Systems Engineering) tool. 
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
package edu.casetools.ocase.extensions.ontologies;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.modelio.vcore.smkernel.mapi.MObject;

import com.hp.hpl.jena.util.FileUtils;

import edu.casetools.ocase.utils.OntologiesUtils;
import edu.stanford.smi.protege.exception.OntologyLoadException;
import edu.stanford.smi.protege.util.MessageError;
import edu.stanford.smi.protegex.owl.ProtegeOWL;
import edu.stanford.smi.protegex.owl.jena.JenaOWLModel;

/**
 * The Class OntologiesUtils.
 */
public class OntologyManager {

    private static final Logger logger = Logger.getLogger(OntologyManager.class.getName());

    /**
     * Creates the file where the OWL ontology will be created.
     *
     * @return void
     */

    public void createOntology(File selectedFile) {
	try {
	    JenaOWLModel jModel = ProtegeOWL.createJenaOWLModel();
	    Collection<MessageError> errors = new ArrayList<>();

	    String name = OntologiesUtils.getInstance().getProjectName();
	    // Stablish default namespace
	    jModel.getNamespaceManager().setDefaultNamespace("http://www.owl-ontologies.com/" + name + ".owl#");

	    ArrayList<MObject> contextualEntities = (ArrayList<MObject>) OntologiesUtils.getInstance()
		    .getContextualEntities();

	    for (MObject SituationalParameter : contextualEntities) {
		jModel.createOWLNamedClass(SituationalParameter.getName().replaceAll("\\s+", ""));
	    }

	    jModel.save(selectedFile.toURI(), FileUtils.langXMLAbbrev, errors);

	} catch (OntologyLoadException oe) {
	    logger.log(Level.SEVERE, oe.getMessage(), oe);
	}

    }

}
