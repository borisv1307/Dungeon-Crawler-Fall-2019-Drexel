package ui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InOrder;
import org.mockito.Mockito;
import org.mockito.internal.util.reflection.Whitebox;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import characters.CharacterClass;
import exceptions.LogicError;

@RunWith(PowerMockRunner.class)
@PrepareForTest(CharacterClass.class)
public class CharacterPainterTest {

	private static final int TILE_X = 10;
	private static final int TILE_Y = 20;

	Graphics graphics;
	CharacterPainter characterPainter;

	@Before
	public void setUp() {
		characterPainter = new CharacterPainter();
		graphics = Mockito.mock(Graphics.class);
	}

	@Test(expected = LogicError.class)
	public void throw_logic_error_when_paint_unknown_character() {

		CharacterClass otherCharClass = PowerMockito.mock(CharacterClass.class);
		Whitebox.setInternalState(otherCharClass, "name", "OTHER_CHAR_CLASS");
		Whitebox.setInternalState(otherCharClass, "ordinal", 3);

		PowerMockito.mockStatic(CharacterClass.class);
		PowerMockito.when(CharacterClass.values()).thenReturn(new CharacterClass[] { CharacterClass.WARRIOR,
				CharacterClass.ROGUE, CharacterClass.SORCERER, otherCharClass });

		characterPainter.paintPlayer(graphics, TILE_X, TILE_Y, 30, 20, otherCharClass);
	}

	@Test
	public void paint_warrior_character() {

		characterPainter.paintPlayer(graphics, TILE_X, TILE_Y, 30, 20, CharacterClass.WARRIOR);

		InOrder inOrder = Mockito.inOrder(graphics);
		inOrder.verify(graphics).setColor(Color.RED);
		inOrder.verify(graphics).fillRect(TILE_X + 5, TILE_Y, 20, 20);
	}

	@Test
	public void paint_rogue_character() {

		characterPainter.paintPlayer(graphics, TILE_X, TILE_Y, 30, 20, CharacterClass.ROGUE);

		InOrder inOrder = Mockito.inOrder(graphics);
		inOrder.verify(graphics).setColor(Color.GREEN);
		int[] xPoints = { TILE_X + 5, TILE_X + 5 + 10, TILE_X + 5 + 20 };
		int[] yPoints = { TILE_Y + 20, TILE_Y, TILE_Y + 20 };
		inOrder.verify(graphics).fillPolygon(xPoints, yPoints, 3);
	}

	@Test
	public void paint_sorcerer_character() {

		characterPainter.paintPlayer(graphics, TILE_X, TILE_Y, 30, 20, CharacterClass.SORCERER);

		InOrder inOrder = Mockito.inOrder(graphics);
		inOrder.verify(graphics).setColor(Color.BLUE);
		inOrder.verify(graphics).fillArc(TILE_X + 5, TILE_Y, 20, 20, 0, 360);
	}

	@Test
	public void calculate_player_size_when_width_is_smaller() {
		int actual = characterPainter.getPlayerSize(10, 15);
		assertThat(actual, equalTo(10));
	}

	@Test
	public void calculate_player_size_when_height_is_smaller() {
		int actual = characterPainter.getPlayerSize(15, 10);
		assertThat(actual, equalTo(10));
	}

	@Test
	public void calculate_player_upper_left_when_width_is_smaller() {
		int tileWidth = 10;
		int tileHeight = 30;

		Point actual = characterPainter.getPlayerUpperLeft(TILE_X, TILE_Y, tileWidth, tileHeight, tileWidth);
		assertThat(actual, equalTo(new Point(TILE_X, TILE_Y + 10)));
	}

	@Test
	public void calculate_player_upper_left_when_height_is_smaller() {
		int tileWidth = 30;
		int tileHeight = 10;

		Point actual = characterPainter.getPlayerUpperLeft(TILE_X, TILE_Y, tileWidth, tileHeight, tileHeight);
		assertThat(actual, equalTo(new Point(TILE_X + 10, TILE_Y)));
	}

}
