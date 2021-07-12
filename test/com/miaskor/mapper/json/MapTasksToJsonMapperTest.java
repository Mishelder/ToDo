package com.miaskor.mapper.json;

import com.miaskor.dto.FetchTaskDto;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class MapTasksToJsonMapperTest {

    private final MapTasksToJsonMapper jsonMapper = MapTasksToJsonMapper.getInstance();

    @Test
    void map() {
        //given
        String expected = """
                {"2021-01-11":[{"id":2,"taskName":"buy beer","done":""}],"2021-01-12":[{"id":1,"taskName":"buy milk","done":"checked"},{"id":2,"taskName":"buy smth","done":""}]}""";
        Map<String, List<FetchTaskDto>> map = new HashMap<>();
        map.put("2021-01-12",List.of(FetchTaskDto.builder()
                .id(1)
                .taskName("buy milk")
                .done("checked")
                .build(),
                FetchTaskDto.builder()
                        .id(2)
                        .taskName("buy smth")
                        .done("")
                        .build()));
        map.put("2021-01-11",List.of(FetchTaskDto.builder()
                .id(2)
                .taskName("buy beer")
                .done("")
                .build()));
        //when
        var actual = jsonMapper.map(map);
        //then
        assertEquals(expected,actual);
    }
}