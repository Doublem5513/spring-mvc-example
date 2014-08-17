package org.doublem.examples.webapp.controller;

import groovy.lang.GroovyShell;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by mint on 17/8/14.
 */

@Controller
@RequestMapping("/shell")
public class GroovyShellController {

    @RequestMapping(method = RequestMethod.GET)
    public String getShellForm(){

        return "shell";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String executeScript(ModelMap model, @RequestParam("code") String groovyCode){

        GroovyShell shell = new GroovyShell();
        Object result = shell.evaluate(groovyCode);

        model.addAttribute("code", groovyCode);
        model.addAttribute("result", result);

        return "shell";
    }
}
