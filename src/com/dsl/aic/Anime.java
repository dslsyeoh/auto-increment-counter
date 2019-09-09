/*
 * Author Steven Yeoh
 * Copyright (c) 2019. All rights reserved.
 */

package com.dsl.aic;

import java.util.Date;

class Anime
{
    private String name;
    private int currentEpisode;
    private int upcomingDays;
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

    int getUpcomingDays()
    {
        return upcomingDays;
    }

    void setUpcomingDays(int upcomingDays)
    {
        this.upcomingDays = upcomingDays;
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