package service;

import view.*;

/**
 * @author Konstantin Molodtsov
 * @author $Author$ (current maintainer)
 * @version $Revision$, $Date$
 * @since 0.1
 */
public interface NoteService {

    PageNoteView findAllNoteList(int pageNumber, int pageSize, String direction, String column);

    PlainNoteView saveOrUpdateNote(PlainNoteView view);

    void deleteNote(Long noteId);

    PlainNoteView findOne(Long noteId);
}
