package launcher;

import java.util.ArrayList;

import wrappers.PrintWriterWrapper;
import wrappers.SecureRandomWrapper;

public class RandomFile {
	private int row;
	private int column;
	private int random_number;
	private int random_position_count;
	private SecureRandomWrapper secureRandomWrapper;
	private final String tileType[] = { " ", "E", "T", "E", "P", "E", "T", "E", "E", "X" };

	public RandomFile(SecureRandomWrapper secureRandomWrapper) {
		this.secureRandomWrapper = secureRandomWrapper;
	}

	public void generateFile(PrintWriterWrapper printWriterWrapper) {
		try {
			ArrayList<Integer> column_line = getRandomNonRepeatingIntegers(50, 2, 19, secureRandomWrapper);
			for (row = 1; row <= 10; row++) {
				for (column = 1; column <= 20; column++) {
					if (row == 1 || row == 10 || column == 1 || column == 20)
						printWriterWrapper.print(tileType[9]);
					else if (column_line.get(random_position_count) == column)
						printWriterWrapper.print(tileType[random_position_count]);
					else
						printWriterWrapper.print(tileType[0]);
				}
				random_position_count++;
				printWriterWrapper.print("\n");
			}
			printWriterWrapper.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public ArrayList<Integer> getRandomNonRepeatingIntegers(int size, int min, int max,
			SecureRandomWrapper secureRandomWrapper) {
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