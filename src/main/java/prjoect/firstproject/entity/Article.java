package prjoect.firstproject.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor // 모든 필드에 있는 것을 생성자를 자동으로 만들어준다.
@ToString
@NoArgsConstructor // 디폴트 생성자를 추가해주는 어노테이션이다. 여기서는 Article(){} 생성자이다.
@Getter
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



}
