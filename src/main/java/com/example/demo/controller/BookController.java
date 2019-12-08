package com.example.demo.controller;

import com.example.demo.dto.BookDTO;
import com.example.demo.service.BookService;
import com.example.demo.vo.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class BookController {
    @Autowired
    private BookService bookService;

    @RequestMapping(method = RequestMethod.GET, value = "/book/all")
    public ResultVO listAllBooks(){
        return bookService.listAllBooks();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/book/search")
    public ResultVO searchBookByName(@RequestParam(value = "name")String name){
        return bookService.searchByName(name);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/admin/book/add")
    public ResultVO addBook(@RequestBody BookDTO bookDTO){
        return bookService.addBook(bookDTO);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/admin/book/delete")
    public ResultVO deleteBook(@RequestParam(value = "id")Integer id){
        return bookService.deleteBook(id);
    }

    @RequestMapping(method = RequestMethod.PUT,value = "/admin/book/update")
    public ResultVO updateBook(@RequestBody BookDTO bookDTO){
        return bookService.updateBook(bookDTO);
    }
}
