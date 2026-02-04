package com.green.board.application;

import com.green.board.application.model.BoardGetOneReq;
import com.green.board.application.model.BoardGetRes;
import com.green.board.application.model.BoardPostReq;
import com.green.board.application.model.BoardPutReq;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BoardMapper {
    int save(BoardPostReq p);
    List<BoardGetRes> findAll(String searchText);
    BoardGetOneReq findOne(int id);
    int update(BoardPutReq p);
    int delete(int id);
}
