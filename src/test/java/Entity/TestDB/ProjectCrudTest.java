/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity.TestDB;

import Entity.Project;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import javax.persistence.CacheRetrieveMode;
import javax.persistence.TypedQuery;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import org.junit.Test;

/**
 *
 * @author Deyse
 */
public class ProjectCrudTest extends GenericTest{
    
   @Test
     public void persistirProjeto() {
        logger.info("Executando persistirProjeto()");
        Project projeto = criarProjeto();
        em.persist(projeto);
        em.flush();
        assertNotNull(projeto.getName());
//        assertNotNull(projeto.getResposible());
        assertNotNull(projeto.getBeginDate());
        assertNotNull(projeto.getDeadLine());
    }
     
    
    @Test 
    public void atualizarProjeto(){
        logger.info("Executando atualizarProjeto()");
        String novonome = "danita";
        Long id = 6L;
        Project project = em.find(Project.class, id);
        project.setName(novonome);
        em.flush();
        String jpql = "SELECT c FROM Project c WHERE c.id = ?1";
        TypedQuery<Project> query = em.createQuery(jpql, Project.class);
        query.setHint("javax.persistence.cache.retrieveMode", CacheRetrieveMode.BYPASS);
        query.setParameter(1, id);   
        project = query.getSingleResult();
        assertEquals(novonome, project.getName());
    }
    @Test
    public void atualizarProjectMerge() {
        logger.info("Executando atualizarProjectMerge()");
        String novoname = "Ductum";
        Long id = 6L;
        Project projeto = em.find(Project.class, id);
        projeto.setName(novoname);
        Calendar beginDate = Calendar.getInstance();
        beginDate.set(Calendar.YEAR, 2019);
        beginDate.set(Calendar.MONTH, Calendar.AUGUST);
        beginDate.set(Calendar.DAY_OF_MONTH, 25);
        projeto.setBeginDate(beginDate);
        Calendar deadLine = Calendar.getInstance();
        deadLine.set(Calendar.YEAR, 2020);
        deadLine.set(Calendar.MONTH, Calendar.AUGUST);
        deadLine.set(Calendar.DAY_OF_MONTH, 25);
        projeto.setDeadLine(deadLine);
        em.clear();
        em.merge(projeto);
        Map<String, Object> properties = new HashMap<>();
        properties.put("javax.persistence.cache.retrieveMode", CacheRetrieveMode.BYPASS);
        projeto = em.find(Project.class, id, properties);
        assertEquals(novoname, projeto.getName());        
    }  
   @Test
    public void removerProjeto() {
        logger.info("Executando removerProject()");
        Project projeto = em.find(Project.class, 5L);
        em.remove(projeto);
        em.flush();
    } 
    
    
    private Project criarProjeto() {
        Project projeto = new Project();
        projeto.setName("Ductum");
        Calendar beginDate = Calendar.getInstance();;
        beginDate.set(Calendar.YEAR, 2019);
        beginDate.set(Calendar.MONTH, Calendar.AUGUST);
        beginDate.set(Calendar.DAY_OF_MONTH, 25);
        projeto.setBeginDate(beginDate);
        Calendar deadLine = Calendar.getInstance();
        deadLine.set(Calendar.YEAR, 2020);
        deadLine.set(Calendar.MONTH, Calendar.AUGUST);
        deadLine.set(Calendar.DAY_OF_MONTH, 25);
        projeto.setDeadLine(deadLine);
        return projeto;
    }
}
