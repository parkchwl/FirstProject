package prjoect.firstproject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import prjoect.firstproject.dto.ArticleForm;

@Controller
public class ArticleController {
    @GetMapping("/articles/new")
    public String newArticleForm(){return "articles/new"; //뷰 페이지를 연결했다.
    }
    @PostMapping("/articles/create") // <form>태그에서 method를 post하기 떄문에 PostMapping으로 데이터를 받는다.
    public String createArticle(ArticleForm form){ // 폼데이터가 Post방식으로 위의 주소 ["/article/create"]로 던져지는데
        //데이터를 받아올려면 파라미터로 DTO를 넣어주어야 한다. () 파라미터 => form
        System.out.println(form.toString());
        return "";

    }
}
