package gom.cave.sleep.bookmark.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by sleepbear on 2015. 12. 2..
 */
@Controller
public class ApplicationController {

    @RequestMapping("/join")
    public String join() {
        return null;
    }

    @RequestMapping("/login")
    public String login() {
        return null;
    }
}
