package Entity;

import Entity.Enum.StatusUser;
import Entity.Enum.TypeUser;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-09-28T20:11:35")
@StaticMetamodel(UserSuper.class)
public abstract class UserSuper_ { 

    public static volatile SingularAttribute<UserSuper, String> password;
    public static volatile SingularAttribute<UserSuper, String> name;
    public static volatile SingularAttribute<UserSuper, Long> id;
    public static volatile SingularAttribute<UserSuper, String> login;
    public static volatile SingularAttribute<UserSuper, TypeUser> type;
    public static volatile SingularAttribute<UserSuper, String> email;
    public static volatile SingularAttribute<UserSuper, StatusUser> status;

}