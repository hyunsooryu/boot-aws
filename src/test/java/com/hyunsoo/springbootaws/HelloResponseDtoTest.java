package com.hyunsoo.springbootaws;


import com.hyunsoo.springbootaws.dto.HelloResponseDto;
import static org.assertj.core.api.Assertions.*;

import org.assertj.core.api.Condition;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class HelloResponseDtoTest {

    @Test
    @DisplayName("롬복 기능 테스트")
    public void lombokTest(){
        String name = "test";
        int amount = 1000;
        //when
        HelloResponseDto dto = new HelloResponseDto(name, amount);
        //then
        assertThat(dto.getName()).isEqualTo(name);
        assertThat(dto.getAmount()).isEqualTo(amount);

    }
}
