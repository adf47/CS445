//Antonino Febbraro
//Assignment 4
// CS 445 - Data Structures
//Due April 6, 2016

public interface VideoEngine{

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
     * Adds a new video or subclass (Film or TvEpisode) of video to the system.
     * newVideo is inserted into the system if
     * it does not already exsist in the system and returns true.
     * Corner cases:
     * No duplicates are allowed, do not add an exsisting video to the system.
     * No max capacity should be reached. Should store n amount of Videos.
     * Resize if need be to make sure that there is always room to add to the system.
     * After executing, the system contains all the previous videos and newVideo.
     * The system should contain n+1 videos. If the video already exsists in the
     * system then throw an IllegalArgumentException, meaning that no videos
     * were added to the system.
     *
     * @param newVideo the video to be added to the system.
     * @return true if video is added successfully.
     * @return false if video already exsists i.e. a duplicate is found.
     * @throws NullPointerException if newVideo is null.
     * @throws IllegalArgumentException if newVideo already exsists in the system.
     */
    public boolean addVideo(Video newVideo);

    /**
     * Removes an existing video from the VideoEngine system.
     * Allows for an object of type Film or TvEpisode to be removed because Film
     * and TvEpisode are subclasses of Video.
     * Meaning a Film, or TvEpisode could be removed from the system completely.
     * After executing, the system should contain all of the
     * videos in the system except for entry (the one to be removed).
     * The system should contain n-1 videos.
     * The video (entry) that is removed is to be return as type Video.
     * The type that is returned is of type Video because Film and TvEpisode are
     * subclasses of type video, allowing them to be returned as well.
     * Corner cases:
     * If the video (entry) does not exsist in the system then remove nothing and
     * throw an IllegalArgumentException.
     * Also, if there are no videos in the system to be removed throw an IllegalArgumentException.
     * If the selected entry is null, then throw a NullPointerException.
     * This method should only work if videos exsists within the system.
     *
     * @param entry is the video to be removed from the system.
     * @return video the video that is removed.
     * @throws NullPointerException if entry is null.
     * @throws IllegalArgumentException if the video doesn not exsist in the system.
     * @throws IllegalArgumentException if the system is completely empty.
     */
    public Video removeVideo(Video entry);

    /**
     * Adds an existing television episode to an existing television series.
     * The TvEpisode can only be added to the TvSeries if it already exsists in
     * the VideoEngine System by being added through the addVideo() method.
     * After executing, an exsisting TvSeries (series) contains a new TvEpisode (episode),
     * Allowing the exsisting TvSeries (series) to now have n+1 episodes.
     * No duplicates are allowed within the TvSeries.
     * Corner cases:
     * No max capacity should be reached. Resize if needed.
     * If the TvEpisode being added to the TvSeries is not an episode in that TvSeries,
     * then an InvalidParameterException is to be thrown.
     * if the TvEpisode already exsists in the TvSeries an IllegalArgumentException
     * should be thrown.
     * If TvSeries or TvEpisode trying to be added to the TvSeries is not in the system,
     * meaning it was not added through the addVideo() method, then throw an IllegalArgumentException.
     *
     * @param series is the TvSeries that is to gain the added episode.
     * @param episode is the TvEpisode that is to be added to the TvSeries.
     * @return true if episode is successfully added to series.
     * @throws NullPointerException if either series or episode are null.
     * @throws java.security.InvalidParameterException if the TvEpisode does not belong to the TvSeries.
     * @throws IllegalArgumentException if the TvEpisode already exsists in the system for the proper
     * TvSeries i.e. there is a duplicate.
     * @throws IllegalArgumentException if the TvSeries or TvEpisode does not exsists in the system.
     * The TvEpisode must already be in the system from the addVideo() method.
     */
    public boolean addToSeries(TvSeries series,TvEpisode episode);

    /**
     * Removes a television episode from a television series.
     * NOTE: This should not remove the TvEpisode from the entire VideoEngine system.
     * It should only remove the TvEpisode from the TvSeries. If needed, call the removeVideo()
     * method to remove the TvEpisode from the entire VideoEngine system completely.
     * After executing, the specified televison series should have n-1 televison
     * episodes. The episode that is removed is to be returned as type TvEpisode.
     * Corner cases:
     * If there are no TvEpisodes in the series to be removed then throw a IllegalArgumentException.
     * If the TvEpisode does not exsist in the system at all then throw a IllegalArgumentException.
     * if the choosen TvSeries or TvEpisode are null, then throw a NullPointerException.
     * If the TvEpisode is not part of the selected TvSeries, then throw an InvalidParameterException.
     *
     * @param series is the TvSeries that is to have an TvEpisode removed from it.
     * @param episode is the TvEpisode that is to be removed from the TvSeries.
     * @return Episode the TvEpisode that was removed from the TvSeries.
     * @throws NullPointerException if either series or episode are null.
     * @throws IllegalArgumentException if the TvSeries does not exsist in the system.
     * @throws IllegalArgumentException if episode does not exsist or if there are no TvEpisodes in the TvSeries.
     * @throws java.security.InvalidParameterException if the TvEpisode is not part of the TvSeries.
     */
    public TvEpisode removeFromSeries(TvSeries series,TvEpisode episode);

    /**
     * Sets a user's rating for a video, as a number of stars from 1 to 5.
     * After executing, a user's rating (number of stars) is added to the selected
     * Video's (Video can be of type Video or any of its subclasses, which include
     * Film and TvEpisode) rating. The chosen Video should then have n+1 ratings, and
     * should have the new rating added and calculated into the overall average rating for theVideo.
     * Star ratings may contain whole numbers and decimals. For example, a user could rated
     * a video 4 stars or 4.5 (4 and half stars). This should be allowed and handled.
     * Corner cases:
     * If a star rating is greater then 5 or less than 1 then the method will
     * throw an InvalidParameterException.
     * If theUser has already rated theVideo, then throw InvalidParameterException.
     * Users should only be able to rate theVideo once. They should also not be able to
     * change their rating once they have rated the video.
     *
     * @param theUser user whose rating will be added to the video.
     * @param theVideo the video that is to be rated.
     * @param stars the number of stars (1 to 5) that theUser has rated the video.
     * @throws IllegalArgumentException if theUser or theVideo does not exsist in the system already.
     * @throws java.security.InvalidParameterException if theVideo has already been rated by theUser.
     * @throws java.security.InvalidParameterException if stars is less than 1 or greater than 5. Must be in range [1,5].
     * @throws NullPointerException if either theUser or theVideo are null.
     */
    public void rateVideo(User theUser,Video theVideo, double stars);

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
     * @throws IllegalArgumentException if theUser or theVideo do not exsist in the system.
     */
    public void clearRating(User theUser, Video theVideo);

    /**
     * Predicts the rating a user will assign to a video that they have not yet rated, as a number
     * of stars from 1 to 5. The predicted rating is based off of theUsers ratings on other videos that
     * are of similar tasted to theVideo.
     * After Executing, a predicted rating for a user of a specific video
     * (Which includes a Film or TvEpisode) is returned as a double. The ratings will be in terms of stars
     * and will range from 1 to 5 stars, with decimals included. For example, a rating could be returned as
     * 4 stars or as 4.5 stars. Half stars are allowed. Also, the video to be rated must be of type Video or
     * any of its subclasses, which include the types Film and TvEpisode.
     * Corner cases:
     * If the theUser already has rated theVideo, then do not give a predicted rating,
     * and throw and IllegalArgumentException.
     *
     * @param theUser user that the predicted rating is for.
     * @param theVideo the video (Film and or TvEpisode) that the predicted rating is for.
     * @return Rating the predicted rating for the user. This must be in range of [1,5]
     * @return null If the user has rated every single video in the system.
     * decimals allowed. For example, 4.5 stars is allowed.
     * @throws NullPointerException if theUser and or theVideo are null.
     * @throws IllegalArgumentException if theUser already has a rating for the chosen video.
     * @throws IllegalArgumentException if theUser or theVideo do not exsist in the system.
     */
    public double predictRating(User theUser, Video theVideo);

    /**
     * Suggests a video for a user (e.g.,based on their predicted ratings).
     * The suggested video should be a video that theUser has not yet viewed.
     * After executing, the method returns a Video that is the suggested Video.
     * The type that is returned is of type Video because Film and TvEpisode are
     * subclasses of type video, allowing them to be returned as well.
     * Corner cases:
     * If the user has already rated and or watched the video do not return that video,
     * and sugested a diferent video.
     * Run the method again until a newly suggested video is found. A video that the user
     * has not seen before. If the user has seen all vidoes within the system, then
     * suggest a video that theUser has not watched in a long time. Always give a sugested video!
     *
     * @param theUser the user that the suggested video is for.
     * @return Suggestion the video that is to be suggested to theUser to watch.
     * @throws NullPointerException if theUser is null.
     * @throws IllegalArgumentException if theUser does not exsist in the system.
     */
    public Video suggestVideo(User theUser);


}
