package models;

import javax.persistence.*;

@Entity
@Table(name="symbolrank")
public class SymbolRank {
    private int id;
    private Symbol symbol;
    private int rank;
    private Timetable timetable;

    public SymbolRank(){}

    public SymbolRank(Symbol symbol, Timetable timetable) {
        this.symbol = symbol;
        this.timetable = timetable;
        rank = 1000;
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

    @ManyToOne
    @JoinColumn(name = "symbol_id", nullable = false)
    public Symbol getSymbol() {
        return symbol;
    }

    public void setSymbol(Symbol symbol) {
        this.symbol = symbol;
    }

    @ManyToOne
    @JoinColumn(name = "timetable_id", nullable = false)
    public Timetable getTimetable() {
        return timetable;
    }

    public void setTimetable(Timetable timetable) {
        this.timetable = timetable;
    }

    @Column
    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public void increaseRank(){
        rank++;
    }
}
