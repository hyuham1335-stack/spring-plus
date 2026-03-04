package org.example.expert.domain.todo.repository;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.example.expert.domain.todo.dto.response.TodoTestResponse;
import org.example.expert.domain.todo.entity.Todo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.example.expert.domain.comment.entity.QComment.comment;
import static org.example.expert.domain.manager.entity.QManager.manager;
import static org.example.expert.domain.todo.entity.QTodo.todo;
import static org.example.expert.domain.user.entity.QUser.user;

@RequiredArgsConstructor
public class TodoCustomRepositoryImpl implements TodoCustomRepository {

    private final JPAQueryFactory queryFactory;

    @Override
    public Optional<Todo> findByIdWithUser(Long id) {
        Todo result = queryFactory
                .selectFrom(todo)
                .leftJoin(todo.user, user).fetchJoin()
                .where(todo.id.eq(id))
                .fetchOne();

        return Optional.ofNullable(result);
    }

    @Override
    public Page<TodoTestResponse> findWithOptions(String title, String nickName, LocalDateTime createFrom, LocalDateTime createTo, Pageable pageable) {
        BooleanBuilder builder = new BooleanBuilder();

        if (title != null && !title.isBlank()) {
            builder.and(todo.title.contains(title));
        }

        if (nickName != null && !nickName.isBlank()) {
            builder.and(todo.user.nickname.contains(nickName));
        }

        if (createFrom != null) {
            builder.and(todo.createdAt.goe(createFrom));
        }

        if (createTo != null) {
            builder.and(todo.createdAt.loe(createTo));
        }

        List<TodoTestResponse> content = queryFactory
                .select(Projections.constructor(
                        TodoTestResponse.class,
                        todo.title,
                        comment.id.countDistinct(),
                        manager.id.countDistinct())
                )
                .from(todo)
                .leftJoin(todo.comments, comment)
                .leftJoin(todo.managers, manager)
                .where(builder)
                .groupBy(todo.id)
                .orderBy(todo.createdAt.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        Long total = queryFactory
                .select(todo.id.countDistinct())
                .from(todo)
                .leftJoin(todo.comments, comment)
                .leftJoin(todo.managers, manager)
                .where(builder)
                .fetchOne();

        if(total == null) {
            total = 0L;
        }

        return new PageImpl<>(content, pageable, total);
    }
}
