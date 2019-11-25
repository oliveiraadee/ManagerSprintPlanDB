
package Entity;

import Entity.Enum.StatusSolicitation;
import Entity.Enum.TypeSolicitation;
import Entity.Validator.ValidateStatusSolicitation;
import Entity.Validator.ValidateTypeSolicitation;
import java.io.Serializable;
import java.util.Calendar;
import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.NotBlank;

/**
 *
 * @author Deyse
 */
@Entity
@Access(AccessType.FIELD)
@Table(name="TB_SOLICITATION") 
public class Solicitation implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotBlank
    @NotNull
    @Column(name="name", nullable = false)
    private String name;
    
    @ValidateTypeSolicitation
    @Column(name="type", nullable = false)
    private TypeSolicitation type; 
    
    @NotBlank
    @NotNull
    @Column(name="description")
    private String description; 
    
    @NotNull
    @ValidateStatusSolicitation
    @Column(name="status")
    private StatusSolicitation status;
    
    @Temporal(TemporalType.DATE)
    @Column(name="created")
    private Calendar created;
    
    @Temporal(TemporalType.DATE)
    @Column(name="updated")
    private Calendar updated;
    
    @ManyToOne
    @JoinColumn(name = "id_Project")
      private Project project;

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public TypeSolicitation getType() {
        return type;
    }

    public void setType(TypeSolicitation type) {
        this.type = type;
    }

    public StatusSolicitation getStatus() {
        return status;
    }

    public void setStatus(StatusSolicitation status) {
        this.status = status;
    }

    public Calendar getCreated() {
        return created;
    }

    public void setCreated(Calendar created) {
        this.created = created;
    }

    public Calendar getUpdated() {
        return updated;
    }

    public void setUpdated(Calendar updated) {
        this.updated = updated;
    }
    
    

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Solicitation)) {
            return false;
        }
        Solicitation other = (Solicitation) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entity.Solicitation[ id=" + id + " ]";
    }
    
}
