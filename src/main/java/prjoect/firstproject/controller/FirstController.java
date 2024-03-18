package prjoect.firstproject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class FirstController {
    @GetMapping("/hi") // URL 연결 요청 == @GetMapping
    public String niceToMeetYou(Model model){ //Model을 사용하기 위하여 파라미터로 Model model 을 넣어주었다.
        model.addAttribute("username", "chul woo");
        // model을 통해 변수를 등록할 수 있다. & 변수 등록 addAttribute()
        // 철우라는 값을 username 이름에 연결 시켜 model이라는 객체를 통해 보내준다.

        return "greetings"; // templates/greetings.mustache를 연결하여 브라우저로 전송하게 한다.
        //return 값으로 보여줄 페이지의 이름을 넣어준다.
        }

        @GetMapping("/bye")
                public String seeYouNext(Model model){
        model.addAttribute("nickname","chul woo");
            return "goodbye";
    }
}
