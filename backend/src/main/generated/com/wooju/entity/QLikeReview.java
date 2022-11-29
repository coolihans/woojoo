package com.wooju.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QLikeReview is a Querydsl query type for LikeReview
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QLikeReview extends EntityPathBase<LikeReview> {

    private static final long serialVersionUID = -840185695L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QLikeReview likeReview = new QLikeReview("likeReview");

    public final QReview review;

    public final QUser user;

    public QLikeReview(String variable) {
        this(LikeReview.class, forVariable(variable), INITS);
    }

    public QLikeReview(Path<? extends LikeReview> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QLikeReview(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QLikeReview(PathMetadata metadata, PathInits inits) {
        this(LikeReview.class, metadata, inits);
    }

    public QLikeReview(Class<? extends LikeReview> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.review = inits.isInitialized("review") ? new QReview(forProperty("review"), inits.get("review")) : null;
        this.user = inits.isInitialized("user") ? new QUser(forProperty("user")) : null;
    }

}

