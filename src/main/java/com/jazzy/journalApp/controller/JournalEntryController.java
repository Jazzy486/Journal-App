package com.jazzy.journalApp.controller;

import com.jazzy.journalApp.entity.JournalEntry;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/journal")
public class JournalEntryController {

private Map<Long, JournalEntry> journalEntries = new HashMap<>();

@GetMapping
    public List<JournalEntry> getJournalEntries() {
    return new ArrayList<>(journalEntries.values());
}

@GetMapping("/id/{myId}")
    public JournalEntry getJournalEntryById(@PathVariable long myId) {
    return journalEntries.get(myId);
}

@DeleteMapping("id/{myId}")
public void deleteJournalEntryById(@PathVariable long myId) {
    journalEntries.remove(myId);
}

@PostMapping
    public boolean addJournalEntry(@RequestBody JournalEntry journalEntry) {
    journalEntries.put(journalEntry.getId(), journalEntry);
    return true;
}

@PutMapping("/id/{myId}")
    public boolean updateJournalEntry(@RequestBody JournalEntry journalEntry, @PathVariable long myId) {
    journalEntries.put(myId, journalEntry);
    return true;
}

}
