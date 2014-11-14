package service.impl;

import org.htmlcleaner.*;
import org.springframework.stereotype.Service;
import service.TextTransformationService;

import java.util.List;

/**
 * Created by adelfiri on 14.11.14.
 */
@Service
public class TextTransformationServiceImpl implements TextTransformationService {

    private CleanerTransformations createTransformations(){

        CleanerTransformations cleanerTransformations = new CleanerTransformations();

        TagTransformation tagTransformationh1 = new TagTransformation("h1", "p", false);

        TagTransformation tagTransformationh2 = new TagTransformation("h2", "p", false);

        TagTransformation tagTransformationh3 = new TagTransformation("h3", "p", false);

        TagTransformation tagTransformationh4 = new TagTransformation("h4", "p", false);

        TagTransformation tagTransformationh5 = new TagTransformation("h5", "p", false);

        TagTransformation tagTransformationI = new TagTransformation("i", "p", false);

        TagTransformation tagTransformationB = new TagTransformation("b", "p", false);

        TagTransformation tagTransformationU = new TagTransformation("u", "p", false);

        TagTransformation tagTransformationSPAN = new TagTransformation("span", "p", false);

        cleanerTransformations.addTransformation(tagTransformationh1);

        cleanerTransformations.addTransformation(tagTransformationh2);

        cleanerTransformations.addTransformation(tagTransformationh3);

        cleanerTransformations.addTransformation(tagTransformationh4);

        cleanerTransformations.addTransformation(tagTransformationh5);

        cleanerTransformations.addTransformation(tagTransformationI);

        cleanerTransformations.addTransformation(tagTransformationB);

        cleanerTransformations.addTransformation(tagTransformationU);

        cleanerTransformations.addTransformation(tagTransformationSPAN);

        return cleanerTransformations;
    }

    private String byHtmlCleaner(String htmlText) {

        HtmlCleaner cleaner = new HtmlCleaner();

        cleaner.setTransformations(createTransformations());

        TagNode node = cleaner.clean(htmlText);

        TagNode[] nodes = node.getAllElements(true);

        String text = "";

        for (TagNode tagNode : nodes) {

            if (tagNode.getName().equals("body")) {

                List list = tagNode.getChildren();

                for (Object object : list) {
                    if (object instanceof ContentNode) {
                        ContentNode contentNode = (ContentNode) object;
                        text += "\n" + contentNode.getContent().toString() + "\n";
                    }
                }


                continue;
            }


            if (tagNode.getName().equals("p")) {
                text += "\n" + tagNode.getText().toString() + "\n";
                continue;
            }

            if (tagNode.getName().equals("ul")) {
                TagNode[] innerNodes = tagNode.getElementsByName("li", true);
                text += "\n";
                for (TagNode innerNode : innerNodes) {
                    text += " * " + innerNode.getText().toString() + "\n";
                }
                continue;
            }

            if (tagNode.getName().equals("ol")) {
                TagNode[] innerNodes = tagNode.getElementsByName("li", true);
                text += "\n";
                int i = 1;
                for (TagNode innerNode : innerNodes) {
                    text += " " + i + ". " + innerNode.getText().toString() + "\n";
                    i++;
                }
                continue;
            }


        }

        return text;
    }

    @Override
    public String htmlToText(String html) {
        return byHtmlCleaner(html);
    }

}
