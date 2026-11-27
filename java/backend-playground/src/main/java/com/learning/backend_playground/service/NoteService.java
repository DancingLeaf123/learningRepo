package com.learning.backend_playground.service;

import org.springframework.stereotype.Service;

import com.learning.backend_playground.entity.Note;
import com.learning.backend_playground.mapper.NoteMapper;
import com.learning.backend_playground.dto.NoteCreateRequest;
import com.learning.backend_playground.dto.NoteVO;
import com.learning.backend_playground.common.PageResult;

import java.util.List;
import java.util.Optional;

@Service
public class NoteService {
    private static final int DEFAULT_PAGE_INDEX = 1;
    private static final int DEFAULT_PAGE_SIZE = 10;

    private static final int MAX_PAGE_SIZE = 100;

    
    private final NoteMapper noteMapper;

    public NoteService(final NoteMapper noteMapper) {
        this.noteMapper = noteMapper;
    }

        /**
     * Paginated note list, optionally filtered by title keyword.
     *
     * @param keyword    title contains; null/blank = no filter
     * @param pageIndex  one-based page index
     * @param pageSize   page size
     * @return page metadata and {@link NoteVO} items
     */
    public PageResult<NoteVO> pageNotes(
        final String keyword,
        final int pageIndex,
        final int pageSize
    ){
        final int safePageIndex = pageIndex < 1 ? DEFAULT_PAGE_INDEX : pageIndex;
        final int safePageSize = normalizePageSize(pageSize);
        final int offset = (safePageIndex - 1) * safePageSize;
        final boolean hasKeyword = keyword != null && !keyword.isBlank();

        final int totalCount = hasKeyword ? noteMapper.countByKeyword(keyword) : noteMapper.countAll();

        if (totalCount == 0){
            return PageResult.empty(safePageIndex, safePageSize);
        }

        final List<Note> rows = hasKeyword ? noteMapper.selectPageByKeyword(keyword, offset, safePageSize) : noteMapper.selectPage(offset, safePageSize);

        final List<NoteVO> items = rows.stream().map(NoteVO::from).toList();

        final PageResult<NoteVO> page = new PageResult<>();
        page.setItems(items);
        page.setPageIndex(safePageIndex);
        page.setPageSize(safePageSize);
        page.setTotalCount(totalCount);
        page.setTotalPage((totalCount + safePageSize - 1) / safePageSize);
        return page;
    }

    private static int normalizePageSize(final int pageSize) {
        if (pageSize < 1) {
            return DEFAULT_PAGE_SIZE;
        }
        return Math.min(pageSize, MAX_PAGE_SIZE);
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

    public Optional<Note> updateTag(Long id, String tag){
        final Note existing = noteMapper.selectById(id);
        if(existing == null){
            return Optional.empty();
        }
        noteMapper.updateTagById(id, tag);
        return Optional.ofNullable(noteMapper.selectById(id));
    }
}
