public class ArrayDeque<T> {
    private int size;
    private int arraySize;
    private T[] array;
    private int nextFirst;
    private int nextLast;

    public ArrayDeque() {
        size = 0;
        arraySize = 20;
        array = (T[]) new Object[arraySize];
        nextFirst = 1;
        nextLast = 2;
    }

    public void addFirst(T item) {
        if(size == arraySize) {
            lengthenArray();
        }
        array[nextFirst] = item;
        nextFirst -= 1;
        if(nextFirst == -1) {
            nextFirst = arraySize - 1;
        }
        size += 1;
    }

    public void addLast(T item) {
        if(size == arraySize) {
            lengthenArray();
        }
        array[nextLast] = item;
        nextLast += 1;
        if(nextLast == arraySize) {
            nextLast = 0;
        }
        size += 1;
    }

    private void shortenArray() {

        // use nextFirst and nextLast to get the position of
        // head and tail in array
        int first = nextFirst + 1;
        if(first == arraySize) {
            first = 0;
        }
        int last = nextLast - 1;
        if(last == -1) {
            last = arraySize - 1;
        }

        arraySize = arraySize / 2;
        T[] newArray = (T[]) new Object[arraySize];
        int p = first;
        int i = 0;

        // copy value in the old array to the new array
        while(p != last) {
            newArray[i] = array[p];
            i += 1;
            p += 1;
            if(p == arraySize * 2) {
                p = 0;
            }
        }
        newArray[i] = array[p];

        // let our ArrayDeque connect to the new array
        // and throw away the old array
        array = newArray;

        // update nextFirst and nextLast to fit the new array
        nextFirst = arraySize - 1;
        nextLast = size;
    }

    private void lengthenArray() {

        // use nextFirst and nextLast to get the position of
        // head and tail in array
        int first = nextFirst + 1;
        if(first == arraySize) {
            first = 0;
        }
        int last = nextLast - 1;
        if(last == -1) {
            last = arraySize - 1;
        }

        arraySize = arraySize * 2;
        T[] newArray = (T[]) new Object[arraySize];
        int p = first;
        int i = 0;

        // copy value in the old array to the new array
        while(p != last) {
            newArray[i] = array[p];
            i += 1;
            p += 1;
            if(p == arraySize / 2) {
                p = 0;
            }
        }
        newArray[i] = array[p];

        // let our ArrayDeque connect to the new array
        // and throw away the old array
        array = newArray;

        // update nextFirst and nextLast to fit the new array
        nextFirst = arraySize - 1;
        nextLast = size;
    }

    public boolean isEmpty() {
        if(size == 0) {
            return true;
        }
        return false;
    }

    public int size() {
        return size;
    }

    public void printDeque() {
        int first = nextFirst + 1;
        if(first == arraySize) {
            first = 0;
        }
        int last = nextLast - 1;
        if(last == -1) {
            last = arraySize - 1;
        }

        int p = first;
        while(p != last) {
            System.out.print(array[p] + " ");
            p += 1;
            if(p == arraySize) {
                p = 0;
            }
        }
        System.out.print(array[last] + " ");
    }

    public T removeFirst() {
        if(size == 0) {
            return null;
        }
        nextFirst += 1;
        if(nextFirst == arraySize) {
            nextFirst = 0;
        }
        T removedValue = array[nextFirst];
        size -= 1;

        if((arraySize >= 16) && ((double) size / arraySize < 0.25)) {
            shortenArray();
        }
        return removedValue;
    }

    public T removeLast() {
        if(size == 0) {
            return null;
        }
        nextLast -= 1;
        if(nextLast == -1) {
            nextLast = arraySize - 1;
        }
        T removedValue = array[nextLast];
        size -= 1;

        if((arraySize >= 16) && ((double) size / arraySize < 0.25)) {
            shortenArray();
        }

        return null;
    }

    public T get(int index) {
        if(index >= size) {
            return null;
        }
        int p = nextFirst + 1 + index;
        if(p >= arraySize) {
            p = p - (arraySize);
        }
        return array[p];
    }
}
