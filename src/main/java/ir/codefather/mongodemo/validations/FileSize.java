package ir.codefather.mongodemo.validations;


import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = FileSizeValidator.class)
@Target({ ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface FileSize {
    long value();
    String message() default "File is larger than we want";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
