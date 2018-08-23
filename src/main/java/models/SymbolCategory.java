package models;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
@Table(name="symbol_categories")
public class SymbolCategory {
    private int id;
    private String descriptor;
    private List<Symbol> symbols;

    public SymbolCategory(){}

    public SymbolCategory(String descriptor) {
        this.descriptor = descriptor;
        symbols = new ArrayList<Symbol>();
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

    @OneToMany(mappedBy = "category")
    public List<Symbol> getSymbols() {
        return symbols;
    }

    public void setSymbols(List<Symbol> symbols) {
        this.symbols = symbols;
    }

    public void addThisSymbol(Symbol symbol){
        symbols.add(symbol);
    }
}
