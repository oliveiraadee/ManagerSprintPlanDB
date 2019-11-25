package Entity.ValidatorTest;

import Entity.Comment;
import java.util.Set;
import javax.persistence.TypedQuery;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import org.hamcrest.CoreMatchers;
import static org.hamcrest.CoreMatchers.startsWith;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import org.junit.Test;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author deecarneiro
 */
public class CommentValidationTest extends GenericTest{
    
    @Test(expected = ConstraintViolationException.class)
    public void persistirCommentInvalido() {
        Comment comment = null;
        try {
            comment = new Comment();
            comment.setContent("");
            em.persist(comment);
        } catch (ConstraintViolationException ex) {
            Set<ConstraintViolation<?>> constraintViolations = ex.getConstraintViolations();

            for (ConstraintViolation violation : constraintViolations) {
                assertThat(violation.getRootBeanClass() + "." + violation.getPropertyPath() + ": " + violation.getMessage(),
                        CoreMatchers.anyOf(
                                startsWith("class Entity.Comment.content: O conteúdo do comentário não pode estar vazio")
                                )
                );
            }

            assertEquals(1, constraintViolations.size());
            throw ex;
        }
    }
    
    @Test(expected = ConstraintViolationException.class)
    public void atualizarCommentInvalido() {
        TypedQuery<Comment> query = em.createQuery("SELECT c FROM Comment c WHERE c.ID = ?1", Comment.class);
        query.setParameter(1, 2L);
        Comment comment= query.getSingleResult();
        comment.setContent("");

        try {
            em.flush();
        } catch (ConstraintViolationException ex) {           
            ConstraintViolation violation = ex.getConstraintViolations().iterator().next();
            assertEquals("O conteúdo do comentário não pode estar vazio", violation.getMessage());
            assertEquals(1, ex.getConstraintViolations().size());
            throw ex;
        }
    }
    
}
