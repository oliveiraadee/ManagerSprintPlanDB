package Entity;

import Entity.Enum.StatusSolicitation;
import Entity.Enum.TypeSolicitation;
import Entity.Project;
import java.util.Calendar;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-09-28T19:41:55")
@StaticMetamodel(Solicitation.class)
public class Solicitation_ { 

    public static volatile SingularAttribute<Solicitation, Calendar> created;
    public static volatile SingularAttribute<Solicitation, String> name;
    public static volatile SingularAttribute<Solicitation, String> description;
    public static volatile SingularAttribute<Solicitation, Project> project;
    public static volatile SingularAttribute<Solicitation, Long> id;
    public static volatile SingularAttribute<Solicitation, TypeSolicitation> type;
    public static volatile SingularAttribute<Solicitation, Calendar> updated;
    public static volatile SingularAttribute<Solicitation, StatusSolicitation> status;

}