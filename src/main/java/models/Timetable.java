package models;

import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Entity
@Table(name="timetables")
public class Timetable {

    private int id;
    private String name;
    private List<Symbol> symbols;
    private User user;

    public Timetable(){
    }

    public Timetable(String name){
        this.name = name;
        this.symbols = new ArrayList<>();
      //  this.user = user;
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

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
        name = "symbols_timetables",
        joinColumns = {@JoinColumn(name = "timetable_id", nullable = false, updatable = false)},
        inverseJoinColumns = {@JoinColumn(name = "symbol_id", nullable = false, updatable = false)}
    )
    @OrderColumn(name="order_in_timetable")
//    @OneToMany(mappedBy = "timetable")
    public List<Symbol> getSymbols() {
        return symbols;
    }

    public void setSymbols(List<Symbol> symbols) {
        this.symbols = symbols;
    }

    public void addSymbol(Symbol symbol){
        this.symbols.add(symbol);
    }

    public void removeSymbolAtPosition(int position){
        this.symbols.remove(position);
    }

    public void moveSymbolAtThisPositionUpByOne(int position){
        if(position == 0){
            return;
        }
        Symbol symbol = symbols.get(position);
        Collections.swap(symbols, position, position-1);
    }

    public void moveSymbolAtThisPositionDownByOne(int position){
        if(position == symbols.size()-1){
            return;
        }
        Symbol symbol = symbols.get(position);
        Collections.swap(symbols, position, position+1);
    }

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = true)
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
