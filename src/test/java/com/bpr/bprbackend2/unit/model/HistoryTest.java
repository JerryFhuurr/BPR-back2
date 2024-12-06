package com.bpr.bprbackend2.unit.model;

import com.bpr.bprbackend2.model.History;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class HistoryTest {

    private History historyUnderTest;

    @BeforeEach
    void setUp() {
        historyUnderTest = new History(0, 0, 0, 0, 0, 0, 0L);
    }

    @Test
    void testHIdGetterAndSetter() {
        final int hId = 0;
        historyUnderTest.setHId(hId);
        assertThat(historyUnderTest.getHId()).isEqualTo(hId);
    }

    @Test
    void testUserIdGetterAndSetter() {
        final int userId = 0;
        historyUnderTest.setUserId(userId);
        assertThat(historyUnderTest.getUserId()).isEqualTo(userId);
    }

    @Test
    void testCourseIdGetterAndSetter() {
        final int courseId = 0;
        historyUnderTest.setCourseId(courseId);
        assertThat(historyUnderTest.getCourseId()).isEqualTo(courseId);
    }

    @Test
    void testResIdGetterAndSetter() {
        final int resId = 0;
        historyUnderTest.setResId(resId);
        assertThat(historyUnderTest.getResId()).isEqualTo(resId);
    }

    @Test
    void testRoleIdGetterAndSetter() {
        final int roleId = 0;
        historyUnderTest.setRoleId(roleId);
        assertThat(historyUnderTest.getRoleId()).isEqualTo(roleId);
    }

    @Test
    void testWatcherIdGetterAndSetter() {
        final int watcherId = 0;
        historyUnderTest.setWatcherId(watcherId);
        assertThat(historyUnderTest.getWatcherId()).isEqualTo(watcherId);
    }

    @Test
    void testWatchTimeGetterAndSetter() {
        final long watchTime = 0L;
        historyUnderTest.setWatchTime(watchTime);
        assertThat(historyUnderTest.getWatchTime()).isEqualTo(watchTime);
    }

    @Test
    void testEquals() {
        assertThat(historyUnderTest.equals("o")).isFalse();
    }

    @Test
    void testToString() {
        assertThat(historyUnderTest.toString()).isEqualTo("result");
    }

    @Test
    void testBuilder() {
        // Setup
        // Run the test
        final History.HistoryBuilder result = History.builder();

        // Verify the results
    }
}
