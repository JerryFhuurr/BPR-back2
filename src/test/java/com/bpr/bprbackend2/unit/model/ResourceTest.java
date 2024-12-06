package com.bpr.bprbackend2.unit.model;

import com.bpr.bprbackend2.model.Resource;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.within;

class ResourceTest {

    private Resource resourceUnderTest;

    @BeforeEach
    void setUp() {
        resourceUnderTest = new Resource(0, 0, 0, 0, 0.0f, "resTitle", "resDescription", "fileUrl", "fileName",
                "fileNameDownload", 0L, "type");
    }

    @Test
    void testResIdGetterAndSetter() {
        final int resId = 0;
        resourceUnderTest.setResId(resId);
        assertThat(resourceUnderTest.getResId()).isEqualTo(resId);
    }

    @Test
    void testCourseIdGetterAndSetter() {
        final int courseId = 0;
        resourceUnderTest.setCourseId(courseId);
        assertThat(resourceUnderTest.getCourseId()).isEqualTo(courseId);
    }

    @Test
    void testUserIdGetterAndSetter() {
        final int userId = 0;
        resourceUnderTest.setUserId(userId);
        assertThat(resourceUnderTest.getUserId()).isEqualTo(userId);
    }

    @Test
    void testRoleIdGetterAndSetter() {
        final int roleId = 0;
        resourceUnderTest.setRoleId(roleId);
        assertThat(resourceUnderTest.getRoleId()).isEqualTo(roleId);
    }

    @Test
    void testResScoreGetterAndSetter() {
        final float resScore = 0.0f;
        resourceUnderTest.setResScore(resScore);
        assertThat(resourceUnderTest.getResScore()).isEqualTo(resScore, within(0.0001f));
    }

    @Test
    void testResTitleGetterAndSetter() {
        final String resTitle = "resTitle";
        resourceUnderTest.setResTitle(resTitle);
        assertThat(resourceUnderTest.getResTitle()).isEqualTo(resTitle);
    }

    @Test
    void testResDescriptionGetterAndSetter() {
        final String resDescription = "resDescription";
        resourceUnderTest.setResDescription(resDescription);
        assertThat(resourceUnderTest.getResDescription()).isEqualTo(resDescription);
    }

    @Test
    void testFileUrlGetterAndSetter() {
        final String fileUrl = "fileUrl";
        resourceUnderTest.setFileUrl(fileUrl);
        assertThat(resourceUnderTest.getFileUrl()).isEqualTo(fileUrl);
    }

    @Test
    void testFileNameGetterAndSetter() {
        final String fileName = "fileName";
        resourceUnderTest.setFileName(fileName);
        assertThat(resourceUnderTest.getFileName()).isEqualTo(fileName);
    }

    @Test
    void testFileNameDownloadGetterAndSetter() {
        final String fileNameDownload = "fileNameDownload";
        resourceUnderTest.setFileNameDownload(fileNameDownload);
        assertThat(resourceUnderTest.getFileNameDownload()).isEqualTo(fileNameDownload);
    }

    @Test
    void testFileSizeGetterAndSetter() {
        final long fileSize = 0L;
        resourceUnderTest.setFileSize(fileSize);
        assertThat(resourceUnderTest.getFileSize()).isEqualTo(fileSize);
    }

    @Test
    void testTypeGetterAndSetter() {
        final String type = "type";
        resourceUnderTest.setType(type);
        assertThat(resourceUnderTest.getType()).isEqualTo(type);
    }

    @Test
    void testEquals() {
        assertThat(resourceUnderTest.equals("o")).isFalse();
    }

    @Test
    void testToString() {
        assertThat(resourceUnderTest.toString()).isEqualTo("result");
    }

    @Test
    void testBuilder() {
        // Setup
        // Run the test
        final Resource.ResourceBuilder result = Resource.builder();

        // Verify the results
    }
}
