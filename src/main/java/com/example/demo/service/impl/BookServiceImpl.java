package com.example.demo.service.impl;

import com.example.demo.constants.ResultMsgConstants;
import com.example.demo.dao.BookRepository;
import com.example.demo.dto.BookDTO;
import com.example.demo.pojo.BookEntity;
import com.example.demo.service.BookService;
import com.example.demo.utils.DTO2EntityUtil;
import com.example.demo.utils.Entity2DTOUtil;
import com.example.demo.vo.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookRepository bookRepository;

   /**
    *@description 列举所有书籍
    *@param
    *@return
    */
    @Override
    public ResultVO listAllBooks() {
        List<BookEntity> list = bookRepository.findAll();
        List<BookDTO> bookDtoList = new ArrayList<>();
        for (BookEntity bookEntity : list) {
            bookDtoList.add(Entity2DTOUtil.entity2DTO(bookEntity));
        }
        return  new ResultVO(ResultMsgConstants.LIST_ALL_BOOKS_SUCCESS, 1, bookDtoList);
    }

    /**
     *@description 按书名搜索
     *@param
     *@return
     */
    @Override
    public ResultVO searchByName(String name){
        List<BookEntity> list = bookRepository.findByNameContaining(name);
        List<BookDTO> bookDtoList = new ArrayList<>();
        for (BookEntity bookEntity : list) {
            bookDtoList.add(Entity2DTOUtil.entity2DTO(bookEntity));
        }
        return  new ResultVO(ResultMsgConstants.SEARCH_BY_NAME_SUCCESS, 1, bookDtoList);
    }

    /**
     *@description 增加新书，只有管理员才有权限
     *@param
     *@return
     */
    @Override
    @Transactional
    public ResultVO addBook(BookDTO bookDTO){
        if(bookDTO.getName() == null || bookDTO.getIsbn() == null ||
                bookDTO.getAuthor() == null || bookDTO.getPrice() == null ){
            return new ResultVO(ResultMsgConstants.BOOK_INFO_WRONG, 0);
        }
        BookEntity bookEntity = DTO2EntityUtil.dto2Entity(bookDTO);
        bookRepository.save(bookEntity);
        return new ResultVO(ResultMsgConstants.ADD_BOOK_SUCCESS, 1, bookEntity.getId());
    }

    /**
     *@description 删除图书，只有管理员才有权限
     *@param id
     *@return
     */
    @Override
    @Transactional
    public ResultVO deleteBook(Integer id) {
        if(!bookRepository.findById(id).isPresent()){
            return new ResultVO(ResultMsgConstants.NO_BOOK_FOUND, 0);
        }
        bookRepository.deleteById(id);
        return new ResultVO(ResultMsgConstants.DELETE_BOOK_SUCCESS, 1, id);
    }
    /**
     *@description 更新图书，只有管理员才有权限
     *@param bookDTO
     *@return
     */
    @Override
    @Transactional
    public ResultVO updateBook(BookDTO bookDTO){
        if(bookDTO.getName() == null || bookDTO.getIsbn() == null ||
                bookDTO.getAuthor() == null || bookDTO.getPrice() == null ||
                !bookRepository.findById(bookDTO.getId()).isPresent()){
            return new ResultVO(ResultMsgConstants.BOOK_INFO_WRONG, 0);
        }
        BookEntity bookEntity = DTO2EntityUtil.dto2Entity(bookDTO);
        bookEntity.setId(bookDTO.getId());
        bookRepository.save(bookEntity);
        return new ResultVO(ResultMsgConstants.UPDATE_BOOK_SUCCESS, 1, bookDTO.getId());
    }
}
