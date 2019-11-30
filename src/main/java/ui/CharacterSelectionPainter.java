package ui;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;

import characters.CharacterClass;
import engine.GameEngine;
import values.CharacterSelectionMenuParameters;

public class CharacterSelectionPainter {

	public void paintMenu(Graphics graphics, GameEngine game, int width, int height) {

		if (game.getMode() != GameEngine.Mode.SELECT_CHARACTER) {
			return;
		}

		Point menuPos = getMenuPosition(width, height);

		graphics.setColor(CharacterSelectionMenuParameters.BACKGROUND);
		graphics.fillRect(menuPos.x, menuPos.y, CharacterSelectionMenuParameters.WIDTH,
				CharacterSelectionMenuParameters.HEIGHT);
		graphics.setColor(CharacterSelectionMenuParameters.TEXT);

		graphics.setFont(new Font(Font.MONOSPACED, Font.BOLD, 14));

		int y = menuPos.y + CharacterSelectionMenuParameters.BORDER_SIZE + CharacterSelectionMenuParameters.LINE_HEIGHT;
		graphics.drawString("Select Character", menuPos.x + CharacterSelectionMenuParameters.BORDER_SIZE, y);

		y += CharacterSelectionMenuParameters.LINE_HEIGHT + CharacterSelectionMenuParameters.BORDER_SIZE;
		graphics.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 14));
		for (CharacterClass c : CharacterClass.values()) {
			String text = c.toString();

			if (c == game.getSelectedCharacter()) {
				text = "> " + text;
			} else {
				text = "  " + text;
			}

			graphics.drawString(text, menuPos.x + CharacterSelectionMenuParameters.BORDER_SIZE, y);
			y += CharacterSelectionMenuParameters.LINE_HEIGHT;
		}
	}

	public Point getMenuCenter(int width, int height) {
		return new Point(width / 2, height / 2);
	}

	public Point getMenuPosition(int width, int height) {
		Point center = getMenuCenter(width, height);
		int x = center.x - CharacterSelectionMenuParameters.WIDTH / 2;
		int y = center.y - CharacterSelectionMenuParameters.HEIGHT / 2;
		return new Point(Math.max(x, 0), Math.max(y, 0));
	}

}
