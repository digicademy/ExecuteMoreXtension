/**
 * SaveToFileOperation.java - is an implementation of a ro.sync.ecss.extensions.api.AuthorOperation which adds a custom operation to the Oxygen XML editor that
 *  saves the current document to a file. It is one of the main classes within the ExecuteMoreXtension developed at the Digital Academy of the Academy of Sciences
 *  and Literature | Mainz.
 * @author Patrick D. Brookshire
 * @version 0.1.0
 */
package org.adwmainz.da.extensions.executemore.operations;

import java.io.IOException;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import org.adwmainz.da.extensions.askmore.utils.APIAccessUtils;
import org.adwmainz.da.extensions.askmore.utils.ArgumentParser;
import org.adwmainz.da.extensions.executemore.utils.ExecuteMoreArgumentProvider;
import org.adwmainz.da.extensions.executemore.utils.IOUtils;

import ro.sync.ecss.extensions.api.ArgumentDescriptor;
import ro.sync.ecss.extensions.api.ArgumentsMap;
import ro.sync.ecss.extensions.api.AuthorAccess;
import ro.sync.ecss.extensions.api.AuthorDocumentController;
import ro.sync.ecss.extensions.api.AuthorOperation;
import ro.sync.ecss.extensions.api.AuthorOperationException;

public class SaveToFileOperation implements AuthorOperation {

	// argument descriptions as displayed in oXygen
	protected ArgumentDescriptor[] arguments;
	
	/**
	 * Creates a new SaveToFileOperation
	 */
	public SaveToFileOperation() {
		// set argument descriptions
		arguments = new ArgumentDescriptor[] {
				ExecuteMoreArgumentProvider.getSaveFilePathArgumentDescriptor(),
				ExecuteMoreArgumentProvider.getEncodingArgumentDescriptor(),
				ExecuteMoreArgumentProvider.getAllowOverwritingArgumentDescriptor(),
				ExecuteMoreArgumentProvider.getDisplayPathArgumentDescriptor()
		};
	}

	@Override
	public String getDescription() {
		return "Saves the current document to the local file system";
	}
	
	@Override
	public void doOperation(AuthorAccess authorAccess, ArgumentsMap args)
			throws IllegalArgumentException, AuthorOperationException {
		// get params
		String filePath;
		try {
			filePath = ArgumentParser.getValidString(args, ExecuteMoreArgumentProvider.ARGUMENT_FILE_PATH);
		} catch (IllegalArgumentException ex) {
			filePath = IOUtils.askForSaveFilePath();
		}
		String encoding = ArgumentParser.getValidString(args, ExecuteMoreArgumentProvider.ARGUMENT_ENCODING);
		boolean allowOverwriting = ArgumentParser.getValidBoolean(args, ExecuteMoreArgumentProvider.ARGUMENT_ALLOW_OVERWRITING, false);
		boolean displayPath = ArgumentParser.getValidBoolean(args, ExecuteMoreArgumentProvider.ARGUMENT_DISPLAY_PATH, false);
		
		// get document controller
		AuthorDocumentController documentController = authorAccess.getDocumentController();
		
		// serialize current doc
		String serializedDoc = APIAccessUtils.serializeAuthorNode(documentController, documentController.getAuthorDocumentNode());
		
		// write to file
		try {
			IOUtils.writeToFile(filePath, serializedDoc, encoding, allowOverwriting);
		} catch (IOException ex) {
			throw new IllegalArgumentException(ex);
		}
		
		// display success message
		if (displayPath) {
			// load localized data
			ResourceBundle rb = ResourceBundle.getBundle("org.adwmainz.da.extensions.executemore.resources.DialogTextBundle");
			JOptionPane.showMessageDialog(null, String.format(rb.getString("SUCCESSFULLY_SAVED_CONTENT_TO_FILE"), filePath));
		}
	}
	
	@Override
	public ArgumentDescriptor[] getArguments() {
		return arguments;
	}

}
