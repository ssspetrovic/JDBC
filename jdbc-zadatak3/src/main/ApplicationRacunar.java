package main;

import ui_handler.MainUIHandler;

public class ApplicationRacunar {

	public static void main(String[] args) {

		System.setProperty(org.slf4j.impl.SimpleLogger.DEFAULT_LOG_LEVEL_KEY, "WARN");

		MainUIHandler mainUIHandler = new MainUIHandler();
		mainUIHandler.handleMainMenu();

	}
	

}
