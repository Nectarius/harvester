package service;

import view.PageNoteView;
import view.PlainNoteView;

/**
 * @author Konstantin Molodtsov
 * @author $Author$ (current maintainer)
 * @version $Revision$, $Date$
 * @since 0.1
 */
public interface NoteService {

    PageNoteView findAllNoteList(String author, int pageNumber, int pageSize, String direction, String column);

    PlainNoteView saveOrUpdateNote(PlainNoteView view, String authorName);

    void deleteNote(Long noteId);

    PlainNoteView findOne(Long noteId);
}
