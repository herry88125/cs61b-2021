import org.junit.Test;
import static org.junit.Assert.*;

public class TestPalindrome {
    // You must use this palindrome, and not instantiate
    // new Palindromes, or the autograder might be upset.
    static Palindrome palindrome = new Palindrome();
    static CharacterComparator offByOne = new OffByOne();

    @Test
    public void testWordToDeque() {
        Deque d = palindrome.wordToDeque("persiflage");
        String actual = "";
        for (int i = 0; i < "persiflage".length(); i++) {
            actual += d.removeFirst();
        }
        assertEquals("persiflage", actual);
    }

    @Test
    public void testIsPalindrome() {
        String s;

        // test: length of word = 0
        s = "";
        assertTrue(palindrome.isPalindrome(s));

        // test: length of word = 1
        s = "a";
        assertTrue(palindrome.isPalindrome(s));

        s = "aba";
        assertTrue(palindrome.isPalindrome(s));

        s = "abc";
        assertFalse(palindrome.isPalindrome(s));

        s = "aA";
        assertFalse(palindrome.isPalindrome(s));
    }

    @Test
    public void testIsPalindromeOffByOne() {
        String s;

        s = "flake";
        assertTrue(palindrome.isPalindrome(s, offByOne));

        s = "aaaab";
        assertFalse(palindrome.isPalindrome(s, offByOne));
    }

    @Test
    public void testIsPalindromeOffByN() {
        String s;

        s = "binding";
        assertTrue(palindrome.isPalindrome(s, new OffByN(5)));

        s = "bindiii";
        assertFalse(palindrome.isPalindrome(s, new OffByN(5)));
    }
}
