package com.learning.backend_playground.dto;

import com.learning.backend_playground.entity.Note;

import java.time.LocalDateTime;

/**
 * Note payload returned to HTTP clients.
 */
public class NoteVO {

    private Long id;

    private String title;

    private String content;

    private LocalDateTime createdAt;

    private String tag;

    /**
     * Builds a view object from a persistence row.
     *
     * @param note the row from the database; must not be null
     * @return a view object with the same field values
     */
    public static NoteVO from(final Note note) {
        final NoteVO view = new NoteVO();
        view.id = note.getId();
        view.title = note.getTitle();
        view.content = note.getContent();
        view.createdAt = note.getCreatedAt();
        view.tag = note.getTag();
        return view;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public String getTag() {
        return tag;
    }
}
