package com.learning.backend_playground.service;

import com.learning.backend_playground.entity.Note;
import com.learning.backend_playground.mapper.NoteMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NoteService {

    private final NoteMapper noteMapper;

    public NoteService(final NoteMapper noteMapper) {
        this.noteMapper = noteMapper;
    }

    public List<Note> listAll() {
        return noteMapper.selectAll();
    }

    public Optional<Note> findById(final Long id){
        return Optional.ofNullable(noteMapper.selectById(id));
    }
}
