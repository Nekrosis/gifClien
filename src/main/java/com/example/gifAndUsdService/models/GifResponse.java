package com.example.gifAndUsdService.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GifResponse {
    private GifResponseData data;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class GifResponseData {
        private GifResponseImages images;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class GifResponseOriginal {
        @JsonProperty("url")
        private String url;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class GifResponseImages {
        private GifResponseOriginal original;
    }
}
