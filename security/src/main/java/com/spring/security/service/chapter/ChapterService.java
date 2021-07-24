package com.spring.security.service.chapter;

import com.spring.security.handle.exception.TdmException;
import com.spring.security.models.Chapter;

import java.util.Optional;


public interface ChapterService {
     Chapter findById(Long id) throws TdmException;
}
