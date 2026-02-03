package com.green.board.application;

import com.green.board.application.model.BoardPostReq;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface BoardMapper {
    int save(BoardPostReq p);
}
