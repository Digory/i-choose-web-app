package models;

import db.DBHelper;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Entity
@Table(name="symbols")
public class Symbol {
    private int id;
    private String name;
    private SymbolCategory category;
    private String imageUrl;
    private int popularityRating;
    //private List<Timetable> timetables;
    // private List<SymbolRank> ranksWithinTimetables;
    //private Timetable timetable;
    //private int rankWithinTimetable;

    public Symbol() {
    }

    public Symbol(String name, SymbolCategory category, String imageUrl) {
        this.name = name;
        this.category = category;
        this.imageUrl = imageUrl;
        popularityRating = 0;
        //  this.timetables = new ArrayList<>();
        // ranksWithinTimetables = new ArrayList<>();
        //timetable = null;
        //rankWithinTimetable = 1;
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
    @JoinColumn(name = "category_id", nullable = true)
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

    public void increasePopularity() {
        popularityRating++;
    }

//    @ManyToOne
//    @JoinColumn(name = "timetable_id", nullable = true)
//    public Timetable getTimetable() {
//        return timetable;
//    }

//    public void setTimetable(Timetable timetable) {
//        this.timetable = timetable;
//    }
//    @ManyToMany(fetch = FetchType.LAZY)
//    @JoinTable(
//            name = "symbols_timetables",
//            joinColumns = {@JoinColumn(name = "symbol_id", nullable = false, updatable = false)},
//            inverseJoinColumns = {@JoinColumn(name = "timetable_id", nullable = false, updatable = false)}
//
//    )
//    public List<Timetable> getTimetables() {
//        return timetables;
//    }
//
//    public void setTimetables(List<Timetable> timetables) {
//        this.timetables = timetables;
//    }
//
//    public void addTimetable(Timetable timetable){
//        this.timetables.add(timetable);
//    }

//    @Column(nullable = true)
//    public int getRankWithinTimetable() {
//        return rankWithinTimetable;
//    }

//    public void setRankWithinTimetable(int rankWithinTimetable) {
//        this.rankWithinTimetable = rankWithinTimetable;
//    }
//
//    public void increaseRankWithinTimetable() {
//        rankWithinTimetable++;
//    }
//
//    public void decreaseRankWithinTimetable() {
//        rankWithinTimetable--;
//    }
//    @OneToMany(mappedBy = "symbol")
//    public List<SymbolRank> getRanksWithinTimetables() {
//        return ranksWithinTimetables;
//    }
//
//    public void setRanksWithinTimetables(List<SymbolRank> ranksWithinTimetables) {
//        this.ranksWithinTimetables = ranksWithinTimetables;
//}

}
