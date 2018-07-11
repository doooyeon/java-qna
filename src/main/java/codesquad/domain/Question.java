package codesquad.domain;

import javax.persistence.*;

@Entity
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(foreignKey =  @ForeignKey(name = "fk_question_writer"))
    private User writer;

    @Column(nullable = false, length = 40)
    private String title;
    private String contents;

    public Question() {
    }

    public Question(User writer, String title, String contents) {
        this.writer = writer;
        this.title = title;
        this.contents = contents;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContents() {
        return contents;
    }

    public User getWriter() {
        return writer;
    }

    public void setWriter(User writer) {
        this.writer = writer;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }

    public Question updateQuestion(Question question) {
        this.title = question.title;
        this.contents = question.contents;
        return this;
    }

    public boolean matchWriter(String userId) {
        return this.writer.matchUserId(userId);
    }
}
