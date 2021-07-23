package com.spring.security.controller.chapter;


import com.spring.security.DTO.Chapter.ChapterDTO;
import com.spring.security.models.Chapter;
import com.spring.security.service.ChapterService;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class ChapterController {

    @Autowired
    ChapterService chapterService;

    @RequestMapping(value = "/chapter/{id}",method = RequestMethod.GET,produces = "application/json")
    public ResponseEntity<ChapterDTO> getChapter(@PathVariable(name = "id") Long id) throws NotFoundException {
        Optional<Chapter> chapter =  chapterService.findById(id);
        chapter.orElseThrow(()->new NotFoundException("Not Found"));
        ChapterDTO chapterDTO = new ChapterDTO(chapter.get());
        return new ResponseEntity<>(chapterDTO, HttpStatus.OK);
    }
}
