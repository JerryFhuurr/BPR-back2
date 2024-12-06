package com.bpr.bprbackend2.unit.impl;

import com.bpr.bprbackend2.mapper.CommentMapper;
import com.bpr.bprbackend2.mapper.ResMapper;
import com.bpr.bprbackend2.mapper.UserMapper;
import com.bpr.bprbackend2.model.Resource;
import com.bpr.bprbackend2.service.impl.ResServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ResServiceImplTest {

    @Mock
    private UserMapper mockUserMapper;
    @Mock
    private ResMapper mockResMapper;
    @Mock
    private CommentMapper mockCommentMapper;

    @InjectMocks
    private ResServiceImpl resServiceImplUnderTest;

    @BeforeEach
    void setUp() {
        ReflectionTestUtils.setField(resServiceImplUnderTest, "uploadDir", "uploadDir");
    }

    @Test
    void testGetResList() {
        // Setup
        final ArrayList<Resource> expectedResult = new ArrayList<>(List.of(Resource.builder()
                .userId(0)
                .roleId(0)
                .resTitle("title")
                .resDescription("description")
                .fileUrl("fileUrl")
                .fileName("fileName")
                .fileNameDownload("fileNameDownload")
                .fileSize(0L)
                .build()));

        // Configure ResMapper.getResList(...).
        final ArrayList<Resource> resources = new ArrayList<>(List.of(Resource.builder()
                .userId(0)
                .roleId(0)
                .resTitle("title")
                .resDescription("description")
                .fileUrl("fileUrl")
                .fileName("fileName")
                .fileNameDownload("fileNameDownload")
                .fileSize(0L)
                .build()));
        when(mockResMapper.getResList(0)).thenReturn(resources);

        // Run the test
        final ArrayList<Resource> result = resServiceImplUnderTest.getResList(0);

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testGetResList_ResMapperReturnsNoItems() {
        // Setup
        when(mockResMapper.getResList(0)).thenReturn(new ArrayList<>());

        // Run the test
        final ArrayList<Resource> result = resServiceImplUnderTest.getResList(0);

        // Verify the results
        assertThat(result).isEqualTo(new ArrayList<>());
    }

    @Test
    void testGetResListByUser() {
        // Setup
        final ArrayList<Resource> expectedResult = new ArrayList<>(List.of(Resource.builder()
                .userId(0)
                .roleId(0)
                .resTitle("title")
                .resDescription("description")
                .fileUrl("fileUrl")
                .fileName("fileName")
                .fileNameDownload("fileNameDownload")
                .fileSize(0L)
                .build()));

        // Configure ResMapper.getResListByUser(...).
        final ArrayList<Resource> resources = new ArrayList<>(List.of(Resource.builder()
                .userId(0)
                .roleId(0)
                .resTitle("title")
                .resDescription("description")
                .fileUrl("fileUrl")
                .fileName("fileName")
                .fileNameDownload("fileNameDownload")
                .fileSize(0L)
                .build()));
        when(mockResMapper.getResListByUser(0)).thenReturn(resources);

        // Run the test
        final ArrayList<Resource> result = resServiceImplUnderTest.getResListByUser(0);

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testGetResListByUser_ResMapperReturnsNoItems() {
        // Setup
        when(mockResMapper.getResListByUser(0)).thenReturn(new ArrayList<>());

        // Run the test
        final ArrayList<Resource> result = resServiceImplUnderTest.getResListByUser(0);

        // Verify the results
        assertThat(result).isEqualTo(new ArrayList<>());
    }

    @Test
    void testGetRes() {
        // Setup
        final Resource expectedResult = Resource.builder()
                .userId(0)
                .roleId(0)
                .resTitle("title")
                .resDescription("description")
                .fileUrl("fileUrl")
                .fileName("fileName")
                .fileNameDownload("fileNameDownload")
                .fileSize(0L)
                .build();

        // Configure ResMapper.getRes(...).
        final Resource resource = Resource.builder()
                .userId(0)
                .roleId(0)
                .resTitle("title")
                .resDescription("description")
                .fileUrl("fileUrl")
                .fileName("fileName")
                .fileNameDownload("fileNameDownload")
                .fileSize(0L)
                .build();
        when(mockResMapper.getRes(0)).thenReturn(resource);

        // Run the test
        final Resource result = resServiceImplUnderTest.getRes(0);

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }


    @Test
    void testRemoveRes() {
        // Setup
        // Configure ResMapper.getRes(...).
        final Resource resource = Resource.builder()
                .userId(0)
                .roleId(0)
                .resTitle("title")
                .resDescription("description")
                .fileUrl("fileUrl")
                .fileName("fileName")
                .fileNameDownload("fileNameDownload")
                .fileSize(0L)
                .build();
        when(mockResMapper.getRes(0)).thenReturn(resource);

        // Run the test
        final String result = resServiceImplUnderTest.removeRes(0);

        // Verify the results
        assertThat(result).isEqualTo("Deleted Successfully");
        verify(mockCommentMapper).removeCommentByVideo(0);
        verify(mockResMapper).removeRes(0);
    }

    @Test
    void testUpdateRes() {
        // Setup
        // Configure ResMapper.getRes(...).
        final Resource resource = Resource.builder()
                .userId(0)
                .roleId(0)
                .resTitle("title")
                .resDescription("description")
                .fileUrl("fileUrl")
                .fileName("fileName")
                .fileNameDownload("fileNameDownload")
                .fileSize(0L)
                .build();
        when(mockResMapper.getRes(0)).thenReturn(resource);

        //when(mockUserMapper.getUserRoleById(0)).thenReturn("result");

        // Run the test
        final String result = resServiceImplUnderTest.updateRes(0, 0, "title", "description");

        // Verify the results
        assertThat(result).isEqualTo("Updated Successfully");
        verify(mockResMapper).updateResInfo(Resource.builder()
                .userId(0)
                .roleId(0)
                .resTitle("title")
                .resDescription("description")
                .fileUrl("fileUrl")
                .fileName("fileName")
                .fileNameDownload("fileNameDownload")
                .fileSize(0L)
                .build());
    }

    @Test
    void testGetFilePathByName() {
        // Setup
        // Configure ResMapper.getFilePathByName(...).
        final Resource resource = Resource.builder()
                .userId(0)
                .roleId(0)
                .resTitle("title")
                .resDescription("description")
                .fileUrl("fileUrl")
                .fileName("fileName")
                .fileNameDownload("fileNameDownload")
                .fileSize(0L)
                .build();
        when(mockResMapper.getFilePathByName("fileName")).thenReturn(resource);

        // Run the test
        final String result = resServiceImplUnderTest.getFilePathByName("fileName");

        // Verify the results
        assertThat(result).isEqualTo("fileUrl");
    }
}
