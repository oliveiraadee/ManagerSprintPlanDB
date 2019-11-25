/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity.TestDB;

import Entity.Comment;
import Entity.Feature;
import static Entity.TestDB.GenericTest.logger;
import java.util.HashMap;
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
public class FeatureCrudTest extends GenericTest {
    
    @Test
    public void persistirFeature() {
        logger.info("Executando persistirFeature()");
        Feature feature = criarFeature();
        em.persist(feature);
        em.flush();
        assertNotNull(feature);
    }
    
    @Test   
    public void atualizarFeature(){
        logger.info("Executando atualizarFeature()");
        Long id = 3L;
        Feature feature = em.find(Feature.class, id);
        feature.setPoints(12);
        feature.setTitle("Atualizado");
        feature.setDescription("Atualizado");
        em.flush();
        String jpql = "SELECT f FROM Feature f WHERE f.ID = ?1";
        TypedQuery<Feature> query = em.createQuery(jpql, Feature.class);
        query.setHint("javax.persistence.cache.retrieveMode", CacheRetrieveMode.BYPASS);
        query.setParameter(1, id);   
        feature = query.getSingleResult();
        assertEquals(12, feature.getPoints());
    }
    
    @Test
    public void atualizarFeatureMerge() {
        logger.info("Executando atualizarFeatureMerge()");
        String novoConteudo = "Merge Feature";
        Long id = 3L;
        Feature feature = em.find(Feature.class, id);
        feature.setTitle(novoConteudo);
        em.clear();
        em.merge(feature);
        Map<String, Object> properties = new HashMap<>();
        properties.put("javax.persistence.cache.retrieveMode", CacheRetrieveMode.BYPASS);
        feature = em.find(Feature.class, id, properties);
        assertEquals(novoConteudo, feature.getTitle());        
    }  
    
    @Test
    public void removerFeature() {
      logger.info("Executando removerFeature()");
      Feature feature= em.find(Feature.class, 1L);
      em.remove(feature);
      em.flush();
    } 
    
    public Feature criarFeature() {
        Feature feature = new Feature();
        feature.setTitle("Serviços EBJ");
        feature.setDescription("Eu, como Deyse, quero que sejam feitos todos os serviços EJB.");
        feature.setPoints(21);
        return feature;
    }
    
}
