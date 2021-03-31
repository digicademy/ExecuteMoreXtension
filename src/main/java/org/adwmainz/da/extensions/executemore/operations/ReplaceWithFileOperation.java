/**
 * ReplaceWithFileOperation.java - is a class for replacing the content of a document opened in oXygen with the content of a local file. It is one of the main classes
 *  within the ExecuteMoreXtension developed at the Digital Academy of the Academy of Sciences and Literature | Mainz.
 * @author Patrick D. Brookshire
 * @version 0.1.0
 */
package org.adwmainz.da.extensions.executemore.operations;

import java.io.IOException;

import org.adwmainz.da.extensions.askmore.models.HashedArgumentsMap;
import org.adwmainz.da.extensions.askmore.utils.ArgumentParser;
import org.adwmainz.da.extensions.executemore.utils.ExecuteMoreArgumentProvider;
import org.adwmainz.da.extensions.executemore.utils.IOUtils;

import ro.sync.ecss.extensions.api.ArgumentDescriptor;
import ro.sync.ecss.extensions.api.ArgumentsMap;
import ro.sync.ecss.extensions.api.AuthorAccess;
import ro.sync.ecss.extensions.api.AuthorOperationException;
import ro.sync.ecss.extensions.commons.operations.ReplaceContentOperation;

public class ReplaceWithFileOperation extends ReplaceContentOperation {

	// argument descriptions as displayed in oXygen
	protected ArgumentDescriptor[] arguments;
	
	// constructor
	/**
	 * Creates a new ReplaceWithFileOperation
	 */
	public ReplaceWithFileOperation() {
		arguments = new ArgumentDescriptor[] {
				ExecuteMoreArgumentProvider.getReplaceFilePathArgumentDescriptor(),
				ExecuteMoreArgumentProvider.getEncodingArgumentDescriptor()
		};
	}
	
	// overridden methods
	@Override
	public String getDescription() {
		return "Replaces the content of the current Document with the content from an external resource";
	}

	@Override
	public void doOperation(AuthorAccess authorAccess, ArgumentsMap args)
			throws IllegalArgumentException, AuthorOperationException {
		// get params
		String filePath;
		try {
			filePath = ArgumentParser.getValidString(args, ExecuteMoreArgumentProvider.ARGUMENT_FILE_PATH);
		} catch (IllegalArgumentException ex) {
			filePath = IOUtils.askForFilePath();
		}
		String encoding = ArgumentParser.getValidString(args, ExecuteMoreArgumentProvider.ARGUMENT_ENCODING);
		
		// try to get content
		String content = "";
		try {
			content = IOUtils.readFile(filePath, encoding);
		} catch (IOException ex) {
			throw new IllegalArgumentException(ex);
		}
		
		// invoke main action from super class
		HashedArgumentsMap parsedArgs = new HashedArgumentsMap();
		parsedArgs.put(ExecuteMoreArgumentProvider.ARGUMENT_CONTENT, content);
		super.doOperation(authorAccess, parsedArgs);		
	}

	@Override
	public ArgumentDescriptor[] getArguments() {
		return arguments;
	}

}
