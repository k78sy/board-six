package com.green.board.application;

import com.green.board.application.model.BoardGetOneReq;
import com.green.board.application.model.BoardGetRes;
import com.green.board.application.model.BoardPostReq;
import com.green.board.application.model.BoardPutReq;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j // Spring log for J
@RestController
@RequiredArgsConstructor
@RequestMapping("api/board")
public class BoardController {
    private final BoardService boardService;

    @PostMapping
    public int PostBoard(@RequestBody BoardPostReq p){
        log.info("p: {}", p);
        return boardService.save(p);
    }

    @GetMapping
    public List<BoardGetRes> GetBoard(@RequestParam(name="search_text", required = false) String searchText){//자동으로 @RequestParam이 들어감
        log.info("searchText: {}", searchText);
        return boardService.findAll(searchText);
    }

    @GetMapping("{id}")
    public BoardGetOneReq GetBoardDetail(@PathVariable int id){
        log.info("id: {}", id);
        return boardService.findOne(id);
    }

    @PutMapping
    public int putUpdate(@RequestBody BoardPutReq p){
        log.info("p: {}", p);
        return boardService.update(p);
    }

    @DeleteMapping
    public int deleteBoard(int id){
        log.info("delete-id: {}", id);
        return boardService.delete(id);
    }
}
