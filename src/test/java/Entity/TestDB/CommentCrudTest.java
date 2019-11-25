
package Entity.TestDB;

import Entity.Comment;
import Entity.Employee;
import Entity.Enum.StatusUser;
import Entity.Enum.TypeUser;
import java.util.HashMap;
import java.util.Map;
import javax.persistence.CacheRetrieveMode;
import javax.persistence.TypedQuery;
import static org.eclipse.persistence.config.ResultType.Map;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import org.junit.Test;

/**
 *
 * @author deecarneiro
 */
public class CommentCrudTest extends GenericTest {
    
    @Test
    public void persistirComment() {
        logger.info("Executando persistirComment()");
        Comment comment = criarComment();
        em.persist(comment);
        em.flush();
        assertNotNull(comment);
    }
   
    @Test   
    public void atualizarComment(){
        logger.info("Executando atualizarComment()");
        String novoConteudo = "Comentario Editado";
        Long id = 2L;
        Comment comment = em.find(Comment.class, id);
        comment.setContent(novoConteudo);
        em.flush();
        String jpql = "SELECT c FROM Comment c WHERE c.ID = ?1";
        TypedQuery<Comment> query = em.createQuery(jpql, Comment.class);
        query.setHint("javax.persistence.cache.retrieveMode", CacheRetrieveMode.BYPASS);
        query.setParameter(1, id);   
        comment = query.getSingleResult();
        assertEquals(novoConteudo, comment.getContent());
    }
    
    @Test
    public void atualizarCommentMerge() {
        logger.info("Executando atualizarCommentMerge()");
        String novoConteudo = "Merge Comentario";
        
        Long id = 3L;
        Comment comment = em.find(Comment.class, id);
        comment.setContent(novoConteudo);
        em.clear();
        em.merge(comment);
        Map<String, Object> properties = new HashMap<>();
        properties.put("javax.persistence.cache.retrieveMode", CacheRetrieveMode.BYPASS);
        comment = em.find(Comment.class, id, properties);
        assertEquals(novoConteudo, comment.getContent());        
    }  
    
   

  
   
    public Comment criarComment() {
        Comment comment = new Comment();
        Employee employee = criarEmployee();
        em.persist(employee);
        em.flush();
        comment.setContent("Finalizando as solicitações da Sprint");
        comment.setUser(employee);
        return comment;
    }
    
    public Employee criarEmployee() {
        Employee employee = new Employee();
        employee.setId(18L);
        employee.setCpf("706.751.384-77");
        employee.setLogin("Carneiro");
        employee.setEmail("email@email.com");
        employee.setName("Deyse Carneiro");
        employee.setState(StatusUser.ACTIVE);
        employee.setType(TypeUser.ADMIN);
        employee.setPassword("Pass123*");
        return employee;
    }
}
