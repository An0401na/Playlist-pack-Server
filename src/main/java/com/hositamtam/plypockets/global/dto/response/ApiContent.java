package com.hositamtam.plypockets.global.dto.response;


public record ApiContent<T>(int status, T results) {}
