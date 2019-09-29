
package Entity.ValidatorTest;

import Entity.Project;
import java.util.Calendar;
import java.util.Set;
import javax.persistence.TypedQuery;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import org.hamcrest.CoreMatchers;
import static org.hamcrest.CoreMatchers.startsWith;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;
import org.junit.Test;

/**
 *
 * @author Deyse
 */
public class ProjectValidationTest extends GenericTest {


    @Test(expected = ConstraintViolationException.class)
    public void persistirProjectInvalido() {
        Project projeto = null;
        try {
            projeto = new Project();
            projeto.setName("Nome");
            Calendar beginDate = Calendar.getInstance();;
            beginDate.set(Calendar.YEAR, 2019);
            beginDate.set(Calendar.MONTH, Calendar.AUGUST);
            beginDate.set(Calendar.DAY_OF_MONTH, 25);
            projeto.setBeginDate(beginDate);
            Calendar deadLine = Calendar.getInstance();
            deadLine.set(Calendar.YEAR, 20);
            deadLine.set(Calendar.MONTH, Calendar.AUGUST);
            deadLine.set(Calendar.DAY_OF_MONTH, 25);
            projeto.setDeadLine(deadLine);
            projeto.setDeadLine(deadLine);
            em.persist(projeto);
        } catch (ConstraintViolationException ex) {
            Set<ConstraintViolation<?>> constraintViolations = ex.getConstraintViolations();

            for (ConstraintViolation violation : constraintViolations) {
                assertThat(violation.getRootBeanClass() + "." + violation.getPropertyPath() + ": " + violation.getMessage(),
                        CoreMatchers.anyOf(
                               startsWith("class Entity.Project.deadLine: deve estar no futuro")
                                )
                );
            }

            assertEquals(1, constraintViolations.size());
            assertNull(projeto.getId());
            throw ex;
        }
    }

    @Test(expected = ConstraintViolationException.class)
    public void atualizarProjectInvalido() {
        TypedQuery<Project> query = em.createQuery("SELECT p FROM Project p WHERE p.id like :ID", Project.class);
        query.setParameter("ID", 4L);
        Project projeto = query.getSingleResult();
        Calendar deadLine = Calendar.getInstance();
            deadLine.set(Calendar.YEAR, 19);
            deadLine.set(Calendar.MONTH, Calendar.AUGUST);
            deadLine.set(Calendar.DAY_OF_MONTH, 25);
            projeto.setDeadLine(deadLine);
            try {
            em.flush();
        } catch (ConstraintViolationException ex) {           
            ConstraintViolation violation = ex.getConstraintViolations().iterator().next();
            assertEquals("deve estar no futuro", violation.getMessage());
            assertEquals(1, ex.getConstraintViolations().size());
            throw ex;
        }
    }
}
