
package Entity;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.br.CNPJ;

/**
 *
 * @author Deyse
 */
@Entity
@Table(name="TB_CLIENT") 
@DiscriminatorValue(value = "C")
@PrimaryKeyJoinColumn(name="ID_USER", referencedColumnName = "ID")
public class Client extends UserSuper implements Serializable {
    
    @ManyToMany
    @JoinTable(name="client_has_Solicitations", joinColumns=
    {@JoinColumn(name="id_Client")}, inverseJoinColumns=
      {@JoinColumn(name="id_Solicitation")})
    private List<Solicitation> solicitations;
    
    @NotNull
    @CNPJ 
    @Column(name = "cnpj")
    private String cnpj;
    
    
    public List<Solicitation> getSolicitations() {
        return solicitations;
    }

    public void setSolicitations(List<Solicitation> solicitations) {
        this.solicitations = solicitations;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
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
        if (!(object instanceof Client)) {
            return false;
        }
        Client other = (Client) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
       StringBuilder sb = new StringBuilder("Entity.Client[");
        sb.append(super.toString());
        sb.append(", ");
        sb.append(this.cnpj);
        sb.append("]");
        return sb.toString();
    }
    
}
