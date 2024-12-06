package com.bpr.bprbackend2.service.interfaces;

import com.bpr.bprbackend2.model.History;

import java.util.ArrayList;

public interface HistoryService {
    String addHistory(History history);
    ArrayList<History> getHistoryList(int watcherId);
    String removeHistory(int hId);
}
