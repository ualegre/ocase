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
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.FileDialog;

import edu.casetools.ocase.module.i18n.I18nMessageService;
import edu.casetools.ocase.utils.OntologiesUtils;

/**
 * The Class OntologyManager enables to create a OWL file based on the context
 * of the system.
 */
public class OntologyFileDialog {
    private static final Logger logger = Logger.getLogger(OntologyFileDialog.class.getName());
    private OntologyManager manager;
    private FileDialog fileDialog;

    /**
     * Instantiates a new ontology manager.
     */
    public OntologyFileDialog() {
	fileDialog = new FileDialog(Display.getCurrent().getActiveShell(), SWT.SAVE);
	manager = new OntologyManager();
	createFileFilters();
	fileDialog.setFilterPath(System.getProperty("user.home"));
	openDialog();

    }

    private void createFileFilters() {
	String name = OntologiesUtils.getInstance().getProjectName() + "_"
		+ I18nMessageService.getString("Dialogs.Ontology.Name");
	fileDialog.setFileName(name + ".owl"); // It should be //
	// <<projectName>>Ontology.owl
	fileDialog.setFilterNames(
		new String[] { I18nMessageService.getString("Dialogs.Ontology.Name"), "All Files (*.*)" });
	fileDialog.setFilterExtensions(new String[] { "*.owl", "*.*" });
    }

    /**
     * Opens the file chooser where the owl file is going to be saved.
     */
    public void openDialog() {
	String fileLocation = fileDialog.open();
	if (fileLocation != null) {
	    File file = new File(fileLocation);
	    if (createFile(file))
		manager.createOntology(file);
	    else {
		openDialog();
	    }
	}
    }

    private boolean createFile(File file) {
	boolean result = false;

	try {
	    if (file.exists()) {
		result = existingFile(file);
	    } else {
		file.createNewFile();
		result = true;
	    }
	} catch (IOException e) {
	    logger.log(Level.SEVERE, e.getMessage(), e);
	    MessageDialog.openInformation(null, I18nMessageService.getString("Dialog.Error"), e.getMessage());
	}
	return result;
    }

    private boolean existingFile(File file) {
	String create = "Dialogs.Ontology.Create";
	String existing = "Dialogs.Ontology.Existing";
	return MessageDialog.openConfirm(null, I18nMessageService.getString(create),
		file.getAbsolutePath() + "\n" + I18nMessageService.getString(existing));

    }

}
