package com.allane.leasingcontract.utils;

import com.allane.leasingcontract.exceptions.ValidationException;
import org.modelmapper.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;

public class DateUtils {

    public static final String DATE_FORMAT="yyyy-MM-dd";
    public static ModelMapper mapperWithLocaleDateProvider(ModelMapper modelMapper){

       /* Provider<LocalDate> localDateProvider = new AbstractProvider<LocalDate>() {
            @Override
            public LocalDate get() {
                return LocalDate.now();
            }
        };
*/
        Converter<String, LocalDate> toStringDate = new AbstractConverter<String, LocalDate>() {
            @Override
            protected LocalDate convert(String source) {
                DateTimeFormatter format = DateTimeFormatter.ofPattern(DATE_FORMAT);
                LocalDate localDate = LocalDate.parse(source, format);
                return localDate;
            }
        };


        modelMapper.createTypeMap(String.class, LocalDate.class);
        modelMapper.addConverter(toStringDate);
       // modelMapper.getTypeMap(String.class, LocalDate.class).setProvider(localDateProvider);

        return modelMapper;
    }

    public static boolean isValid(String date){
        try {
            LocalDate.parse(date, DateTimeFormatter.ofPattern(DATE_FORMAT));
        } catch (DateTimeParseException e) {
            return false;
        }
        return true;
    }

    public static LocalDate convertToLocalDate(String date){
        try {
           return LocalDate.parse(date, DateTimeFormatter.ofPattern(DATE_FORMAT));
        } catch (DateTimeParseException e) {
            throw new ValidationException("Invalid date format");
        }
    }

    public static boolean isEligibleForLeasing(String date){
        try {
            LocalDate localDate=LocalDate.parse(date, DateTimeFormatter.ofPattern(DATE_FORMAT));
            return ChronoUnit.YEARS.between(localDate,LocalDate.now())>=18;
        } catch (DateTimeParseException e) {
            return false;
        }
    }
}
