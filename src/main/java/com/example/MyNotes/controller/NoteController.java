package com.example.MyNotes.controller;

import com.example.MyNotes.entity.Note;
import com.example.MyNotes.entity.User;
import com.example.MyNotes.repository.NoteRepository;
import com.example.MyNotes.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/notes")
@RequiredArgsConstructor
public class NoteController {

    private final NoteRepository noteRepository;
    private final UserRepository userRepository;

    @PostMapping
    public Note addNote(@RequestBody Note note, Authentication auth) {
        User user = userRepository.findByUsername(auth.getName()).orElseThrow();
        note.setUser(user);
        note.setAddingDate(LocalDateTime.now());
        return noteRepository.save(note);
    }

    @PutMapping("/{id}")
    public Note editNote(@PathVariable Long id, @RequestBody Note noteData, Authentication auth) {
        Note note = noteRepository.findById(id).orElseThrow();
        note.setTitle(noteData.getTitle());
        note.setDescription(noteData.getDescription());
        note.setEditingDate(LocalDateTime.now());
        note.setDeadline(noteData.getDeadline());
        return noteRepository.save(note);
    }

    @DeleteMapping("/{id}")
    public void deleteNote(@PathVariable Long id) {
        noteRepository.deleteById(id);
    }

    @GetMapping
    public List<Note> getNotes(Authentication auth) {
        User user = userRepository.findByUsername(auth.getName()).orElseThrow();
        return noteRepository.findByUser(user);
    }
}
