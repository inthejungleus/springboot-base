package com.example.demo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class ComicWrapper {
	private final double FIVE_STAR_RATING = 5.0;

	List<RetrieveComicAPI> comics;

	public List<RetrieveComicAPI> getComics() {
		return comics;
	}

	public void setComics(List<RetrieveComicAPI> comics) {
		this.comics = comics;
	}
	
	public void addRatingtoStarTrek() {
		
		comics.forEach(comic->{
			if(comic.getTitle().toLowerCase().contains("star trek")) {
				comic.setRating(FIVE_STAR_RATING);
			};
			});
	
	}
	
	public List<RetrieveComicAPI> sortByPublishedYear(){
		List<RetrieveComicAPI> sortedComics = getComics();
		Comparator<RetrieveComicAPI> compareByPublishedYear = (RetrieveComicAPI o1, RetrieveComicAPI o2) -> o1.getPublishedYear().compareTo(o2.getPublishedYear());
		sortedComics.sort(compareByPublishedYear);
		return sortedComics;
	}

	public List<RetrieveComicAPI> getComicsByDecade(Long decade) {
		List<RetrieveComicAPI> sortedByDecade = this.sortByPublishedYear();
		List<RetrieveComicAPI> sortedByYear = this.sortByPublishedYear();
		List<RetrieveComicAPI> bySpecifiedDecade = new ArrayList<>(); 
//		sortedByDecade = sortedByDecade.stream().filter(comic -> (((comic.getPublishedYear())/10)%10) == ((decade/10)%10)).collect(Collectors.toList());
		sortedByYear.forEach(comic -> {
			//System.out.println((comic.getPublishedYear()/10)%10);
			//System.out.println("This is the decade comparrison: " +(decade/10)%10);

			if ((((comic.getPublishedYear())/10)%10) == ((decade/10)%10)) {
				bySpecifiedDecade.add(comic);
//				System.out.println(comic.getPublishedYear());
			}
		}); 
		//sortedbyDecade.forEach(comic -> System.out.println("This is the comic: " + comic));
//		System.out.println("is there anything here? " + decade);
		
		return bySpecifiedDecade;
	}
	
	
}
