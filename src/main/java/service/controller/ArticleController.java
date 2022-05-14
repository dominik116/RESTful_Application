package service.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import service.apimodel.service.ArticleService;
import service.dto.ArticleDto;

import java.util.List;

@RestController
@Slf4j
public class ArticleController {

    @Autowired
    public ArticleService articleService;

    private static final String CREATE_ARTICLE_RESPONSE_MESSAGE = "Article has been created successfully.";

    private static final String DUPLICATE_MESSAGE = "Article already exists in the table.";

    private static final String UPDATE_ARTICLE_RESPONSE_MESSAGE = "Article has been updated successfully.";

    private static final String DELETE_ARTICLE_RESPONSE_MESSAGE = "Article has been deleted successfully.";

    @RequestMapping(method = RequestMethod.PUT, path = "/articles/createArticle", consumes = {"application/json"})
    public ResponseEntity<String> createArticle(@RequestBody final ArticleDto articleDto) {
        try {
            if (this.articleService.insertArticle(articleDto)) {
                return new ResponseEntity<>(CREATE_ARTICLE_RESPONSE_MESSAGE, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(DUPLICATE_MESSAGE, HttpStatus.OK);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(method = RequestMethod.GET, path = "/articles/listAllArticles", produces = "application/json")
    public ResponseEntity<List<ArticleDto>> listAllArticles() {
        final List<ArticleDto> listArticles = articleService.listAllArticles();
        return new ResponseEntity<>(listArticles, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, path = "/articles/listArticle/{idArticle}", produces = "application/json")
    public ResponseEntity<ArticleDto> listArticle(@PathVariable("idArticle") final int idArticle) throws Exception {
        final ArticleDto article = articleService.listArticle(idArticle);
        return new ResponseEntity<>(article, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST, path = "/articles/updateArticle/{idArticle}", consumes = "application/json", produces = "application/json")
    public ResponseEntity<String> updateArticles(@PathVariable("idArticle") final int idArticle, @RequestBody final ArticleDto articleDto) {
        articleService.updateArticle(idArticle, articleDto);
        return new ResponseEntity<>(UPDATE_ARTICLE_RESPONSE_MESSAGE, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.DELETE, path = "/articles/deleteArticle/{idArticle}", produces = "application/json")
    public ResponseEntity<String> deleteArticles(@PathVariable("idArticle") final int idArticle) {
        articleService.deleteArticle(idArticle);
        return new ResponseEntity<>(DELETE_ARTICLE_RESPONSE_MESSAGE, HttpStatus.OK);
    }
}