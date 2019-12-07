package engine;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import java.awt.Component;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import parser.LevelCreator;
import tiles.TileType;
import ui.GameFrame;
import wrappers.JOptionPaneWrapper;
import wrappers.RandomNumberWrapper;

public class GameEngineTest {

    private static final int ZERO = 0;
    private static final int ONE = 1;
    private static final int TWO = 2;


    GameEngine gameEngine;
    RandomNumberWrapper randomNumber;
    JOptionPaneWrapper jOptionPaneWrapper;



    @Before
    public void setUp() throws Exception {
        LevelCreator levelCreator = Mockito.mock(LevelCreator.class);
        RandomNumberWrapper randomNumber = Mockito.mock(RandomNumberWrapper.class);
        JOptionPaneWrapper jOptionPaneWrapper =Mockito.mock(JOptionPaneWrapper.class) ;
        this.randomNumber=randomNumber;
        this.jOptionPaneWrapper = jOptionPaneWrapper;
        gameEngine = new GameEngine(levelCreator, randomNumber, jOptionPaneWrapper);
        int level = 1;
        Mockito.verify(levelCreator, Mockito.times(level)).createLevel(gameEngine, level);
    }

    @Test
    public void run() {
        GameFrame gameFrame = Mockito.mock(GameFrame.class);
        Component component = Mockito.mock(Component.class);
        Mockito.when(gameFrame.getComponents()).thenReturn(new Component[] {
            component
        });
        gameEngine.run(gameFrame);
        Mockito.verify(component, Mockito.times(1)).repaint();
    }

    @Test
    public void add_and_get_tile() {
        TileType tileType = TileType.PASSABLE;
        gameEngine.addTile(ZERO, ONE, TileType.PASSABLE);
        TileType actual = gameEngine.getTileFromCoordinates(ZERO, ONE);
        assertThat(actual, equalTo(tileType));
    }

    @Test
    public void set_and_get_horizontal_dimension() {
        gameEngine.setLevelHorizontalDimension(ONE);
        int actual = gameEngine.getLevelHorizontalDimension();
        assertThat(actual, equalTo(ONE));
    }

    @Test
    public void set_and_get_vertical_dimension() {
        gameEngine.setLevelVerticalDimension(ONE);
        int actual = gameEngine.getLevelVerticalDimension();
        assertThat(actual, equalTo(ONE));
    }

    @Test
    public void add_and_get_player_coordinates() {
        TileType tileType = TileType.PLAYER;
        gameEngine.addTile(ZERO, ONE, tileType);
        int actualX = gameEngine.getPlayerXCoordinate();
        int actualY = gameEngine.getPlayerYCoordinate();
        assertThat(actualX, equalTo(ZERO));
        assertThat(actualY, equalTo(ONE));
    }


    @Test
    public void set_and_get_exit() {
        boolean exit = true;
        gameEngine.setExit(exit);
        boolean actual = gameEngine.isExit();
        assertThat(actual, equalTo(exit));
    }
    @Test
    public void random_Number_Generator() {
        gameEngine.addTile(ONE, ONE, TileType.PLAYER);
        gameEngine.addTile(ZERO, ONE, TileType.PASSABLE);
        gameEngine.addTile(ONE, ZERO, TileType.BLOCKER);

        Mockito.when(randomNumber.nextInt(16)).thenReturn(10);
        Mockito.when(randomNumber.nextInt(8)).thenReturn(10);
        gameEngine.generatingRandomBlockers(randomNumber);

        assertThat(10, equalTo(gameEngine.randomXCoordinatesArray.get(0)));
        assertThat(10, equalTo(gameEngine.randomYCoordinatesArray.get(0)));
    }
    @Test
    public void updating_blocker_tile_as_passable_after_each_move_if_player_moves_into_empty_space() {

        gameEngine.addTile(ONE, ONE, TileType.PLAYER);
        gameEngine.addTile(ZERO, ONE, TileType.PASSABLE);
        gameEngine.addTile(ONE, ZERO, TileType.BLOCKER);
        gameEngine.updateBlockerTile(1);
        TileType actual = gameEngine.getTileFromCoordinates(ONE, ZERO);
        assertThat(actual, equalTo(TileType.PASSABLE));


    }
    @Test
    public void generating_blocker_at_random_position_after_each_move_if_player_moves_into_empty_space() {

        gameEngine.addTile(ONE, ONE, TileType.PLAYER);
        gameEngine.addTile(ZERO, ONE, TileType.PASSABLE);
        gameEngine.addTile(ONE, ZERO, TileType.BLOCKER);

        Mockito.when(randomNumber.nextInt(16)).thenReturn(2);
        Mockito.when(randomNumber.nextInt(8)).thenReturn(2);
        gameEngine.generatingRandomBlockers(randomNumber);
        TileType actual = gameEngine.getTileFromCoordinates(TWO, TWO);
        assertThat(actual, equalTo(TileType.BLOCKER));

    }
    @Test
    public void displaying_number_of_moves_when_game_is_over() {
    	gameEngine.displayGameStatus(1, jOptionPaneWrapper);
    	Mockito.verify(jOptionPaneWrapper,Mockito.times(1)).showMessage(1);
    }


}