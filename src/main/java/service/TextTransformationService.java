package service;

/**
 * Created by adelfiri on 14.11.14.
 */
public interface TextTransformationService {

    /**
     *  remove all html tags and insert '*' & 'Number' li & ul instead
     *
     * @param html
     * @return
     */
      String htmlToText(String html);

}
