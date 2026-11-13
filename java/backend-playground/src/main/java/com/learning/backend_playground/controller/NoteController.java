package com.learning.backend_playground.controller;

import com.learning.backend_playground.common.Result;
import com.learning.backend_playground.dto.NoteVO;
import com.learning.backend_playground.entity.Note;
import com.learning.backend_playground.service.NoteService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/notes")
public class NoteController {

    private final NoteService noteService;

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
}
