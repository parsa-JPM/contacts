package ir.codefather.mongodemo.validations;

import org.springframework.web.multipart.MultipartFile;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class FileSizeValidator implements ConstraintValidator<FileSize, MultipartFile> {

    protected Long size;

    @Override
    public void initialize(FileSize constraintAnnotation) {
        this.size = constraintAnnotation.value();
    }

    @Override
    public boolean isValid(MultipartFile s, ConstraintValidatorContext constraintValidatorContext) {
        return s.getSize() <= size;
    }
}
