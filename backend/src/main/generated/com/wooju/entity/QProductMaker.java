package com.wooju.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QProductMaker is a Querydsl query type for ProductMaker
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QProductMaker extends EntityPathBase<ProductMaker> {

    private static final long serialVersionUID = 1591004903L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QProductMaker productMaker = new QProductMaker("productMaker");

    public final QMaker maker;

    public final QProduct product;

    public QProductMaker(String variable) {
        this(ProductMaker.class, forVariable(variable), INITS);
    }

    public QProductMaker(Path<? extends ProductMaker> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QProductMaker(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QProductMaker(PathMetadata metadata, PathInits inits) {
        this(ProductMaker.class, metadata, inits);
    }

    public QProductMaker(Class<? extends ProductMaker> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.maker = inits.isInitialized("maker") ? new QMaker(forProperty("maker")) : null;
        this.product = inits.isInitialized("product") ? new QProduct(forProperty("product")) : null;
    }

}

