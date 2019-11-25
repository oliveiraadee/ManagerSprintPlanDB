
package Entity;

import Entity.Enum.TypeUser;
import Entity.Enum.StatusUser;
import Entity.Validator.ValidateStatusUser;
import Entity.Validator.ValidateTypeUser;
import java.io.Serializable;
import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

/**
 *
 * @author Deyse
 */
@Entity
@Table(name = "TB_USER")
@Inheritance(strategy = InheritanceType.JOINED) //Estratégia de herança.
@DiscriminatorColumn(name = "DISC_USER", //Nome da coluna que vai discriminar subclasses.
        discriminatorType = DiscriminatorType.STRING, length = 1)
@Access(AccessType.FIELD)
public abstract class UserSuper implements Serializable {
    
    @Id
    @Column(name="ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected Long id;
    
    @NotNull
    @NotBlank
    @Size(min = 6, max = 10)
    @Column(name="login")
    private String login;
    
    @NotBlank
    @NotNull
    @Column(name="name")
    private String name;
    
    @NotNull
    @Email
    @Column(name="email")
    private String email;
    
    @ValidateStatusUser
    @Column(name="status")
    private StatusUser status;
    
    @Column(name="type")
    private TypeUser type;

    @NotNull
    @Pattern(regexp = "((?=.*\\p{Digit})(?=.*\\p{Lower})(?=.*\\p{Upper})(?=.*\\p{Punct}).{6,20})",  message = "{Entity.UserSuper.password}")
    @Column(name="password")
    private String password;

    public UserSuper() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public StatusUser getStatus() {
        return status;
    }

    public void setState(StatusUser status) {
        this.status = status;
    }

    public TypeUser getType() {
        return type;
    }

    public void setType(TypeUser type) {
        this.type = type;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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
        if (!(object instanceof UserSuper)) {
            return false;
        }
        UserSuper other = (UserSuper) object;
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
        sb.append(this.login);
        sb.append(", ");
        sb.append(this.email);
        sb.append(",");
        sb.append(this.name);
        sb.append(", ");
        sb.append(this.status);
        sb.append(", ");
        sb.append(this.password);
        sb.append(" ,");
        sb.append(this.type);
        return sb.toString();
    }

}