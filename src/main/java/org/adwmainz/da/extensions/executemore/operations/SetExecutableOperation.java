/**
 * SetExecutableOperation.java - is an implementation of a ro.sync.ecss.extensions.api.AuthorOperation which adds a custom operation to the Oxygen XML editor that
 *  makes a local file executable. It is one of the main classes within the ExecuteMoreXtension developed at the Digital Academy of the Academy of Sciences and
 *  Literature | Mainz.
 * @author Patrick D. Brookshire
 * @version 0.1.0
 */
package org.adwmainz.da.extensions.executemore.operations;

import java.io.File;

import org.adwmainz.da.extensions.askmore.utils.ArgumentParser;
import org.adwmainz.da.extensions.executemore.utils.ExecuteMoreArgumentProvider;
import org.adwmainz.da.extensions.executemore.utils.IOUtils;

import ro.sync.ecss.extensions.api.ArgumentDescriptor;
import ro.sync.ecss.extensions.api.ArgumentsMap;
import ro.sync.ecss.extensions.api.AuthorAccess;
import ro.sync.ecss.extensions.api.AuthorOperation;
import ro.sync.ecss.extensions.api.AuthorOperationException;

public class SetExecutableOperation implements AuthorOperation {

	// argument descriptions as displayed in oXygen
	protected ArgumentDescriptor[] arguments;

	public SetExecutableOperation() {
		arguments = new ArgumentDescriptor[] {
				ExecuteMoreArgumentProvider.getExecutableFilePathArgumentDescriptor()
		};
	}

	@Override
	public String getDescription() {
		return "Makes a local file executable";
	}

	@Override
	public void doOperation(AuthorAccess authorAccess, ArgumentsMap args) throws IllegalArgumentException, AuthorOperationException {
		// get params
		String filePath;
		try {
			filePath = ArgumentParser.getValidString(args, ExecuteMoreArgumentProvider.ARGUMENT_FILE_PATH);
		} catch (IllegalArgumentException ex) {
			filePath = IOUtils.askForFilePath();
		}

		// make file executable
		File file = new File(filePath);
		try {
			if (!file.setExecutable(true, false))
				throw new IllegalArgumentException("Cannot make "+filePath+" executable");
		} catch (Exception ex) {
			throw new IllegalArgumentException("Cannot make "+filePath+" executable", ex);
		}
	}

	@Override
	public ArgumentDescriptor[] getArguments() {
		return arguments;
	}

}
