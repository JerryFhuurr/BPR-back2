package com.bpr.bprbackend2.mapper;

import com.bpr.bprbackend2.model.History;
import org.apache.ibatis.annotations.Mapper;

import java.util.ArrayList;

@Mapper
public interface HistoryMapper {
    void addHistory(History history);
    ArrayList<History> getHistoryList(int watcherId);
    void removeHistory(int hId);
    void removeHistoryByUser(int watcherId);
    void updateHistory(History history);
}
