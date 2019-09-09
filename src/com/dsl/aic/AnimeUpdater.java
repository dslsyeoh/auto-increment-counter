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
        LocalDate nextReleaseDate = DateUtils.toLocalDate(anime.getCurrentDate()).plusDays(anime.getNextReleaseDuration());

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
            int nextReleaseDuration = anime.getNextReleaseDuration();
            int allocateDays = evaluateAllocateDays(difference, nextReleaseDuration);
            int increment = evaluateIncrement(difference, nextReleaseDuration);
            LocalDate newNextReleaseDate = nextReleaseDate.plusDays(allocateDays);
            int newNextReleaseDuration = nextReleaseDuration - DateUtils.daysDiff(currentDate, newNextReleaseDate);
            int nextReleaseCountdown = evaluateNextReleaseCountdown(newNextReleaseDate, newNextReleaseDuration);

            update(anime, newNextReleaseDate, nextReleaseCountdown, increment);
            return;
        }

        update(anime, nextReleaseDate);
    }

    private void update(Anime anime, LocalDate updatedDate)
    {
        update(anime, updatedDate, anime.getNextReleaseDuration(), INCREMENT);
    }

    private int evaluateNextReleaseCountdown(LocalDate currentDate, int nextReleaseDurations)
    {
        return DateUtils.daysDiff(currentDate, currentDate.plusDays(nextReleaseDurations));
    }

    private void update(Anime anime, LocalDate updatedDate, int nextReleaseCountdown, int increment)
    {
        anime.setCurrentDate(DateUtils.toDate(updatedDate));
        anime.setCurrentEpisode(anime.getCurrentEpisode() + increment);
        anime.setNextReleaseCountdown(nextReleaseCountdown);
    }

    private int evaluateIncrement(int difference, int nextReleaseDuration)
    {
        return INCREMENT + difference / nextReleaseDuration;
    }

    private int evaluateAllocateDays(int difference, int nextReleaseDuration)
    {
        return difference / nextReleaseDuration * nextReleaseDuration;
    }
}
