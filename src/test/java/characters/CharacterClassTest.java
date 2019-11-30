package characters;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import org.junit.Test;

public class CharacterClassTest {

	@Test
	public void warrior_ordered_bofore_rogue() {
		CharacterClass actual = CharacterClass.WARRIOR.next();
		assertThat(actual, equalTo(CharacterClass.ROGUE));
	}

	@Test
	public void rogue_ordered_bofore_sorcerer() {
		CharacterClass actual = CharacterClass.ROGUE.next();
		assertThat(actual, equalTo(CharacterClass.SORCERER));
	}

	@Test
	public void sorcerer_ordered_bofore_warrior() {
		CharacterClass actual = CharacterClass.SORCERER.next();
		assertThat(actual, equalTo(CharacterClass.WARRIOR));
	}

}
