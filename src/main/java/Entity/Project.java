
package Entity;

import java.io.Serializable;
import java.util.Calendar;
import java.util.List;
import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Deyse
 */
@Entity
@Access(AccessType.FIELD)
@Table(name = "TB_PROJECT")

public class Project implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name="name")
    private String name;
    
    @Future
    @Temporal(TemporalType.DATE)
    @Column(name="deadLine")
    private Calendar deadLine;
    
    @Temporal(TemporalType.DATE)
    @Column(name="beginDate")
    private Calendar beginDate;
    
    @ManyToOne
    @JoinColumn(name = "id_Client")
    private Client client;
    
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "responsible", referencedColumnName = "id_UserSuper")
    private Employee responsible;
    
     
    @ManyToMany
    @JoinTable(name="project_Has_Employee", joinColumns=
    {@JoinColumn(name="id_Project")}, inverseJoinColumns=
      {@JoinColumn(name="id_Employee")})
   private List<Employee> employees;
    
    public Project() {
    }
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Employee getResponsible() {
        return responsible;
    }

    public void setResponsible(Employee responsible) {
        this.responsible = responsible;
    }

    public Calendar getDeadLine() {
        return deadLine;
    }

    public void setDeadLine(Calendar deadLine) {
        this.deadLine = deadLine;
    }

    public Calendar getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(Calendar beginDate) {
        this.beginDate = beginDate;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }


    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
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
        if (!(object instanceof Project)) {
            return false;
        }
        Project other = (Project) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
     public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.id);
        sb.append(", ");
        sb.append(this.name);
        sb.append(", ");
        sb.append(this.deadLine);
        sb.append(",");
        sb.append(this.beginDate);
        return sb.toString();
    }

    
}
