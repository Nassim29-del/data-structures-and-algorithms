/**
 * Command-line demo that exercises {@link Playlist} and {@link Song} and
 * checks the results.
 *
 * <p>The first part prints the playlist as songs are added, played,
 * reordered, shuffled and removed. The second part runs a set of
 * self-checks and prints how many passed and failed; each failure is also
 * printed by name.</p>
 *
 * <p>Run it with:</p>
 * <pre>
 *   javac Song.java Playlist.java PlaylistDemo.java
 *   java PlaylistDemo
 * </pre>
 *
 * @see Playlist
 * @see Song
 */
public class PlaylistDemo {

    /** Number of self-checks that have passed so far. */
    private static int passed = 0;

    /** Number of self-checks that have failed so far. */
    private static int failed = 0;

    /**
     * Creates the demo. Not normally needed; run {@link #main(String[])} instead.
     */
    public PlaylistDemo() {
    }

    /**
     * Records the result of one self-check, and prints its name if it failed.
     *
     * @param name      a short description of what is being checked
     * @param condition {@code true} if the check passed
     */
    private static void check(String name, boolean condition) {
        if (condition) {
            passed++;
        } else {
            failed++;
            System.out.println("   FAILED: " + name);
        }
    }

    /**
     * Tells whether a song has the given title, treating a {@code null}
     * song as not matching.
     *
     * @param song  the song to test; may be {@code null}
     * @param title the expected title
     * @return {@code true} if {@code song} is not {@code null} and has that title
     */
    private static boolean is(Song song, String title) {
        return song != null && song.getTitle().equals(title);
    }

    /**
     * Runs the demo and the self-checks, printing each step to standard output.
     *
     * @param args command-line arguments (not used)
     */
    public static void main(String[] args) {
        Playlist road = new Playlist("Road Trip");

        System.out.println("=== Building ===");
        road.addSong(new Song("Bohemian Rhapsody", "Queen", 355));
        road.addSong(new Song("Hotel California", "Eagles", 391));
        road.addSong(new Song("Africa", "Toto", 295));
        road.addSong(new Song("Take On Me", "a-ha", 225));
        road.print();

        System.out.println("\n=== Playing forward and back in O(1) ===");
        System.out.println("Now playing: " + road.nowPlaying());
        System.out.println("next()     -> " + road.next());
        System.out.println("next()     -> " + road.next());
        System.out.println("previous() -> " + road.previous());

        System.out.println("\n=== Play next ===");
        road.playNext(new Song("Don't Stop Believin'", "Journey", 251));
        road.print();

        System.out.println("\n=== End of playlist: repeat off, then on ===");
        while (road.next() != null) { /* skip to the last song */ }
        System.out.println("At the end, now playing: " + road.nowPlaying());
        System.out.println("next() with repeat off -> " + road.next());
        road.setRepeat(true);
        System.out.println("next() with repeat on  -> " + road.next() + "  (wrapped to the start)");
        System.out.println("previous() with repeat -> " + road.previous() + "  (wrapped to the end)");

        System.out.println("\n=== Move up ===");
        road.moveUp("africa");
        road.print();

        System.out.println("\n=== Shuffle (seed 7) ===");
        road.shuffle(7);
        road.print();

        System.out.println("\n=== Remove ===");
        System.out.println("removeSong(\"Hotel California\") -> " + road.removeSong("Hotel California"));
        System.out.println("removeSong(\"Not A Song\")       -> " + road.removeSong("Not A Song"));
        road.print();

        System.out.println("\n=== Self-checks ===");
        Playlist t = new Playlist("Test");
        check("starts empty", t.size() == 0 && t.nowPlaying() == null);
        check("next on empty is null", t.next() == null && t.previous() == null);
        check("empty duration", t.totalDuration().equals("0 min 00 s"));

        t.addSong(new Song("A", "X", 60));
        t.addSong(new Song("B", "X", 90));
        t.addSong(new Song("C", "X", 30));
        check("first song becomes current", is(t.nowPlaying(), "A"));
        check("size after adds", t.size() == 3);
        check("total duration", t.totalDuration().equals("3 min 00 s"));

        check("next", is(t.next(), "B") && is(t.nowPlaying(), "B"));
        check("previous", is(t.previous(), "A"));
        check("previous at start, repeat off", t.previous() == null && is(t.nowPlaying(), "A"));

        t.playNext(new Song("P", "X", 10));
        check("playNext keeps current", is(t.nowPlaying(), "A"));
        check("playNext plays next", is(t.next(), "P"));

        check("moveUp swaps with previous", t.moveUp("b") && is(t.previous(), "B"));
        check("current follows moved song", is(t.next(), "P"));
        check("moveUp first song fails", !t.moveUp("A"));
        check("moveUp missing fails", !t.moveUp("Z"));

        t.setRepeat(true);
        while (!is(t.nowPlaying(), "C")) t.next();
        check("repeat wraps at end", is(t.next(), "A"));
        check("repeat wraps at start", is(t.previous(), "C"));

        check("remove current moves back from last", t.removeSong("c") && is(t.nowPlaying(), "P"));
        check("remove missing", !t.removeSong("Z") && t.size() == 3);

        t.setRepeat(false);
        t.shuffle(1);
        check("shuffle resets to first", t.nowPlaying() != null && t.previous() == null);
        check("shuffle keeps size", t.size() == 3);

        t.removeSong("A"); t.removeSong("B"); t.removeSong("P");
        check("remove all", t.size() == 0 && t.nowPlaying() == null);

        boolean threw = false;
        try { new Song("Bad", "X", -1); } catch (IllegalArgumentException e) { threw = true; }
        check("negative duration throws", threw);
        check("song toString", new Song("Africa", "Toto", 295).toString().equals("Africa - Toto (4:55)"));

        System.out.println(passed + " passed, " + failed + " failed");
    }
}