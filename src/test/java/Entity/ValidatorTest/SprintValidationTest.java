/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity.ValidatorTest;

import Entity.Sprint;
import java.util.Set;
import javax.persistence.TypedQuery;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import org.hamcrest.CoreMatchers;
import static org.hamcrest.CoreMatchers.startsWith;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import org.junit.Test;

/**
 *
 * @author deecarneiro
 */
public class SprintValidationTest extends GenericTest{
    
    @Test(expected = ConstraintViolationException.class)
    public void persistirSprintInvalido() {
        Sprint sprint = null;
        try {
            sprint = new Sprint();
            sprint.setIdent(0);
            em.persist(sprint);
        } catch (ConstraintViolationException ex) {
            Set<ConstraintViolation<?>> constraintViolations = ex.getConstraintViolations();

            for (ConstraintViolation violation : constraintViolations) {
                assertThat(violation.getRootBeanClass() + "." + violation.getPropertyPath() + ": " + violation.getMessage(),
                        CoreMatchers.anyOf(
                                startsWith("class Entity.Sprint.ident: A identificação da sprint deve ser maior que 1.")
                        )
                );
            }

            assertEquals(1, constraintViolations.size());
            throw ex;
        }
    }
    
    @Test(expected = ConstraintViolationException.class)
    public void atualizarSprintInvalido() {
        TypedQuery<Sprint> query = em.createQuery("SELECT s FROM Sprint s WHERE s.ID = ?1", Sprint.class);
        query.setParameter(1, 1L);
        Sprint sprint = query.getSingleResult();
        sprint.setIdent(0);

        try {
            em.flush();
        } catch (ConstraintViolationException ex) {           
            ConstraintViolation violation = ex.getConstraintViolations().iterator().next();
            assertEquals("A identificação da sprint deve ser maior que 1.", violation.getMessage());
            assertEquals(1, ex.getConstraintViolations().size());
            throw ex;
        }
    }
}
