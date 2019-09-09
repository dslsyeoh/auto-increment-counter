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
class AnimeUpdaterTest
{
    private Anime anime;
    private AnimeUpdater animeUpdater;

    @BeforeAll
    void initialize()
    {
        animeUpdater = new AnimeUpdater();
    }

    @BeforeEach
    void setup() throws ParseException
    {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM-dd-yyyy", Locale.ENGLISH);
        Date initialDate = simpleDateFormat.parse("09-09-2019");

        anime = new Anime();
        anime.setName("Anime");
        anime.setCurrentEpisode(1);
        anime.setNextReleaseDuration(7);
        anime.setCurrentDate(initialDate);
    }

    @Test
    void testEpisodeNotYetRelease()
    {
        LocalDate stimulateReleaseDate = DateUtils.toLocalDate(anime.getCurrentDate()).plusDays(1);
        animeUpdater.update(anime, stimulateReleaseDate);

        Assertions.assertEquals(1, anime.getCurrentEpisode());
    }

    @Test
    void testEpisodeEqualTo2()
    {
        LocalDate stimulateReleaseDate = DateUtils.toLocalDate(anime.getCurrentDate()).plusDays(7);
        animeUpdater.update(anime, stimulateReleaseDate);

        Assertions.assertEquals(2, anime.getCurrentEpisode());
    }

    @Test
    void testReleaseCountdownEqualTo0()
    {
        LocalDate stimulateReleaseDate = DateUtils.toLocalDate(anime.getCurrentDate()).plusDays(7);
        animeUpdater.update(anime, stimulateReleaseDate);

        Assertions.assertEquals(0, anime.getNextReleaseCountdown());
    }

    @Test
    void testReleaseCountdownEqualTo6()
    {
        LocalDate stimulateReleaseDate = DateUtils.toLocalDate(anime.getCurrentDate()).plusDays(1);
        animeUpdater.update(anime, stimulateReleaseDate);

        Assertions.assertEquals(6, anime.getNextReleaseCountdown());
    }

    @Test
    void testEpisodeEqualTo5AndReleaseCountdownEqual6()
    {
        LocalDate stimulateReleaseDate = DateUtils.toLocalDate(anime.getCurrentDate()).plusDays(28);
        animeUpdater.update(anime, stimulateReleaseDate);

        stimulateReleaseDate = DateUtils.toLocalDate(anime.getCurrentDate()).plusDays(1);
        animeUpdater.update(anime, stimulateReleaseDate);

        Assertions.assertEquals(5, anime.getCurrentEpisode());
        Assertions.assertEquals(6, anime.getNextReleaseCountdown());
    }
}