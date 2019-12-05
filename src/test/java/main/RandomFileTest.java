package main;

import java.io.IOException;

import org.junit.Test;
import org.mockito.Mockito;

import launcher.RandomFile;
import wrappers.PrintWritterWrapper;
import wrappers.SecureRandomWrapper;

public class RandomFileTest {
	RandomFile randomfile = new RandomFile();

	@Test
	public void rows_random_number_check() {

		SecureRandomWrapper secureRandomWrapper = Mockito.mock(SecureRandomWrapper.class);
		randomfile.getRandomNonRepeatingIntegers(50, 2, 9, secureRandomWrapper);
		Mockito.verify(secureRandomWrapper, Mockito.times(50)).nextInt(9, 2);

	}

	@Test
	public void coulms_random_number_check() {

		SecureRandomWrapper secureRandomWrapper = Mockito.mock(SecureRandomWrapper.class);
		randomfile.getRandomNonRepeatingIntegers(50, 2, 19, secureRandomWrapper);
		Mockito.verify(secureRandomWrapper, Mockito.times(50)).nextInt(19, 2);

	}

	@Test
	public void verifying_player_writing_status() throws IOException {
		PrintWritterWrapper printWritterWrapper = Mockito.mock(PrintWritterWrapper.class);
		randomfile.generateFile(printWritterWrapper);
		Mockito.verify(printWritterWrapper, Mockito.times(1)).print("P");

	}

	@Test
	public void verifying_enemy_writing_status() throws IOException {
		PrintWritterWrapper printWritterWrapper = Mockito.mock(PrintWritterWrapper.class);
		randomfile.generateFile(printWritterWrapper);
		Mockito.verify(printWritterWrapper, Mockito.times(2)).print("T");
	}

	@Test
	public void verifying_energy_writing_status() throws IOException {
		PrintWritterWrapper printWritterWrapper = Mockito.mock(PrintWritterWrapper.class);
		randomfile.generateFile(printWritterWrapper);
		Mockito.verify(printWritterWrapper, Mockito.times(5)).print("E");
	}

	@Test
	public void verifying_wall_tile_writing_status() throws IOException {
		PrintWritterWrapper printWritterWrapper = Mockito.mock(PrintWritterWrapper.class);
		randomfile.generateFile(printWritterWrapper);
		Mockito.verify(printWritterWrapper, Mockito.times(56)).print("X");
	}

	@Test
	public void verifying_empty_tile_writing_status() throws IOException {
		PrintWritterWrapper printWritterWrapper = Mockito.mock(PrintWritterWrapper.class);
		randomfile.generateFile(printWritterWrapper);
		Mockito.verify(printWritterWrapper, Mockito.times(136)).print(" ");
	}

	@Test
	public void verifying_next_line_writing_status() throws IOException {
		PrintWritterWrapper printWritterWrapper = Mockito.mock(PrintWritterWrapper.class);
		randomfile.generateFile(printWritterWrapper);
		Mockito.verify(printWritterWrapper, Mockito.times(10)).print("\n");
	}

}
