package dev.amylovelace.demo.model;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@Data
public class Course {

    private int courseId;

    private String title;

    private String description;

    private String link;

    public Course(String title, String description, String link) {
        this.title = title;
        this.description = description;
        this.link = link;
    }
}
