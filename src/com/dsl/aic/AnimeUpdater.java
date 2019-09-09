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

        if(currentDate.equals(nextReleaseDate))
        {
            update(nextReleaseDate, anime);
        }
        else
        {
            anime.setNextReleaseCountdown(Period.between(currentDate, nextReleaseDate).getDays());
        }
    }

    private void update(LocalDate currentDate, Anime anime)
    {
        anime.setCurrentDate(DateUtils.toDate(currentDate));
        anime.setCurrentEpisode(anime.getCurrentEpisode() + 1);
    }
}
