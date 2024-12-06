package com.bpr.bprbackend2.unit.impl;

import com.bpr.bprbackend2.mapper.HistoryMapper;
import com.bpr.bprbackend2.mapper.ResMapper;
import com.bpr.bprbackend2.mapper.UserMapper;
import com.bpr.bprbackend2.model.History;
import com.bpr.bprbackend2.service.impl.HistoryServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class HistoryServiceImplTest {

    @Mock
    private HistoryMapper mockHistoryMapper;
    @Mock
    private UserMapper mockUserMapper;
    @Mock
    private ResMapper mockResMapper;

    @InjectMocks
    private HistoryServiceImpl historyServiceImplUnderTest;

    @Test
    void testAddHistory() {
        // Setup
        final History history = History.builder()
                .resId(0)
                .watcherId(0)
                .watchTime(0L)
                .build();

        // Configure HistoryMapper.getHistoryList(...).
        final ArrayList<History> histories = new ArrayList<>(List.of(History.builder()
                .resId(0)
                .watcherId(0)
                .watchTime(0L)
                .build()));
        when(mockHistoryMapper.getHistoryList(0)).thenReturn(histories);

        // Run the test
        final String result = historyServiceImplUnderTest.addHistory(history);

        // Verify the results
        assertThat(result).isEqualTo("History added");
        //verify(mockHistoryMapper).addHistory(History.builder()
        //        .resId(0)
        //        .watcherId(0)
        //        .watchTime(0L)
        //        .build());
        //verify(mockHistoryMapper).updateHistory(History.builder()
        //        .resId(0)
        //        .watcherId(0)
        //        .watchTime(0L)
        //        .build());
    }

    @Test
    void testAddHistory_HistoryMapperGetHistoryListReturnsNoItems() {
        // Setup
        final History history = History.builder()
                .resId(0)
                .watcherId(0)
                .watchTime(0L)
                .build();
        when(mockHistoryMapper.getHistoryList(0)).thenReturn(new ArrayList<>());

        // Run the test
        final String result = historyServiceImplUnderTest.addHistory(history);

        // Verify the results
        assertThat(result).isEqualTo("History added");
        //verify(mockHistoryMapper).addHistory(History.builder()
        //        .resId(0)
        //        .watcherId(0)
        //        .watchTime(0L)
        //        .build());
        //verify(mockHistoryMapper).updateHistory(History.builder()
        //        .resId(0)
        //        .watcherId(0)
        //        .watchTime(0L)
        //        .build());
    }

    @Test
    void testGetHistoryList() {
        // Setup
        final ArrayList<History> expectedResult = new ArrayList<>(List.of(History.builder()
                .resId(0)
                .watcherId(0)
                .watchTime(0L)
                .build()));

        // Configure HistoryMapper.getHistoryList(...).
        final ArrayList<History> histories = new ArrayList<>(List.of(History.builder()
                .resId(0)
                .watcherId(0)
                .watchTime(0L)
                .build()));
        when(mockHistoryMapper.getHistoryList(0)).thenReturn(histories);

        // Run the test
        final ArrayList<History> result = historyServiceImplUnderTest.getHistoryList(0);

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testGetHistoryList_HistoryMapperReturnsNoItems() {
        // Setup
        when(mockHistoryMapper.getHistoryList(0)).thenReturn(new ArrayList<>());

        // Run the test
        final ArrayList<History> result = historyServiceImplUnderTest.getHistoryList(0);

        // Verify the results
        assertThat(result).isEqualTo(new ArrayList<>());
    }

    @Test
    void testRemoveHistory() {
        // Setup
        // Run the test
        final String result = historyServiceImplUnderTest.removeHistory(0);

        // Verify the results
        assertThat(result).isEqualTo("History removed");
        verify(mockHistoryMapper).removeHistory(0);
    }
}
