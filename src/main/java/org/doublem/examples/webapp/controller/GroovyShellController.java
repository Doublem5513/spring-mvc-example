package org.doublem.examples.webapp.controller;

import groovy.lang.Binding;
import groovy.lang.GroovyShell;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
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
public class GroovyShellController implements ApplicationContextAware {

    private ApplicationContext applicationContext;

    @RequestMapping(method = RequestMethod.GET)
    public String getShellForm(){

        return "shell";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String executeScript(ModelMap model, @RequestParam("code") String groovyCode){

        Binding binding = new Binding();
        binding.setVariable("appContext", applicationContext);
        GroovyShell shell = new GroovyShell(binding);
        Object result = shell.evaluate(groovyCode);

        model.addAttribute("code", groovyCode);
        model.addAttribute("result", result);

        return "shell";
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
