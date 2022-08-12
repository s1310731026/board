package com.jasoncode.controller;

import com.jasoncode.entity.Board;
import com.jasoncode.repository.BoardResposity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
@Transactional // rollback
@CrossOrigin(value = "http://35.201.129.178:8080/")
public class BoardController {

    @Autowired
    private BoardResposity boardResposity;
    @GetMapping( "/boards")
    public List<Board> getAll(){
        return boardResposity.findAll();
    }

    @PostMapping( "/boards")
    public Board create(
                        @RequestParam("context")String context,
                        @RequestParam("title")String title,
                        @RequestParam("publisher")String publisher,
                        @RequestParam(value = "publishstartdate",required = false)String publishstartdate,
                        @RequestParam(value = "publishenddate",required = false)String publishenddate){
        Board board = new Board();
        board.setTitle(title);
        board.setPublisher(publisher);
        board.setContext(context);
        board.setPublishstartdate(publishstartdate);
        board.setPublishenddate(publishenddate);

        return boardResposity.save(board);
    }

    @GetMapping("/boards/{id}")
    public Board findById(@PathVariable("id")Integer id){
        return boardResposity.findById(id).orElse(null);
    }

    @DeleteMapping("/boards")
    public void deleteAll(){
        boardResposity.deleteAll();
    }

    @DeleteMapping("/boards/{id}")
    public void deleteById(@PathVariable("id")Integer id){
        boardResposity.deleteById(id);
    }

//    @PutMapping("/boards/{id}")
    @PostMapping("/boards/{id}")
    public Board update(@PathVariable("id")Integer id,
                         @RequestParam("title")String title,
                         @RequestParam(value = "context",required = false)String context,
                         @RequestParam(value = "publisher",required = false)String publisher,
                         @RequestParam(value = "publishstartdate",required = false)String publishstartdate,
                         @RequestParam(value = "publishenddate",required = false)String publishenddate)
    {
        Optional<Board> optional=  boardResposity.findById(id);
        Board board;
        if (optional.isPresent()){
            board = optional.get();
        }else {
            return null;
        }
        board.setTitle(title);
        board.setContext(context!=null?context:board.getContext());
        board.setPublisher(publisher!=null?publisher:board.getPublisher());
        board.setPublishstartdate(publishstartdate!=null?publishstartdate:board.getPublishstartdate());
        board.setPublishenddate(publishenddate!=null?publishenddate:board.getPublishenddate());
        return boardResposity.save(board);

    }

//    @GetMapping
//    public String getById(){
//        System.out.println("springboot is running");
//        return "springboot is running...";
//    }



}
