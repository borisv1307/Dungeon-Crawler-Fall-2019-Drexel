package ui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

import characters.CharacterClass;
import exceptions.LogicError;

public class CharacterPainter {

	public void paintPlayer(Graphics graphics, int playerX, int playerY, int tileWidth, int tileHeight,
			CharacterClass character) {

		switch (character) {
		case WARRIOR:
			paintWarrior(graphics, playerX, playerY, tileWidth, tileHeight);
			return;
		case ROGUE:
			paintRogue(graphics, playerX, playerY, tileWidth, tileHeight);
			return;
		case SORCERER:
			paintSorcerer(graphics, playerX, playerY, tileWidth, tileHeight);
			return;
		}

		throw new LogicError("unhandled character class");
	}

	public int getPlayerSize(int tileWidth, int tileHeight) {
		return Math.min(tileWidth, tileHeight);
	}

	public Point getPlayerUpperLeft(int tileX, int tileY, int tileWidth, int tileHeight, int size) {
		int x = tileX + tileWidth / 2 - size / 2;
		int y = tileY + tileHeight / 2 - size / 2;
		return new Point(x, y);
	}

	private void paintSorcerer(Graphics graphics, int playerX, int playerY, int tileWidth, int tileHeight) {
		graphics.setColor(Color.BLUE);
		int size = getPlayerSize(tileWidth, tileHeight);
		Point player = getPlayerUpperLeft(playerX, playerY, tileWidth, tileHeight, size);
		graphics.fillArc(player.x, player.y, size, size, 0, 360);
	}

	private void paintRogue(Graphics graphics, int playerX, int playerY, int tileWidth, int tileHeight) {
		graphics.setColor(Color.GREEN);
		int size = getPlayerSize(tileWidth, tileHeight);
		Point player = getPlayerUpperLeft(playerX, playerY, tileWidth, tileHeight, size);
		int[] xPoints = { player.x, player.x + size / 2, player.x + size };
		int[] yPoints = { player.y + size, player.y, player.y + size };
		graphics.fillPolygon(xPoints, yPoints, 3);
	}

	private void paintWarrior(Graphics graphics, int playerX, int playerY, int tileWidth, int tileHeight) {
		graphics.setColor(Color.RED);
		int size = getPlayerSize(tileWidth, tileHeight);
		Point player = getPlayerUpperLeft(playerX, playerY, tileWidth, tileHeight, size);
		graphics.fillRect(player.x, player.y, size, size);
	}

}
