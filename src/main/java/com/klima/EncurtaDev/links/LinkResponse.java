package com.klima.EncurtaDev.links;

import java.time.LocalDateTime;

public class LinkResponse {

    private Long id;
    private String longUrl;
    private String shortenedUrl;
    private String qrCodeUrl;
    private LocalDateTime createdAt;

    public LinkResponse(Long id, String longUrl, String shortenedUrl, String qrCodeUrl, LocalDateTime createdAt) {
        this.id = id;
        this.longUrl = longUrl;
        this.shortenedUrl = shortenedUrl;
        this.qrCodeUrl = qrCodeUrl;
        this.createdAt = createdAt;
    }

    public LinkResponse() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLongUrl() {
        return longUrl;
    }

    public void setLongUrl(String longUrl) {
        this.longUrl = longUrl;
    }

    public String getShortenedUrl() {
        return shortenedUrl;
    }

    public void setShortenedUrl(String shortenedUrl) {
        this.shortenedUrl = shortenedUrl;
    }

    public String getQrCodeUrl() {
        return qrCodeUrl;
    }

    public void setQrCodeUrl(String qrCodeUrl) {
        this.qrCodeUrl = qrCodeUrl;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
