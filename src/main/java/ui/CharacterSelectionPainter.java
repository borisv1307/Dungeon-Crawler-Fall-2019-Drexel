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

		paintMenuBackground(graphics, menuPos);
		paintMenuText(graphics, menuPos, game.getSelectedCharacter());
	}
	
	private void paintMenuBackground(Graphics graphics, Point menuPos) {
		graphics.setColor(CharacterSelectionMenuParameters.BACKGROUND);
		graphics.fillRect(menuPos.x, menuPos.y, CharacterSelectionMenuParameters.WIDTH,
				CharacterSelectionMenuParameters.HEIGHT);
	}
	
	private void paintMenuText(Graphics graphics, Point menuPos, CharacterClass selectedCharacter) {
		graphics.setColor(CharacterSelectionMenuParameters.TEXT);
		
		int y = menuPos.y + CharacterSelectionMenuParameters.BORDER_SIZE + CharacterSelectionMenuParameters.LINE_HEIGHT;
		paintMenuHeader(graphics, menuPos, y);
		paintMenuEntries(graphics, menuPos, selectedCharacter, y);
	}
	
	private void paintMenuHeader(Graphics graphics, Point menuPos, int y) {
		graphics.setFont(new Font(Font.MONOSPACED, Font.BOLD, 14));
		graphics.drawString("Select Character", menuPos.x + CharacterSelectionMenuParameters.BORDER_SIZE, y);
	}
	
	private void paintMenuEntries(Graphics graphics, Point menuPos, CharacterClass selectedCharacter, int y) {
		y += CharacterSelectionMenuParameters.LINE_HEIGHT + CharacterSelectionMenuParameters.BORDER_SIZE;
		graphics.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 14));
		for (CharacterClass c : CharacterClass.values()) {
			paintMenuEntry(graphics, c, selectedCharacter, menuPos.x + CharacterSelectionMenuParameters.BORDER_SIZE, y);
			y += CharacterSelectionMenuParameters.LINE_HEIGHT;
		}
	}
	
	private void paintMenuEntry(Graphics graphics, CharacterClass character, CharacterClass selectedCharacter, int x, int y) {
			String text = character.toString();

			if (character == selectedCharacter) {
				text = "> " + text;
			} else {
				text = "  " + text;
			}

			graphics.drawString(text, x, y);
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
