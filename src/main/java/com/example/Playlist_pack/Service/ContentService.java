package com.example.Playlist_pack.Service;

import com.example.Playlist_pack.Domain.Content;
import com.example.Playlist_pack.Global.exception.custom.content.ContentNotFoundException;
import com.example.Playlist_pack.Repository.ContentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class ContentService {

    private final ContentRepository contentRepository;


    public Content searchContent(Long contentId) {
        Content content = contentRepository.findById(contentId)
                .orElseThrow(ContentNotFoundException::new);

        content.updateViewCnt();

        return content;
    }

    public Content likes(Long contentId) {
        Content content = contentRepository.findById(contentId)
                .orElseThrow(ContentNotFoundException::new);
        content.updateLikeCnt();
        return content;
    }
}
