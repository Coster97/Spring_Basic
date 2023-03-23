package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {

    @GetMapping("hello")
    public String hello(Model model){
        model.addAttribute("data", "박은경 님");
        return "hello";

        //컨트롤러, 모델과 관련된 부분들 비즈니스 로직이나 내부적인 것들을 처리할때
        // 리소시스의 템플리츠의 폴더 이하의 헬로우를 찾는다. 걔를 렌더링 함
        // 뷰 리졸버가 화면을 찾아서 처리한다.
    }
    @GetMapping("hello-mvc") //메소드, 어떤 파라미터를 쓸것인지
    public String helloMvc(@RequestParam("name") String name, Model model){ //파라미터 네임과 파라미터의 발루
        model.addAttribute("name", name); //name이 모델에 담겨서 hello-template 템플릿에 랜더링 됨
        return "hello-template";
    }
    @GetMapping("hello-string")
    @ResponseBody //http의 바디부분에 이 데이터를 내가 직접 넣어주겠다는 뜻
    //그대로 데이터를 내려주는 방식
    public String helloString(@RequestParam("name") String name) {
        return "hello " + name;
    }

    @GetMapping("hello-api")
    @ResponseBody
    //ResponseBody 어노테이션을 썼을때 동작 과정 :

    //1. 웹브라우저에서 localhost:8080/hello-api 를 친다.
    //2. 톰캣 내장 서버에서 스프링으로 hello-api가 왔다고 전달
    //3. 스프링에서 hello-api를 확인.
    //4. ResponseBody 어노테이션이 붙어있는 것을 확인한 후 http 응답에 그대로 이 데이터를 넣어야 겠구나 함.
    //5. 근데 아래 코드는 문자가 아니라 객체가 들어왔음
    //6. 객체가 오면 기본 default가 json으로 반환하는 것임.
    //7. 객체가 오면 http메시지 컨버터가 동작을 함. 만약 단순 문자면 스트링컨버터가 동 객체가 오면 제이슨컨버터가 동작
    //8. json을 반환
    //템플릿 방식이였으면 뷰 리졸버한테 던져줬을것임
    // key value로 나옴. json 포멧! 요즘엔 json이 defaultㄹ
    public Hello helloApi(@RequestParam("name") String name){
        Hello hello = new Hello();
        hello.setName(name);
        return  hello;
    }

    //java bean 규약. 메서드를 통해서 접근할 수 있음. 프로퍼티 접근방식이라고도 부름

    static class Hello {
        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
