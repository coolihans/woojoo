package com.wooju.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QProductFood is a Querydsl query type for ProductFood
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QProductFood extends EntityPathBase<ProductFood> {

    private static final long serialVersionUID = -87419557L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QProductFood productFood = new QProductFood("productFood");

    public final QFood food;

    public final QProduct product;

    public QProductFood(String variable) {
        this(ProductFood.class, forVariable(variable), INITS);
    }

    public QProductFood(Path<? extends ProductFood> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QProductFood(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QProductFood(PathMetadata metadata, PathInits inits) {
        this(ProductFood.class, metadata, inits);
    }

    public QProductFood(Class<? extends ProductFood> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.food = inits.isInitialized("food") ? new QFood(forProperty("food")) : null;
        this.product = inits.isInitialized("product") ? new QProduct(forProperty("product")) : null;
    }

}

