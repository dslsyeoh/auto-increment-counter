/*
 * Author Steven Yeoh
 * Copyright (c) 2019. All rights reserved.
 */

package com.dsl.aic;

import com.dsl.aic.utils.DateUtils;
import org.junit.jupiter.api.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.util.Date;
import java.util.Locale;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class AnimeUpdaterTest2
{
    private Anime anime;
    private AnimeUpdater animeUpdater;

    @BeforeAll
    void initialize() throws ParseException
    {
        animeUpdater = new AnimeUpdater();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM-dd-yyyy", Locale.ENGLISH);
        Date initialDate = simpleDateFormat.parse("09-09-2019");

        anime = new Anime();
        anime.setName("Anime");
        anime.setCurrentEpisode(1);
        anime.setNextReleaseDuration(7);
        anime.setCurrentDate(initialDate);
    }

    @Test
    void testEpisodeEqualTo2()
    {
        LocalDate stimulateReleaseDate = DateUtils.toLocalDate(anime.getCurrentDate()).plusDays(7);
        animeUpdater.update(anime, stimulateReleaseDate);

        Assertions.assertEquals(2, anime.getCurrentEpisode());
    }

    @Test
    void testEpisodeEqualTo3()
    {
        LocalDate stimulateReleaseDate = DateUtils.toLocalDate(anime.getCurrentDate()).plusDays(7);
        animeUpdater.update(anime, stimulateReleaseDate);

        Assertions.assertEquals(3, anime.getCurrentEpisode());
    }

    @Test
    void testEpisodeEqualTo5()
    {
        for(int i = 0; i < 2; i++)
        {
            LocalDate stimulateReleaseDate = DateUtils.toLocalDate(anime.getCurrentDate()).plusDays(7);
            animeUpdater.update(anime, stimulateReleaseDate);
        }
        Assertions.assertEquals(5, anime.getCurrentEpisode());
    }
}