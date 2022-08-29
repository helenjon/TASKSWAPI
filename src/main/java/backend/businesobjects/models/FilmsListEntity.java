package backend.businesobjects.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class FilmsListEntity {

    private int count;
    private Object next;
    private Object previous;
    private List<FilmEntity> results;

}
