/*
 * Author Steven Yeoh
 * Copyright (c) 2019. All rights reserved.
 */

package com.dsl.aic;

import com.dsl.aic.utils.DateUtils;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.text.ParseException;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class AnimeUpdaterTest3
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
        anime.setNextReleaseDuration(5);
        anime.setCurrentDate(DateUtils.toDate("09-09-2019"));
    }

    @Test
    void testEpisodeEqualTo2()
    {
        LocalDate stimulateReleaseDate = DateUtils.toLocalDate(anime.getCurrentDate()).plusDays(5);
        animeUpdater.update(anime, stimulateReleaseDate);

        assertEquals(2, anime.getCurrentEpisode());
        assertEquals(DateUtils.toDate("09-14-2019"), anime.getCurrentDate());
    }

    @Test
    void testEpisodeEqualTo3()
    {
        LocalDate stimulateReleaseDate = DateUtils.toLocalDate(anime.getCurrentDate()).plusDays(5);
        animeUpdater.update(anime, stimulateReleaseDate);

        assertEquals(3, anime.getCurrentEpisode());
        assertEquals(DateUtils.toDate("09-19-2019"), anime.getCurrentDate());
    }

    @Test
    void testEpisodeEqualTo5() throws ParseException
    {
        LocalDate stimulateReleaseDate = DateUtils.toLocalDate(anime.getCurrentDate()).plusDays(11);
        animeUpdater.update(anime, stimulateReleaseDate);

        assertEquals(5, anime.getCurrentEpisode());
        assertEquals(DateUtils.toDate("09-29-2019"), anime.getCurrentDate());
    }
}