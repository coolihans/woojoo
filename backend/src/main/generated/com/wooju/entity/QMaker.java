package com.wooju.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QMaker is a Querydsl query type for Maker
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QMaker extends EntityPathBase<Maker> {

    private static final long serialVersionUID = 1837120050L;

    public static final QMaker maker = new QMaker("maker");

    public final StringPath address = createString("address");

    public final StringPath ceo_name = createString("ceo_name");

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final StringPath name = createString("name");

    public final StringPath official_name = createString("official_name");

    public QMaker(String variable) {
        super(Maker.class, forVariable(variable));
    }

    public QMaker(Path<? extends Maker> path) {
        super(path.getType(), path.getMetadata());
    }

    public QMaker(PathMetadata metadata) {
        super(Maker.class, metadata);
    }

}

