package org.example.cqrs.tag.service;

import org.example.cqrs.tag.entity.Tag;

public interface TagService {
    Tag getTagById(Long id);
}
