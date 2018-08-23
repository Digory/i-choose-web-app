package models;

import javax.persistence.*;

@Entity
@Table(name="symbols")
public class Symbol {
    private int id;
    private String name;
    private SymbolCategory category;
    private String imageUrl;
    private int popularityRating;

    public Symbol(){}

    public Symbol(String name, SymbolCategory category, String imageUrl) {
        this.name = name;
        this.category = category;
        this.imageUrl = imageUrl;
        popularityRating = 0;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Column
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    public SymbolCategory getCategory() {
        return category;
    }

    public void setCategory(SymbolCategory category) {
        this.category = category;
    }

    @Column
    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    @Column
    public int getPopularityRating() {
        return popularityRating;
    }

    public void setPopularityRating(int popularityRating) {
        this.popularityRating = popularityRating;
    }

    public void increasePopularity(){
        popularityRating++;
    }
}
