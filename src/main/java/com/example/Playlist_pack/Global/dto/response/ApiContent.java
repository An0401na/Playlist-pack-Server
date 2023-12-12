package com.example.Playlist_pack.Global.dto.response;


public record ApiContent<T>(int status, T results) {}
