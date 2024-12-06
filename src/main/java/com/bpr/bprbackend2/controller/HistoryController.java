package com.bpr.bprbackend2.controller;

import com.bpr.bprbackend2.model.History;
import com.bpr.bprbackend2.service.interfaces.HistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/history")
public class HistoryController {
    @Autowired
    private HistoryService historyService;

    @PostMapping("/add")
    public String addHistory(@RequestBody History history) {
        return historyService.addHistory(history);
    }

    @GetMapping("/get")
    public ArrayList<History> getHistory(@RequestParam int watcherId) {
        return historyService.getHistoryList(watcherId);
    }

    @DeleteMapping("/delete")
    public String deleteHistory(@RequestParam int id) {
        return historyService.removeHistory(id);
    }
}
