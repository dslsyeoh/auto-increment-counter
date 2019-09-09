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

    @BeforeAll
    void initialize() throws ParseException
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
    void testEpisodeEqualTo2()
    {
        LocalDate nextReleaseDate = DateUtils.localDate(anime.getCurrentDate()).plusDays(anime.getNextReleaseDuration());
        LocalDate stimulateReleaseDate = DateUtils.localDate(anime.getCurrentDate()).plusDays(7);

        if(stimulateReleaseDate.isEqual(nextReleaseDate))
        {
            validate(nextReleaseDate, nextReleaseDate);
        }

        Assertions.assertEquals(2, anime.getCurrentEpisode());
    }

    @Test
    void testEpisodeEqualTo3()
    {
        LocalDate nextReleaseDate = DateUtils.localDate(anime.getCurrentDate()).plusDays(anime.getNextReleaseDuration());
        LocalDate stimulateReleaseDate = DateUtils.localDate(anime.getCurrentDate()).plusDays(7);

        if(stimulateReleaseDate.isEqual(nextReleaseDate))
        {
            validate(nextReleaseDate, nextReleaseDate);
        }

        Assertions.assertEquals(3, anime.getCurrentEpisode());
    }

    @Test
    void testEpisodeEqualTo5()
    {
        for(int i = 0; i < 2; i++)
        {
            LocalDate nextReleaseDate = DateUtils.localDate(anime.getCurrentDate()).plusDays(anime.getNextReleaseDuration());
            LocalDate stimulateReleaseDate = DateUtils.localDate(anime.getCurrentDate()).plusDays(7);

            if(stimulateReleaseDate.isEqual(nextReleaseDate))
            {
                validate(nextReleaseDate, nextReleaseDate);
            }
        }
        Assertions.assertEquals(5, anime.getCurrentEpisode());
    }

    private void validate(LocalDate stimulateReleaseDate, LocalDate nextReleaseDate)
    {
        if(stimulateReleaseDate.isEqual(nextReleaseDate))
        {
            update(nextReleaseDate, anime);
        }
        else
        {
            anime.setNextReleaseCountdown(Period.between(stimulateReleaseDate, nextReleaseDate).getDays());
        }
    }

    private void update(LocalDate currentDate, Anime anime)
    {
        anime.setCurrentDate(DateUtils.toDate(currentDate));
        anime.setCurrentEpisode(anime.getCurrentEpisode() + 1);
    }
}