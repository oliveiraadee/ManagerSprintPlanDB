

package Entity.jpqlTest;

import Entity.Enum.StatusSolicitation;
import Entity.Enum.TypeSolicitation;
import Entity.Solicitation;
import Entity.ValidatorTest.GenericTest;
import java.util.List;
import javax.persistence.TypedQuery;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import org.junit.Test;

/**
 *
 * @author Deyse
 */
public class SolicitationJPQLTest extends GenericTest {
    
    
    @Test
    public void allSolicitation(){
        logger.info("Running allSolicitation()");
        TypedQuery<Solicitation> query = em.createQuery(
        "SELECT s FROM Solicitation s",
        Solicitation.class
        );
        
        List<Solicitation> solicitations = query.getResultList();
        
            for (Solicitation solicitation : solicitations){
                System.out.println(solicitation);
            }
            assertEquals(6, solicitations.size());  
        
    }
    
    @Test 
    public void solicitationByName(){
        logger.info("Running solicitationByName()");
        TypedQuery<Solicitation> query = em.createQuery(
        "SELECT s FROM Solicitation s WHERE s.name LIKE :name",
        Solicitation.class
        );
        query.setParameter("name","TESTE");
        
        Solicitation solicitation = query.getSingleResult();
        System.out.println(solicitation);
        assertNotNull(solicitation);
        
    }
    
    @Test
    public void solicitationByType(){
        logger.info("Running solicitationByType");
        TypedQuery<Solicitation> query = em.createQuery(
        "SELECT s FROM Solicitation s WHERE s.type LIKE :type",
        Solicitation.class
        );
        query.setParameter("type", TypeSolicitation.NEW_FEATURE);
        
        List<Solicitation> solicitations = query.getResultList();
        
            for (Solicitation solicitation : solicitations){
               System.out.println(solicitation);
            }
        
        assertEquals(3,solicitations.size());
    }
    
    @Test
    public void solicitationByStatus(){
        logger.info("Running solicitationByStatus");
        TypedQuery<Solicitation> query = em.createQuery(
        "SELECT s FROM Solicitation s WHERE s.status LIKE :status",
         Solicitation.class
        );
        query.setParameter("status", StatusSolicitation.DONE);
        
        List<Solicitation> solicitations = query.getResultList();
        
            for (Solicitation solicitation : solicitations){
                System.out.println(solicitation);
            }
            
            assertEquals(2, solicitations.size());
    }
    
    @Test
    public void solicitationByDescription(){
        logger.info("Running solicitationByDescription");
        TypedQuery<Solicitation> query = em.createQuery(
        "SELECT s FROM Solicitation s WHERE s.description LIKE :description",
         Solicitation.class
        );
        query.setParameter("description", "Bug");
        
        List<Solicitation> solicitations = query.getResultList();
        
            for (Solicitation solicitation : solicitations){
                System.out.println(solicitation);
            }
            
            assertEquals(3, solicitations.size());
    }

 
}
