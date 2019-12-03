package exceptions;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import org.junit.Test;

public class LogicErrorTest {

	@Test
	public void error_message_is_set() {
		String message = "error message";
		Exception ex = new LogicError(message);

		String actual = ex.getMessage();
		assertThat(actual, equalTo(message));
	}
}
