package prjoect.firstproject.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor // 모든 필드에 있는 것을 생성자를 자동으로 만들어준다.
@ToString
@NoArgsConstructor // 디폴트 생성자를 추가해주는 어노테이션이다. 여기서는 Article(){} 생성자이다.
@Getter
@Entity // DB가 해당 객체를 인식가능하게 하는 어노테이션이다. (해당 클래스로 테이블을 만든다)
public class Article {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY) // 1,2,3 ... 자동 생성 어노테이션이다., DB가 id를 자동 생성(strategy =..)
    private Long id; //대표값

    // @Column은 DB에서 이해할 수 있게 하는 어노테이션이다.
    // DB에서 관리하는 테이블이라는 단위에 연결하게 한다. -> @Column
    @Column
    private String title;
    @Column
    private String content;


    public void patch(Article article) {
        if(article.title != null)
            this.title = article.title;
        if (article.content != null)
            this.content = article.content;
    }
}
