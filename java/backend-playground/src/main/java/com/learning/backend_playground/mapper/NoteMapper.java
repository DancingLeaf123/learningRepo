package com.learning.backend_playground.mapper;

import com.learning.backend_playground.entity.Note;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface NoteMapper {

    List<Note> selectAll();
}
