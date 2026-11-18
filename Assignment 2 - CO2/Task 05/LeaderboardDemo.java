import java.util.*;
class Team {
    final String name; final int points; final int goalDiff;
    Team(String name, int points, int goalDiff) {
        this.name = name; this.points = points; this.goalDiff = goalDiff;
    }
    public String toString() { return name + " (points=" + points + ", GD=" + goalDiff + ")"; }
}
class Leaderboard<T> {
    private final List<T> items = new ArrayList<>();
    private final Comparator<? super T> order;
    Leaderboard(Comparator<? super T> order) { this.order = order; }
    void add(T item) { items.add(item); }
    List<T> ranking() {
        List<T> copy = new ArrayList<>(items);
        copy.sort(order);
        return copy;
    }
}
public class LeaderboardDemo {
    public static void main(String[] args) {
        Comparator<Team> byRank = Comparator
            .comparingInt((Team t) -> t.points).reversed()
            .thenComparing(Comparator.comparingInt((Team t) -> t.goalDiff).reversed())
            .thenComparing(t -> t.name);
        Leaderboard<Team> board = new Leaderboard<>(byRank);
        board.add(new Team("Falcons", 18, 7));
        board.add(new Team("Tigers", 18, 10));
        board.add(new Team("Eagles", 15, 12));
        board.add(new Team("Bulls", 18, 10));
        System.out.println("Team ranking:");
        for (Team t : board.ranking()) System.out.println(t);
    }
}
