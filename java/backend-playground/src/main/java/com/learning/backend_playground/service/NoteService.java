package com.learning.backend_playground.service;

import org.springframework.stereotype.Service;

import com.learning.backend_playground.entity.Note;
import com.learning.backend_playground.mapper.NoteMapper;
import com.learning.backend_playground.dto.NoteCreateRequest;
import com.learning.backend_playground.dto.NoteVO;

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

    public NoteVO create(final NoteCreateRequest request) {
        final Note note = new Note();
        note.setTitle(request.getTitle());
        note.setContent(request.getContent());

        noteMapper.insert(note);

        final Note persisted = noteMapper.selectById(note.getId());
        return NoteVO.from(persisted);
    }
}
