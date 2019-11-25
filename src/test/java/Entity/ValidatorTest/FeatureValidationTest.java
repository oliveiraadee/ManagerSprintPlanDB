/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity.ValidatorTest;

import Entity.Feature;
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
public class FeatureValidationTest extends GenericTest {
    
    @Test(expected = ConstraintViolationException.class)
    public void persistirClientInvalido() {
        Feature feature = null;
        try {
            feature = new Feature();
            feature.setTitle("");
            feature.setDescription("");
            
            em.persist(feature);
        } catch (ConstraintViolationException ex) {
            Set<ConstraintViolation<?>> constraintViolations = ex.getConstraintViolations();

            for (ConstraintViolation violation : constraintViolations) {
                assertThat(violation.getRootBeanClass() + "." + violation.getPropertyPath() + ": " + violation.getMessage(),
                        CoreMatchers.anyOf(
                                startsWith("class Entity.Feature.title: O titulo não pode ser vazia."),
                                startsWith("class Entity.Feature.description: A descrição não pode ser vazia.")
                        )
                );
            }

            assertEquals(2, constraintViolations.size());
            throw ex;
        }
    }
    
    @Test(expected = ConstraintViolationException.class)
    public void atualizarFeatureInvalido() {
        TypedQuery<Feature> query = em.createQuery("SELECT f FROM Feature f WHERE f.ID = ?1", Feature.class);
        query.setParameter(1, 2L);
        Feature feature = query.getSingleResult();
        feature.setTitle("");

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
