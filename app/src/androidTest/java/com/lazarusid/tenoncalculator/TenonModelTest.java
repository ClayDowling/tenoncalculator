package com.lazarusid.tenoncalculator;

/**
 * Created by clay on 7/7/15.
 */
import android.app.Application;
import android.test.AndroidTestCase;
import android.test.ApplicationTestCase;

/**
 * <a href="http://d.android.com/tools/testing/testing_android.html">Testing Fundamentals</a>
 */
public class TenonModelTest extends AndroidTestCase {
    public TenonModelTest() {
        super();
    }

    private TenonModel model;

    public void setUp() throws Exception {
        super.setUp();
        model = new TenonModel();

        model.setLeg(2.0);
        model.setRailWidth(0.75);
    }

    public void testGetRailWidthReturnsSetRailWidth() {
        assertEquals(0.75, model.getRailWidth());
    }

    public void testGetTenonWidth() throws Exception {
        assertEquals(0.25, model.getTenonWidth());
    }

    public void testGetTennonWidthMatchesSetTennonWidth() throws Exception {
        model.setTenonWidth(0.375);
        assertEquals(0.375, model.getTenonWidth());
    }

    public void testGetDefaultFrontTenonShoulder() throws Exception {
        assertEquals(0.25, model.getFrontTenonShoulder());
    }

    public void testGetFrontTenonShoulderMatchesSetFrontTenonShoulder() throws Exception {
        model.setFrontTenonShoulder(0.375);
        assertEquals(0.375, model.getFrontTenonShoulder());
    }

    public void testGetMortiseShoulderWithoutRailSetback() throws Exception {
        assertEquals(0.25, model.getMortiseShoulder());
    }

    public void testGetMortiseShoulderWithRailSetback() throws Exception {
        model.setRailSetback(0.125);
        assertEquals(0.375, model.getMortiseShoulder());
    }

    public void testGetMortiseDepthWithoutRailSetback() throws Exception {
        assertEquals(1.75, model.getMortiseDepth());
    }

    public void testGetMortiseDepthWithRailSetback() throws Exception {
        model.setRailSetback(0.125);
        assertEquals(1.625, model.getMortiseDepth());
    }

    public void testGetMortiseInsideDepthWithoutRailSetback() throws Exception {
        assertEquals(1.5, model.getMortiseInsideDepth());
    }

    public void testGetMortiseInsideDepthWithRailSetback() throws Exception {
        model.setRailSetback(0.125);
        assertEquals(1.375, model.getMortiseInsideDepth());
    }

    public void testGetMortiseDepthIsZeroIfLegIsZero() throws Exception {
        model.setLeg(0.0);
        model.setRailWidth(0.25);
        assertEquals(0.0, model.getMortiseDepth());
    }

    public void testGetMortiseDepthIsZeroIfRailWidthIsZero() throws Exception {
        model.setLeg(2.0);
        model.setRailWidth(0.0);
        assertEquals(0.0, model.getMortiseDepth());
    }
}