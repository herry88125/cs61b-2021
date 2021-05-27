public class Launcher {
    public static void main(String[] args) {
        ArrayDeque<Integer> a = new ArrayDeque<>();
       /** a.addFirst(1);
        a.addFirst(2);
        a.addFirst(3);
        a.addLast(-1);
        a.addLast(-2);
        a.removeFirst(); */

        for(int i = 0; i < 20; i++) {
            a.addFirst(20 - i);
        }

        for(int i = 0; i < 18; i++) {
            a.removeFirst();
        }
        a.printDeque();
        System.out.print(a.get(1));

    }
}
