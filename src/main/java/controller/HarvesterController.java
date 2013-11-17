package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;
import service.WordService;
import view.PlainWordView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.security.Principal;
import java.util.List;

/**
 * Created by nectarius on 11/16/13.
 */
@Controller
public class HarvesterController  {

    @Autowired
    private WordService wordService;

    @RequestMapping(value = "harvester.page", method = RequestMethod.GET)
    public String chart() {

        return "harvester";
    }

    /**
     *
     * @return list
     */
    @RequestMapping(value = "wordlist.data", method = RequestMethod.GET)
    @ResponseBody
    public List<PlainWordView> findWordList() {

        List<PlainWordView> wordList = wordService.findAll();

        return wordList;
    }




}
