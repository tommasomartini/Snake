package snake;

import snake.GameManager.Direction;

import com.googlecode.lanterna.TerminalFacade;
import com.googlecode.lanterna.input.Key;
import com.googlecode.lanterna.input.Key.Kind;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.ScreenCharacterStyle;
import com.googlecode.lanterna.terminal.Terminal.Color;
import com.googlecode.lanterna.terminal.swing.SwingTerminal;

public class TerminalManager {
	
	private static TerminalManager terminalManager;
	
	private GameManager gameManager;
	
	private static SwingTerminal terminal;
	private static Screen screen;
	
	private boolean pause = false;
	
	private TerminalManager() {
		
		gameManager = GameManager.getGameManager();
		
		terminal = TerminalFacade.createSwingTerminal();
		screen = new Screen(terminal);
		terminal.enterPrivateMode();
		screen.startScreen();
	}
	
	public void readInput() {
		
		Key key = terminal.readInput();
		
		if(key != null) {
			
			Kind kind = key.getKind();
						
			switch (kind) {
			case ArrowUp:
				
				gameManager.turn(Direction.DIRECTION_NORTH);
				
				break;
				
			case ArrowDown:
				
				gameManager.turn(Direction.DIRECTION_SOUTH);
				
				break;

			case ArrowLeft:
				
				gameManager.turn(Direction.DIRECTION_WEST);
				
				break;

			case ArrowRight:
				
				gameManager.turn(Direction.DIRECTION_EAST);
				
				break;

			case NormalKey:
				
				char pressedChar = key.getCharacter();
								
				if (pressedChar == 'q') {
					Snake.stopGame();
				}
				
				else if (pressedChar == ' ') {
					pause = !pause;
					if (pause)
						Snake.pause();
					
					else if (!pause)
						Snake.resume();
				}
				
				else if (pressedChar == 'n')
					Snake.newGame();
				
				else if (pressedChar == 'e')
					Snake.quitGame();
					
				break;

			default:
				break;
			}
		}
	}
	
	public void drawOnTerminal(String playGround) {
		
		screen.clear();

		int currentRow = 0;
		int currentColumn = 0;
		
		for (int i = 0; i < playGround.length(); i++) {
			
			char currentChar = playGround.charAt(i);
			
			if (currentChar == '\n') {
				currentRow++;
				currentColumn = 0;
			}
			
			else {
				String s = "" + currentChar;
				screen.putString(currentColumn++, currentRow, s, Color.WHITE, Color.BLACK, ScreenCharacterStyle.Bold);
			}
		}
		
		screen.putString(0, currentRow++, "\'backspace\': pause/resume game", Color.WHITE, Color.BLACK, ScreenCharacterStyle.Reverse);
		screen.putString(0, currentRow++, "\'n\': start a new game", Color.WHITE, Color.BLACK, ScreenCharacterStyle.Reverse);
		screen.putString(0, currentRow++, "\'q\': end this game", Color.WHITE, Color.BLACK, ScreenCharacterStyle.Reverse);
		screen.putString(0, currentRow++, "\'e\': quit game and close this program", Color.WHITE, Color.BLACK, ScreenCharacterStyle.Reverse);
		
		screen.refresh();
	}
	
	public void endGame() {

		screen.putString(Variables.PLAYGROUND_WIDTH / 2 - 2, Variables.PLAYGROUND_HEIGTH / 2, " GAME ", Color.WHITE, Color.RED, ScreenCharacterStyle.Bold);
		screen.putString(Variables.PLAYGROUND_WIDTH / 2 - 2, (Variables.PLAYGROUND_HEIGTH / 2) + 1, " OVER ", Color.WHITE, Color.RED, ScreenCharacterStyle.Bold);
		screen.putString(Variables.PLAYGROUND_WIDTH / 2 - 4, (Variables.PLAYGROUND_HEIGTH / 2) + 2, " score: " + gameManager.getCounter() + " ", Color.WHITE, Color.RED, ScreenCharacterStyle.Bold);
		screen.refresh();
	}
	
	public void pauseGame() {
		
		screen.putString(Variables.PLAYGROUND_WIDTH / 2 - 2, (Variables.PLAYGROUND_HEIGTH / 2), " PAUSE ", Color.BLACK, Color.WHITE, ScreenCharacterStyle.Bold);
		screen.refresh();
	}

	public static TerminalManager getTerminalManager() {
		if (terminalManager == null) 
			terminalManager = new TerminalManager();
		return terminalManager;
	}
}
