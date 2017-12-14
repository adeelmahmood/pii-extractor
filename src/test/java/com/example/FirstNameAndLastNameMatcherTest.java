package com.example;

import com.example.json.Entry;
import com.example.matchers.FirstNameAndLastNameMatcher;
import com.example.matchers.LastNameThenFirstNameMatcher;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class FirstNameAndLastNameMatcherTest {

    FirstNameAndLastNameMatcher matcher = new FirstNameAndLastNameMatcher();

    @Test
    public void match() throws Exception {
        String line = "FirstName LastName, Purple, 14537, 713 905 0383";
        assertThat(matcher.match(line)).isTrue();

        Entry entry = matcher.parse(line);
        assertThat(entry).isNotNull();
        assertThat(entry.getFirstName()).isEqualTo("FirstName");
        assertThat(entry.getLastName()).isEqualTo("LastName");
        assertThat(entry.getPhoneNumber()).isEqualTo("713 905 0383");
        assertThat(entry.getColor()).isEqualTo("Purple");
        assertThat(entry.getZipCode()).isEqualTo("14537");
    }

    @Test
    public void noMatchByTokens() throws Exception {
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