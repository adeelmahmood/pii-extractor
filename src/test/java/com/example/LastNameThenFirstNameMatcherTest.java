package com.example;

import com.example.matchers.LastNameThenFirstNameMatcher;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class LastNameThenFirstNameMatcherTest {

    LastNameThenFirstNameMatcher matcher = new LastNameThenFirstNameMatcher();

    @Test
    public void match() throws Exception {
        assertThat(matcher.match("LastName, FirstName, (703)-711-0996, Blue, 11013")).isFalse();
    }

    @Test
    public void noMatchByTokens() throws Exception {
        assertThat(matcher.match("FirstName LastName, Purple, 14537, 713 905 0383")).isFalse();
    }
    @Test
    public void noMatchByNonZip() throws Exception {
        assertThat(matcher.match("FirstName, LastName, 12023, 636 121 1111, Yellow")).isFalse();
    }

    @Test
    public void invalidMatches() throws Exception {
        assertThat(matcher.match("abc")).isFalse();
        assertThat(matcher.match("")).isFalse();
    }

    @Test(expected = IllegalArgumentException.class)
    public void nullMatch() throws Exception {
        assertThat(matcher.match(null)).isFalse();
    }
}