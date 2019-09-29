package Entity.ValidatorTest;


import Entity.Employee;
import Entity.Enum.StatusUser;
import Entity.Enum.TypeUser;
import java.util.Set;
import javax.persistence.TypedQuery;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import org.hamcrest.CoreMatchers;
import static org.hamcrest.CoreMatchers.startsWith;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;

/**
 *
 * @author Deyse
 */
public class EmployeeValidationTest extends GenericTest {

    @Test(expected = ConstraintViolationException.class)
    public void persistirEmployeeInvalido() {
        Employee cliente = null;
        try {
            cliente = new Employee();
            cliente.setCpf("258.171.482-34"); //CPF inválido
            cliente.setEmail("email_invalido@"); //E-mail inválido
            cliente.setLogin("fulano_silva");
            cliente.setPassword("papa");
            cliente.setName("joaninha");
            cliente.setState(StatusUser.ACTIVE);
            cliente.setType(TypeUser.ADMIN);
            em.persist(cliente);
        } catch (ConstraintViolationException ex) {
            Set<ConstraintViolation<?>> constraintViolations = ex.getConstraintViolations();

            for (ConstraintViolation violation : constraintViolations) {
                assertThat(violation.getRootBeanClass() + "." + violation.getPropertyPath() + ": " + violation.getMessage(),
                        CoreMatchers.anyOf(
                                startsWith("class Entity.Employee.cpf: CPF inválido"),
                                startsWith("class Entity.Employee.email: Não é um endereço de e-mail"),
                                startsWith("class Entity.Employee.login: tamanho deve estar entre 6 e 10"),
                                startsWith("class Entity.Employee.password: A senha deve possuir pelo menos um caractere de: pontuação, maiúscula, minúscula e número.")
                                )
                );
            }

            assertEquals(4, constraintViolations.size());
            assertNull(null);
            throw ex;
        }
    }

    @Test(expected = ConstraintViolationException.class)
    public void atualizarEmployeeInvalido() {
        TypedQuery<Employee> query = em.createQuery("SELECT v FROM Employee v WHERE v.cpf like :cpf", Employee.class);
        query.setParameter("cpf", "845.564.180-03");
        Employee cliente = query.getSingleResult();
        cliente.setEmail("email@");

        try {
            em.flush();
        } catch (ConstraintViolationException ex) {           
            ConstraintViolation violation = ex.getConstraintViolations().iterator().next();
            assertEquals("Não é um endereço de e-mail", violation.getMessage());
            assertEquals(1, ex.getConstraintViolations().size());
            throw ex;
        }
    }
}
