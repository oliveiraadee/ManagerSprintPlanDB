
package Entity.ValidatorTest;

import Entity.Enum.StatusSolicitation;
import Entity.Solicitation;
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
public class SolicitationValidationTest extends GenericTest {


    @Test(expected = ConstraintViolationException.class)
    public void persistirSolicitationInvalido() {
        Solicitation solicitacao = null;
        try {
            solicitacao = new Solicitation();
            solicitacao.setName(" ");
            solicitacao.setDescription(" ");
            solicitacao.setStatus(StatusSolicitation.REFUSED);
            
            em.persist(solicitacao);
        } catch (ConstraintViolationException ex) {
            Set<ConstraintViolation<?>> constraintViolations = ex.getConstraintViolations();

            for (ConstraintViolation violation : constraintViolations) {
                assertThat(violation.getRootBeanClass() + "." + violation.getPropertyPath() + ": " + violation.getMessage(),
                        CoreMatchers.anyOf(
                               startsWith("class Entity.Solicitation.name: Não pode estar em branco"),
                               startsWith("class Entity.Solicitation.description: Não pode estar em branco")
                                )
                );
            }

            assertEquals(2, constraintViolations.size());
            assertNull(solicitacao.getId());
            throw ex;
        }
    }

    @Test(expected = ConstraintViolationException.class)
    public void atualizarSolicitationInvalido() {
        TypedQuery<Solicitation> query = em.createQuery("SELECT p FROM Solicitation p WHERE p.id like :ID", Solicitation.class);
        query.setParameter("ID", 4L);
        Solicitation solicitacao = query.getSingleResult();
        solicitacao.setDescription(" ");
            try {
            em.flush();
        } catch (ConstraintViolationException ex) {           
            ConstraintViolation violation = ex.getConstraintViolations().iterator().next();
            assertEquals("Não pode estar em branco", violation.getMessage());
            assertEquals(1, ex.getConstraintViolations().size());
            throw ex;
        }
    }
}
