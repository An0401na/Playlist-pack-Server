package com.example.Playlist_pack.Service;

import com.example.Playlist_pack.Domain.Content;
import com.example.Playlist_pack.Global.exception.HttpExceptionCode;
import com.example.Playlist_pack.Global.exception.custom.BusinessException;
import com.example.Playlist_pack.Repository.ContentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
@Slf4j
public class ContentService {

    private final ContentRepository contentRepository;


    public Content searchContent(Long contentId) {
        return contentRepository.findById(contentId)
                .orElseThrow(() -> new BusinessException(HttpExceptionCode.CONTENT_NOT_FOUND));
    }
}
