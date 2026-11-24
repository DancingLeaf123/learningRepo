package com.learning.backend_playground.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class NoteTagUpdateRequest {

    private static final int TAG_MAX_LENGTH = 128;

    private static final String TAG_BLANK_MESSAGE = "tag must not be blank";

    private static final String TAG_TOO_LONG_MESSAGE = "tag must not exceed 128 characters";


    @NotBlank(message = TAG_BLANK_MESSAGE)
    @Size(max = TAG_MAX_LENGTH, message = TAG_TOO_LONG_MESSAGE)
    private String tag;

    public String getTag() {
        return tag;
    }
    public void setTag(final String tag) {
        this.tag = tag;
    }
    
}
