package models;

import javax.persistence.*;

@Entity
@Table(name="symbol_categories")
public class SymbolCategory {
    private int id;
    private String descriptor;

    public SymbolCategory(String descriptor) {
        this.descriptor = descriptor;
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
    public String getDescriptor() {
        return descriptor;
    }

    public void setDescriptor(String descriptor) {
        this.descriptor = descriptor;
    }
}
