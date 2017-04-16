package com.example.repository;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.stereotype.Repository;

import com.example.entity.Book;

@Repository
public class BookRepository {

	List<Book> books;
	public static final String PATHNAME = "data.json";

	public BookRepository(){
		ObjectMapper mapper = new ObjectMapper();
        try {
            books = mapper.readValue(new File(PATHNAME), new TypeReference<List<Book>>(){});
        } catch (IOException e) {
            e.printStackTrace();
            books = new ArrayList<>();
        }
    }
	
	// return all books from the json file
	public List<Book> findAll(){
		return books;
	}

	// delete by id
	public void delete(int id){
		for (int i = 0; i < books.size(); i++) {
			if(id == books.get(i).getId()){
				books.remove(i);
				break;
			}
		}
        updateJsonFile();
	}
	
	// if id of the book != null update book
	// if id of the book is null add book	
	public void save(Book book){
		if(book.getId() != 0) {
			for (int i = 0; i < books.size(); i++) {
				if(book.getId() == books.get(i).getId()){
					books.set(i, book);
					break;
				}
			}
		}else{
		    book.setId(books.size() + 1);
			books.add(book);
		}
        updateJsonFile();
	}
	
	// find book by id
	public Book findBookById(long id){
		for(Book book:findAll()){
			if(book.getId() == id){
				return book;
			}
		}
		return new Book();
	}

    private void updateJsonFile() {
        ObjectMapper mapper = new ObjectMapper();
        try {
            mapper.writeValue(new File(PATHNAME), books);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
