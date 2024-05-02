package com.innoventes.test.app.dto;

public @interface EvenNumberOrZero {
    String messages() default "Field must be an even number or could be a zero";

    Class<?>[] groups() default {};
    Class<? extends CompanyDTO>[] companyDto() default{};

}
