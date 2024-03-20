package prjoect.firstproject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import prjoect.firstproject.dto.ArticleForm;
import prjoect.firstproject.entity.Article;
import prjoect.firstproject.repository.ArticleRepository;

@Controller
public class ArticleController {

    @Autowired // @Autowired :: 스프링 부트가 미리 생성해놓은 객체를 가져다가 자동으로 연결해준다.
    private ArticleRepository articleRepository;
    @GetMapping("/articles/new")
    public String newArticleForm(){return "articles/new"; //뷰 페이지를 연결했다.
    }
    @PostMapping("/articles/create") // <form>태그에서 method를 post하기 떄문에 PostMapping으로 데이터를 받는다.
    public String createArticle(ArticleForm form){ // 폼데이터가 Post방식으로 위의 주소 ["/article/create"]로 던져지는데
        //데이터를 받아올려면 파라미터로 DTO를 넣어주어야 한다. () 파라미터 => form은 파라미터. ArticleForm 객체.
        System.out.println(form.toString());

        //1. Dto를 Entity로 변환한다.
        Article article = form.toEntity();


        //2.Repository에게 Entity를 DB안에 저장하게 한다.
        Article saved = articleRepository.save(article); // 위의 article데이터를 save할 수 있도록 한다.
        // saved라는 이름으로 Article Entity 타입으로 반환.


        return "";

    }
}
