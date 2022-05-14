package service.repository;

import org.springframework.data.repository.CrudRepository;
import service.dto.ArticleDto;

public interface ArticleRepository extends CrudRepository<ArticleDto, Integer> {
}
