package nl.rabobank.banking_application.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="category")
public class Category{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Column(name = "type")
    private String type;
    @Column(name = "generalcategoryid")
    private long generalCategoryId;

    public Category() {

    }

    public Category(long id, String type, long generalCategoryId) {
        this.id = id;
        this.type = type;
        this.generalCategoryId = generalCategoryId;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public long getGeneralCategoryId() {
        return generalCategoryId;
    }

    public void setGeneralCategoryId(long generalCategoryId) {
        this.generalCategoryId = generalCategoryId;
    }
}
