package com.example.demo.service;

import com.example.demo.dto.BookDTO;
import com.example.demo.vo.ResultVO;


public interface BookService {
     ResultVO listAllBooks();

     ResultVO searchByName(String name);

     ResultVO addBook(BookDTO bookDTO);

     ResultVO deleteBook(Integer id);

     ResultVO updateBook(BookDTO bookDTO);


}
