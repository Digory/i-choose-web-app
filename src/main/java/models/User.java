package models;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="users")
public class User {
    private int id;
    private String name;
    private List<Timetable> timetables;
    private String childName;
    private String timetableUnlockCode;

    public User(){}

    public User(String name) {
        this.name = name;
        timetables = new ArrayList<>();
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

    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
    public List<Timetable> getTimetables() {
        return timetables;
    }

    public void setTimetables(List<Timetable> timetables) {
        this.timetables = timetables;
    }

    public void addTimetable(Timetable timetable){
        this.timetables.add(timetable);
    }

    @Column
    public String getChildName() {
        return childName;
    }

    public void setChildName(String childName) {
        this.childName = childName;
    }

    @Column
    public String getTimetableUnlockCode() {
        return timetableUnlockCode;
    }

    public void setTimetableUnlockCode(String timetableUnlockCode) {
        this.timetableUnlockCode = timetableUnlockCode;
    }
}
