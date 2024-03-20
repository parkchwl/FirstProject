package prjoect.firstproject.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity // DB가 해당 객체를 인식가능하게 하는 어노테이션이다.
public class Article {

    @Id
    @GeneratedValue // 1,2,3 ... 자동 생성 어노테이션이다.
    private Long id; //대표값

    // @Column은 DB에서 이해할 수 있게 하는 어노테이션이다.
    // DB에서 관리하는 테이블이라는 단위에 연결하게 한다. -> @Column
    @Column
    private String title;
    @Column
    private String content;

    public Article(Long id, String title, String content) { // 생성자를 생성했다.
        this.id = id;
        this.title = title;
        this.content = content;
    }

    public Article() {

    }

    @Override
    public String toString() {
        return "Article{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}
