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
    @Override
    public void update(Anime anime)
    {
        update(anime, DateUtils.toLocalDate(new Date()));
    }

    @Override
    public void update(Anime anime, LocalDate currentDate)
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
        int diff = DateUtils.daysDiff(currentDate, nextReleaseDate);
        int leapEpisode = diff / anime.getNextReleaseDuration();
        int increment = 1 + (diff > 0 ? leapEpisode : 0);

        LocalDate updatedDate = diff > 0 ? nextReleaseDate.plusDays((leapEpisode * anime.getNextReleaseDuration())) : nextReleaseDate;
        anime.setCurrentDate(DateUtils.toDate(updatedDate));
        anime.setCurrentEpisode(anime.getCurrentEpisode() + increment);
    }
}
