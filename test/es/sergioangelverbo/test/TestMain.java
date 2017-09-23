package es.sergioangelverbo.test;

import es.sergioangelverbo.helper.ExceptionHelper;

public class TestMain {

	public static void main(String[] args) {
		try {
		EntityTest t = new EntityTest();
		t.setVisible(true);
		} 
		catch (Exception e) {
			ExceptionHelper.ShowExceptionClose(e);
		}
	}

}
