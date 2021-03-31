package org.adwmainz.da.extensions.executemore.utils;

import ro.sync.ecss.extensions.api.ArgumentDescriptor;
import ro.sync.ecss.extensions.api.AuthorConstants;

public class ExecuteMoreArgumentProvider {

	// constant list of all argument names used within this ExecuteMoreXtension
	public static final String ARGUMENT_ACTION_IDS = "actionIDs";
	public static final String ARGUMENT_ALLOW_OVERWRITING = "allowOverwriting";
	public static final String ARGUMENT_CONTENT = "content";
	public static final String ARGUMENT_DISPLAY_PATH = "displayPath";
	public static final String ARGUMENT_ENCODING = "encoding";
	public static final String ARGUMENT_FILE_PATH = "filePath";
	public static final String ARGUMENT_MAC_ACTION_ID = "macActionIDs";
	public static final String ARGUMENT_UNIX_ACTION_ID = "unixActionIDs";
	public static final String ARGUMENT_WIN_ACTION_ID = "windowsActionIDs";
	
	// basic ArgumentDescriptor getter methods	
	public static ArgumentDescriptor getAllowOverwritingArgumentDescriptor() {
		return new ArgumentDescriptor(
				ARGUMENT_ALLOW_OVERWRITING, 
				ArgumentDescriptor.TYPE_CONSTANT_LIST, 
				"Decides whether an existing file should be overwritten or not.", 
				new String[] {
						AuthorConstants.ARG_VALUE_TRUE, 
						AuthorConstants.ARG_VALUE_FALSE},
				AuthorConstants.ARG_VALUE_FALSE);
	}
	
	public static ArgumentDescriptor getDisplayPathArgumentDescriptor() {
		return new ArgumentDescriptor(
				ARGUMENT_DISPLAY_PATH, 
				ArgumentDescriptor.TYPE_CONSTANT_LIST, 
				"Decides whether the path of a successfully saved file should be displayed or not.", 
				new String[] {
						AuthorConstants.ARG_VALUE_TRUE, 
						AuthorConstants.ARG_VALUE_FALSE},
				AuthorConstants.ARG_VALUE_FALSE);
	}
	
	public static ArgumentDescriptor getEncodingArgumentDescriptor() {
		return new ArgumentDescriptor(
				ARGUMENT_ENCODING, 
				ArgumentDescriptor.TYPE_CONSTANT_LIST, 
				"The encoding that should be used.", 
				IOUtils.getSupportedCharsetNames(),
				IOUtils.DEFAULT_CHARSET_NAME);
	}
	
	public static ArgumentDescriptor getExecutableFilePathArgumentDescriptor() {
		return new ArgumentDescriptor(
				ARGUMENT_FILE_PATH, 
				ArgumentDescriptor.TYPE_STRING, 
				"The absolute path to the file that should be made executable (opens a file chooser if left empty).");
	}
	
	public static ArgumentDescriptor getReplaceFilePathArgumentDescriptor() {
		return new ArgumentDescriptor(
				ARGUMENT_FILE_PATH, 
				ArgumentDescriptor.TYPE_STRING, 
				"The absolute path to the file that should replace the currently displayed document (opens a file chooser if left empty).");
	}
	
	public static ArgumentDescriptor getSaveFilePathArgumentDescriptor() {
		return new ArgumentDescriptor(
				ARGUMENT_FILE_PATH, 
				ArgumentDescriptor.TYPE_STRING, 
				"The absolute path the currently displayed document should be saved to (opens a file chooser if left empty).");
	}
	
	public static ArgumentDescriptor getMacActionIdArgumentDescriptor() {
		return new ArgumentDescriptor(
				ARGUMENT_MAC_ACTION_ID, 
				ArgumentDescriptor.TYPE_STRING, 
				"The ID(s) of one or more actions that should be invoked from a Mac OS separated by new lines");
	}
	
	public static ArgumentDescriptor getUnixActionIdArgumentDescriptor() {
		return new ArgumentDescriptor(
				ARGUMENT_UNIX_ACTION_ID, 
				ArgumentDescriptor.TYPE_STRING, 
				"The ID(s) of one or more actions that should be invoked from a Unix OS separated by new lines");
	}
	
	public static ArgumentDescriptor getWinActionIdArgumentDescriptor() {
		return new ArgumentDescriptor(
				ARGUMENT_WIN_ACTION_ID, 
				ArgumentDescriptor.TYPE_STRING, 
				"The ID(s) of one or more actions that should be invoked from a Windows OS separated by new lines");
	}
	
}
