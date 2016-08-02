/////////////////////////////////////////////////////////////////////////////
// Name:        YoutubeService.java
// Encoding:	UTF-8
//
// Purpose:     Single file beta Youtube service implementation
//
// Author:      Erik Welander (mail@erikwelander.se)
// Modified:    2016-07-28
// Copyright:   Erik Welander
// Licence:     Creative Commons "by-nc-nd"
/////////////////////////////////////////////////////////////////////////////
package kira;
/*
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestInitializer;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.youtube.YouTube;
import com.google.api.services.youtube.model.Video;
import com.google.api.services.youtube.model.VideoListResponse;

import java.io.IOException;
import java.math.BigInteger;
import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class YoutubeService
{
    private final String PROJECT_API_NAME = "Youtube-info";
    private final String PROJECT_API_KEY = "AIzaSyAVaxYzSpnU4_rSA55te4LIfEfAjJrW0sw";
    private YouTube youTube;
    YoutubeService()
    {
        buildYouTubeService();
    }

    public final Video getVideoData(final String URL) throws YoutubeServiceException
    {
        final String videoID = getVideoID(URL);
        YouTube.Videos.List videoRequest = null;

        try
        {
            videoRequest = youTube.videos().list("snippet,statistics,contentDetails");
        }
        catch (IOException ex)
        {
            throw new YoutubeServiceException("YoutubeService: getYouTubeData(): Failed to initialize videos list. Cause: "+ex.getMessage(), ex);
        }

        videoRequest.setId(videoID);
        videoRequest.setKey(PROJECT_API_KEY);

        VideoListResponse videoResponseList;
        try
        {
            videoResponseList = videoRequest.execute();
        }
        catch (IOException ex)
        {
            throw new YoutubeServiceException("YoutubeService: getYouTubeData(): Failed to retrieve videos list. Cause: "+ex.getMessage(), ex);
        }
        Video video = videoResponseList.getItems().get(0);
        return video;
    }

    private final String getVideoID(final String URL) throws YoutubeServiceException
    {
        String videoID = "";
        Pattern pattern = Pattern.compile(
                "(?:youtube(?:-nocookie)?\\.com\\/(?:[^\\/\\n\\s]+\\/\\S+\\/|(?:v|e(?:mbed)?)\\/|\\S*?[?&]v=)|youtu\\.be\\/)([a-zA-Z0-9_-]{11})",
                Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(URL);
        if (matcher.find())
        {
            videoID = matcher.group(1);
        }
        else
        {
            throw new YoutubeServiceException("YoutubeService: getVideoID(): Invalid youtube URL: No video ID found.");
        }

        return videoID;
    }
    private void buildYouTubeService()
    {
        youTube = new YouTube.Builder
                (
                        new NetHttpTransport(), new JacksonFactory(),
                request -> {}
                ).setApplicationName(PROJECT_API_NAME).build();
    }

    private class YoutubeServiceException extends RuntimeException
    {
        private static final long serialVersionUID = 1L;

        public YoutubeServiceException (final String message, final Throwable cause)
        {
            super(message, cause);
        }

        public YoutubeServiceException (final String message)
        {
            super(message);
        }
    }

    public static void main(String[] args)
    {
        YoutubeService service = new YoutubeService();
        Video video = service.getVideoData("https://www.youtube.com/watch?v=8nDP0ke3tfU");

        String title = video.getSnippet().getTitle();
        String channel = video.getSnippet().getChannelTitle();
        String viewCount = NumberFormat.getInstance(Locale.forLanguageTag("SE")).format(video.getStatistics().getViewCount());
        String duration = video.getContentDetails().getDuration();

        duration = duration.substring(2);
        int mIndex = duration.indexOf("M");
        duration = duration.substring(0, mIndex+1)+" "+duration.substring(mIndex+1);
        duration = duration.toLowerCase();

        System.out.println("Video Title: "+title);
        System.out.println("Video Channel: "+channel);
        System.out.println("Video Viewcount: "+viewCount);
        System.out.println("Video Duration: "+duration);
    }
}

*/