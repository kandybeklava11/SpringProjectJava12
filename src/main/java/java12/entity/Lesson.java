package java12.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "lessons")
@Getter
@Setter
@NoArgsConstructor
public class Lesson {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "lesson_gen")
    @SequenceGenerator(name = "lesson_gen", sequenceName = "lesson_seq", allocationSize = 1)
    private Long id;

    private String title;

    private String description;

    private String videoLink;

    private LocalDate publishedDate;

    private boolean isPresentation;

    public Lesson(String title, String description, String videoLink, LocalDate publishedDate, boolean isPresentation) {
        this.title = title;
        this.description = description;
        this.videoLink = videoLink;
        this.publishedDate = publishedDate;
        this.isPresentation = isPresentation;
    }

    @Override
    public String toString() {
        return "Lesson{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", videoLink='" + videoLink + '\'' +
                ", publishedDate=" + publishedDate +
                ", isPresentation=" + isPresentation +
                '}';
    }
}
