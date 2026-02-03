package com.green.board.application;

import com.green.board.application.model.BoardPostReq;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
