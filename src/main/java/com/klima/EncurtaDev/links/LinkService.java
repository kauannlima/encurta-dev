package com.klima.EncurtaDev.links;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Base64;


@Service
public class LinkService {

    @Autowired
    private LinkRepository linkRepository;

    //TODO: REFATORAR
    public String gerarUrlAleatorio(){
        return RandomStringUtils.randomAlphanumeric(5,10);
    }

    // TODO: REFATORAR
    // Codigo da Internet, dois deploy e o codigo quebra
    public String gerarQrCode(String url) throws WriterException, IOException {
        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        BitMatrix bitMatrix = qrCodeWriter.encode(url, BarcodeFormat.QR_CODE, 200, 200);

        ByteArrayOutputStream pngOutputStream = new ByteArrayOutputStream();
        MatrixToImageWriter.writeToStream(bitMatrix, "PNG", pngOutputStream);

        byte[] pngData = pngOutputStream.toByteArray();
        return Base64.getEncoder().encodeToString(pngData);
    }

    public Link encurtarUrl(String urlOriginal) throws IOException, WriterException {
        Link link = new Link();
        link.setUrlLonga(urlOriginal);
        String urlEncurtada = gerarUrlAleatorio();
        link.setUrlEncurtada(urlEncurtada);
      //link.setUrlQrCode(gerarQrCode(gerarUrlDeRedirecionamentoDoUsuario("http://localhost:8080/r/", link.getUrlEncurtada())));
        link.setUrlQrCode(gerarQrCode(gerarUrlDeRedirecionamentoDoUsuario("https://encurta-dev.onrender.com/r/", link.getUrlEncurtada())));
        link.setUrlCriadaEm(LocalDateTime.now());

        return linkRepository.save(link);
    }

    public String gerarUrlDeRedirecionamentoDoUsuario(String urlRaiz, String urlEncurtada){
        return urlRaiz + urlEncurtada;
    }

    public Link findByUrlEncurtada(String urlEncurtada){
        try{
        }catch (Exception erro){
            throw  new RuntimeException("Url não existe nos nossos registros", erro);
        }
        return linkRepository.findByUrlEncurtada(urlEncurtada);
    }
}
