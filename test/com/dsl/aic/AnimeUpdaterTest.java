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

    @BeforeEach
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
    void testEpisodeNotYetRelease()
    {
        LocalDate nextReleaseDate = DateUtils.localDate(anime.getCurrentDate()).plusDays(anime.getNextReleaseDuration());
        LocalDate stimulateReleaseDate = DateUtils.localDate(anime.getCurrentDate()).plusDays(1);

        validate(stimulateReleaseDate, nextReleaseDate);
        Assertions.assertEquals(1, anime.getCurrentEpisode());
    }

    @Test
    void testEpisodeEqualTo2()
    {
        LocalDate nextReleaseDate = DateUtils.localDate(anime.getCurrentDate()).plusDays(anime.getNextReleaseDuration());
        LocalDate stimulateReleaseDate = DateUtils.localDate(anime.getCurrentDate()).plusDays(7);

        validate(stimulateReleaseDate, nextReleaseDate);

        Assertions.assertEquals(2, anime.getCurrentEpisode());
    }

    @Test
    void testReleaseCountdownEqualTo0()
    {
        LocalDate nextReleaseDate = DateUtils.localDate(anime.getCurrentDate()).plusDays(anime.getNextReleaseDuration());
        LocalDate stimulateReleaseDate = DateUtils.localDate(anime.getCurrentDate()).plusDays(7);

        validate(stimulateReleaseDate, nextReleaseDate);
        Assertions.assertEquals(0, anime.getNextReleaseCountdown());
    }

    @Test
    void testReleaseCountdownEqualTo6()
    {
        LocalDate nextReleaseDate = DateUtils.localDate(anime.getCurrentDate()).plusDays(anime.getNextReleaseDuration());
        LocalDate stimulateReleaseDate = DateUtils.localDate(anime.getCurrentDate()).plusDays(1);

        validate(stimulateReleaseDate, nextReleaseDate);
        Assertions.assertEquals(6, anime.getNextReleaseCountdown());
    }

    @Test
    void testEpisodeEqualTo5AndReleaseCountdownEqual6()
    {
        LocalDate nextReleaseDate;
        LocalDate stimulateReleaseDate;

        nextReleaseDate = DateUtils.localDate(anime.getCurrentDate()).plusDays(anime.getNextReleaseDuration());
        stimulateReleaseDate = DateUtils.localDate(anime.getCurrentDate()).plusDays(28);

        validate(stimulateReleaseDate, nextReleaseDate);

        nextReleaseDate = DateUtils.localDate(anime.getCurrentDate()).plusDays(anime.getNextReleaseDuration());
        stimulateReleaseDate = DateUtils.localDate(anime.getCurrentDate()).plusDays(1);

        validate(stimulateReleaseDate, nextReleaseDate);

        Assertions.assertEquals(5, anime.getCurrentEpisode());
        Assertions.assertEquals(6, anime.getNextReleaseCountdown());
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
        int counter = 0;
        if (diff > 0) counter = diff / anime.getNextReleaseDuration();
        anime.setCurrentDate(DateUtils.toDate(currentDate));
        anime.setCurrentEpisode(anime.getCurrentEpisode() + 1 + counter);
    }
}