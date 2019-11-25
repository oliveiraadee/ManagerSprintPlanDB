/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity.TestDB;

import Entity.Meeting;
import Entity.Meeting;
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
public class MeetingCrudTest extends GenericTest {
    
    @Test
    public void persistirMeeting() {
        logger.info("Executando persistirMeeting()");
        Meeting meeting = criarMeeting();
        em.persist(meeting);
        em.flush();
        assertNotNull(meeting);
    }
    
    @Test   
    public void atualizarMeeting(){
        logger.info("Executando atualizarMeeting()");
        Long id = 3L;
        Meeting meeting = em.find(Meeting.class, id);
        meeting.setTitle("Atualizado");
        meeting.setDescription("Atualizado");
        em.flush();
        String jpql = "SELECT m FROM Meeting m WHERE m.ID = ?1";
        TypedQuery<Meeting> query = em.createQuery(jpql, Meeting.class);
        query.setHint("javax.persistence.cache.retrieveMode", CacheRetrieveMode.BYPASS);
        query.setParameter(1, id);   
        meeting = query.getSingleResult();
        assertEquals("Atualizado", meeting.getTitle());
    }
    
    @Test
    public void atualizarMeetingMerge() {
        logger.info("Executando atualizarMeetingMerge()");
        String novoConteudo = "Merge Meeting";
        Long id = 3L;
        Meeting meeting = em.find(Meeting.class, id);
        meeting.setTitle(novoConteudo);
        em.clear();
        em.merge(meeting);
        Map<String, Object> properties = new HashMap<>();
        properties.put("javax.persistence.cache.retrieveMode", CacheRetrieveMode.BYPASS);
        meeting = em.find(Meeting.class, id, properties);
        assertEquals(novoConteudo, meeting.getTitle());        
    }  
    @Test
    public void removerMeeting() {
      logger.info("Executando removerMeeting()");
      Meeting meeting= em.find(Meeting.class, 1L);
      em.remove(meeting);
      em.flush();
    } 
    public Meeting criarMeeting() {
        Meeting meeting = new Meeting();
        meeting.setTitle("Inception");
        meeting.setDescription("Concepção de Projetos");
        return meeting;
    }
}
