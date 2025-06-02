package com.jazzy.journalApp.service;

import com.jazzy.journalApp.entity.JournalEntry;
import com.jazzy.journalApp.repository.JournalEntryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Component
public class JournalEntryService {

    @Autowired
    private JournalEntryRepository journalEntryRepository;

    public void saveJournalEntry(JournalEntry journalEntry) {
        journalEntryRepository.save(journalEntry);
    }

    public List<JournalEntry> getAllJournalEntries() {
        return journalEntryRepository.findAll();
    }

    public Optional<JournalEntry> getJournalEntryById(long id) {
        return journalEntryRepository.findById(id);
    }

    public void deleteJournalEntryById(long id) {
        journalEntryRepository.deleteById(id);
    }
}
