package prjoect.firstproject.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import prjoect.firstproject.dto.ArticleForm;
import prjoect.firstproject.entity.Article;
import prjoect.firstproject.repository.ArticleRepository;

import java.util.List;


@Controller
@Slf4j // 로깅을 위한 어노테이션
public class ArticleController {

    @Autowired // @Autowired :: 스프링 부트가 미리 생성해놓은 객체를 가져다가 자동으로 연결해준다.
    private ArticleRepository articleRepository;
    @GetMapping("/articles/new")
    public String newArticleForm(){return "articles/new"; //뷰 페이지를 연결했다.
    }


    @PostMapping("/articles/create") // <form>태그에서 method를 post하기 떄문에 PostMapping으로 데이터를 받는다.
    public String createArticle(ArticleForm form){ // 폼데이터가 Post방식으로 위의 주소 ["/article/create"]로 던져지는데
        //데이터를 받아올려면 파라미터로 DTO를 넣어주어야 한다. () 파라미터 => form은 파라미터. ArticleForm 객체.

        //System.out.println(form.toString());
        //->로깅 기능으로 대체해보자
        log.info(form.toString()); //여기에 우리가 출력을 원하는 폼을 입력하면 된다.



        //1. Dto를 Entity로 변환한다.
        Article article = form.toEntity();


        //2.Repository에게 Entity를 DB안에 저장하게 한다.
        Article saved = articleRepository.save(article); // 위의 article데이터를 save할 수 있도록 한다.
        log.info(saved.toString());
        // saved라는 이름으로 Article Entity 타입으로 반환.


        return "redirect:/articles/" + saved.getId(); //redirect
    }

    @GetMapping("/articles/{id}")
    public String show(@PathVariable Long id, Model model) { // URL Path로 부터 입력이 되는 것이다 => : @PathVartiable 어노테이션
        log.info("id = " + id);

        // 1 : id로 데이터를 가져온다.
        Article articleEntity = articleRepository.findById(id).orElse(null); // 값이 없다면 null을 반환한다.
        //@AutoWired를 통해서 가져왔던 Repository를 연결해준다.

        // 2 : 가져온 데이터를 모델에 등록한다.
        model.addAttribute("article", articleEntity);
        // article이라는 이름으로 articleEntity를 등록했다 -> 이것을 뷰페이지에서 쓸 수 있다.


        // 3 : 보여줄 페이지를 설정한다.
        return "articles/show";
    }

    @GetMapping("/articles") //이 요청이 들어오면, 해당 메서드가 수행이되고 결과적으로 View페이지를 수행하는 코드
    public String index(Model model){
        // 1 : 모든 Article을 가져온다.
        List<Article> articleEntityList = articleRepository.findAll(); // findAll은 해당 Repository에 있는 모든 데이터를 가져오는 것이다.
        // List타입으로 할 것 --> articleRepository.findAll()가 ArrayList로 반환했으면 좋겠다.

        // 2 : 가져온 Article 묶음을 뷰로 전달한다. (모델을 사용한다 => 파라미터에 Model)
        model.addAttribute("articleList", articleEntityList); //앞에는 이름, 뒤에는 던져줄 데이터를 명시.

        // 3 : 뷰 페이지를 설정한다 !
        return "article/index"; // articles/index.mustache
    }

    @GetMapping("/articles/{id}/edit") // 일단 URL 요청을 받아와야 한다 : @GetMapping
    public String eidt(@PathVariable Long id, Model model){
        //URL에서 id를 가져오고 싶어 << Long 타입의 id를 가져온다. 어디서? URL Path 경로로 가져온다. @Pathvariable

        // 수정할 데이터를 가져온다. => Repository를 통해서 데이터를 가져오자.
        Article articleEntity = articleRepository.findById(id).orElse(null); // Article Entity로 데이터를 가져온다 !

        // (1)데이터를 가져옴, (2) View 페이지에서 사용할 수 있도록 모델에 데이터를 등록 = 파라미터에 Model model 선언을 해야 한다.
        model.addAttribute("article", articleEntity); // 이제 우리는 해당 데이터를 article이라는 이름으로 view페이지에서 사용 가능하다.

        // View 페이지 설정한다. return "articles/eidt";
        return "articles/edit"; // 수정 페이지를 응답으로 반환해야 한다.
    }

    @PostMapping("/articles/update")
    public String update(ArticleForm form){
        log.info(form.toString());

        // 1 : DTO를 Entity로 변환한다
        Article articleEntity = form.toEntity();
        log.info(articleEntity.toString());

        // 2 : Entity를 DB로 저장한다
        // 2-1 : DB에서 기존 데이터를 가져온다
        Article target = articleRepository.findById(articleEntity.getId()).orElse(null);

        // 2-2 : 기존 데이터가 존재한다면, 값을 갱신한다.
        if(target != null){
            articleRepository.save(articleEntity); // Entity가 DB로 갱신된다.
        }

        // 3 : 수행 결과 페이지를 리다이렉트 한다.
        return "redirect:/articles/" + articleEntity.getId();
    }

    @GetMapping("/articles/{id}/delete")
    public String delete(@PathVariable Long id, RedirectAttributes rttr){
        log.info("삭제 요청이 입력되었습니다.");

        // 1: 삭제 대상을 가져온다!
        Article target = articleRepository.findById(id).orElse(null);
        log.info(target.toString());

        // 2: 대상을 삭제한다 !
        if (target != null){
            articleRepository.delete(target); // target이 값이 null이 아니면(데이터가 존재한다면 삭제한다.)
            rttr.addFlashAttribute("msg", "삭제가 완료되었습니다 !");

        }

        // 3: 결과 페이지로 리다이렉트 한다!

        return "redirect:/articles";
        // 리다이렉트 될 페이지는 index/mustache이다.
    }
}
// 사용자가 브라우저를 통해서 데이터를 요청하고, 요청URL을 컨트롤러가 받고, 받아진 URL을 찾고자 하는 정보를 Repository에 전달함.
// 이를 DB에게 요청을 보내고, DB는 해당 데이터를 찾아서 Entity로 반환한다.
// 반환한 Entity는 Model을 통해 View Template로 전달되고, 최종적으로 결과 페이지가 완성되어서 Client에게 보여진다.