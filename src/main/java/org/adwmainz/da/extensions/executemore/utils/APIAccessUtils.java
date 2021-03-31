package org.adwmainz.da.extensions.executemore.utils;

import java.util.Map;

import ro.sync.ecss.extensions.api.AuthorOperationException;
import ro.sync.exml.workspace.api.editor.page.author.actions.AuthorActionsProvider;

public class APIAccessUtils {
	
	/**
	 * Returns the action with the given ID 
	 * @param actionsProvider an AuthorActionsProvider
	 * @param actionId the ID of aa common action or one that is defined in the current AuthorExtension
	 * @throws AuthorOperationException Thrown if no action with the given ID is a common action or defined in the current AuthorExtension
	 */
	public static Object getAction(AuthorActionsProvider actionsProvider, String actionId)
			throws AuthorOperationException {		
		// check if action is an AuthorExtensionAction
		Map<String, Object> authorExtensionActions = actionsProvider.getAuthorExtensionActions();
		if (authorExtensionActions != null) {
			Object action = authorExtensionActions.get(actionId);
			if (action != null)
				return action;
		}

		// check if action is an AuthorCommonAction
		Map<String, Object> authorCommonActions = actionsProvider.getAuthorCommonActions();
		if (authorCommonActions != null) {
			Object action = authorCommonActions.get(actionId);
			if (action != null)
				return action;
		}
		
		throw new AuthorOperationException("Could not find an action with the ID: \'" + actionId + "\'");
	}
	
}
