package models;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="categories")
public class SymbolCategory {
    private int id;
    private String icon;
    private String descriptor;
    private List<Symbol> symbols;

    public SymbolCategory(){}

    public SymbolCategory(String icon, String descriptor) {
        this.icon = icon;
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

    @Column(name="icon")
    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    @Column
    public String getDescriptor() {
        return descriptor;
    }

    public void setDescriptor(String descriptor) {
        this.descriptor = descriptor;
    }

    @OneToMany(mappedBy = "category", fetch = FetchType.EAGER)
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
