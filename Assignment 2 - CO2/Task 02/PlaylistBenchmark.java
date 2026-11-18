import java.util.*;
public class PlaylistBenchmark {
    static long timeGet(List<Integer> list, int k) {
        long start = System.nanoTime();
        int value = list.get(k);
        return System.nanoTime() - start;
    }
    public static void main(String[] args) {
        for (int n : new int[]{1000, 10000, 100000}) {
            List<Integer> a = new ArrayList<>();
            List<Integer> l = new LinkedList<>();
            for (int i = 0; i < n; i++) { a.add(i); l.add(i); }
            System.out.println("Size " + n + " -> ArrayList get: " +
                timeGet(a, n/2) + " ns, LinkedList get: " +
                timeGet(l, n/2) + " ns");
        }
        Deque<String> upNext = new ArrayDeque<>();
        upNext.addLast("Song A"); upNext.addLast("Song B");
        upNext.addFirst("Priority Song");
        System.out.println("Up Next: " + upNext);
    }
}
