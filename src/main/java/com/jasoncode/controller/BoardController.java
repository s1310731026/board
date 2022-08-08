package com.jasoncode.controller;

import com.jasoncode.entity.Board;
import com.jasoncode.repository.BoardResposity;
import com.sun.istack.NotNull;
import com.sun.istack.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
//@RequestMapping("/board")
@Transactional // rollback
public class BoardController {

    @Autowired
    private BoardResposity boardResposity;

    @GetMapping("/boards")
    public List<Board> getAll(){
        return boardResposity.findAll();
    }

    @PostMapping("/boards")
    public Board create(@RequestParam("context")String context,
                        @RequestParam("title")String title,
                        @RequestParam("publisher")String publisher){
        Board board = new Board();
        board.setTitle(title);
        board.setPublisher(publisher);
        board.setContext(context);

        return boardResposity.save(board);
    }

    @GetMapping("/boards/{id}")
    public Board findById(@PathVariable("id")Integer id){
        return boardResposity.findById(id).orElse(null);
    }

    @PutMapping("/boards/{id}")
    public Board update(@PathVariable("id")Integer id,
                         @RequestParam(value = "context",required = false)String context,
                         @RequestParam(value = "publisher",required = false)String publisher)
    {
        Optional<Board> optional=  boardResposity.findById(id);
        Board board;
        if (optional.isPresent()){
            board = optional.get();
        }else {
            return null;
        }

        board.setContext(context!=null?context:board.getContext());
        board.setPublisher(publisher!=null?publisher:board.getPublisher());
        return boardResposity.save(board);

    }

    @GetMapping
    public String getById(){
        System.out.println("springboot is running");
        return "springboot is running...";
    }






}
