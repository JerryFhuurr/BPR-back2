package com.bpr.bprbackend2.service.impl;

import com.bpr.bprbackend2.mapper.HistoryMapper;
import com.bpr.bprbackend2.mapper.UserMapper;
import com.bpr.bprbackend2.mapper.VideoMapper;
import com.bpr.bprbackend2.model.History;
import com.bpr.bprbackend2.service.HistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class HistoryServiceImpl implements HistoryService {

    @Autowired
    private HistoryMapper historyMapper;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private VideoMapper videoMapper;

    @Override
    public String addHistory(History history) {
        ArrayList<History> historyList = historyMapper.getHistoryList(history.getWatcherId());

        System.out.println("size:" + historyList.size());
        int count = 0;
        History newHistory = new History();
        for (History h : historyList) {
            if (h.getVideoId() == history.getVideoId() && h.getWatcherId() == history.getWatcherId()) {
                count++;
                newHistory = h;
            }
        }

        if (count == 0) {
            history.setWatchTime(System.currentTimeMillis());
            historyMapper.addHistory(history);
        } else {
            newHistory.setWatchTime(System.currentTimeMillis());
            historyMapper.updateHistory(newHistory);
        }
        return "History added";
    }

    @Override
    public ArrayList<History> getHistoryList(int watcherId) {
        ArrayList<History> histories = historyMapper.getHistoryList(watcherId);
        for (History h : histories) {
            h.setUpName(userMapper.getUserInfoByUserId(h.getUserId()).getUsername());
            h.setVideoTitle(videoMapper.getVideo(h.getVideoId()).getVideoTitle());
        }
        return histories;
    }

    @Override
    public String removeHistory(int hId) {
        historyMapper.removeHistory(hId);
        return "History removed";
    }
}
