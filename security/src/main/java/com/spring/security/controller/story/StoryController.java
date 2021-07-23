package com.spring.security.controller.story;


import com.spring.security.models.Story;
import com.spring.security.service.story.StoryService;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class StoryController {
    @Autowired
    StoryService storyService;

    @RequestMapping(value = "/story/{id}",method = RequestMethod.GET,produces = "application/json")
    public ResponseEntity<Story> getStory(@PathVariable(name = "id")Long id) throws NotFoundException {
        Optional<Story> story = storyService.findById(id);
        story.orElseThrow(()-> new NotFoundException("Not Found"));
        return new ResponseEntity<>(story.get(), HttpStatus.OK);
    }
}
