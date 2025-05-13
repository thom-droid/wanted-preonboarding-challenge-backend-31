package org.example.cqrs.tag.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.example.cqrs.tag.entity.Tag;
import org.example.cqrs.tag.repository.TagRepository;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@RequiredArgsConstructor
@Transactional
@Service
public class TagServiceImpl implements TagService {

    private final TagRepository tagRepository;

    @Override
    public Tag getTagById(Long id) {
        return tagRepository.findById(id).orElseThrow(NoSuchElementException::new);
    }
}
