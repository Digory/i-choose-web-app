package models;

import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="timetables")
public class Timetable {

    private int id;
    private String name;
    private List<Symbol> symbols;

    public Timetable(){

    }

    public Timetable(String name){
        this.name = name;
        this.symbols = new ArrayList<>();
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

    @Cascade(org.hibernate.annotations.CascadeType.SAVE_UPDATE)
    @ManyToMany
    @JoinTable(
        name = "symbols_timetables",
        joinColumns = {@JoinColumn(name = "timetable_id", nullable = false, updatable = false)},
        inverseJoinColumns = {@JoinColumn(name = "symbol_id", nullable = false, updatable = false)}
    )
    public List<Symbol> getSymbols() {
        return symbols;
    }

    public void setSymbols(List<Symbol> symbols) {
        this.symbols = symbols;
    }

    public void addSymbol(Symbol symbol){
        this.symbols.add(symbol);
    }
}
