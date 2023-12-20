package com.iqbaal.restful.controller;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import com.iqbaal.restful.dto.response.APIResponse;
import com.iqbaal.restful.entity.Area;
import com.iqbaal.restful.entity.PostalCode;
import com.iqbaal.restful.service.PostalCodeService;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping(path = "/postal-code")
public class PostalCodeController {
    
    private final PostalCodeService postalCodeService;

    @GetMapping(
        path = "/province",
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<APIResponse<List<Area>>> getAllProvince(){
        return new ResponseEntity<APIResponse<List<Area>>>(new APIResponse<List<Area>>("Success", 200, "Success get all province data", postalCodeService.getAllProvince()), HttpStatus.OK); 
    }

    @GetMapping(
        path = "/{provinceId}/regency",
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<APIResponse<List<Area>>> getRegenciesInProvince(@PathVariable String provinceId){
        return new ResponseEntity<APIResponse<List<Area>>>(new APIResponse<List<Area>>("Success", 200, "Success get all regency data in province with id: "+provinceId, postalCodeService.getAllRegency(provinceId)), HttpStatus.OK); 
    }

    @GetMapping(
        path = "/{regencyId}",
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<APIResponse<List<PostalCode>>> getPostalCodeinRegency(@PathVariable String regencyId){
        return new ResponseEntity<APIResponse<List<PostalCode>>>(new APIResponse<List<PostalCode>>("Success", 200, "Success get all postal code data in regency with id: "+regencyId, postalCodeService.getAllPostalCode(regencyId)), HttpStatus.OK); 
    }
}
