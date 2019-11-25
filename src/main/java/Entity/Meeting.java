/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.eclipse.persistence.jpa.jpql.parser.DateTime;
import org.hibernate.validator.constraints.NotBlank;

/**
 *
 * @author deecarneiro
 */
@Entity
@Table(name="TB_MEETING")
public class Meeting implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ID;
    
    @NotBlank( message = "{Entity.Meeting.title}")
    @Column(name="title")
    private String title;
    
    @NotBlank( message = "{Entity.Meeting.description}")
    @Column(name="description")
    private String description;
    
    @Temporal(TemporalType.DATE)
    @Column(name = "dateMeeting")
    private Calendar dateMeeting;
    
    @Temporal(TemporalType.TIME)
    @Column(name = "timeMeeting")
    private Date timeMeeting;
    
    @ManyToOne
    @JoinColumn(name = "id_Sprint")
    private Sprint sprint;

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

    public Sprint getSprint() {
        return sprint;
    }

    public void setSprint(Sprint sprint) {
        this.sprint = sprint;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 83 * hash + Objects.hashCode(this.ID);
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
        final Meeting other = (Meeting) obj;
        if (!Objects.equals(this.ID, other.ID)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Meeting{" + "ID=" + ID + ", title=" + title + ", description=" + description + ", sprint=" + sprint + '}';
    }
    
    
    
    
}
