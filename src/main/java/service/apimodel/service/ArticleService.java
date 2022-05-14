package service.apimodel.service;

import service.dto.ArticleDto;

import java.util.List;

/**
 * The interface Article service.
 */
public interface ArticleService {

    /**
     * Insert article to DB.
     *
     * @param articleDto the article dto
     */
    boolean insertArticle(ArticleDto articleDto) throws Exception;

    /**
     * List all the articles from DB.
     */
    List<ArticleDto> listAllArticles();

    /**
     * List one article from DB.
     *
     * @param idArticle the article id
     * @return the article
     */
    ArticleDto listArticle(int idArticle) throws Exception;

    /**
     * Update article from DB.
     *
     * @param article the article's id to update
     */
    void updateArticle(int idArticle, ArticleDto article);

    /**
     * Delete article from DB.
     *
     * @param idArticle the article's id to delete
     */
    void deleteArticle(int idArticle);
}
