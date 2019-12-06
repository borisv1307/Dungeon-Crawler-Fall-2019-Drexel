package launcher;

import java.io.IOException;
import java.util.ArrayList;

import wrappers.PrintWriterWrapper;
import wrappers.SecureRandomWrapper;

public class RandomFile {
	int row, column, random_position_count = 0;
	SecureRandomWrapper secureRandomWrapper = new SecureRandomWrapper();
	ArrayList<Integer> column_line = getRandomNonRepeatingIntegers(50, 2, 19, secureRandomWrapper);
	ArrayList<Integer> row_line = getRandomNonRepeatingIntegers(50, 2, 9, secureRandomWrapper);

	public void generateFile(PrintWriterWrapper printWriterWrapper) throws IOException {
		row_line.sort(null);
		for (row = 1; row <= 10; row++) {
			for (column = 1; column <= 20; column++) {

				if (row == 1 || row == 10 || column == 1 || column == 20)
					printWriterWrapper.print("X");
				else {

					if (row_line.get(random_position_count) == row) {

						if (column_line.get(random_position_count) == column) {

							if (random_position_count <= 8) {

								if (random_position_count == 4) {
									printWriterWrapper.print("P");
									random_position_count++;
								} else if (random_position_count == 1 || random_position_count == 3) {
									printWriterWrapper.print("T");
									random_position_count++;

								} else {
									printWriterWrapper.print("E");
									if (random_position_count != 7)
										random_position_count++;

								}

							}

						} else
							printWriterWrapper.print(" ");

					} else
						printWriterWrapper.print(" ");

				}

			}
			printWriterWrapper.print("\n");
		}
		printWriterWrapper.close();

	}

	public ArrayList<Integer> getRandomNonRepeatingIntegers(int size, int min, int max,
			SecureRandomWrapper secureRandomWrapper) {
		int random_number;
		ArrayList<Integer> random_numbers = new ArrayList<Integer>();
		for (int i = 1; i <= size; i++) {
			random_number = secureRandomWrapper.nextInt(max, min);
			if (!(random_numbers.contains(random_number)))
				if (random_numbers.size() < 9)
					random_numbers.add(random_number);

		}
		return random_numbers;
	}

}