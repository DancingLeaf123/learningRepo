package com.learning.backend_playground.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

/**
 * Request body for creating a note.
 */
public class NoteCreateRequest {

    private static final int TITLE_MAX_LENGTH = 128;

    private static final int CONTENT_MAX_LENGTH = 512;

    private static final String TITLE_BLANK_MESSAGE = "title must not be blank";

    private static final String TITLE_TOO_LONG_MESSAGE = "title must not exceed 128 characters";

    private static final String CONTENT_TOO_LONG_MESSAGE =
            "content must not exceed 512 characters";

    @NotBlank(message = TITLE_BLANK_MESSAGE)
    @Size(max = TITLE_MAX_LENGTH, message = TITLE_TOO_LONG_MESSAGE)
    private String title;

    @Size(max = CONTENT_MAX_LENGTH, message = CONTENT_TOO_LONG_MESSAGE)
    private String content;

    public String getTitle() {
        return title;
    }

    public void setTitle(final String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(final String content) {
        this.content = content;
    }
}