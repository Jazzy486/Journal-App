package com.jazzy.journalApp.controller;

import com.jazzy.journalApp.entity.JournalEntry;
import com.jazzy.journalApp.service.JournalEntryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.*;

@RestController
@RequestMapping("/journal")
public class JournalEntryControllerV2 {

    @Autowired
    private JournalEntryService journalEntryService;

    @GetMapping
    public ResponseEntity<?> getJournalEntries() {
        List<JournalEntry> allJournalEntries = journalEntryService.getAllJournalEntries();
        if(allJournalEntries != null && !allJournalEntries.isEmpty()) {
            return new ResponseEntity<>(allJournalEntries, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/id/{myId}")
    public ResponseEntity<JournalEntry> getJournalEntryById(@PathVariable long myId) {
        Optional<JournalEntry> journalEntry = journalEntryService.getJournalEntryById(myId);
        if (journalEntry.isPresent()) {
            return new ResponseEntity<>(journalEntry.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("id/{myId}")
    public ResponseEntity<?> deleteJournalEntryById(@PathVariable long myId) {
        journalEntryService.deleteJournalEntryById(myId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping
    public ResponseEntity<JournalEntry> addJournalEntry(@RequestBody JournalEntry journalEntry) {
        try
        {
            journalEntry.setDate(LocalDateTime.now());
            journalEntryService.saveJournalEntry(journalEntry);
            return new ResponseEntity<>(journalEntry, HttpStatus.CREATED);
        }
        catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

    }

    @PutMapping("/id/{myId}")
    public ResponseEntity<?> updateJournalEntry(@RequestBody JournalEntry newEntry, @PathVariable long myId) {
        JournalEntry oldJE = journalEntryService.getJournalEntryById(myId).orElse(null);
        if (oldJE != null) {
            oldJE.setTitle(newEntry.getTitle() != null && !newEntry.getTitle().equals("") ? newEntry.getTitle() : oldJE.getTitle());
            oldJE.setContent(newEntry.getContent() != null && !newEntry.getContent().equals("") ? newEntry.getContent() : oldJE.getContent());
            oldJE.setDate(oldJE.getDate());
            journalEntryService.saveJournalEntry(oldJE);
            return new ResponseEntity<>(oldJE, HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}
