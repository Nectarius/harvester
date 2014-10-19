package service;

import view.PageNoteView;
import view.PlainNoteView;

import javax.servlet.http.HttpServletResponse;

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

    void download(String author, String type, String token, HttpServletResponse response);
}
