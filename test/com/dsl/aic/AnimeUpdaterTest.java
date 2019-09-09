/*
 * Author Steven Yeoh
 * Copyright (c) 2019. All rights reserved.
 */

package com.dsl.aic;

import com.dsl.aic.utils.DateUtils;
import org.junit.jupiter.api.*;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
    void setup()
    {
        anime = new Anime();
        anime.setName("Anime");
        anime.setCurrentEpisode(1);
        anime.setNextReleaseDuration(7);
        anime.setCurrentDate(DateUtils.toDate("09-09-2019"));
    }

    @Test
    void testEpisodeNotYetRelease()
    {
        LocalDate stimulateReleaseDate = DateUtils.toLocalDate(anime.getCurrentDate()).plusDays(1);
        animeUpdater.update(anime, stimulateReleaseDate);

        assertEquals(1, anime.getCurrentEpisode());
        assertEquals(DateUtils.toDate("09-09-2019"), anime.getCurrentDate());
    }

    @Test
    void testEpisodeEqualTo2()
    {
        LocalDate stimulateReleaseDate = DateUtils.toLocalDate(anime.getCurrentDate()).plusDays(7);
        animeUpdater.update(anime, stimulateReleaseDate);

        assertEquals(2, anime.getCurrentEpisode());
        assertEquals(DateUtils.toDate("09-16-2019"), anime.getCurrentDate());
    }

    @Test
    void testReleaseCountdownEqualTo0()
    {
        LocalDate stimulateReleaseDate = DateUtils.toLocalDate(anime.getCurrentDate()).plusDays(7);
        animeUpdater.update(anime, stimulateReleaseDate);

        assertEquals(0, anime.getNextReleaseCountdown());
        assertEquals(DateUtils.toDate("09-16-2019"), anime.getCurrentDate());
    }

    @Test
    void testReleaseCountdownEqualTo6()
    {
        LocalDate stimulateReleaseDate = DateUtils.toLocalDate(anime.getCurrentDate()).plusDays(1);
        animeUpdater.update(anime, stimulateReleaseDate);

        assertEquals(6, anime.getNextReleaseCountdown());
        assertEquals(DateUtils.toDate("09-09-2019"), anime.getCurrentDate());
    }

    @Test
    void testEpisodeEqualTo5AndReleaseCountdownEqual6()
    {
        LocalDate stimulateReleaseDate = DateUtils.toLocalDate(anime.getCurrentDate()).plusDays(28);
        animeUpdater.update(anime, stimulateReleaseDate);

        stimulateReleaseDate = DateUtils.toLocalDate(anime.getCurrentDate()).plusDays(1);
        animeUpdater.update(anime, stimulateReleaseDate);

        assertEquals(5, anime.getCurrentEpisode());
        assertEquals(6, anime.getNextReleaseCountdown());
        assertEquals(DateUtils.toDate("10-07-2019"), anime.getCurrentDate());
    }
}