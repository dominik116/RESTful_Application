package service.repository.serviceImpl;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import service.apimodel.service.ArticleService;
import service.dto.ArticleDto;
import service.repository.ArticleRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@Data
@Slf4j
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    private ArticleRepository articleRepository;

    private boolean exists;

    private static final String DUPLICATE_MESSAGE = "Article already exists in the table.";

    private static final String NO_FOUND_ARTICLE_MESSAGE = "Article not found.";

    @Override
    public boolean insertArticle(final ArticleDto article) throws Exception {
        ArticleServiceImpl.log.info("Starting inserting a new article {}", article);
        boolean saved = true;
        try {
            System.out.println(article.toString());
            exists = false;
            Iterable<ArticleDto> findArticle = articleRepository.findAll();
            findArticle.forEach(articleDto -> {
                if (articleDto.getB_articleName().equals(article.getB_articleName())) {
                    exists = true;
                }
            });
            if (!exists) {
                articleRepository.save(article);
                ArticleServiceImpl.log.info("Article inserted successfully.");
            } else {
                ArticleServiceImpl.log.info(DUPLICATE_MESSAGE);
                saved = false;
            }
            return saved;
        } catch (final Exception e) {
            ArticleServiceImpl.log.error(e.getMessage());
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public List<ArticleDto> listAllArticles() {
        ArticleServiceImpl.log.info("Starting listing all articles.");
        List<ArticleDto> articles = new ArrayList<>();
        articleRepository.findAll().forEach(articles::add);
        ArticleServiceImpl.log.info("Articles {}", articles);
        return articles;
    }

    @Override
    public ArticleDto listArticle(final int idArticle) throws Exception {
        try {
            ArticleServiceImpl.log.info("Starting listing article with id {}", idArticle);
            final ArticleDto article = articleRepository.findById(idArticle).get();
            if (article == null){
                throw new Exception(NO_FOUND_ARTICLE_MESSAGE);
            }else{
                ArticleServiceImpl.log.info("Article {}", article);
                return article;
            }

        } catch (final Exception e) {
            ArticleServiceImpl.log.error(e.getMessage());
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public void updateArticle(final int idArticle, final ArticleDto article) {
        ArticleServiceImpl.log.info("Starting update of the article with id {}", idArticle);
        final ArticleDto updatedArticle = articleRepository.findById(idArticle).get();
        ArticleServiceImpl.log.info("Article to update {}", updatedArticle);
        updatedArticle.setB_articleName(article.getB_articleName());
        updatedArticle.setC_articleDescription(article.getC_articleDescription());
        updatedArticle.setD_articlePrice(article.getD_articlePrice());
        updatedArticle.setE_articleStock(article.getE_articleStock());
        ArticleServiceImpl.log.info("Article with id {} updated.", idArticle);
        ArticleServiceImpl.log.info("Updated article {}", updatedArticle);
        articleRepository.save(updatedArticle);
    }

    @Override
    public void deleteArticle(final int idArticle) {
        ArticleServiceImpl.log.info("Starting deleting article with id {}", idArticle);
        articleRepository.deleteById(idArticle);
        ArticleServiceImpl.log.info("Article deleted successfully.");
    }
}
