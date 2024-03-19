package prjoect.firstproject.dto;

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
}
