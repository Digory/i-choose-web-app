package models;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.ArrayList;

@Entity
@Table(name = "library")
public class Library {
    private ArrayList<Symbol> allSymbols;


}
