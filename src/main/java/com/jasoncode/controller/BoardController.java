package com.jasoncode.controller;

import com.jasoncode.entity.Board;
import com.jasoncode.repository.BoardResposity;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
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
    public Board create(@RequestParam(value = "file",required = false) MultipartFile file,
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
        try {
            board.setFile(file!=null?file.getBytes():null);
        } catch (IOException e) {
            e.printStackTrace();
        }
//        String fileName = file.getOriginalFilename();
//        try {
//            file.transferTo( new File(  fileName));
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
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
                         @RequestParam(value = "file",required = false) MultipartFile file,
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
        try {
            board.setFile(file!=null?file.getBytes():board.getFile());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return boardResposity.save(board);

    }

//    @GetMapping
//    public String getById(){
//        System.out.println("springboot is running");
//        return "springboot is running...";
//    }

    @PostMapping("/upload")
    public ResponseEntity<?> handleFileUpload(@RequestParam("file") MultipartFile file ) {

        String fileName = file.getOriginalFilename();
        try {
            file.transferTo( new File("\\upload\\" + fileName));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
        return ResponseEntity.ok("File uploaded successfully.");
    }

    @GetMapping("/exportcsv")
    public void exportIntoCSV(HttpServletResponse response) throws IOException {

        response.setContentType("text/csv");

        response.addHeader("Content-Disposition", "attachment; filename=\"student.csv\"");

        CSVPrinter printer = new CSVPrinter(response.getWriter(), CSVFormat.DEFAULT);
        List<Board> boards = boardResposity.findAll();
        for (Board board : boards) {

            printer.printRecord(board.getId(),
                    board.getContext(),
                    board.getPublisher(),
                    board.getTitle(),
                    board.getStartdate(),
                    board.getEnddate());
        }
    }


}
