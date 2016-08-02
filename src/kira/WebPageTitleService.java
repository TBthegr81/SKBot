/////////////////////////////////////////////////////////////////////////////
// Name:        WebPageTitleService.java
// Encoding:	UTF-8
//
// Purpose:     A bit overworked title extractor for web pages.
//
// Author:      Erik Welander (mail@erikwelander.se)
// Modified:    2016-07-28
// Copyright:   Erik Welander
// Licence:     Creative Commons "by-nc-nd"
/////////////////////////////////////////////////////////////////////////////
package kira;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class WebPageTitleService
{
    private static final Pattern TITLE_TAG =
            Pattern.compile("\\<title>(.*)\\</title>", Pattern.CASE_INSENSITIVE|Pattern.DOTALL);

    public String getWebPageTitle(final String URL) throws WebPageTitleServiceException
    {
        URL url;
        String newURL = "";
        try
        {
            newURL = followRedirects(URL);
            url = new URL(newURL);
        }
        catch (MalformedURLException ex)
        {
            throw new WebPageTitleServiceException("WebPageTitleService: getWebPageTitle(): Followed URL is not a valid URL: " + newURL);
        }

        String contentType = getContentType(newURL);
        if (contentType.indexOf("text/html") == -1)
        {
            return "";
        }


        try
        {
            URLConnection urlConnection = url.openConnection();
            InputStream inputStream = urlConnection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "utf8"));

            char[] buffer = new char[1024];
            int index = 0;
            StringBuilder content = new StringBuilder();
            while ((index = bufferedReader.read(buffer, 0, buffer.length)) != -1)
            {
                content.append(buffer, 0, index);
            }
            bufferedReader.close();

            Matcher matcher = TITLE_TAG.matcher(content);
            if(matcher.find())
            {
                return matcher.group(1).replaceAll("[\\s\\<>]+", " ");
            }
            else
            {
                return "";
            }

        }
        catch (IOException ex)
        {
            throw new WebPageTitleServiceException("WebPageTitleService: getWebPageTitle(): Failed to read data from site.\n Cause: " + ex.getMessage(), ex);
        }
    }


    private final String followRedirects(final String URL)
    {
        URL url;
        try
        {
            url = new URL(URL);
        }
        catch (MalformedURLException ex)
        {
            throw new WebPageTitleServiceException("WebPageTitleService: followRedirects(): Not a valid URL: "+URL);
        }

        try
        {
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("HEAD");
            if (isRedirect(connection.getResponseCode()))
            {
                String newUrl = connection.getHeaderField("Location"); // get redirect url from "location" header field
                return followRedirects(newUrl);
            }
            String location = connection.getHeaderField("Location");

            if(null == location)
            {
                return URL;
            }
            else
            {
                return location;
            }
        }
        catch (IOException ex)
        {
            throw new WebPageTitleServiceException("WebPageTitleService: followRedirects(): Connection failure.\n Cause: "+ex.getMessage(),ex);
        }
    }
    private String getContentType(final String URL) throws WebPageTitleServiceException
    {
        URL url;
        try
        {
            url = new URL(URL);
        }
        catch (MalformedURLException ex)
        {
            throw new WebPageTitleServiceException("WebPageTitleService: getContentType(): Not a valid URL: "+URL);
        }

        try
        {
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("HEAD");

            String contentType = connection.getContentType();
            connection.disconnect();

            return contentType;
        }
        catch (IOException ex)
        {
            throw new WebPageTitleServiceException("WebPageTitleService: getContentType(): Connection failure.\n Cause: "+ex.getMessage(),ex);
        }
    }

    private final boolean isRedirect(final int statusCode)
    {
        if (statusCode != HttpURLConnection.HTTP_OK)
        {
            if (statusCode == HttpURLConnection.HTTP_MOVED_TEMP
                    || statusCode == HttpURLConnection.HTTP_MOVED_PERM
                    || statusCode == HttpURLConnection.HTTP_SEE_OTHER)
            {
                return true;
            }
        }
        return false;
    }

    private class WebPageTitleServiceException extends RuntimeException
    {
        private static final long serialVersionUID = 1L;

        public WebPageTitleServiceException (final String message, final Throwable cause)
        {
            super(message, cause);
        }

        public WebPageTitleServiceException (final String message)
        {
            super(message);
        }
    }
    public static void main(String[] args)
    {
        WebPageTitleService webPageTitleService = new WebPageTitleService();
        System.out.println(webPageTitleService.getWebPageTitle("http://stackoverflow.com/questions/30567361/retrieving-details-from-a-youtube-video-given-the-url/30570377#30570377"));
    }
}
