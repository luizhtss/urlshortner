package com.luizhtss.urlshortner.controllers;

import com.luizhtss.urlshortner.models.URL;
import com.luizhtss.urlshortner.repository.URLRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.net.URI;
import java.util.Optional;

@Controller
public class UrlController {

    @Autowired
    private URLRepository ur;

    @RequestMapping(value="/", method = RequestMethod.POST)
    public String form(URL url){
        ur.save(url);
        return "redirect:/";
    }

    @RequestMapping("/")
    public ModelAndView urls(){
        ModelAndView mav = new ModelAndView("index");
        Iterable<URL> enc = ur.findAll();
        mav.addObject("url", enc);
        return mav;
    }

    @RequestMapping(value = "/deletarEnc")
    public Object deletarEnc(String encurtador){
        Optional<URL> optionalURL = Optional.ofNullable(ur.findByEncurtador(encurtador));
        if (!optionalURL.isPresent()){
            ModelAndView mav = new ModelAndView("404");
            mav.setStatus(HttpStatus.NOT_FOUND);
            return mav;
        }
        ur.delete(optionalURL.get());
        return "redirect:/";
    }

    @RequestMapping("/{encurtador}")
    public Object  redirect(@PathVariable("encurtador") String encurtador){
       Optional<URL> optionalURL = Optional.ofNullable(ur.findByEncurtador(encurtador));
       if (!optionalURL.isPresent()){
           ModelAndView mav = new ModelAndView("404");
           mav.setStatus(HttpStatus.NOT_FOUND);
           return mav;
       }
       return ResponseEntity.status(HttpStatus.PERMANENT_REDIRECT).location(URI.create(optionalURL.get().getUrl())).build();

    }


}
