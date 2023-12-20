package com.iqbaal.restful.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.iqbaal.restful.entity.Tutorial;

@DataJpaTest
public class TutorialRepositoryTest {
    
    @Autowired
    private TutorialRepository tutorialRepository;

    @Test
    public void givenTutorialObject_whenSave_thenReturnSavedTutorial(){
        Tutorial tutorial = new Tutorial();
        tutorial.setTitle("Tutorial Database");
        tutorial.setPublished(false);
        tutorial.setTitle("Tutorial for relational database");
        Tutorial savedTutorial = tutorialRepository.save(tutorial);
        assertNotNull(savedTutorial);
        assertEquals(savedTutorial, tutorial);
        assertEquals(savedTutorial.getId(), tutorial.getId());
        assertEquals(savedTutorial.getTitle(), tutorial.getTitle());
        assertEquals(savedTutorial.isPublished(), tutorial.isPublished());
        assertEquals(savedTutorial.getDescription(), tutorial.getDescription());
    }

    @Test
    public void givenSomeTutorialObject_whenFindAll_thenReturnTutorialList(){
        Tutorial tutorial = new Tutorial();
        tutorial.setTitle("Tutorial Database");
        tutorial.setPublished(false);
        tutorial.setTitle("Tutorial for relational database");
        Tutorial newTutorial = new Tutorial();
        newTutorial.setTitle("Tutorial Network");
        newTutorial.setPublished(false);
        newTutorial.setTitle("Tutorial for network");
        tutorialRepository.save(tutorial);
        tutorialRepository.save(newTutorial);
        
        List<Tutorial> tutorialList = tutorialRepository.findAll();

        assertNotNull(tutorialList);
        assertEquals(tutorialList.size(), 2);
    }

    @Test
    public void givenSomeTutorialObject_whenFindIsPublished_thenReturnTutorialList(){
        Tutorial tutorial = new Tutorial();
        tutorial.setTitle("Tutorial Database");
        tutorial.setPublished(true);
        tutorial.setTitle("Tutorial for relational database");
        Tutorial newTutorial = new Tutorial();
        newTutorial.setTitle("Tutorial Network");
        newTutorial.setPublished(false);
        newTutorial.setTitle("Tutorial for network");
        tutorialRepository.save(tutorial);
        tutorialRepository.save(newTutorial);
        
        List<Tutorial> tutorialList = tutorialRepository.findByPublished(true);

        assertNotNull(tutorialList);
        assertEquals(tutorialList.size(), 1);
    }

    @Test
    public void givenSomeTutorialObject_whenFindByTitleContainingKeyword_thenReturnTutorialList(){
        Tutorial tutorial = new Tutorial();
        tutorial.setTitle("Tutorial Database");
        tutorial.setPublished(true);
        tutorial.setTitle("Tutorial for relational database");
        Tutorial newTutorial = new Tutorial();
        newTutorial.setTitle("Tutorial Network");
        newTutorial.setPublished(false);
        newTutorial.setTitle("Tutorial for network");
        tutorialRepository.save(tutorial);
        tutorialRepository.save(newTutorial);
        
        List<Tutorial> tutorialList = tutorialRepository.findByTitleContainingIgnoreCase("database");

        assertNotNull(tutorialList);
        assertEquals(tutorialList.size(), 1);
    }

    @Test
    public void givenTutorialObject_whenFindById_thenReturnTutorialObject(){

        Tutorial tutorial = new Tutorial();
        tutorial.setTitle("Tutorial Database");
        tutorial.setPublished(false);
        tutorial.setTitle("Tutorial for relational database");
        tutorialRepository.save(tutorial);

        Optional<Tutorial> tutorialDB = tutorialRepository.findById(tutorial.getId());
        assertTrue(tutorialDB.isPresent());
        assertEquals(tutorialDB.get().getId(), tutorial.getId());
        assertEquals(tutorialDB.get().getTitle(), tutorial.getTitle());
        assertEquals(tutorialDB.get().isPublished(), tutorial.isPublished());
        assertEquals(tutorialDB.get().getDescription(), tutorial.getDescription());
    }

    @Test
    public void givenTutorialObject_whenUpdate_thenReturnUpdatedObject(){
        Tutorial tutorial = new Tutorial();
        tutorial.setTitle("Tutorial Database");
        tutorial.setPublished(false);
        tutorial.setTitle("Tutorial for relational database");
        tutorialRepository.save(tutorial);

        Optional<Tutorial> tutorialDB = tutorialRepository.findById(tutorial.getId());
        Tutorial currenTutorial = tutorialDB.get();
        currenTutorial.setTitle("New Title for Tutorial Database");
        tutorialRepository.save(currenTutorial);

        Optional<Tutorial> latestTutorial = tutorialRepository.findById(tutorial.getId());

        assertTrue(latestTutorial.isPresent());
        assertEquals(latestTutorial.get().getTitle(), "New Title for Tutorial Database");
    }

    @Test
    public void givenTutorialObject_whenDeleteById_thenReturnEmpty(){
        Tutorial tutorial = new Tutorial();
        tutorial.setTitle("Tutorial Database");
        tutorial.setPublished(false);
        tutorial.setTitle("Tutorial for relational database");
        tutorialRepository.save(tutorial);

        tutorialRepository.deleteById(tutorial.getId());

        Optional<Tutorial> latestTutorial = tutorialRepository.findById(tutorial.getId());

        assertFalse(latestTutorial.isPresent());
    }

    @Test
    public void givenSomeTutorialObject_whenDeleteAll_thenReturnEmpty(){
        Tutorial tutorial = new Tutorial();
        tutorial.setTitle("Tutorial Database");
        tutorial.setPublished(false);
        tutorial.setTitle("Tutorial for relational database");
        Tutorial newTutorial = new Tutorial();
        newTutorial.setTitle("Tutorial Network");
        newTutorial.setPublished(false);
        newTutorial.setTitle("Tutorial for network");
        tutorialRepository.save(tutorial);
        tutorialRepository.save(newTutorial);
        
        List<Tutorial> tutorialList = tutorialRepository.findAll();

        assertNotNull(tutorialList);
        assertEquals(tutorialList.size(), 2);

        tutorialRepository.deleteAll();
        tutorialList = tutorialRepository.findAll();
        assertNotNull(tutorialList);
        assertEquals(tutorialList.size(), 0);
    }

}
