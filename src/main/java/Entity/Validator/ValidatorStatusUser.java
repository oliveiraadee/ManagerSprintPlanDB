package Entity.Validator;

import Entity.Enum.StatusUser;
import java.util.ArrayList;
import java.util.List;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 *
 * @author MASC
 */
public class ValidatorStatusUser implements ConstraintValidator<ValidateStatusUser, StatusUser> {
    private List<StatusUser> status;
    
    public void initialize(ValidateStatusUser validateStatusUser) {
        this.status = new ArrayList<>();
        this.status.add(StatusUser.ACTIVE);
        this.status.add(StatusUser.INACTIVE);

    }

    @Override
    public boolean isValid(StatusUser t, ConstraintValidatorContext cvc) {
        return t == null ? false : status.contains(t);
    }

  
   
}
