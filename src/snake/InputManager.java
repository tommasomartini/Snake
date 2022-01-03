package snake;

import java.util.TimerTask;

public class InputManager extends TimerTask {
	
	private TerminalManager terminalManager;
	
	public InputManager() {
		terminalManager = TerminalManager.getTerminalManager();
	}
	
	@Override
	public void run() {
		terminalManager.readInput();
	}
}
