package ir.codefather.mongodemo.validations;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = FileTypeValidator.class)
@Target({ ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface FileType {
    String[] value();
    String message() default "File has wrong type";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
