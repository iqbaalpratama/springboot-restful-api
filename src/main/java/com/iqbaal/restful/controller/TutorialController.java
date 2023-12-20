package com.iqbaal.restful.controller;

import java.math.BigInteger;
import java.util.List;
import java.util.Objects;

import javax.naming.NameNotFoundException;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.iqbaal.restful.dto.request.TutorialRequest;
import com.iqbaal.restful.dto.response.APIResponse;
import com.iqbaal.restful.entity.Tutorial;
import com.iqbaal.restful.service.TutorialService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping(path = "/tutorials")
public class TutorialController {
    
    private final TutorialService tutorialService;
    
    @GetMapping(
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<APIResponse<List<Tutorial>>> getAll(@RequestParam(required = false) String title){
        if (!Objects.isNull(title)) {
            return new ResponseEntity<APIResponse<List<Tutorial>>>(new APIResponse<List<Tutorial>>("Success", 200, "Success get all tutorials by keyword: "+title+" in title tutorial", tutorialService.getByTitle(title)), HttpStatus.OK);
        }
        return new ResponseEntity<APIResponse<List<Tutorial>>>(new APIResponse<List<Tutorial>>("Success", 200, "Success get all tutorials", tutorialService.getAll()), HttpStatus.OK); 
    }

        
    @GetMapping(
        path = "/{id}",
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<APIResponse<Tutorial>> getById(@PathVariable BigInteger id){
        return new ResponseEntity<APIResponse<Tutorial>>(new APIResponse<Tutorial>("Success", 200, "Success get tutorial with id: "+id, tutorialService.getOne(id)),HttpStatus.OK);
    }

    @GetMapping(
        path = "/published",
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<APIResponse<List<Tutorial>>> getByPublished() throws NameNotFoundException{
        return new ResponseEntity<APIResponse<List<Tutorial>>>(new APIResponse<List<Tutorial>>("Success", 200, "Success get all published tutorials", tutorialService.getPublishedTutorials()), HttpStatus.OK);
   
    }

    @PostMapping(
        consumes = MediaType.APPLICATION_JSON_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<APIResponse<Tutorial>> createTutorial(@RequestBody TutorialRequest tutorialRequest){
        return new ResponseEntity<APIResponse<Tutorial>>(new APIResponse<Tutorial>("Success", 201, "Success create new tutorial", tutorialService.create(tutorialRequest)), HttpStatus.CREATED);
    }

    @DeleteMapping(
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<APIResponse<Object>> deleteAllTutorial() {
        tutorialService.deleteAll();
        return new ResponseEntity<APIResponse<Object>>(new APIResponse<>("Success", 200, "Success delete all tutorials", null), HttpStatus.OK);
    } 

    @DeleteMapping(
        path = "/{id}",
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<APIResponse<Object>> deleteTutorialById(@PathVariable BigInteger id) throws NameNotFoundException {
        tutorialService.deleteOne(id);
        return new ResponseEntity<APIResponse<Object>>(new APIResponse<>("Success", 200, "Success delete tutorial with id: "+id, null), HttpStatus.OK);
    }
    
    @PutMapping(
        path = "/{id}",
        produces = MediaType.APPLICATION_JSON_VALUE,
        consumes = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<APIResponse<Tutorial>> updateTutorial(@PathVariable BigInteger id, @RequestBody TutorialRequest tutorialRequest){
        return new ResponseEntity<APIResponse<Tutorial>>(new APIResponse<Tutorial>("Success", 201, "Success update tutorial with id: "+id, tutorialService.edit(tutorialRequest, id)), HttpStatus.OK);
    } 
}
