package com.learning.backend_playground.controller;

import com.learning.backend_playground.common.Result;
import com.learning.backend_playground.dto.NoteVO;
import com.learning.backend_playground.entity.Note;
import com.learning.backend_playground.service.NoteService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@RestController
@RequestMapping("/notes")
public class NoteController {

    private final NoteService noteService;

    private static final int NOTE_NOT_FOUND_CODE = 404;

    private static final String NOTE_NOT_FOUND_MESSAGE = "note not found for id";

    public NoteController(final NoteService noteService) {
        this.noteService = noteService;
    }

    @GetMapping
    public Result<List<NoteVO>> listAll() {
        final List<NoteVO> views = noteService.listAll().stream()
                .map(NoteVO::from)
                .toList();
        return Result.success(views);
    }

    @GetMapping("/{id}")
    public Result<NoteVO> getById(@PathVariable("id") final Long id){
        return noteService.findById(id)
            .map(note -> Result.success(NoteVO.from(note)))
            .orElseGet(() -> Result.fail(
                NOTE_NOT_FOUND_CODE,
                NOTE_NOT_FOUND_MESSAGE + ": " + id
            ));
    }
}
