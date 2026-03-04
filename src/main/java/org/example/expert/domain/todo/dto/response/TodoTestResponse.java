package org.example.expert.domain.todo.dto.response;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class TodoTestResponse {
    private final String title;
    private final Long commentNum;
    private final Long managerNum;
}
