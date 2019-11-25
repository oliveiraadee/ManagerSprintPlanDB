/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity.ValidatorTest;

import Entity.Meeting;
import java.util.Calendar;
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
public class MeetingValidationTest extends GenericTest{
    
    
    @Test(expected = ConstraintViolationException.class)
    public void persistirMeetingInvalido() {
        Meeting meeting = null;
        try {
           
            meeting = new Meeting();
            meeting.setTitle("");
            meeting.setDescription("");
            em.persist(meeting);
        } catch (ConstraintViolationException ex) {
            Set<ConstraintViolation<?>> constraintViolations = ex.getConstraintViolations();

            for (ConstraintViolation violation : constraintViolations) {
                assertThat(violation.getRootBeanClass() + "." + violation.getPropertyPath() + ": " + violation.getMessage(),
                        CoreMatchers.anyOf(
                                startsWith("class Entity.Meeting.title: O titulo não pode ser vazia."),
                                startsWith("class Entity.Meeting.description: A descrição não pode ser vazia.")
                        )
                );
            }

            assertEquals(2, constraintViolations.size());
            throw ex;
        }
    }
    
    @Test(expected = ConstraintViolationException.class)
    public void atualizarMeetingInvalido() {
        TypedQuery<Meeting> query = em.createQuery("SELECT m FROM Meeting m WHERE m.ID = ?1", Meeting.class);
        query.setParameter(1, 1L);
        Meeting meeting = query.getSingleResult();
        meeting.setTitle("");

        try {
            em.flush();
        } catch (ConstraintViolationException ex) {           
            ConstraintViolation violation = ex.getConstraintViolations().iterator().next();
            assertEquals("O titulo não pode ser vazia.", violation.getMessage());
            assertEquals(1, ex.getConstraintViolations().size());
            throw ex;
        }
    }
    
}
