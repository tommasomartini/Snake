package snake;

import java.util.TimerTask;

public class SnakeTimerTask extends TimerTask {

	public enum GamePhase {
		GAME_PHASE_PLAYING,
		GAME_PHASE_PAUSE,
		GAME_PHASE_END;
	}

	private GameManager gameManager;
	private GUIManager guiManager;
	private TerminalManager terminalManager;

	private GamePhase currentPhase;

	public SnakeTimerTask() {

		currentPhase = GamePhase.GAME_PHASE_PLAYING;
		gameManager = GameManager.getGameManager();
		guiManager = GUIManager.getGUIManager();
		terminalManager = TerminalManager.getTerminalManager();
	}

	@Override
	public void run() {
		
		switch (currentPhase) {
		
		case GAME_PHASE_PLAYING:
			gameManager.move();
			guiManager.draw();
			break;

		case GAME_PHASE_PAUSE:
			break;

		case GAME_PHASE_END:
			terminalManager.endGame();
			break;

		default:
			break;
		}
	}

	public void setCurrentPhase(GamePhase currentPhase) {
		this.currentPhase = currentPhase;
	}

}
