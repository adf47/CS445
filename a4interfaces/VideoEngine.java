
package videoengine;

/**
 * This abstract data type is a predictive engine for video ratings in a streaming video system. It
 * stores a set of users, a set of videos, and a set of ratings that users have assigned to videos.
 *
 * ADD OTHER DETAILS HERE IF NECESSARY
 */
public interface VideoEngine {

    /**
     * The abstract methods below are declared as void methods with no parameters. You need to
     * expand each declaration to specify a return type and parameters, as necessary. You also need
     * to include a detailed comment for each abstract method describing its effect, its return
     * value, any corner cases that the client may need to consider, any exceptions the method may
     * throw (including a description of the circumstances under which this will happen), and so on.
     * You should include enough details that a client could use this data structure without ever
     * being surprised or not knowing what will happen, even though they haven't read the
     * implementation.
     */

    /**
     * Adds a new video to the system.
     */
    void addVideo();

    /**
     * Removes an existing video from the system.
     */
    void removeVideo();

    /**
     * Adds an existing television episode to an existing television series.
     */
    void addToSeries();

    /**
     * Removes a television episode from a television series.
     */
    void removeFromSeries();

    /**
     * Sets a user's rating for a video, as a number of stars from 1 to 5.
     */
    void rateVideo();

    /**
     * Clears a user's rating on a video. If this user has rated this video and the rating has not
     * already been cleared, then the rating is cleared and the state will appear as if the rating
     * was never made. If this user has not rated this video, or if the rating has already been
     * cleared, then this method will throw an IllegalArgumentException.
     *
     * @param theUser user whose rating should be cleared
     * @param theVideo video from which the user's rating should be cleared
     * @throws IllegalArgumentException if the user does not currently have a rating on record for
     * the video
     * @throws NullPointerException if either the user or the video is null
     */
    public void clearRating(User theUser, Video theVideo);

    /**
     * Predicts the rating a user will assign to a video that they have not yet rated, as a number
     * of stars from 1 to 5.
     */
    void predictRating();

    /**
     * Suggests a video for a user (e.g.,based on their predicted ratings).
     */
    void suggestVideo();


}
