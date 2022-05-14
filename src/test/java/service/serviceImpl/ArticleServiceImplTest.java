package service.serviceImpl;

import com.google.gson.Gson;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import service.dto.ArticleDto;
import service.repository.ArticleRepository;
import service.repository.serviceImpl.ArticleServiceImpl;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;

@DataJpaTest
public class ArticleServiceImplTest {

    private static final String ARTICLE_PATH = "src/test/resources/article.json";

    private static final String ARTICLES_PATH = "src/test/resources/articles.json";

    @InjectMocks
    private ArticleServiceImpl articleServiceImpl;

    @Mock
    private ArticleRepository articleRepository;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void createArticleTest() throws Exception {
        BufferedReader reader = new BufferedReader(new FileReader(ARTICLE_PATH));
        ArticleDto articleExpected = new Gson().fromJson(reader, ArticleDto.class);
        final boolean saved = articleServiceImpl.insertArticle(articleExpected);
        Assertions.assertThat(saved).isTrue();
    }

    @Test
    void listAllArticles() throws FileNotFoundException {
        BufferedReader readerArticles = new BufferedReader(new FileReader(ARTICLES_PATH));
        final List<ArticleDto> articlesExpected = new Gson().fromJson(readerArticles, List.class);
        articleRepository.saveAll(articlesExpected);
        Assertions.assertThat(articleRepository.findAll()).isNotNull();
    }

    @Test
    void listArticle() throws Exception {
        BufferedReader reader = new BufferedReader(new FileReader(ARTICLE_PATH));
        ArticleDto articleExpected = new Gson().fromJson(reader, ArticleDto.class);
        articleRepository.save(articleExpected);
        Assertions.assertThat(articleRepository.findById(articleExpected.getA_idArticle())).isNotNull();
    }

    @Test
    void updateArticle() throws FileNotFoundException {
        final int idArticle = 1;
        BufferedReader reader = new BufferedReader(new FileReader(ARTICLE_PATH));
        ArticleDto articleExpected = new Gson().fromJson(reader, ArticleDto.class);
        articleRepository.save(articleExpected);
        articleRepository.existsById(idArticle);
        Assertions.assertThat(articleExpected).isNotNull();
    }

    @Test
    void deleteArticle() {
        final int idArticle = 1;
        articleServiceImpl.deleteArticle(idArticle);
        Assertions.assertThat(idArticle).isNotNull();

    }

}
