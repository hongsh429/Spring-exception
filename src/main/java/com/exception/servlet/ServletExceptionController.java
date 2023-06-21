package com.exception.servlet;

import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.IOException;

@Controller
@Slf4j
public class ServletExceptionController {

    @GetMapping("/error-ex")
    public void errorEx() {
        throw new RuntimeException("예외발생");

        /*
        에러발생해서 역순으로 진행
                    > servlet > ... > WAS -> WebServerCustomizer에 등록된 에러페이지를 확인 >>> 다시 WAS에서 Controller를 호출
        */
    }

    @GetMapping("error-404")
    public void error404(HttpServletResponse response) throws IOException {
        response.sendError(404,"404 오류!");
    }

    @GetMapping("error-500")
    public void error500(HttpServletResponse response) throws IOException {
        response.sendError(500);
    }
}
