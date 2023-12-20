package com.iqbaal.restful.service;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.iqbaal.restful.entity.Area;
import com.iqbaal.restful.entity.PostalCode;
import com.iqbaal.restful.exception.ProvinceNotFoundException;
import com.iqbaal.restful.exception.RegencyNotFoundException;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class PostalCodeService {

	@Cacheable("province")
	public List<Area> getAllProvince(){
		String uri = "https://kodepos-2d475.firebaseio.com/list_propinsi.json";
        RestTemplate restTemplate = new RestTemplate();
        Map<String, String> responseMap = restTemplate.getForObject(uri, Map.class);

        return responseMap.entrySet()
                .stream()
                .map(entry -> new Area(entry.getKey(), entry.getValue()))
                .collect(Collectors.toList());
	}

	@Cacheable(value = "regency", key = "#provinceId", unless = "#result.size <= 0")
	public List<Area> getAllRegency(String provinceId){
		String uri = "https://kodepos-2d475.firebaseio.com/list_kotakab/"+ provinceId +".json";
        RestTemplate restTemplate = new RestTemplate();
        Map<String, String> responseMap = restTemplate.getForObject(uri, Map.class);

		if (responseMap == null) {
			throw new ProvinceNotFoundException("Province with id: "+provinceId+" is not found");
		}

        return responseMap.entrySet()
                .stream()
                .map(entry -> new Area(entry.getKey(), entry.getValue()))
                .collect(Collectors.toList());
	}

	@Cacheable(value = "postal-code", key = "#regencyId", unless = "#result.size <= 0")
	public List<PostalCode> getAllPostalCode(String regencyId){
		String uri = "https://kodepos-2d475.firebaseio.com/kota_kab/"+ regencyId +".json";
        RestTemplate restTemplate = new RestTemplate();
        PostalCode[] result = restTemplate.getForObject(uri, PostalCode[].class);

		if (result == null) {
			throw new RegencyNotFoundException("Regency with id: "+regencyId+" is not found");
		}

        return Arrays.asList(result);
	}
}
