package backend.businesobjects.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class FilmEntity {
        public String title;
        public Integer episode_id;
        public String opening_crawl;
        public String director;
        public String producer;
        public String release_date;
        public List<String> characters;
        public List<String> planets;
        public List<String> starships;
        public List<String> vehicles;
        public List<String> species;
        public Date created;
        public Date edited;
        public String url;
}
