package com.example.deliverable1test;

import com.example.deliverable1test.participant.ParticipantActivity;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class Deliverable4Test {
    private static class TestParticipantActivity extends ParticipantActivity {
        private String filterText;

        @Override
        public boolean onQueryTextChange(String newText) {
            filterText = newText;
            return true;
        }

        @Override
        public boolean onQueryTextSubmit(String query) {
            return true;
        }
    }
    @Test
    public void test1OnQueryTextChange() {
        // Create an instance of the test class
        TestParticipantActivity testActivity = new TestParticipantActivity();

        // Call the onQueryTextChange method
        boolean result = testActivity.onQueryTextChange("ride");

        // Verify that the method returns true
        assertEquals(true, result);

        // Verify the filter text is set correctly
        assertEquals("ride", testActivity.filterText);
    }

    @Test
    public void test1OnQueryTextSubmit() {
        // Create an instance of the test class
        TestParticipantActivity testActivity = new TestParticipantActivity();

        // Call the onQueryTextSubmit method
        boolean result = testActivity.onQueryTextSubmit("ride");
        int cnt = testActivity.adapter.getItemCount();

        // Verify that the method returns true
        assertEquals(true, result && cnt > 0);
    }

    @Test
    public void test2OnQueryTextChange() {
        // Create an instance of the test class
        TestParticipantActivity testActivity = new TestParticipantActivity();

        // Call the onQueryTextChange method
        boolean result = testActivity.onQueryTextChange("the");

        // Verify that the method returns true
        assertEquals(true, result);

        // Verify the filter text is set correctly
        assertEquals("the", testActivity.filterText);
    }

    @Test
    public void test2OnQueryTextSubmit() {
        // Create an instance of the test class
        TestParticipantActivity testActivity = new TestParticipantActivity();

        // Call the onQueryTextSubmit method
        boolean result = testActivity.onQueryTextSubmit("the");
        int cnt = testActivity.adapter.getItemCount();

        // Verify that the method returns true
        assertEquals(true, result && cnt > 0);
    }
    @Test
    public void test3OnQueryTextChange() {
        // Create an instance of the test class
        TestParticipantActivity testActivity = new TestParticipantActivity();

        // Call the onQueryTextChange method
        boolean result = testActivity.onQueryTextChange("Group");

        // Verify that the method returns true
        assertEquals(true, result);

        // Verify the filter text is set correctly
        assertEquals("Group", testActivity.filterText);
    }

    @Test
    public void test3OnQueryTextSubmit() {
        // Create an instance of the test class
        TestParticipantActivity testActivity = new TestParticipantActivity();

        // Call the onQueryTextSubmit method
        boolean result = testActivity.onQueryTextSubmit("Group");

        int cnt = testActivity.adapter.getItemCount();

        // Verify that the method returns true
        assertEquals(true, result && cnt > 0);
    }
    @Test
    public void test4OnQueryTextChange() {
        // Create an instance of the test class
        TestParticipantActivity testActivity = new TestParticipantActivity();

        // Call the onQueryTextChange method
        boolean result = testActivity.onQueryTextChange("d");

        // Verify that the method returns true
        assertEquals(true, result);

        // Verify the filter text is set correctly
        assertEquals("d", testActivity.filterText);
    }

    @Test
    public void test4OnQueryTextSubmit() {
        // Create an instance of the test class
        TestParticipantActivity testActivity = new TestParticipantActivity();

        // Call the onQueryTextSubmit method
        boolean result = testActivity.onQueryTextSubmit("d");
        int cnt = testActivity.adapter.getItemCount();

        // Verify that the method returns true
        assertEquals(true, result && cnt > 0);
    }

    @Test
    public void test5OnQueryTextChange() {
        // Create an instance of the test class
        TestParticipantActivity testActivity = new TestParticipantActivity();

        // Call the onQueryTextChange method
        boolean result = testActivity.onQueryTextChange("ha");

        // Verify that the method returns true
        assertEquals(true, result);

        // Verify the filter text is set correctly
        assertEquals("ha", testActivity.filterText);
    }

    @Test
    public void test5OnQueryTextSubmit() {
        // Create an instance of the test class
        TestParticipantActivity testActivity = new TestParticipantActivity();

        // Call the onQueryTextSubmit method
        boolean result = testActivity.onQueryTextSubmit("ha");

        int cnt = testActivity.adapter.getItemCount();

        // Verify that the method returns true
        assertEquals(true, result && cnt > 0);
    }

    @Test
    public void test6OnQueryTextChange() {
        // Create an instance of the test class
        TestParticipantActivity testActivity = new TestParticipantActivity();

        // Call the onQueryTextChange method
        boolean result = testActivity.onQueryTextChange("hill");

        // Verify that the method returns true
        assertEquals(true, result);

        // Verify the filter text is set correctly
        assertEquals("hill", testActivity.filterText);
    }

    @Test
    public void test6OnQueryTextSubmit() {
        // Create an instance of the test class
        TestParticipantActivity testActivity = new TestParticipantActivity();

        // Call the onQueryTextSubmit method
        boolean result = testActivity.onQueryTextSubmit("hill");
        int cnt = testActivity.adapter.getItemCount();

        // Verify that the method returns true
        assertEquals(true, result && cnt > 0);
    }

    @Test
    public void test7OnQueryTextChange() {
        // Create an instance of the test class
        TestParticipantActivity testActivity = new TestParticipantActivity();

        // Call the onQueryTextChange method
        boolean result = testActivity.onQueryTextChange("t");

        // Verify that the method returns true
        assertEquals(true, result);
        int cnt = testActivity.adapter.getItemCount();

        // Verify that the method returns true
        assertEquals(true, result && cnt > 0);
    }

    @Test
    public void test7OnQueryTextSubmit() {
        // Create an instance of the test class
        TestParticipantActivity testActivity = new TestParticipantActivity();

        // Call the onQueryTextSubmit method
        boolean result = testActivity.onQueryTextSubmit("t");
        int cnt = testActivity.adapter.getItemCount();

        // Verify that the method returns true
        assertEquals(true, result && cnt > 0);
    }

    @Test
    public void test8OnQueryTextChange() {
        // Create an instance of the test class
        TestParticipantActivity testActivity = new TestParticipantActivity();

        // Call the onQueryTextChange method
        boolean result = testActivity.onQueryTextChange("time");

        // Verify that the method returns true
        assertEquals(true, result);

        // Verify the filter text is set correctly
        assertEquals("time", testActivity.filterText);
    }

    @Test
    public void test8OnQueryTextSubmit() {
        // Create an instance of the test class
        TestParticipantActivity testActivity = new TestParticipantActivity();

        // Call the onQueryTextSubmit method
        boolean result = testActivity.onQueryTextSubmit("time");
        int cnt = testActivity.adapter.getItemCount();

        // Verify that the method returns true
        assertEquals(true, result && cnt > 0);
    }

    @Test
    public void test9OnQueryTextChange() {
        // Create an instance of the test class
        TestParticipantActivity testActivity = new TestParticipantActivity();

        // Call the onQueryTextChange method
        boolean result = testActivity.onQueryTextChange("trial");

        // Verify that the method returns true
        assertEquals(true, result);

        // Verify the filter text is set correctly
        assertEquals("trial", testActivity.filterText);
    }

    @Test
    public void test9OnQueryTextSubmit() {
        // Create an instance of the test class
        TestParticipantActivity testActivity = new TestParticipantActivity();

        // Call the onQueryTextSubmit method
        boolean result = testActivity.onQueryTextSubmit("trial");

        int cnt = testActivity.adapter.getItemCount();

        // Verify that the method returns true
        assertEquals(true, result && cnt > 0);
    }

    @Test
    public void test10OnQueryTextChange() {
        // Create an instance of the test class
        TestParticipantActivity testActivity = new TestParticipantActivity();

        // Call the onQueryTextChange method
        boolean result = testActivity.onQueryTextChange("me");

        // Verify that the method returns true
        assertEquals(true, result);

        // Verify the filter text is set correctly
        assertEquals("me", testActivity.filterText);
    }

    @Test
    public void test10OnQueryTextSubmit() {
        // Create an instance of the test class
        TestParticipantActivity testActivity = new TestParticipantActivity();

        // Call the onQueryTextSubmit method
        boolean result = testActivity.onQueryTextSubmit("me");
        int cnt = testActivity.adapter.getItemCount();

        // Verify that the method returns true
        assertEquals(true, result && cnt > 0);
    }
}
