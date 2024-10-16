package com.klima.EncurtaDev.controller;

import com.google.zxing.WriterException;
import com.klima.EncurtaDev.links.Link;
import com.klima.EncurtaDev.links.LinkResponse;
import com.klima.EncurtaDev.links.LinkService;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Map;

@RestController
public class LinkController {

    private static final Logger logger = LoggerFactory.getLogger(LinkController.class);

    @Autowired
    private LinkService linkService;

    @PostMapping("/encurta-dev")
    public ResponseEntity<LinkResponse> gerarUrlEncurtada(@RequestBody Map<String, String> request) throws IOException, WriterException {

        String urlOriginal = request.get("urlOriginal");
        Link link = linkService.encurtarUrl(urlOriginal);



        LinkResponse response = new LinkResponse(
                linkService.gerarUrlDeRedirecionamentoDoUsuario("https://encurta-dev.onrender.com/r/", link.getUrlEncurtada()),
              //linkService.gerarUrlDeRedirecionamentoDoUsuario("http://localhost:8080/r/", link.getUrlEncurtada()),
                link.getUrlQrCode()
        );

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
    @GetMapping("/r/{urlEncurtada}")
    public void redirecionarLink(@PathVariable String urlEncurtada, HttpServletResponse response) throws IOException {
        logger.info("Redirecionamento solicitado para a URL encurtada: " + urlEncurtada);
        Link link = linkService.findByUrlEncurtada(urlEncurtada);

        if (link != null){
            logger.info("URL longa encontrada: " + link.getUrlLonga());
            response.sendRedirect(link.getUrlLonga());
        }else {
            logger.error("URL encurtada não encontrada: " + urlEncurtada);
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
        }
    }
}
