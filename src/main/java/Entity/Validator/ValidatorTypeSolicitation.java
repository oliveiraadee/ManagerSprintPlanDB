package Entity.Validator;

import Entity.Enum.TypeSolicitation;
import java.util.ArrayList;
import java.util.List;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 *
 * @author MASC
 */
public class ValidatorTypeSolicitation implements ConstraintValidator<ValidateTypeSolicitation, TypeSolicitation> {
    private List<TypeSolicitation> type;
    
    public void initialize(ValidateTypeSolicitation validateTypeSolicitation) {
        this.type = new ArrayList<>();
        this.type.add(TypeSolicitation.BUG);        
        this.type.add(TypeSolicitation.NEW_FEATURE);
        this.type.add(TypeSolicitation.NEW_RELEASE);
        this.type.add(TypeSolicitation.DOCUMENT);
    }

    @Override
    public boolean isValid(TypeSolicitation t, ConstraintValidatorContext cvc) {
        return t == null ? false : type.contains(t);
    }

   
}
