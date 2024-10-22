package org.demo.app.util;


import lombok.experimental.UtilityClass;
import org.demo.app.dto.EmployeeDto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;

@UtilityClass
public class EmployeeUtil {

    private final String[] names = {"Ahmed", "Ali", "Hassan", "Mohamed", "Said", "Saad", "Mostafa", "Ibrahim"};
    private final String[] mail = {"fawry.com", "gmail.com", "yahoo.com", "outlook.com", "hotmail.com", "new.markets.com", "stc.com", "stc.pay.com"};
    private final int max = 7;
    private final int min = 0;

    public EmployeeDto createRandomEmployeeDto() {
        EmployeeDto employeeDto = new EmployeeDto();
        employeeDto.setFirstName(names[getRandomIndex()]);
        employeeDto.setLastName(names[getRandomIndex()]);
        String email = employeeDto.getFirstName() + "." +
                       employeeDto.getLastName() + "_" +
                       getRandomInt() + "@" +
                       mail[getRandomIndex()];
        employeeDto.setEmail(email.toLowerCase(Locale.ROOT));
        employeeDto.setSalary(generateRandomSalary(5000, 50000));
        employeeDto.setJoinDate(getRandomDate());
        return employeeDto;
    }

    public BigDecimal generateRandomSalary(int min, int max) {
        int random = min + (int) (Math.random() * ((max - min) + 1));
        return BigDecimal.valueOf((random / 1000) * 1000);
    }

    public List<EmployeeDto> getEmployeeDtoList(int size) {
        List<EmployeeDto> employeeDtos = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            employeeDtos.add(createRandomEmployeeDto());
        }
        return employeeDtos;
    }

    private int getRandomIndex() {
        Random rand = new Random();
        return rand.nextInt((max - min) + 1) + min;
    }

    private Long getRandomId() {
        Random rand = new Random();
        return rand.nextLong();
    }

    private Integer getRandomInt() {
        Random rand = new Random();
        return rand.nextInt(50000);
    }

    private Date getRandomDate() {
        Random random = new Random();
        int minDay = (int) LocalDate.of(2015, 1, 1).toEpochDay();
        int maxDay = (int) LocalDate.now().toEpochDay();
        long randomDay = random.nextInt(maxDay - minDay) + minDay;
        LocalDate localDate = LocalDate.ofEpochDay(randomDay);
        return Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
    }

}
