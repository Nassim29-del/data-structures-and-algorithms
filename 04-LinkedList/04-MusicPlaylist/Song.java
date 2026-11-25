/**
 * A single song that can be added to a {@link Playlist}.
 *
 * <p>A song has a title, an artist and a length in seconds. Songs are
 * immutable: once created, their values cannot change.</p>
 *
 * @see Playlist
 */
public class Song {

    /** The song's title; never {@code null}. */
    private final String title;

    /** The performing artist; never {@code null}. */
    private final String artist;

    /** The length of the song in seconds; never negative. */
    private final int durationSeconds;

    /**
     * Creates a song.
     *
     * @param title           the song's title; must not be {@code null}
     * @param artist          the performing artist; must not be {@code null}
     * @param durationSeconds the length of the song in seconds; must not be negative
     * @throws IllegalArgumentException if {@code title} or {@code artist} is
     *         {@code null}, or {@code durationSeconds} is negative
     */
    public Song(String title, String artist, int durationSeconds) {
        if (title == null) throw new IllegalArgumentException("title must not be null");
        if (artist == null) throw new IllegalArgumentException("artist must not be null");
        if (durationSeconds < 0) {
            throw new IllegalArgumentException("durationSeconds must not be negative: " + durationSeconds);
        }
        this.title = title;
        this.artist = artist;
        this.durationSeconds = durationSeconds;
    }

    /**
     * Returns the song's title.
     *
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * Returns the performing artist.
     *
     * @return the artist
     */
    public String getArtist() {
        return artist;
    }

    /**
     * Returns the length of the song in seconds.
     *
     * @return the duration in seconds
     */
    public int getDurationSeconds() {
        return durationSeconds;
    }

    /**
     * Returns the song as text, for example
     * {@code "Bohemian Rhapsody - Queen (5:55)"}.
     *
     * @return the title, artist and duration in {@code m:ss} form
     */
    @Override
    public String toString() {
        return String.format("%s - %s (%d:%02d)", title, artist, durationSeconds / 60, durationSeconds % 60);
    }
}