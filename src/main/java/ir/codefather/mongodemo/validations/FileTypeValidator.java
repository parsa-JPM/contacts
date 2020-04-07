package ir.codefather.mongodemo.validations;

import org.springframework.web.multipart.MultipartFile;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Arrays;

public class FileTypeValidator implements ConstraintValidator<FileType, MultipartFile> {

    protected String[] types;

    @Override
    public void initialize(FileType constraintAnnotation) {
        this.types = constraintAnnotation.value();
    }

    /**
     * Now it will check just extension of file
     *
     * @param file
     * @param constraintValidatorContext
     * @return boolean
     */
    @Override
    public boolean isValid(MultipartFile file, ConstraintValidatorContext constraintValidatorContext) {

        if (file.isEmpty())
            return true;

        String fileSuffix = file.getOriginalFilename().split("\\.")[1];

        return Arrays.stream(types)
                .anyMatch(fileSuffix::equalsIgnoreCase);
    }
}
