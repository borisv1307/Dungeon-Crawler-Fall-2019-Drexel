package parser;

import java.util.List;

import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class NextLevelStepDefs extends NextLevelStepDefHelper {

	@Given("^the level (\\d+) is:$")
	public void the_level_is(List<String> levelStrings) throws Throwable {
		writeLevelFile(levelStrings);
	}

	@When("^the player reaches (\\d+),(\\d+) of current level (\\d+)$")
	public void the_player_reaches_of_current_level(int arg1, int arg2, int arg3) throws Throwable {

	}

	@Then("^the next level will be (\\d+)$")
	public void the_next_level_will_be(int arg1) throws Throwable {
		// Write code here that turns the phrase above into concrete actions
		throw new PendingException();
	}

}
