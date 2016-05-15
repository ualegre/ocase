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
package edu.casetools.ocase.utils;

import java.util.ArrayList;
import java.util.List;

import org.modelio.api.model.IModelingSession;
import org.modelio.api.modelio.Modelio;
import org.modelio.metamodel.mda.Project;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.metamodel.uml.statik.GeneralClass;
import org.modelio.metamodel.uml.statik.Package;
import org.modelio.vcore.smkernel.mapi.MObject;

import edu.casetools.rcase.module.api.RCaseStereotypes;
import edu.casetools.rcase.module.impl.RCasePeerModule;

/**
 * The Class OntologiesUtils.
 */
public class OntologiesUtils {
    public static final String MODULE_NAME = "LocalModule";
    private static OntologiesUtils instance = null;

    /**
     * Gets the single instance of OntologiesUtils.
     *
     * @return single instance of OntologiesUtils
     */
    public static OntologiesUtils getInstance() {
	if (instance == null) {
	    instance = new OntologiesUtils();
	}
	return instance;
    }

    /**
     * Gets the project name.
     *
     * @return the project name
     */
    public String getProjectName() {
	IModelingSession session = Modelio.getInstance().getModelingSession();
	for (MObject rootObj : session.getModel().getModelRoots())
	    return rootObj.getName();
	return null;
    }

    /**
     * Gets the contextual entities.
     *
     * @return the contextual entities
     */
    public List<MObject> getContextualEntities() {
	List<MObject> list = new ArrayList<>();
	list = OntologiesUtils.getInstance().getAllElementsStereotypedAs(list,
		RCaseStereotypes.STEREOTYPE_SITUATIONAL_PARAMETER);
	return list;
    }

    /**
     * Gets all the elements in the project by its stereotype
     *
     * @return the contextual entities
     */
    public List<MObject> getAllElementsStereotypedAs(List<MObject> list, String stereotype) {
	List<MObject> allElements = getAllElements();

	for (MObject object : allElements) {
	    if (((ModelElement) object).isStereotyped(RCasePeerModule.MODULE_NAME, stereotype))
		list.add(object);
	}

	return list;
    }

    /**
     * Gets all the elements in the project
     *
     * @return the contextual entities
     */
    public List<MObject> getAllElements() {
	ArrayList<MObject> vector = new ArrayList<>();
	IModelingSession session = Modelio.getInstance().getModelingSession();
	for (MObject rootObj : session.getModel().getModelRoots()) {
	    if (((rootObj instanceof GeneralClass) || (rootObj instanceof Project))
		    && (!rootObj.getName().equals(MODULE_NAME))) {

		for (MObject child : rootObj.getCompositionChildren()) {
		    vector = getElementsFromMObject(vector, child);
		}

	    }
	}
	return vector;
    }

    /**
     * Gets all the child elements from a given element
     *
     * @return the contextual entities
     */
    private ArrayList<MObject> getElementsFromMObject(ArrayList<MObject> vector, MObject project) {

	ArrayList<MObject> auxiliarVector = vector;
	for (MObject child : project.getCompositionChildren()) {

	    if (child instanceof Package) {
		auxiliarVector = getElementsFromMObject(vector, child);
	    }
	    if (auxiliarVector != null)
		auxiliarVector.add(child);
	}
	return auxiliarVector;

    }

}
