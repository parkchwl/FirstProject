package prjoect.firstproject.repository;

import org.springframework.data.repository.CrudRepository;
import prjoect.firstproject.entity.Article;

import java.util.ArrayList;

public interface ArticleRepository extends CrudRepository<Article, Long> { // Crud Repository를 extends 했다.
                //  <> 앞에는 Article(관리대상) 을 넣어주어야 하고 , 뒤에는 대표값(Long)을 넣어주어야 한다.
    @Override
    ArrayList<Article> findAll(); // ArrayList 타입으로 반환하기 위해, Override & 명시하였다.
}