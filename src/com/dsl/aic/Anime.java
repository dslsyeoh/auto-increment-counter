package com.dsl.aic;

import java.util.Date;

class Anime
{
    private String name;
    private int currentEpisode;
    private int nextReleaseDuration;
    private int nextReleaseCountdown;
    private Date currentDate;

    String getName()
    {
        return name;
    }

    void setName(String name)
    {
        this.name = name;
    }

    int getCurrentEpisode()
    {
        return currentEpisode;
    }

    void setCurrentEpisode(int current)
    {
        this.currentEpisode = current;
    }

    int getNextReleaseDuration()
    {
        return nextReleaseDuration;
    }

    void setNextReleaseDuration(int nextReleaseDuration)
    {
        this.nextReleaseDuration = nextReleaseDuration;
    }

    int getNextReleaseCountdown()
    {
        return nextReleaseCountdown;
    }

    void setNextReleaseCountdown(int nextReleaseCountdown)
    {
        this.nextReleaseCountdown = nextReleaseCountdown;
    }

    Date getCurrentDate()
    {
        return currentDate;
    }

    void setCurrentDate(Date currentDate)
    {
        this.currentDate = currentDate;
    }
}