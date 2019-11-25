package Entity;

import java.io.Serializable;
import java.util.Calendar;
import java.util.List;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.NotBlank;

/**
 *
 * @author deecarneiro
 */

@Entity
@Table(name="TB_FEATURE") 
public class Feature implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ID;
    
    @NotBlank(message= "{Entity.Feature.title}")
    @Column(name = "title")
    private String title;
    
    @NotBlank(message = "{Entity.Feature.description}")
    @Column(name = "description")
    private String description;
    
    @NotNull(message = "{Entity.Feature.points}")
    @Column(name = "points")
    private int points;
    
    @ManyToOne
    @JoinColumn(name = "reporter")
    private Employee reporter;
    
    @OneToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "responsible", referencedColumnName = "ID")
    private Employee responsible;
    
    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "feature_id")
    private List<Comment> comments;
    
    @Temporal(TemporalType.DATE)
    @Column(name="created")
    private Calendar created;
     
    public Long getID() {
        return ID;
    }

    public void setID(Long ID) {
        this.ID = ID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public Employee getReporter() {
        return reporter;
    }

    public void setReporter(Employee reporter) {
        this.reporter = reporter;
    }

    public Employee getResponsible() {
        return responsible;
    }

    public void setResponsible(Employee responsible) {
        this.responsible = responsible;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public Calendar getCreated() {
        return created;
    }

    public void setCreated(Calendar created) {
        this.created = created;
    }
    

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 37 * hash + Objects.hashCode(this.ID);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Feature other = (Feature) obj;
        if (this.points != other.points) {
            return false;
        }
        if (!Objects.equals(this.title, other.title)) {
            return false;
        }
        if (!Objects.equals(this.description, other.description)) {
            return false;
        }
        if (!Objects.equals(this.ID, other.ID)) {
            return false;
        }
        if (!Objects.equals(this.reporter, other.reporter)) {
            return false;
        }
        if (!Objects.equals(this.responsible, other.responsible)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Feature{" + "ID=" + ID + ", title=" + title + ", description=" + description + ", points=" + points + ", reporter=" + reporter + ", responsible=" + responsible + '}';
    }
    
    
    
}
