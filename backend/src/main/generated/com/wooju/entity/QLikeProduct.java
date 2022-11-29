package com.wooju.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QLikeProduct is a Querydsl query type for LikeProduct
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QLikeProduct extends EntityPathBase<LikeProduct> {

    private static final long serialVersionUID = -1685379898L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QLikeProduct likeProduct = new QLikeProduct("likeProduct");

    public final QProduct product;

    public final QUser user;

    public QLikeProduct(String variable) {
        this(LikeProduct.class, forVariable(variable), INITS);
    }

    public QLikeProduct(Path<? extends LikeProduct> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QLikeProduct(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QLikeProduct(PathMetadata metadata, PathInits inits) {
        this(LikeProduct.class, metadata, inits);
    }

    public QLikeProduct(Class<? extends LikeProduct> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.product = inits.isInitialized("product") ? new QProduct(forProperty("product")) : null;
        this.user = inits.isInitialized("user") ? new QUser(forProperty("user")) : null;
    }

}

