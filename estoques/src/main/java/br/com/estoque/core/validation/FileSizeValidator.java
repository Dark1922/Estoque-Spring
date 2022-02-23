package br.com.estoque.core.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.util.unit.DataSize;
import org.springframework.web.multipart.MultipartFile;

public class FileSizeValidator implements ConstraintValidator<FileSize, MultipartFile> {

	private DataSize maxSize;

	@Override
	public boolean isValid(MultipartFile value, ConstraintValidatorContext context) {

		return value == null || value.getSize() <= this.maxSize.toBytes();
	}

	@Override
	public void initialize(FileSize constraintAnnotation) {
		// vai fazer um parse da string para datasize
		this.maxSize = DataSize.parse(constraintAnnotation.max());
	}

}
