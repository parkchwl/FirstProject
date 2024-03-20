package prjoect.firstproject.dto;

import prjoect.firstproject.entity.Article;

public class ArticleForm {

    // <form> 태그에서 title 과 content의 폼 데이터를 2개를 받기 때문에
    // String title, content 2개를 선언한 모습이다.
    private String title;
    private String content;

    public ArticleForm(String title, String content) {
        this.title = title;
        this.content = content;
    }

    @Override // 데이터가 잘 받아졌는지 확인하기 위해 toString() 또한 선언했다.
    public String toString() {
        return "ArticleForm{" +
                "title='" + title + '\'' +
                ", content='" + content + '\'' +
                '}';
    }

    public Article toEntity() { //toEntity 메소드 선언 -> Article 타입을 반환하기 원한다.
        return new Article(null, title, content);//새롭게 Article을 만들어 준다. why? Article Entity를 만들어야 DB에 CRUD가능
        // Article이 뭐였죠 ? @Entity 클래스였죠. 이 Entity 클래스의 객체를 생성해야 하니까, Article 생성자를 호출해야한다.
        // Article.java의 public Article(Long id, String title, String content) 를 입력받고 있으니까 이것에 맞게 입력해야 한다.
    }
}
