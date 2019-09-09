/*
 * Author Steven Yeoh
 * Copyright (c) 2019. All rights reserved.
 */

package com.dsl.aic;

import com.dsl.aic.utils.DateUtils;

import java.time.LocalDate;
import java.util.Date;

class AnimeUpdater implements Updater<Anime>
{
    private static final int INCREMENT = 1;

    @Override
    public void update(Anime anime)
    {
        update(anime, DateUtils.toLocalDate(new Date()));
    }

    @Override
    public void updateTest(Anime anime, LocalDate currentDate)
    {
        LocalDate nextReleaseDate = DateUtils.toLocalDate(anime.getCurrentDate()).plusDays(anime.getUpcomingDays());

        if(currentDate.isEqual(nextReleaseDate) || currentDate.isAfter(nextReleaseDate))
        {
            update(currentDate, nextReleaseDate, anime);
        }
        else
        {
            anime.setNextReleaseCountdown(DateUtils.daysDiff(currentDate, nextReleaseDate));
        }
    }

    private void update(LocalDate currentDate, LocalDate nextReleaseDate, Anime anime)
    {
        int difference = DateUtils.daysDiff(currentDate, nextReleaseDate);
        if(difference > 0)
        {
            int upcomingDays = anime.getUpcomingDays();
            int actualDays = evaluateActualDays(difference, upcomingDays);
            int increment = evaluateIncrement(difference, upcomingDays);
            LocalDate newNextReleaseDate = nextReleaseDate.plusDays(actualDays);
            int nextReleaseCountdown = DateUtils.daysDiff(currentDate, newNextReleaseDate.plusDays(upcomingDays));

            update(anime, newNextReleaseDate, nextReleaseCountdown, increment);
        }
        else
        {
            update(anime, nextReleaseDate);
        }
    }

    private void update(Anime anime, LocalDate updatedDate)
    {
        update(anime, updatedDate, anime.getUpcomingDays(), INCREMENT);
    }

    private void update(Anime anime, LocalDate updatedDate, int nextReleaseCountdown, int increment)
    {
        anime.setCurrentDate(DateUtils.toDate(updatedDate));
        anime.setCurrentEpisode(anime.getCurrentEpisode() + increment);
        anime.setNextReleaseCountdown(nextReleaseCountdown);
    }

    private int evaluateIncrement(int difference, int upcomingDays)
    {
        return INCREMENT + difference / upcomingDays;
    }

    private int evaluateActualDays(int difference, int upcomingDays)
    {
        return difference / upcomingDays * upcomingDays;
    }
}
