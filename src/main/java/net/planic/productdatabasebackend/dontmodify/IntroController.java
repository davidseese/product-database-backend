package net.planic.productdatabasebackend.dontmodify;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/intro")
public class IntroController {

    @GetMapping
    public ResponseEntity<String> getIntro() {
        return ResponseEntity.ok("Backend erfolgreich verbunden!");
    }

}