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
class AnimeUpdaterTest2
{
    private Anime anime;
    private AnimeUpdater animeUpdater;

    @BeforeAll
    void initialize()
    {
        animeUpdater = new AnimeUpdater();

        anime = new Anime();
        anime.setName("Anime");
        anime.setCurrentEpisode(1);
        anime.setUpcomingDays(7);
        anime.setCurrentDate(DateUtils.toDate("09-09-2019"));
    }

    @Test
    void testEpisodeEqualTo2()
    {
        LocalDate stimulateReleaseDate = DateUtils.toLocalDate(anime.getCurrentDate()).plusDays(7);
        animeUpdater.updateTest(anime, stimulateReleaseDate);

        assertEquals(2, anime.getCurrentEpisode());
        assertEquals(7, anime.getNextReleaseCountdown());
        assertEquals(DateUtils.toDate("09-16-2019"), anime.getCurrentDate());
    }

    @Test
    void testEpisodeEqualTo3()
    {
        LocalDate stimulateReleaseDate = DateUtils.toLocalDate(anime.getCurrentDate()).plusDays(8);
        animeUpdater.updateTest(anime, stimulateReleaseDate);

        assertEquals(3, anime.getCurrentEpisode());
        assertEquals(6, anime.getNextReleaseCountdown());
        assertEquals(DateUtils.toDate("09-23-2019"), anime.getCurrentDate());
    }

    @Test
    void testEpisodeEqualTo5()
    {
        for(int i = 0; i < 2; i++)
        {
            LocalDate stimulateReleaseDate = DateUtils.toLocalDate(anime.getCurrentDate()).plusDays(7);
            animeUpdater.updateTest(anime, stimulateReleaseDate);
        }
        assertEquals(5, anime.getCurrentEpisode());
        assertEquals(7, anime.getNextReleaseCountdown());
        assertEquals(DateUtils.toDate("10-07-2019"), anime.getCurrentDate());
    }
}