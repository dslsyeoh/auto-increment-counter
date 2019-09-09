/*
 * Author Steven Yeoh
 * Copyright (c) 2019. All rights reserved.
 */

package com.dsl.aic;

import com.dsl.aic.utils.DateUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.util.Date;
import java.util.Locale;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class AnimeUpdaterTest3
{
    private Anime anime;

    @BeforeAll
    void initialize() throws ParseException
    {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM-dd-yyyy", Locale.ENGLISH);
        Date initialDate = simpleDateFormat.parse("09-09-2019");

        anime = new Anime();
        anime.setName("Anime");
        anime.setCurrentEpisode(1);
        anime.setNextReleaseDuration(5);
        anime.setCurrentDate(initialDate);
    }

    @Test
    void testEpisodeEqualTo2()
    {
        LocalDate nextReleaseDate = DateUtils.localDate(anime.getCurrentDate()).plusDays(anime.getNextReleaseDuration());
        LocalDate stimulateReleaseDate = DateUtils.localDate(anime.getCurrentDate()).plusDays(5);

        if(stimulateReleaseDate.isEqual(nextReleaseDate))
        {
            validate(stimulateReleaseDate, nextReleaseDate);
        }

        Assertions.assertEquals(2, anime.getCurrentEpisode());
    }

    @Test
    void testEpisodeEqualTo3()
    {
        LocalDate nextReleaseDate = DateUtils.localDate(anime.getCurrentDate()).plusDays(anime.getNextReleaseDuration());
        LocalDate stimulateReleaseDate = DateUtils.localDate(anime.getCurrentDate()).plusDays(5);

        if(stimulateReleaseDate.isEqual(nextReleaseDate))
        {
            validate(stimulateReleaseDate, nextReleaseDate);
        }

        Assertions.assertEquals(3, anime.getCurrentEpisode());
    }

    @Test
    void testEpisodeEqualTo5()
    {
        LocalDate nextReleaseDate = DateUtils.localDate(anime.getCurrentDate()).plusDays(anime.getNextReleaseDuration());
        LocalDate stimulateReleaseDate = DateUtils.localDate(anime.getCurrentDate()).plusDays(10);

        validate(stimulateReleaseDate, nextReleaseDate);
        Assertions.assertEquals(5, anime.getCurrentEpisode());
    }

    private void validate(LocalDate stimulateReleaseDate, LocalDate nextReleaseDate)
    {
        if(stimulateReleaseDate.isEqual(nextReleaseDate) || stimulateReleaseDate.isAfter(nextReleaseDate))
        {
            update(stimulateReleaseDate, nextReleaseDate, anime);
        }
        else
        {
            anime.setNextReleaseCountdown(Period.between(stimulateReleaseDate, nextReleaseDate).getDays());
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