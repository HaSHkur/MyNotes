package com.example.MyNotes.repository;

import com.example.MyNotes.entity.Note;
import com.example.MyNotes.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface NoteRepository extends JpaRepository<Note, Long> {
    List<Note> findByUser(User user);
}