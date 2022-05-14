package service.controller;

import com.google.gson.Gson;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import service.apimodel.service.ArticleService;
import service.dto.ArticleDto;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ArticleControllerTest {

    private static final String ARTICLE_PATH = "src/test/resources/article.json";

    private static final String ARTICLES_PATH = "src/test/resources/articles.json";

    private static final int ID_ARTICLE = 1;

    @InjectMocks
    private ArticleController articleController;

    @Mock
    private ArticleService articleService;

    @Test
    public void createArticleControllerTest() throws Exception {
        BufferedReader reader = new BufferedReader(new FileReader(ARTICLE_PATH));
        ArticleDto articleExpected = new Gson().fromJson(reader, ArticleDto.class);
        when(articleService.insertArticle(any())).thenReturn(true);
        ResponseEntity<String> responseEntity = articleController.createArticle(articleExpected);
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    public void listAllArticlesControllerTest() throws FileNotFoundException {
        BufferedReader readerArticles = new BufferedReader(new FileReader(ARTICLES_PATH));
        final List<ArticleDto> articlesExpected = new Gson().fromJson(readerArticles, List.class);
        when(articleService.listAllArticles()).thenReturn(articlesExpected);
        ResponseEntity<List<ArticleDto>> responseEntity = articleController.listAllArticles();
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    public void listArticleControllerTest() throws Exception {
        BufferedReader reader = new BufferedReader(new FileReader(ARTICLE_PATH));
        ArticleDto articleExpected = new Gson().fromJson(reader, ArticleDto.class);
        when(articleService.listArticle(anyInt())).thenReturn(articleExpected);
        ResponseEntity<ArticleDto> responseEntity = articleController.listArticle(ID_ARTICLE);
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    public void updateArticlesControllerTest() throws FileNotFoundException {
        BufferedReader reader = new BufferedReader(new FileReader(ARTICLE_PATH));
        ArticleDto articleExpected = new Gson().fromJson(reader, ArticleDto.class);
        ResponseEntity<String> responseEntity = articleController.updateArticles(ID_ARTICLE, articleExpected);
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    public void deleteArticlesControllerTest() {
        ResponseEntity<String> responseEntity = articleController.deleteArticles(ID_ARTICLE);
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
    }
}
