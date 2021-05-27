public class Palindrome {
    public Deque<Character> wordToDeque(String word) {

        Deque<Character> a = new ArrayDeque<>();

        for(int i = 0; i < word.length(); i++) {
            a.addLast(word.charAt(i));
        }

        return a;
    }

    public boolean isPalindrome(String word) {
        Deque<Character> a = wordToDeque(word);

        if(a.size() == 0 || a.size() == 1) {
            return true;
        }

        int needCheck = a.size() / 2;

        return isPalindrome(a, needCheck, 1);
    }

    private boolean isPalindrome(Deque<Character> a, int needCheck, int nowCheck) {
        if(nowCheck > needCheck) {
            return true;
        }
        if(a.removeFirst() != a.removeLast()) {
            return false;
        }
        return isPalindrome(a, needCheck, nowCheck + 1);
    }

    public boolean isPalindrome(String word, CharacterComparator cc) {
        Deque<Character> a = wordToDeque(word);

        if(a.size() == 0 || a.size() == 1) {
            return true;
        }

        int needCheck = a.size() / 2;

        return isPalindrome(a, new OffByOne(), needCheck, 1);
    }

    private boolean isPalindrome(Deque<Character> a, CharacterComparator cc, int needCheck, int nowCheck) {
        if(nowCheck > needCheck) {
            return true;
        }

        char front = a.removeFirst();
        char back = a.removeLast();

        if(!cc.equalChars(front, back)) {
            return false;
        }

        return isPalindrome(a, cc, needCheck, nowCheck + 1);
    }
}

