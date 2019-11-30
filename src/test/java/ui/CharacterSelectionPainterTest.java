package ui;

import static org.junit.Assert.*;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InOrder;
import org.mockito.Mockito;

import characters.CharacterClass;
import engine.GameEngine;
import engine.GameEngine.Mode;
import values.CharacterSelectionMenuParameters;

public class CharacterSelectionPainterTest {

	private final int WIDTH = 900;
	private final int HEIGHT = 600;

	Graphics graphics;
	GameEngine game;
	CharacterSelectionPainter characterSelectionPainter;

	@Before
	public void setUp() {
		characterSelectionPainter = new CharacterSelectionPainter();
		graphics = Mockito.mock(Graphics.class);
		game = Mockito.mock(GameEngine.class);
	}

	public void paint_character_selection_menu(CharacterClass selectedClass, String... menuEntries) {

		Mockito.when(game.getMode()).thenReturn(Mode.SELECT_CHARACTER);
		Mockito.when(game.getSelectedCharacter()).thenReturn(selectedClass);

		characterSelectionPainter.paintMenu(graphics, game, WIDTH, HEIGHT);

		int menuPosX = WIDTH / 2 - CharacterSelectionMenuParameters.WIDTH / 2;
		int menuPosY = HEIGHT / 2 - CharacterSelectionMenuParameters.HEIGHT / 2;

		InOrder inOrder = Mockito.inOrder(graphics);
		inOrder.verify(graphics).setColor(CharacterSelectionMenuParameters.BACKGROUND);
		inOrder.verify(graphics).fillRect(menuPosX, menuPosY, CharacterSelectionMenuParameters.WIDTH,
				CharacterSelectionMenuParameters.HEIGHT);
		inOrder.verify(graphics).setColor(CharacterSelectionMenuParameters.TEXT);
		inOrder.verify(graphics).setFont(new Font(Font.MONOSPACED, Font.BOLD, 14));
		inOrder.verify(graphics).drawString("Select Character", menuPosX + CharacterSelectionMenuParameters.BORDER_SIZE,
				menuPosY + CharacterSelectionMenuParameters.BORDER_SIZE + CharacterSelectionMenuParameters.LINE_HEIGHT);
		inOrder.verify(graphics).setFont(new Font(Font.MONOSPACED, Font.PLAIN, 14));
		inOrder.verify(graphics).drawString(menuEntries[0], menuPosX + CharacterSelectionMenuParameters.BORDER_SIZE,
				menuPosY + CharacterSelectionMenuParameters.BORDER_SIZE * 2
						+ CharacterSelectionMenuParameters.LINE_HEIGHT * 2);
		inOrder.verify(graphics).drawString(menuEntries[1], menuPosX + CharacterSelectionMenuParameters.BORDER_SIZE,
				menuPosY + CharacterSelectionMenuParameters.BORDER_SIZE * 2
						+ CharacterSelectionMenuParameters.LINE_HEIGHT * 3);
		inOrder.verify(graphics).drawString(menuEntries[2], menuPosX + CharacterSelectionMenuParameters.BORDER_SIZE,
				menuPosY + CharacterSelectionMenuParameters.BORDER_SIZE * 2
						+ CharacterSelectionMenuParameters.LINE_HEIGHT * 4);
	}

	@Test
	public void paint_character_selection_menu_warrior() {
		paint_character_selection_menu(CharacterClass.WARRIOR, "> WARRIOR", "  ROGUE", "  SORCERER");
	}

	@Test
	public void paint_character_selection_menu_rogue() {
		paint_character_selection_menu(CharacterClass.ROGUE, "  WARRIOR", "> ROGUE", "  SORCERER");
	}

	@Test
	public void paint_character_selection_menu_sorcerer() {
		paint_character_selection_menu(CharacterClass.SORCERER, "  WARRIOR", "  ROGUE", "> SORCERER");
	}

	@Test
	public void do_not_paint_menu_in_play_level_mode() {

		Mockito.when(game.getMode()).thenReturn(Mode.PLAY_LEVEL);

		characterSelectionPainter.paintMenu(graphics, game, WIDTH, HEIGHT);

		Mockito.verifyZeroInteractions(graphics);
	}

	@Test
	public void menu_center_of_100x200_is_50_100() {
		Point center = characterSelectionPainter.getMenuCenter(100, 200);
		assertThat(center, equalTo(new Point(50, 100)));
	}

	@Test
	public void menu_center_of_300x200_is_150_100() {
		Point center = characterSelectionPainter.getMenuCenter(300, 200);
		assertThat(center, equalTo(new Point(150, 100)));
	}

	@Test
	public void menu_position_in_200x200_is_26_52() {
		Point pos = characterSelectionPainter.getMenuPosition(200, 200);
		assertThat(pos, equalTo(new Point(26, 53)));
	}

	@Test
	public void menu_position_in_50x50_is_0_0() {
		Point pos = characterSelectionPainter.getMenuPosition(50, 50);
		assertThat(pos, equalTo(new Point(0, 0)));
	}
}
