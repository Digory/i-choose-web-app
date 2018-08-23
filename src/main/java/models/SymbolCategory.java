package models;

import javax.persistence.*;

@Entity
@Table(name="symbol_categories")
public class SymbolCategory {
    private int id;
    private String category;

    public SymbolCategory(String category) {
        this.category = category;
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
    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
