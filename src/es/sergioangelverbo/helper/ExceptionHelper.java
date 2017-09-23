package es.sergioangelverbo.helper;

import javax.swing.JOptionPane;

public class ExceptionHelper {
	private ExceptionHelper() {
	}

	public static void ShowExceptionClose(Exception ex) {
		StringBuffer buffer = new StringBuffer();
		buffer.append("The following exception happened: ");
		buffer.append(ex.getClass().getName());
		buffer.append("\n\nThe message given is: ");
		buffer.append(ex.getMessage());
		buffer.append("\n\n");
		buffer.append(ex.getStackTrace());
		buffer.append("\n\nThe program will now close");
		
		infoBox(buffer.toString(), "EXCEPTION");
		System.exit(1);
	}

	public static void infoBox(String infoMessage, String titleBar) {
		JOptionPane.showMessageDialog(null, infoMessage, titleBar, JOptionPane.ERROR_MESSAGE);
	}
}
