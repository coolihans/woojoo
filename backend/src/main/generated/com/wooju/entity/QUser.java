package com.wooju.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QUser is a Querydsl query type for User
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QUser extends EntityPathBase<User> {

    private static final long serialVersionUID = -1464503267L;

    public static final QUser user = new QUser("user");

    public final DatePath<java.time.LocalDate> birthdate = createDate("birthdate", java.time.LocalDate.class);

    public final StringPath email = createString("email");

    public final StringPath gender = createString("gender");

    public final BooleanPath gosu = createBoolean("gosu");

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final StringPath img = createString("img");

    public final ListPath<LikeProduct, QLikeProduct> likeproducts = this.<LikeProduct, QLikeProduct>createList("likeproducts", LikeProduct.class, QLikeProduct.class, PathInits.DIRECT2);

    public final StringPath nickname = createString("nickname");

    public final NumberPath<Integer> question1 = createNumber("question1", Integer.class);

    public final NumberPath<Integer> question2 = createNumber("question2", Integer.class);

    public final NumberPath<Integer> question3 = createNumber("question3", Integer.class);

    public final NumberPath<Integer> question4 = createNumber("question4", Integer.class);

    public final NumberPath<Integer> question5 = createNumber("question5", Integer.class);

    public final NumberPath<Integer> review_count = createNumber("review_count", Integer.class);

    public final ListPath<Review, QReview> reviews = this.<Review, QReview>createList("reviews", Review.class, QReview.class, PathInits.DIRECT2);

    public final StringPath usertype = createString("usertype");

    public QUser(String variable) {
        super(User.class, forVariable(variable));
    }

    public QUser(Path<? extends User> path) {
        super(path.getType(), path.getMetadata());
    }

    public QUser(PathMetadata metadata) {
        super(User.class, metadata);
    }

}

