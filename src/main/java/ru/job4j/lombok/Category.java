package ru.job4j.lombok;

import lombok.*;

@RequiredArgsConstructor
public class Category {

    @NonNull
    @Getter
    @EqualsAndHashCode.Include
    private int id;

    @Getter
    @Setter
    private String name;

}