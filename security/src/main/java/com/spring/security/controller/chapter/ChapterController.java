package com.spring.security.controller.chapter;


import com.spring.security.models.Chapter;
import com.spring.security.service.ChapterService;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;
import java.util.UUID;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class ChapterController {

    @Autowired
    ChapterService chapterService;

    @GetMapping("/chapter/{id}")
    public ResponseEntity<?> getChappter(@PathVariable(name = "id") UUID id) throws NotFoundException {
        Optional<Chapter> chapter =  chapterService.findById(id);
        chapter.orElseThrow(()->new NotFoundException("Not Found"));
        return new ResponseEntity<Chapter>(chapter.get(), HttpStatus.OK);
    }
}
