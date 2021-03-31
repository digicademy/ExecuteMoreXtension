package org.adwmainz.da.extensions.executemore.utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileSystemView;

import org.adwmainz.da.extensions.askmore.utils.AskMoreArgumentProvider;

public class IOUtils {
	
	public static final String DEFAULT_CHARSET_NAME = "UTF-8";

	/**
	 * Returns an array of all supported Charset names
	 */
	public static String[] getSupportedCharsetNames() {
		List<String> encodings = new ArrayList<>();
		for (Charset charset: Charset.availableCharsets().values())
			encodings.add(charset.displayName());
		return encodings.toArray(new String[encodings.size()]);
	}
	
	/**
	 * Reads the content of a given file
	 * @param filePath the path to the file
	 * @param charsetName the name of the Charset used in the file
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public static String readFile(String filePath, String charsetName) throws FileNotFoundException, IOException {
		String content = "";
		try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(filePath), Charset.forName(charsetName)))) {
			String line;
			while ((line = reader.readLine()) != null)
			    content += line;
		}
		return content;
	}
	
	/**
	 * Returns a file path by using JFileChooser.showOpenDialog()
	 * @throws IllegalArgumentException THrown if the dialog is closed
	 */
	public static String askForFilePath()
			throws IllegalArgumentException {
		JFileChooser fileChooser = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
		if (fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION)
			return fileChooser.getSelectedFile().getAbsolutePath();
		throw new IllegalArgumentException(AskMoreArgumentProvider.getClosedDialogMessage());
	}
	
	/**
	 * Returns a file path by using JFileChooser.showSaveDialog()
	 * @throws IllegalArgumentException THrown if the dialog is closed
	 */
	public static String askForSaveFilePath()
			throws IllegalArgumentException {
		JFileChooser fileChooser = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
		if (fileChooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION)
			return fileChooser.getSelectedFile().getAbsolutePath();
		throw new IllegalArgumentException(AskMoreArgumentProvider.getClosedDialogMessage());
	}
	
	/**
	 * Writes content to a file
	 * @param filePath the path to the file
	 * @param content the content that soulf be written to the file
	 * @param charsetName the name of the Charset that should be used
	 * @param allowOverwriting Controls whether an existing file should be overwritten or not. Throws an IOException in this case if set to <code>false</code>.
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public static void writeToFile(String filePath, String content, String charsetName, boolean allowOverwriting)
			throws FileNotFoundException, IOException {
		File file = new File(filePath);

		if (!allowOverwriting && file.exists() && !file.isDirectory())
			throw new IOException(filePath+" exists");
		try (BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(filePath), Charset.forName(charsetName)))) {
			writer.write(content);
		}
	}

}
