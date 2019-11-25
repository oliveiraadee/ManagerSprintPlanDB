package Entity.Validator;

import Entity.Enum.StatusUser;
import Entity.Enum.TypeUser;
import java.util.ArrayList;
import java.util.List;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 *
 * @author MASC
 */
public class ValidatorTypeUser implements ConstraintValidator<ValidateTypeUser, TypeUser> {
    private List<TypeUser> type;
    
    public void initialize(ValidateTypeUser validateTypeUser) {
        this.type = new ArrayList<>();
        this.type.add(TypeUser.ADMIN);
        this.type.add(TypeUser.DEFAULT);
        this.type.add(TypeUser.CLIENT);
        this.type.add(TypeUser.EMPLOYEE);

    }

    @Override
    public boolean isValid(TypeUser valor, ConstraintValidatorContext context) {
        return valor == null ? false : type.contains(valor);
    }

   
}
