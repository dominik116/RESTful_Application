package service.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Table(name = "articles")
public class ArticleDto {

    @Id
    @Column(name = "idArticle", nullable = false, unique = true, insertable = false, updatable = false)
    @GeneratedValue
    private int a_idArticle;

    @Column(name = "articleName")
    private String b_articleName;

    @Column(name = "articleDescription")
    private String c_articleDescription;

    @Column(name = "articlePrice")
    private float d_articlePrice;

    @Column(name = "articleStock")
    private int e_articleStock;
}
