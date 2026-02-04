package com.green.board.application;

import com.green.board.application.model.BoardGetOneReq;
import com.green.board.application.model.BoardGetRes;
import com.green.board.application.model.BoardPostReq;
import com.green.board.application.model.BoardPutReq;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardService {
    private final BoardMapper boardMapper;

    public int save(BoardPostReq p){
        return boardMapper.save(p);
    }

    public List<BoardGetRes> findAll(String searchText){
        return boardMapper.findAll(searchText);
    }
    public BoardGetOneReq findOne(int id){
        return boardMapper.findOne(id);
    }

    public int update(BoardPutReq p){
        return boardMapper.update(p);
    }

    public int delete(int id){
        return boardMapper.delete(id);
    }
}
