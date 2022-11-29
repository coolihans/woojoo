package com.wooju.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QProduct is a Querydsl query type for Product
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QProduct extends EntityPathBase<Product> {

    private static final long serialVersionUID = -901280931L;

    public static final QProduct product = new QProduct("product");

    public final NumberPath<Integer> acidity = createNumber("acidity", Integer.class);

    public final NumberPath<Double> alcohol = createNumber("alcohol", Double.class);

    public final StringPath anju_note = createString("anju_note");

    public final StringPath award = createString("award");

    public final NumberPath<Integer> bitter = createNumber("bitter", Integer.class);

    public final NumberPath<Integer> body = createNumber("body", Integer.class);

    public final NumberPath<Integer> carbonic = createNumber("carbonic", Integer.class);

    public final StringPath cutted_img = createString("cutted_img");

    public final StringPath distinction = createString("distinction");

    public final NumberPath<Integer> flavor = createNumber("flavor", Integer.class);

    public final NumberPath<Integer> flower_category = createNumber("flower_category", Integer.class);

    public final NumberPath<Integer> fruit_category = createNumber("fruit_category", Integer.class);

    public final NumberPath<Integer> herb_category = createNumber("herb_category", Integer.class);

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final StringPath image = createString("image");

    public final StringPath ingredient = createString("ingredient");

    public final StringPath keyword = createString("keyword");

    public final NumberPath<Integer> like = createNumber("like", Integer.class);

    public final StringPath name = createString("name");

    public final NumberPath<Integer> nut_category = createNumber("nut_category", Integer.class);

    public final NumberPath<Integer> nutty = createNumber("nutty", Integer.class);

    public final NumberPath<Integer> other_category = createNumber("other_category", Integer.class);

    public final StringPath other_note = createString("other_note");

    public final NumberPath<Integer> plain = createNumber("plain", Integer.class);

    public final NumberPath<Integer> price = createNumber("price", Integer.class);

    public final NumberPath<Integer> review = createNumber("review", Integer.class);

    public final NumberPath<Integer> richness = createNumber("richness", Integer.class);

    public final NumberPath<Integer> situation_category = createNumber("situation_category", Integer.class);

    public final NumberPath<Integer> spicy = createNumber("spicy", Integer.class);

    public final StringPath storage_note = createString("storage_note");

    public final StringPath subtext = createString("subtext");

    public final NumberPath<Integer> sweet = createNumber("sweet", Integer.class);

    public final NumberPath<Integer> tannin = createNumber("tannin", Integer.class);

    public final StringPath taste_note = createString("taste_note");

    public final StringPath taste_standard = createString("taste_standard");

    public final StringPath type = createString("type");

    public final StringPath volume = createString("volume");

    public final NumberPath<Integer> weight = createNumber("weight", Integer.class);

    public QProduct(String variable) {
        super(Product.class, forVariable(variable));
    }

    public QProduct(Path<? extends Product> path) {
        super(path.getType(), path.getMetadata());
    }

    public QProduct(PathMetadata metadata) {
        super(Product.class, metadata);
    }

}

