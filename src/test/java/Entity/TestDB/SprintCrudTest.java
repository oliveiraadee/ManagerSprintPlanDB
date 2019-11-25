/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity.TestDB;

import Entity.Feature;
import Entity.Project;
import Entity.Sprint;
import static Entity.TestDB.GenericTest.logger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.persistence.CacheRetrieveMode;
import javax.persistence.TypedQuery;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import org.junit.Test;

/**
 *
 * @author deecarneiro
 */
public class SprintCrudTest extends GenericTest{
    
    @Test
    public void persistirSprint() {
        logger.info("Executando persistirSprint()");
        Sprint sprint = criarSprint();
        em.persist(sprint);
        em.flush();
        assertNotNull(sprint);
    }
    
    @Test   
    public void atualizarSprint(){
        logger.info("Executando atualizarSprint()");
        Long id = 3L;
        Sprint sprint = em.find(Sprint.class, id);
        sprint.setIdent(12);
        em.flush();
        String jpql = "SELECT s FROM Sprint s WHERE s.ID = ?1";
        TypedQuery<Sprint> query = em.createQuery(jpql, Sprint.class);
        query.setHint("javax.persistence.cache.retrieveMode", CacheRetrieveMode.BYPASS);
        query.setParameter(1, id);   
        sprint = query.getSingleResult();
        assertEquals(12, sprint.getIdent());
    }
    @Test
    public void atualizarSprintMerge() {
        logger.info("Executando atualizarSprintMerge()");
        Long id = 3L;
        Sprint sprint= em.find(Sprint.class, id);
        Project project = em.find(Project.class, 2L);
        sprint.setProject(project);
        em.clear();
        em.merge(sprint);
        Map<String, Object> properties = new HashMap<>();
        properties.put("javax.persistence.cache.retrieveMode", CacheRetrieveMode.BYPASS);
        sprint = em.find(Sprint.class, id, properties);
        assertEquals(project, sprint.getProject());        
    }
    
    @Test
    public void removerFeature() {
      logger.info("Executando removerFeature()");
      Sprint sprint= em.find(Sprint.class, 1L);
      em.remove(sprint);
      em.flush();
    } 
 
    public Sprint criarSprint() {
        Sprint sprint = new Sprint();
        sprint.setIdent(1);
        Feature feature = criarFeature();
        List<Feature> features = new ArrayList<>();
        features.add(feature);
        em.persist(feature);
        sprint.setFeatures(features);
        return sprint;
    }
    
    public Feature criarFeature(){
        Feature feature = new Feature();
        feature.setTitle("Serviços EBJ");
        feature.setDescription("Eu, como Deyse, quero que sejam feitos todos os serviços EJB.");
        feature.setPoints(21);
        return feature;
    }
}
