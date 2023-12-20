package com.iqbaal.restful.service;

import java.math.BigInteger;
import java.util.List;
import java.util.Objects;

import org.springframework.stereotype.Service;

import com.iqbaal.restful.dto.request.TutorialRequest;
import com.iqbaal.restful.entity.Tutorial;
import com.iqbaal.restful.exception.TutorialNotFoundException;
import com.iqbaal.restful.repository.TutorialRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TutorialService {
    private final TutorialRepository tutorialRepository;

    public Tutorial create(TutorialRequest request){
        Tutorial createdTutorial = new Tutorial();
        createdTutorial.setDescription(request.getDescription());
        createdTutorial.setTitle(request.getTitle());
        if (Objects.isNull(request.getPublished())) {
            createdTutorial.setPublished(false);
        }else{
            createdTutorial.setPublished(request.getPublished());
        }
        tutorialRepository.save(createdTutorial);
        return createdTutorial;
    }

    public Tutorial edit(TutorialRequest request, BigInteger id){
        Tutorial currentTutorial = tutorialRepository.findById(id).orElseThrow(() -> new TutorialNotFoundException("Tutorial with id: "+id+" is not found"));
        if (Objects.nonNull(request.getPublished())) {
            currentTutorial.setPublished(request.getPublished());
        }
        if (Objects.nonNull(request.getTitle())) {
            currentTutorial.setTitle(request.getTitle());
        }
        if (Objects.nonNull(request.getDescription())) {
            currentTutorial.setDescription(request.getDescription());
        }
        tutorialRepository.save(currentTutorial);
        return currentTutorial;
    }

    public List<Tutorial> getAll(){
        List<Tutorial> result = tutorialRepository.findAll();
        return result;
    }

    public Tutorial getOne(BigInteger id) {
        Tutorial result = tutorialRepository.findById(id).orElseThrow(() -> new TutorialNotFoundException("Tutorial with id: "+id+" is not found"));
        return result;
    }

    public void deleteAll(){
        tutorialRepository.deleteAll();
    }

    public void deleteOne(BigInteger id){
        Tutorial result = tutorialRepository.findById(id).orElseThrow(() -> new TutorialNotFoundException("Tutorial with id: "+id+" is not found"));
        tutorialRepository.delete(result);
    }

    public List<Tutorial> getPublishedTutorials(){
        List<Tutorial> result = tutorialRepository.findByPublished(true);
        return result;
    }

    public List<Tutorial> getByTitle(String keyword){
        List<Tutorial> result = tutorialRepository.findByTitleContainingIgnoreCase(keyword);
        return result;
    }
}
