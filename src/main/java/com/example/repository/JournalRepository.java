package com.example.repository;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.example.entity.Journal;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Repository;

@Repository
public class JournalRepository {

	List<Journal> journals;
	public static final String PATHNAME = "data.json";

	public JournalRepository(){
		ObjectMapper mapper = new ObjectMapper();
        try {
            journals = mapper.readValue(new File(PATHNAME), new TypeReference<List<Journal>>(){});
        } catch (IOException e) {
            e.printStackTrace();
            journals = new ArrayList<>();
        }
    }
	
	// return all books from the json file
	public List<Journal> findAll(){
		return journals;
	}

	// delete by id
	public void delete(int id){
		for (int i = 0; i < journals.size(); i++) {
			if(id == journals.get(i).getId()){
				journals.remove(i);
				break;
			}
		}
        updateJsonFile();
	}
	
	// if id of the book != null update book
	// if id of the book is null add book	
	public void save(Journal journal){
		if(journal.getId() != 0) {
			for (int i = 0; i < journals.size(); i++) {
				if(journal.getId() == journals.get(i).getId()){
					journals.set(i, journal);
					break;
				}
			}
		}else{
			journal.setId(journals.size() + 1);
			journals.add(journal);
		}
        updateJsonFile();
	}
	
	// find book by id
	public Journal findJournalById(long id){
		for(Journal book:findAll()){
			if(book.getId() == id){
				return book;
			}
		}
		return new Journal();
	}

    private void updateJsonFile() {
        ObjectMapper mapper = new ObjectMapper();
        try {
            mapper.writeValue(new File(PATHNAME), journals);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
