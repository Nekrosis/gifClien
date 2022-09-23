package com.example.gifAndUsdService.service;

import com.example.gifAndUsdService.models.GifResponse;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

class GifServiceIT extends BaseIT {
    private static final String URL_RICH = "https://media1.giphy.com/media/Ke3vYfKXQXfhRM8DUQ/giphy.gif?cid=9a04831e265d0e4f2c2a3dc576ae28f055b95c4af89053ff&rid=giphy.gif&ct=g";
    private static final String URL_BROKE = "https://media3.giphy.com/media/l0HUefxlVh69BbvzO/giphy.gif?cid=9a04831ed44878ccd4ddd1e771d13d6783015426c02d595a&rid=giphy.gif&ct=g";

    @Test
    void getCorrectedRichUrl() {
        whenUrlRich(URL_RICH);
        var actual = gifService.getGif();
        assertThat(actual).isEqualTo(URL_RICH);
    }


    private void whenUrlRich(String url) {
        var response = GifResponse.builder()
                .data(new GifResponse
                        .GifResponseData(new GifResponse
                        .GifResponseImages(new GifResponse
                        .GifResponseOriginal(url))))
                .build();
        when(gifClient.getRandomRich()).thenReturn(response);
    }
}