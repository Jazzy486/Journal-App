package com.jazzy.journalApp.service;

import com.jazzy.journalApp.entity.JournalEntry;
import com.jazzy.journalApp.entity.User;
import com.jazzy.journalApp.repository.JournalEntryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Component
public class JournalEntryService {

    @Autowired
    private JournalEntryRepository journalEntryRepository;
    @Autowired
    private UserService userService;

    @Transactional
    public void saveJournalEntry(JournalEntry journalEntry, String username) {
        User user = userService.getUserByUsername(username);
        journalEntry.setUser(user);
        journalEntry.setDate(LocalDateTime.now());
        journalEntryRepository.save(journalEntry);
    }

    public void saveJournalEntry(JournalEntry journalEntry) {
        journalEntry.setDate(LocalDateTime.now());
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
