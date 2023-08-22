package org.springframework.samples.ai.azure.openai.output;

import java.util.List;

public class ActorsFilms {

	private String actor;

	private List<String> movies;

	public ActorsFilms() {
	}

	public String getActor() {
		return actor;
	}

	public void setActor(String actor) {
		this.actor = actor;
	}

	public List<String> getMovies() {
		return movies;
	}

	public void setMovies(List<String> movies) {
		this.movies = movies;
	}

	@Override
	public String toString() {
		return "ActorsFilms{" + "actor='" + actor + '\'' + ", movies=" + movies + '}';
	}

}
