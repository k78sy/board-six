package com.green.board.application;

import com.green.board.application.model.BoardPostReq;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BoardService {
    private final BoardMapper boardMapper;

    public int save(BoardPostReq p){
        return boardMapper.save(p);
    }
}
