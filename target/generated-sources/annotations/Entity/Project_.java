package Entity;

import Entity.Client;
import Entity.Employee;
import java.util.Calendar;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-09-28T19:41:55")
@StaticMetamodel(Project.class)
public class Project_ { 

    public static volatile SingularAttribute<Project, Calendar> beginDate;
    public static volatile SingularAttribute<Project, Employee> responsible;
    public static volatile SingularAttribute<Project, String> name;
    public static volatile SingularAttribute<Project, Client> client;
    public static volatile SingularAttribute<Project, Long> id;
    public static volatile ListAttribute<Project, Employee> employees;
    public static volatile SingularAttribute<Project, Calendar> deadLine;

}