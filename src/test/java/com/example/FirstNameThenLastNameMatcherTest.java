package com.example;

import com.example.json.Entry;
import com.example.matchers.FirstNameThenLastNameMatcher;
import com.example.matchers.LastNameThenFirstNameMatcher;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class FirstNameThenLastNameMatcherTest {

    FirstNameThenLastNameMatcher matcher = new FirstNameThenLastNameMatcher();

    @Test
    public void match() throws Exception {
        String line = "FirstName, LastName, 12023, 636 121 1111, Yellow";
        assertThat(matcher.match(line)).isTrue();

        Entry entry = matcher.parse(line);
        assertThat(entry).isNotNull();
        assertThat(entry.getFirstName()).isEqualTo("FirstName");
        assertThat(entry.getLastName()).isEqualTo("LastName");
        assertThat(entry.getPhoneNumber()).isEqualTo("636 121 1111");
        assertThat(entry.getColor()).isEqualTo("Yellow");
        assertThat(entry.getZipCode()).isEqualTo("12023");
    }

    @Test
    public void noMatchByTokens() throws Exception {
        assertThat(matcher.match("FirstName LastName, Purple, 14537, 713 905 0383")).isFalse();
    }

    @Test
    public void noMatchByNonColor() throws Exception {
        assertThat(matcher.match("LastName, FirstName, (703)-711-0996, Blue, 11013")).isFalse();
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