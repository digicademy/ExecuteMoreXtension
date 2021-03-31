/**
 * ExecuteOSSpecificActionsOperation - is an extension of a ro.sync.ecss.extensions.commons.operations.ExecuteMultipleActionsOperation which adds a custom
 *  operation to the Oxygen XML Editor that executes different actions based on the current operating system. It is one of the main classes within the
 *   ExecuteMoreXtension developed at the Digital Academy of the Academy of Sciences and Literature | Mainz.
 * @author Patrick D. Brookshire
 * @version 0.1.3
 */
package org.adwmainz.da.extensions.executemore.operations;

import org.adwmainz.da.extensions.askmore.models.HashedArgumentsMap;
import org.adwmainz.da.extensions.askmore.utils.ArgumentParser;
import org.adwmainz.da.extensions.executemore.utils.ExecuteMoreArgumentProvider;
import org.adwmainz.da.extensions.executemore.utils.OSUtils;

import ro.sync.ecss.extensions.api.ArgumentDescriptor;
import ro.sync.ecss.extensions.api.ArgumentsMap;
import ro.sync.ecss.extensions.api.AuthorAccess;
import ro.sync.ecss.extensions.api.AuthorOperationException;
import ro.sync.ecss.extensions.commons.operations.ExecuteMultipleActionsOperation;

public class ExecuteOSSpecificActionsOperation extends ExecuteMultipleActionsOperation {
	
	// field
	protected ArgumentDescriptor[] arguments;

	// constructor
	/**
	 * Creates a new ExecuteOSSpecificOperation
	 */
	public ExecuteOSSpecificActionsOperation() {
		super();

		// set arguments
		arguments = new ArgumentDescriptor[] {
				ExecuteMoreArgumentProvider.getWinActionIdArgumentDescriptor(),
				ExecuteMoreArgumentProvider.getMacActionIdArgumentDescriptor(),
				ExecuteMoreArgumentProvider.getUnixActionIdArgumentDescriptor()
		};
	}

	@Override
	public String getDescription() {
		return "Executes a different action defined in the associated document type depending on the operating system.";
	}

	@Override
	public void doOperation(AuthorAccess authorAccess, ArgumentsMap args)
			throws IllegalArgumentException, AuthorOperationException {
		// get param depending on the OS
		String actionIds;
		if (OSUtils.isWindows())
			actionIds = ArgumentParser.getValidString(args, ExecuteMoreArgumentProvider.ARGUMENT_WIN_ACTION_ID);
		else if (OSUtils.isMac())
			actionIds = ArgumentParser.getValidString(args, ExecuteMoreArgumentProvider.ARGUMENT_MAC_ACTION_ID);
		else if (OSUtils.isUnix())
			actionIds = ArgumentParser.getValidString(args, ExecuteMoreArgumentProvider.ARGUMENT_UNIX_ACTION_ID);
		else
			throw new AuthorOperationException("Your OS is not supported!");
		
		// try to invoke the respective actions using the super class
		HashedArgumentsMap parsedArgs = new HashedArgumentsMap();
		parsedArgs.put(ExecuteMoreArgumentProvider.ARGUMENT_ACTION_IDS, actionIds);
		super.doOperation(authorAccess, parsedArgs);
	}

	@Override
	public ArgumentDescriptor[] getArguments() {
		return arguments;
	}

}
