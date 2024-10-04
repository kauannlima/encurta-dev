package com.klima.EncurtaDev.links;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;


@Service
public class LinkService {

    @Autowired
    private LinkRepository linkRepository;

    //TODO: REFATORAR
    public String gerarUrlAleatorio(){
        return RandomStringUtils.randomAlphanumeric(5,10);
    }
    public Link encurtarUrl(String urlOriginal){
        Link link = new Link();
        link.setUrlLonga(urlOriginal);
        link.setUrlEncurtada(gerarUrlAleatorio());
        link.setUrlCriadaEm(LocalDateTime.now());
        link.setUrlQrCode("INDISPONIVEL NO MOMENTO");

        return linkRepository.save(link);
    }

    public Link findByUrlEncurtada(String urlEncurtada){
        try{
        }catch (Exception erro){
            throw  new RuntimeException("Url não existe nos nossos registros", erro);
        }
        return linkRepository.findByUrlEncurtada(urlEncurtada);
    }
}
