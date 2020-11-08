package com.affinipay;


import org.testng.Assert;
import org.testng.annotations.Test;

public class CalculateTimeTest {

   // As a user I want to add a number of minutes to a time represented as a String.

    @Test
    public void TestAddMinutes() {
        CalculateTime calculateTime = new CalculateTime();
        String newTime = calculateTime.addMinutes("9:10 AM", 200);

        Assert.assertEquals(newTime, "12:30 PM");

    }

    @Test
    public void TestAddMinutesSmallNumber() {
        CalculateTime calculateTime = new CalculateTime();
        String newTime = calculateTime.addMinutes("12:10 PM", 5);

        Assert.assertEquals(newTime, "12:15 PM");

    }

    @Test
    public void TestAddMinutesTwentyFourHours() {
        CalculateTime calculateTime = new CalculateTime();
        String newTime = calculateTime.addMinutes("9:10 AM",1440);

        Assert.assertEquals(newTime, "09:10 AM");

    }

    @Test
    public void TestAddMinutesTwelveHours() {
        CalculateTime calculateTime = new CalculateTime();
        String newTime = calculateTime.addMinutes("09:10 AM",720);

        Assert.assertEquals(newTime, "09:10 PM");

    }

    @Test(expectedExceptions = RuntimeException.class)
    public void TestAddMinutesNegativeMins() {
        CalculateTime calculateTime = new CalculateTime();
        calculateTime.addMinutes("01:10 PM",-1);

    }

    @Test
    public void TestAddMinutesFirstNumSingleChar() {
        CalculateTime calculateTime = new CalculateTime();
        String newTime = calculateTime.addMinutes("3:10 AM", 200);

        Assert.assertEquals(newTime, "06:30 AM");

    }

}