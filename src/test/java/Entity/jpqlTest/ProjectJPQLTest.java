
package Entity.jpqlTest;

import Entity.Client;
import Entity.Project;
import Entity.ValidatorTest.GenericTest;
import java.util.Calendar;
import java.util.List;
import javax.persistence.TypedQuery;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import org.junit.Test;

/**
 *
 * @author Deyse
 */
public class ProjectJPQLTest extends GenericTest{
    
    @Test
    public void allProjects() {
        logger.info("Running allProjects()");
        TypedQuery<Project> query = em.createQuery(
        "SELECT p FROM Project p",
        Project.class
        );
        
        List<Project> projects = query.getResultList();
        
        for (Project project : projects){
            System.out.println(project);
        }
        assertEquals(7, projects.size());  
    }
    
    @Test
    public void projectByName(){
        logger.info("Running projectByName()");
        TypedQuery<Project> query = em.createQuery(
        "SELECT p FROM Project p WHERE p.name LIKE :name",
        Project.class
        );
        query.setParameter("name", "SYS HOME CARE");
        Project project = query.getSingleResult();
        System.out.println(project);
        assertNotNull(project);
    }
    
    @Test
    public void projectByDeadLinePeriod(){
        logger.info("Running projectByDeadLinePeriod()");
        TypedQuery<Project> query = em.createQuery(
        "SELECT p FROM Project p WHERE p.deadLine BETWEEN ?1 AND ?2", 
        Project.class
        );
        query.setParameter(1, getData(21, Calendar.NOVEMBER,2019));
        query.setParameter(2, getData(21, Calendar.NOVEMBER,2019));
        List<Project> projects = query.getResultList();
        
         for (Project project : projects){
            System.out.println(project);
        }
         
        assertEquals(7, projects.size());
    }
    
    @Test
    public void projectByBeginDatePeriod(){
        logger.info("Running projectByBeginDatePeriod()");
        TypedQuery<Project> query = em.createQuery(
        "SELECT p FROM Project p WHERE p.beginDate BETWEEN ?1 AND ?2", 
        Project.class
        );
        query.setParameter(1, getData(21, Calendar.MAY,2019));
        query.setParameter(2, getData(21, Calendar.NOVEMBER,2020));
        List<Project> projects = query.getResultList();
        
         for (Project project : projects){
            System.out.println(project);
        }
         
        assertEquals(7, projects.size());
    }
    
    @Test
    public void projectByClient(){
        logger.info("Running projectByClient()");
        TypedQuery<Project> query = em.createQuery(
        "SELECT p FROM Project p WHERE p.client.id LIKE ?1",
        Project.class
        );

        query.setParameter(1, 1);

        List<Project> projects = query.getResultList();

            for (Project project : projects){
                System.out.println(project);
            }

            assertEquals(3, projects.size());
    }
    
    @Test
    public void projectByResponsible(){
        logger.info("Running projectByResponsible()");
        TypedQuery<Project> query = em.createQuery(
        "SELECT p FROM Project p WHERE p.responsible.id LIKE ?1",
        Project.class
        );

        query.setParameter(1, 10);

        List<Project> projects = query.getResultList();

            for (Project project : projects){
                System.out.println(project);
            }

            assertEquals(1, projects.size());
    }
    
    
}
