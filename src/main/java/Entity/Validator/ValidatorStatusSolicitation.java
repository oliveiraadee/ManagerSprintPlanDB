package Entity.Validator;

import Entity.Enum.StatusSolicitation;
import java.util.ArrayList;
import java.util.List;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 *
 * @author MASC
 */
public class ValidatorStatusSolicitation implements ConstraintValidator<ValidateStatusSolicitation, StatusSolicitation> {
    private List<StatusSolicitation> status;
    
    public void initialize(ValidateStatusSolicitation validateStatusSolicitation) {
        this.status = new ArrayList<>();
        this.status.add(StatusSolicitation.NOT_VERIFY);
        this.status.add(StatusSolicitation.REFUSED);
        this.status.add(StatusSolicitation.OPEN);
        this.status.add(StatusSolicitation.IN_PROGRESS);
        this.status.add(StatusSolicitation.DONE);

    }

    @Override
    public boolean isValid(StatusSolicitation t, ConstraintValidatorContext cvc) {
        return t == null ? false : status.contains(t);
    }

   
}
