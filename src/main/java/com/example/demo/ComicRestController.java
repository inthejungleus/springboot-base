package com.example.demo;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class ComicRestController {
	
	
	@GetMapping("/comics")
	public List<RetrieveComicAPI> comics() {
		
		RestTemplate restTemplate = new RestTemplate();
		ComicWrapper comicWrapper = restTemplate.getForObject("http://stapi.co/api/v1/rest/comics/search",   ComicWrapper.class);	
		return comicWrapper.getComics();
	}
	
	@GetMapping("/comics-with-ratings")
	public List<RetrieveComicAPI> comicsWithRatings(){
		RestTemplate restTemplate = new RestTemplate();
		ComicWrapper comicWrapper = restTemplate.getForObject("http://stapi.co/api/v1/rest/comics/search",   ComicWrapper.class);
		comicWrapper.addRatingtoStarTrek();
		return comicWrapper.getComics();		
	}
	

	@GetMapping("/comics-sorted-published-year")
	public List<RetrieveComicAPI> comicsSortedByPublishedYear() {
		
		RestTemplate restTemplate = new RestTemplate();
		ComicWrapper comicWrapper = restTemplate.getForObject("http://stapi.co/api/v1/rest/comics/search",   ComicWrapper.class);	
		comicWrapper.sortByPublishedYear();
		comicWrapper.addRatingtoStarTrek();
		return comicWrapper.getComics();
	}

	//may use the full year or the two digit decade as the input to parameter.  If the year is used, it will return back the comics in the same decade.
	@GetMapping("/comics-sorted-decade")
	public List<RetrieveComicAPI> comicsSortedByDecade(@RequestParam(value="decade", defaultValue = "70") Long decade) {
		
		RestTemplate restTemplate = new RestTemplate();
		ComicWrapper comicWrapper = restTemplate.getForObject("http://stapi.co/api/v1/rest/comics/search",   ComicWrapper.class);	
		comicWrapper.sortByPublishedYear();
		comicWrapper.addRatingtoStarTrek();
		return comicWrapper.getComicsByDecade(decade);
	}

	
}
