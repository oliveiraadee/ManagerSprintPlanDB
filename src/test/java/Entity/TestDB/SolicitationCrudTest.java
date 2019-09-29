/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity.TestDB;

import Entity.Enum.StatusSolicitation;
import Entity.Enum.TypeSolicitation;
import Entity.Solicitation;
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
 * @author Deyse
 */
public class SolicitationCrudTest extends GenericTest {
    
    @Test
    public void persistirSolicitation() {
        logger.info("Executando persistirSolicitation()");
        Solicitation solicitation = criarSolicitation();
        em.persist(solicitation);
        em.flush();
        assertNotNull(solicitation.getName());
        assertNotNull(solicitation.getType());
    }
   @Test
    public void removerSolicitacao() {
        logger.info("Executando removerSolicitacao()");
        Solicitation solicitacao = em.find(Solicitation.class, 2L);
        em.remove(solicitacao);
        em.flush();
    } 
    
    @Test
     public void atualizarSolicitacao(){
        logger.info("Executando atualizarSolicitacao()");
        String novonome = "Atualizado";
        Long id = 6L;
        Solicitation solicitacao = em.find(Solicitation.class, id);
        solicitacao.setName(novonome);
        em.flush();
        String jpql = "SELECT c FROM Solicitation c WHERE c.id = ?1";
        TypedQuery<Solicitation> query = em.createQuery(jpql, Solicitation.class);
        query.setHint("javax.persistence.cache.retrieveMode", CacheRetrieveMode.BYPASS);
        query.setParameter(1, id);   
        solicitacao = query.getSingleResult();
        assertEquals(novonome, solicitacao.getName());
    }
    
     @Test
    public void atualizarSolicitationMerge() {
        logger.info("Executando atualizarSolicitationMerge()");
        String novoname = "atualizado";
        Long id = 1L;
        Solicitation solicitation = em.find(Solicitation.class, id);
        solicitation.setName(novoname);
        em.clear();
        em.merge(solicitation);
        Map<String, Object> properties = new HashMap<>();
        properties.put("javax.persistence.cache.retrieveMode", CacheRetrieveMode.BYPASS);
        solicitation = em.find(Solicitation.class, id, properties);
        assertEquals(novoname, solicitation.getName());        
    }  
   
    private Solicitation criarSolicitation() {
        Solicitation solicitation = new Solicitation();
        solicitation.setName("Criar Solicitatione");
        solicitation.setType(TypeSolicitation.BUG);
        solicitation.setStatus(StatusSolicitation.REFUSED);
        solicitation.setDescription("Teste de descrição");
        return solicitation;
    }
}
