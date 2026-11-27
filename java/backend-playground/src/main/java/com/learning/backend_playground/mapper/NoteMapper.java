package com.learning.backend_playground.mapper;

import com.learning.backend_playground.entity.Note;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

@Mapper
public interface NoteMapper {

    List<Note> selectAll();

    int countAll();

    List<Note> selectPage(@Param("offset") int offset, @Param("limit") int limit);

    Note selectById(@Param("id") Long id);

      /**
   * Inserts a note row. On success, {@link Note#getId()} is set from generated keys.
   *
   * @param note row to insert; title required, id and createdAt ignored by SQL
   * @return number of rows affected
   */
    int insert(Note note);

    List<Note> selectByKeyword(@Param("keyword") String keyword);

    int countByKeyword(@Param("keyword") String keyword);

    List<Note> selectPageByKeyword(
            @Param("keyword") String keyword,
            @Param("offset") int offset,
            @Param("limit") int limit);

    int updateTagById(@Param("id") Long id, @Param("tag") String tag);
}
