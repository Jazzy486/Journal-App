package com.jazzy.journalApp.repository;

import com.jazzy.journalApp.entity.JournalEntry;
import org.springframework.data.jpa.repository.JpaRepository;


public interface JournalEntryRepository extends JpaRepository<JournalEntry, Long> {

}
