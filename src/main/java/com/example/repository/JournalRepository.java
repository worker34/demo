package com.example.repository;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.example.entity.Journal;
import com.example.entity.SearchObject;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Repository;

@Repository
public class JournalRepository {

	List<Journal> journals;
	private boolean byTitle = false;
	private boolean byAuthor = false;
	private boolean byPublisher = false;
	private boolean byPrice = false;
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
	
	// return all journals from the json file
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
	
	// if id of the journal != null update journal
	// if id of the journal is null add journal
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
	
	// find journal by id
	public Journal findJournalById(int id){
		for(Journal journal:findAll()){
			if(journal.getId() == id){
				return journal;
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

	public List<Journal> findByCriteria(SearchObject searchObject) {

		List<Journal> journalsByCriteria = new ArrayList();
		byTitle = false;
		byAuthor = false;
		byPublisher = false;
		byPrice = false;

		// filter for the title of the journals
		if(searchObject.getTitle().length() > 0){
			byTitle = true;
			for(Journal journal: journals){
				if(journal.getTitle().toLowerCase().contains(searchObject.getTitle().toLowerCase())){
					journalsByCriteria.add(journal);
				}
			}
		}

		// filter for the author of the journals
		if(searchObject.getAuthor().length() > 0){
			byAuthor = true;
			if(!byTitle){
				for(Journal journal: journals){
					if(journal.getAuthor().toLowerCase().contains(searchObject.getAuthor().toLowerCase())){
						journalsByCriteria.add(journal);
					}
				}
			}else{
				for(Journal journal: journalsByCriteria){
					if(!journal.getAuthor().toLowerCase().contains(searchObject.getAuthor().toLowerCase())){
						journalsByCriteria.remove(journal);
					}
				}
			}
		}

		// filter for the publisher of the journals
		if(searchObject.getPublisher().length() > 0){
			byPublisher = true;
			if(!byTitle && !byAuthor) {
				for(Journal journal: journals){
					if(journal.getPublisher().toLowerCase().contains(searchObject.getPublisher().toLowerCase())){
						journalsByCriteria.add(journal);
					}
				}
			}else{
				for(Journal journal: journalsByCriteria){
					if(!journal.getPublisher().toLowerCase().contains(searchObject.getPublisher().toLowerCase())){
						System.out.println(journal.getPublisher().toLowerCase());
						System.out.println(searchObject.getPublisher().toLowerCase());
						journalsByCriteria.remove(journal);
					}
				}
			}
		}

		// filter for the price of the journals
		if(!byTitle && !byAuthor && !byPublisher) {
			if (searchObject.getPriceFrom() != 0 && searchObject.getPriceTo() != 0) {
				for (Journal journal : journals) {
					if ((searchObject.getPriceFrom() <= journal.getPrice()) && (searchObject.getPriceTo() >= journal.getPrice())) {
						journalsByCriteria.add(journal);
					}
				}
			} else if (searchObject.getPriceFrom() != 0) {
				for (Journal journal : journals) {
					if (searchObject.getPriceFrom() <= journal.getPrice()) {
						journalsByCriteria.add(journal);
					}
				}
			} else if (searchObject.getPriceTo() != 0) {
				for (Journal journal : journals) {
					if (searchObject.getPriceTo() >= journal.getPrice()) {
						journalsByCriteria.add(journal);
					}
				}
			}
		}else{
			if (searchObject.getPriceFrom() != 0 && searchObject.getPriceTo() != 0) {
				for (Journal journal : journalsByCriteria) {
					if (!(searchObject.getPriceFrom() <= journal.getPrice()) && (searchObject.getPriceTo() >= journal.getPrice())) {
						journalsByCriteria.remove(journal);
					}
				}
			} else if (searchObject.getPriceFrom() != 0) {
				for (Journal journal : journalsByCriteria) {
					if (!(searchObject.getPriceFrom() <= journal.getPrice())) {
						journalsByCriteria.remove(journal);
					}
				}
			} else if (searchObject.getPriceTo() != 0) {
				for (Journal journal : journalsByCriteria) {
					if (!(searchObject.getPriceTo() >= journal.getPrice())) {
						journalsByCriteria.remove(journal);
					}
				}
			}
		}

		for (int i = 0; i < journalsByCriteria.size() -1; i++) {
			Journal a1 = journalsByCriteria.get(i);
			Journal a2 = journalsByCriteria.get(i+1);
			if (a1.getIssn().equals(a2.getIssn())) {
				journalsByCriteria.remove(a1);
			}
		}
		return journalsByCriteria;
	}
}
