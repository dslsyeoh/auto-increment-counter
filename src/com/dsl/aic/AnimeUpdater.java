/*
 * Author Steven Yeoh
 * Copyright (c) 2019. All rights reserved.
 */

package com.dsl.aic;

import com.dsl.aic.utils.DateUtils;

import java.time.LocalDate;
import java.time.Period;
import java.util.Date;

class AnimeUpdater
{
    void update(Anime anime)
    {
        LocalDate nextReleaseDate = DateUtils.localDate(anime.getCurrentDate()).plusDays(anime.getNextReleaseDuration());
        LocalDate currentDate = DateUtils.localDate(new Date());

        if(currentDate.isEqual(nextReleaseDate) || currentDate.isAfter(nextReleaseDate))
        {
            update(currentDate, nextReleaseDate, anime);
        }
        else
        {
            anime.setNextReleaseCountdown(Period.between(currentDate, nextReleaseDate).getDays());
        }
    }

    private void update(LocalDate currentDate, LocalDate nextReleaseDate, Anime anime)
    {
        int diff = Math.abs(Period.between(currentDate, nextReleaseDate).getDays());
        int counter = 1 + diff > 0 ? diff / anime.getNextReleaseDuration() : 0;
        anime.setCurrentDate(DateUtils.toDate(currentDate));
        anime.setCurrentEpisode(anime.getCurrentEpisode() + 1 + counter);
    }
}
