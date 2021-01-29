package com.zs.hobbytracker.lru;


import com.zs.hobbytracker.models.Badminton;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.sql.Date;
import java.sql.Time;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

/**
 * To test the Cache class
 */
public class CacheTest {

    /**
     * Cache for testing
     */
    Cache cache;
    Badminton badminton1, badminton2, badminton3, badminton4;

    /**
     * Initialising cache
     */
    @Before
    public void setup() {
        cache = new Cache(2);
        badminton1 = new Badminton(1, 1, true, Time.valueOf("10:10:10"), Time.valueOf("11:11:11"), Date.valueOf("2021-01-07"), "badminton", 4, "win");
        badminton2 = new Badminton(2, 1, true, Time.valueOf("11:11:11"), Time.valueOf("11:11:11"), Date.valueOf("2021-01-07"), "badminton", 4, "win");
        badminton3 = new Badminton(3, 1, true, Time.valueOf("12:12:12"), Time.valueOf("11:11:11"), Date.valueOf("2021-01-07"), "badminton", 4, "win");
        badminton4 = new Badminton(4, 1, true, Time.valueOf("10:10:10"), Time.valueOf("11:11:11"), Date.valueOf("2021-01-07"), "badminton", 4, "win");

    }


    @Test
    /**
     * Get when key is present
     */
    public void getWhenKeyIsPresent() {
        cache.put(badminton1.getUserId(), badminton1);
        assertEquals(badminton1, cache.get(badminton1.getUserId()));
        cache.put(badminton2.getUserId(), badminton2);
        assertEquals(badminton2, cache.get(badminton2.getUserId()));
    }

    @Test
    /**
     * Get when key is absent
     */
    public void getWhenKeyIsAbsent() {
        assertNull(cache.get(badminton1.getUserId()));
        assertNull(cache.get(badminton2.getUserId()));
    }

    @Test
    /**
     * Put when key is present
     */
    public void putWhenKeyIsPresent() {
        cache.put(badminton1.getUserId(), badminton1);
        badminton1.setNumberOfPlayers(99);
        cache.put(badminton1.getUserId(), badminton1);
        assertEquals(badminton1, cache.get(badminton1.getUserId()));
    }

    @Test
    /**
     * Put when capacity is at max
     */
    public void putWhenCapacityIsAtMax() {
        cache.put(badminton1.getUserId(), badminton1);
        cache.put(badminton2.getUserId(), badminton2);
        cache.put(badminton3.getUserId(), badminton3);
        assertEquals(null, cache.get(badminton1.getUserId()));
    }

    /**
     * Test case for testing get and put of cache
     */
    @Ignore
    @Test
    public void get() {

        cache.put(1, 1);
        Object o = cache.get(1);
        assertEquals(1, o);

        cache.put(2, 2);
        Integer i = (Integer) cache.get(2);
        assertEquals((Integer) 2, i);

        o = cache.get(3);
        assertEquals(null, o);

        cache.put(3, 3);
        o = cache.get(3);
        assertEquals(3, o);

        o = cache.get(1);
        assertEquals(null, o);


    }

    /**
     * Test for generic cache
     */
    @Test
    @Ignore
    public void genericTest() {

        Badminton badminton1 = new Badminton();
        badminton1.setUserId(1);
        badminton1.setResult("win");
        badminton1.setNumberOfPlayers(4);
        badminton1.setStartTime(Time.valueOf("10:10:10"));
        badminton1.setEndTime(Time.valueOf("10:11:11"));
        cache.put(badminton1.getUserId(), badminton1);
        assertEquals(cache.get(badminton1.getUserId()), badminton1);

        Badminton badminton2 = new Badminton();
        badminton2.setUserId(2);
        badminton2.setResult("win");
        badminton2.setNumberOfPlayers(4);
        badminton2.setStartTime(Time.valueOf("10:10:10"));
        badminton2.setEndTime(Time.valueOf("10:11:11"));
        cache.put(badminton2.getUserId(), badminton2);
        assertEquals(cache.get(badminton2.getUserId()), badminton2);

        assertEquals(null, cache.get(3));

        Badminton badminton3 = new Badminton();
        badminton3.setUserId(3);
        badminton3.setResult("win");
        badminton3.setNumberOfPlayers(4);
        badminton3.setStartTime(Time.valueOf("11:11:11"));
        badminton3.setEndTime(Time.valueOf("12:12:12"));
        cache.put(badminton3.getUserId(), badminton3);

        assertEquals(badminton3, cache.get(badminton3.getUserId()));

        assertEquals(null, cache.get(badminton1.getUserId()));

    }

}
